package com.mvmlabs.springboot.service.impl;

import java.io.IOException;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvmlabs.springboot.dao.LinkCheckRepository;
import com.mvmlabs.springboot.dao.LinkCheckResultRepository;
import com.mvmlabs.springboot.dao.LinkRepository;
import com.mvmlabs.springboot.domain.Link;
import com.mvmlabs.springboot.domain.LinkCheck;
import com.mvmlabs.springboot.domain.LinkCheckResult;
import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.domain.User;
import com.mvmlabs.springboot.service.LinkService;

@Service
@Transactional(readOnly = false)
public class LinkServiceJpaImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;
    
    @Autowired
    private LinkCheckRepository linkCheckRepository;
    
    @Autowired
    private LinkCheckResultRepository linkCheckResultRepository;
        
    @Override
    @Transactional(readOnly = true)
    public Page<Link> allLinksWithTag(final Pageable pageable, final Tag tag) {
        return linkRepository.findByTags(pageable, tag);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Link> allLinks(final Pageable pageable) {
        return linkRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Link loadLinkById(final Long id) {
        return linkRepository.findOne(id);
    }

    @Override
    public Link save(final Link link) {
        return linkRepository.save(link);
    }

    @Override
    public LinkCheck addHocLinkCheck(final Link link, final User user) {

        final LinkCheck linkCheck = new LinkCheck();
        linkCheck.setLaunchedBy(user);
        linkCheck.setLaunchedDate(Calendar.getInstance());
        linkCheck.setTotalLinksChecked(1);

        LinkCheckResult linkCheckResult = things(link);
        
        linkCheckResult = linkCheckResultRepository.save(linkCheckResult);
        linkCheck.getResults().add(linkCheckResult);
        linkCheck.setTotalInitialErrors(linkCheckResult.getSuccess() ? 1 : 0);
        linkCheck.setTotalFinalErrors(linkCheckResult.getSuccess() ? 1 : 0);
        
        // Should the link check and result be stored separately or together?
        linkCheckRepository.save(linkCheck);
        
        return linkCheck;
    }
    
    private LinkCheckResult things(final Link link) {

        final CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            final HttpGet httpget = new HttpGet(link.getUrl());

            // Create a custom response handler
            final ResponseHandler<LinkCheckResult> responseHandler = new ResponseHandler<LinkCheckResult>() {

                public LinkCheckResult handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    final LinkCheckResult result = new LinkCheckResult();
                    result.setLink(link);
                    result.setAttempt(1);
                    int status = response.getStatusLine().getStatusCode();
                    result.setResponseCode(status);
                    if (status >= 200 && status < 300) {
                        result.setSuccess(true);
                        final HttpEntity entity = response.getEntity();
                        result.setResponseBody(entity != null ? EntityUtils.toString(entity) : null);
                    } else {
                        result.setSuccess(false);
                        result.setErrorMessage("Invalid response " + status);
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                    
                    return result;
                }
            };
            final long millis = System.currentTimeMillis();
            final LinkCheckResult result = httpclient.execute(httpget, responseHandler);
            result.setTimeTakenMillis(System.currentTimeMillis() - millis);
            return result;
        } catch (Exception e) {
            return new LinkCheckResult(link, e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
            }
        }
    }
}
