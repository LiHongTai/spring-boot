package com.pingan.ph.cis.appbusiness.mockdata;

import java.util.ArrayList;
import java.util.List;

import com.pingan.ph.cis.appbusiness.entity.CustInfo;

public class CustInfoMockData {

	
	public static List<CustInfo> initCustInfoData(){
		List<CustInfo> custInfos = new ArrayList<>();
		custInfos.add(createCustInfo("cust001", "超人"));
		custInfos.add(createCustInfo("cust002", "钢铁侠"));
		custInfos.add(createCustInfo("cust003", "金刚狼"));
		custInfos.add(createCustInfo("cust004", "小叮当"));
		custInfos.add(createCustInfo("cust005", "巴拉巴拉小魔仙"));
		return custInfos;
	}
	
	private static CustInfo createCustInfo(String custNo,String custName) {
		CustInfo custMainInfo = new CustInfo();
		
		custMainInfo.setCustNo(custNo);
		custMainInfo.setCustName(custName);
		
		return custMainInfo;
	}
}
