package com.mvmlabs.springboot.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mvmlabs.springboot.domain.LinkCheck;

public interface LinkCheckRepository extends PagingAndSortingRepository<LinkCheck, Long> {

}
