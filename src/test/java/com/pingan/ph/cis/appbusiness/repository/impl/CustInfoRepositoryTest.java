package com.pingan.ph.cis.appbusiness.repository.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pingan.ph.cis.appbusiness.BaseTestSuit;
import com.pingan.ph.cis.appbusiness.mockdata.AccountInfoMockData;
import com.pingan.ph.cis.appbusiness.mockdata.BankCardInfoMockData;
import com.pingan.ph.cis.appbusiness.mockdata.CustDetailInfoMockData;
import com.pingan.ph.cis.appbusiness.mockdata.CustInfoMockData;
import com.pingan.ph.cis.appbusiness.repository.AccountInfoRepository;
import com.pingan.ph.cis.appbusiness.repository.BankCardInfoRepository;
import com.pingan.ph.cis.appbusiness.repository.CustDetailInfoRepository;
import com.pingan.ph.cis.appbusiness.repository.CustInfoRepository;

public class CustInfoRepositoryTest extends BaseTestSuit{

	@Autowired
	private CustInfoRepository custInfoRepository;
	
	@Autowired
	private AccountInfoRepository accountInfoRepository;
	
	@Autowired
	private BankCardInfoRepository bankCardInfoRepository;
	
	@Autowired
	private CustDetailInfoRepository custDetailInfoRepository;
	
	@Test
	public void testInitData() {
		custInfoRepository.save(CustInfoMockData.initCustInfoData());
		accountInfoRepository.save(AccountInfoMockData.initAccountInfos());
		bankCardInfoRepository.save(BankCardInfoMockData.initBankCardInfos());
		custDetailInfoRepository.save(CustDetailInfoMockData.initCustDetailInfos());
	}
}
