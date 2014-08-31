package com.mvmlabs.springboot.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mvmlabs.springboot.domain.LinkCheckResult;

public interface LinkCheckResultRepository extends PagingAndSortingRepository<LinkCheckResult, Long> {

}
