package com.pingan.ph.cis.appbusiness.mockdata;

import java.util.ArrayList;
import java.util.List;

import com.pingan.ph.cis.appbusiness.entity.AccountInfo;

public class AccountInfoMockData {

	public static List<AccountInfo> initAccountInfos() {
		List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
		
		accountInfos.add(createAccountInfo("account001","账户001","1","cust001"));
		accountInfos.add(createAccountInfo("account002","账户002","0","cust001"));
		accountInfos.add(createAccountInfo("account003","账户003","1","cust002"));
		accountInfos.add(createAccountInfo("account004","账户004","1","cust003"));
		
		return accountInfos;
	}
	
	private static AccountInfo createAccountInfo(String accountNo,String accountName,String accountStatus,String custNo) {
		AccountInfo accountInfo = new AccountInfo();
		
		accountInfo.setAccountNo(accountNo);
		accountInfo.setAccountName(accountName);
		accountInfo.setAccountStatus(accountStatus);
		accountInfo.setCustNo(custNo);
		
		return accountInfo;
	}
}
