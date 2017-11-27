package com.yt.shop.common;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseDao<T> extends JpaSpecificationExecutor<T>,PagingAndSortingRepository<T,Long> {
}
