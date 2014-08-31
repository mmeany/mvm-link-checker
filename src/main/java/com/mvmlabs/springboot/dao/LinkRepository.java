package com.mvmlabs.springboot.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mvmlabs.springboot.domain.Link;
import com.mvmlabs.springboot.domain.Tag;

public interface LinkRepository extends PagingAndSortingRepository<Link, Long> {

//    @Query("SELECT link FROM Link link WHERE link.tags IN :tags")
    @Query("SELECT link FROM Link link LEFT JOIN link.tags tags WHERE tags.tag IN :tags")
    Collection<Link> findByTagNameCustom(@Param("tags") List<String> list);

    @Query("SELECT link FROM Link link LEFT JOIN link.tags tags WHERE tags IN :tags")
    Collection<Link> findByTagCustom(@Param("tags") List<Tag> list);

    Page<Link> findByTags(Pageable pageable, Tag tag);
    
    
}
