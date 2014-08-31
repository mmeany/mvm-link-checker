package com.mvmlabs.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mvmlabs.springboot.domain.Link;
import com.mvmlabs.springboot.domain.LinkCheck;
import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.domain.User;

public interface LinkService {

    Page<Link> allLinks(Pageable pageable);

    Page<Link> allLinksWithTag(Pageable pageable, Tag tag);

    Link loadLinkById(Long id);

    Link save(Link link);

    LinkCheck addHocLinkCheck(Link link, User user);

}
