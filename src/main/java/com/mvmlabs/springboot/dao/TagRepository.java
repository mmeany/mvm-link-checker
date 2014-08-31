package com.mvmlabs.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mvmlabs.springboot.domain.Tag;

public interface TagRepository extends PagingAndSortingRepository<Tag, String> {

    List<Tag> findByTagStartingWithOrderByTagDesc(String criteria);

    @Query("SELECT tag.tag FROM Tag tag ORDER BY tag.tag")
    List<String> findAllTagNames();
}
