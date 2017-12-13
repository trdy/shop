package com.yt.shop.dao;

import com.yt.shop.model.OperRecord;
import com.yt.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OperRecordJpa extends JpaSpecificationExecutor<OperRecord>,PagingAndSortingRepository<OperRecord,Long> {


}
