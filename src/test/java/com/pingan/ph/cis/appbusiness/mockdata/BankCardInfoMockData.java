package com.pingan.ph.cis.appbusiness.mockdata;

import java.util.ArrayList;
import java.util.List;

import com.pingan.ph.cis.appbusiness.entity.BankCardInfo;

public class BankCardInfoMockData {

	public static List<BankCardInfo> initBankCardInfos() {
		List<BankCardInfo> bankCardInfos = new ArrayList<BankCardInfo>();
		
		bankCardInfos.add(createBankCardInfo("zs-925910", "招商银行001","account001"));
		bankCardInfos.add(createBankCardInfo("zs-925901", "招商银行002","account001"));
		bankCardInfos.add(createBankCardInfo("gs-62591123", "工商银行001","account002"));
		bankCardInfos.add(createBankCardInfo("gs-62592311", "工商银行002","account002"));
		bankCardInfos.add(createBankCardInfo("ny-62372311", "农业银行002","account003"));
		
		return bankCardInfos;
	}
	
	private static BankCardInfo createBankCardInfo(String bankCardNo,String bankCardName,String accountNo) {
		BankCardInfo bankCardInfo = new BankCardInfo();
		
		bankCardInfo.setBankCardNo(bankCardNo);
		bankCardInfo.setBankCardName(bankCardName);
		bankCardInfo.setAccountNo(accountNo);
		
		return bankCardInfo;
	}
}
