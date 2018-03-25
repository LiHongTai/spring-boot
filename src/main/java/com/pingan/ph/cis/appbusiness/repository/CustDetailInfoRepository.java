package com.pingan.ph.cis.appbusiness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingan.ph.cis.appbusiness.entity.CustDetailInfo;

public interface CustDetailInfoRepository extends JpaRepository<CustDetailInfo, Long> {
	
	List<CustDetailInfo> findByCustNo(String custNo);
}
