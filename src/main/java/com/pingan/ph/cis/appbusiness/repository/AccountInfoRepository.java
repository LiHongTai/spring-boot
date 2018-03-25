package com.pingan.ph.cis.appbusiness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingan.ph.cis.appbusiness.entity.AccountInfo;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {

	AccountInfo findOneByAccountNo(String accountNo);
	
	List<AccountInfo> findByCustNo(String custNo);

	List<AccountInfo> findByAccountNo(String accountNo);

}
