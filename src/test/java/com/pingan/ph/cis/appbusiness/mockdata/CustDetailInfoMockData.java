package com.pingan.ph.cis.appbusiness.mockdata;

import java.util.ArrayList;
import java.util.List;

import com.pingan.ph.cis.appbusiness.entity.CustDetailInfo;

public class CustDetailInfoMockData {

	public static List<CustDetailInfo> initCustDetailInfos() {
		List<CustDetailInfo> custDetailInfos = new ArrayList<CustDetailInfo>();
		
		custDetailInfos.add(createCustDetailInfo("cust001", "超人","20","男"));
		custDetailInfos.add(createCustDetailInfo("cust002", "钢铁侠","30","男"));
		custDetailInfos.add(createCustDetailInfo("cust003", "钢铁侠","40","女"));
		custDetailInfos.add(createCustDetailInfo("cust004", "小叮当","10","女"));
		custDetailInfos.add(createCustDetailInfo("cust005", "巴拉巴拉小魔仙","10","女"));
		
		return custDetailInfos;
	}
	
	private static CustDetailInfo createCustDetailInfo(String custNo,String custName,String custAge,String custSex) {
		CustDetailInfo custDetailInfo = new CustDetailInfo();
		
		custDetailInfo.setCustNo(custNo);
		custDetailInfo.setCustName(custName);
		custDetailInfo.setCustAge(custAge);
		custDetailInfo.setCustSex(custSex);
		
		return custDetailInfo;
	}
}
