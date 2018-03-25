package com.pingan.ph.cis.appbusiness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingan.ph.cis.appbusiness.entity.BankCardInfo;

public interface BankCardInfoRepository extends JpaRepository<BankCardInfo, Long> {

	List<BankCardInfo> findByAccountNo(String accountNo);

	List<BankCardInfo> findByBankCardNo(String bankCardNo);

}
