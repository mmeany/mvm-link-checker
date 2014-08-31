package com.mvmlabs.springboot;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mvmlabs.springboot.Application;
import com.mvmlabs.springboot.dao.LinkCheckRepository;
import com.mvmlabs.springboot.dao.LinkRepository;
import com.mvmlabs.springboot.dao.TagRepository;
import com.mvmlabs.springboot.dao.UserRepository;
import com.mvmlabs.springboot.domain.Link;
import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.domain.User;

/**
 * Testing the database tier via repository and general JPA stuff.
 * 
 * @author Mark Meany
 */
@Transactional
@TransactionConfiguration(defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, TestConfigurtaion.class})
@WebAppConfiguration
public class DataAccessTests {

    @Autowired
    private LinkRepository linkRepo;
    
    @Autowired
    private LinkCheckRepository linkCheckRepo;
    
    @Autowired
    private TagRepository tagRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    
    
//	@Test
	public void testLinkRepo() throws Exception {
	    
	    Link link = linkRepo.findOne(1L);
	    Assert.assertNotNull(link);
	}

//	@Test
	public void testFetchAllLinksShowingTags() throws Exception {
	    
	    for(Link link : linkRepo.findAll()) {
	        System.out.println("Link: " + link.getUrl());
	        for(Tag tag : link.getTags()) {
	            System.out.println("\t" + tag.getTag());
	        }
	    }
	}

//    @Test
    public void testCreateLink() throws Exception {
        System.out.println("-----------------------------------------------");
        final Tag tag = tagRepo.findOne("dev");

        final User user = userRepo.findOne(1L);
        
        Link l = new Link();
        l.setCreatedBy(user);
        l.setCreateDate(Calendar.getInstance());
        l.setDescription("Test created link");
        l.setLastUpdatedDate(Calendar.getInstance());
        l.setName("Test Link");
        l.getTags().add(tag);
        l.setUpdatedBy(user);
        l.setUrl("http://google.co.uk");
        
        Link ll = linkRepo.save(l);
        Assert.assertNotNull(ll);
        System.out.println("-----------------------------------------------");
    }
    
    @Test
    public void findAllDevTaggedLinksByTagName() throws Exception {
        System.out.println("-----------------------------------------------");

        Collection<Link> links = linkRepo.findByTagNameCustom(Arrays.asList("dev"));
        for(Link link : links) {
            System.out.println("Link: " + link.getUrl());
        }

        Assert.assertNotNull(links);
        System.out.println("-----------------------------------------------");
    }    
    
    @Test
    public void findAllDevTaggedLinksByTagCustom() throws Exception {
        System.out.println("-----------------------------------------------");
        final Tag tag = tagRepo.findOne("dev");

        Collection<Link> links = linkRepo.findByTagCustom(Arrays.asList(tag));
        for(Link link : links) {
            System.out.println("## Link: " + link.getUrl());
        }
        
        Assert.assertNotNull(links);
        System.out.println("-----------------------------------------------");
    }
    
    @Test
    public void findAllDevTaggedLinksByTags() throws Exception {
        System.out.println("-----------------------------------------------");
        final Tag tag = tagRepo.findOne("dev");

        Page<Link> links = linkRepo.findByTags(createPageable(), tag);
        for(Link link : links) {
            System.out.println("## Link: " + link.getUrl());
        }
        
        Assert.assertNotNull(links);
        System.out.println("-----------------------------------------------");
    }
    
    @Test
    public void canFetchTagNames() throws Exception {
        System.out.println("-----------------------------------------------");
        final List<String> names = tagRepo.findAllTagNames();
        for(String name : names) {
            System.out.println(name);
        }
        System.out.println("-----------------------------------------------");        
    }

    @Test
    public void checkFetchLinkWorks() throws Exception {
        System.out.println("-----------------------------------------------");        
        final Link link = linkRepo.findOne(1L);
        
        System.out.println(link.getName());
        System.out.println(link.getTags().size());
        
        System.out.println("-----------------------------------------------");        
    }
    
    private Pageable createPageable() {
        
        return new Pageable() {

            @Override
            public Pageable first() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public int getOffset() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getPageNumber() {
                // TODO Auto-generated method stub
                return 1;
            }

            @Override
            public int getPageSize() {
                // TODO Auto-generated method stub
                return 5;
            }

            @Override
            public Sort getSort() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean hasPrevious() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public Pageable next() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                // TODO Auto-generated method stub
                return null;
            }
            
        };
    }
    
}
