package com.mvmlabs.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mvmlabs.springboot.domain.Tag;

public interface TagService {

    Page<Tag> getAllTags(Pageable pageable);

    Tag getTag(String name) throws NotFoundException;

    Tag save(Tag tag);

    Tag rename(String originalName, Tag tag);

    List<Tag> findAllStartingWith(String criteria);

    Iterable<Tag> findAll();

    List<String> getAllTagNames();

}
