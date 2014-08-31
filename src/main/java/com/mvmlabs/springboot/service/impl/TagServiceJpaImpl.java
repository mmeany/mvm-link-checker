package com.mvmlabs.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mvmlabs.springboot.dao.TagRepository;
import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.service.NotFoundException;
import com.mvmlabs.springboot.service.TagService;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class TagServiceJpaImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Tag getTag(String name) throws NotFoundException {
        final Tag tag = tagRepository.findOne(name);
        if (tag == null) {
            throw new NotFoundException(name);
        }
        return tag;
    }

    @Override
    public Tag save(final Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag rename(String originalName, Tag tag) {
        tagRepository.delete(originalName);
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> findAllStartingWith(String criteria) {
        return tagRepository.findByTagStartingWithOrderByTagDesc(criteria);
    }

    @Override
    public Iterable<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<String> getAllTagNames() {
        return tagRepository.findAllTagNames();
    }
    
}
