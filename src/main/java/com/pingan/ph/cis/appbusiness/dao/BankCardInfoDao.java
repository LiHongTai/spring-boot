package com.pingan.ph.cis.appbusiness.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pingan.ph.cis.appbusiness.entity.BankCardInfo;
import com.pingan.ph.cis.appbusiness.repository.BankCardInfoRepository;
import com.pingan.ph.cis.appbusiness.utils.ReflectUtil;

@Component
public class BankCardInfoDao {

	private static final String BANK_CARD_NO = "bankCardNo";
	
	@Autowired
	private BankCardInfoRepository repository;

	public List<BankCardInfo> findByParam(Map<String,Object> param){
		if(CollectionUtils.isEmpty(param))
			return new ArrayList<>();
		
		if(!param.containsKey(BANK_CARD_NO))
			return new ArrayList<>();
		String bankCardNo = (String) param.get(BANK_CARD_NO);
		
		List<BankCardInfo> bankCardInfos = repository.findByBankCardNo(bankCardNo);
		
		List<BankCardInfo> finalBankCardInfos = new ArrayList<>();
		for (BankCardInfo bankCardInfo : bankCardInfos) {
			if (ReflectUtil.filterEntityByParam(bankCardInfo, param))
				finalBankCardInfos.add(bankCardInfo);
		}
		
		return finalBankCardInfos;
	}
	
	public List<BankCardInfo> findByParam4AccountInfo(String accountNo, Map<String, Object> param) {
		List<BankCardInfo> bankCardInfos = repository.findByAccountNo(accountNo);

		if (CollectionUtils.isEmpty(param))
			return bankCardInfos;

		List<BankCardInfo> finalBankCardInfos = new ArrayList<>();
		for (BankCardInfo bankCardInfo : bankCardInfos) {
			if (ReflectUtil.filterEntityByParam(bankCardInfo, param))
				finalBankCardInfos.add(bankCardInfo);
		}

		return finalBankCardInfos;
	}
}
