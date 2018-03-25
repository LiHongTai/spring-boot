package com.pingan.ph.cis.appbusiness.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pingan.ph.cis.appbusiness.entity.CustDetailInfo;
import com.pingan.ph.cis.appbusiness.repository.CustDetailInfoRepository;
import com.pingan.ph.cis.appbusiness.utils.ReflectUtil;

@Component
public class CustDetailInfoDao {

	private static final String CUST_NO = "custNo";
	
	@Autowired
	private CustDetailInfoRepository repository;

	public List<CustDetailInfo> findByParam(Map<String,Object> param){
		if(CollectionUtils.isEmpty(param))
			return new ArrayList<>();
		
		if(!param.containsKey(CUST_NO))
			return new ArrayList<>();
		String custNo = (String) param.get(CUST_NO);
		
		List<CustDetailInfo> custDetailInfos = repository.findByCustNo(custNo);
		
		List<CustDetailInfo> finalCustDetailInfos = new ArrayList<>();
		for (CustDetailInfo custDetailInfo : custDetailInfos) {
			if (ReflectUtil.filterEntityByParam(custDetailInfo, param))
				finalCustDetailInfos.add(custDetailInfo);
		}
		
		return finalCustDetailInfos;
	}
	
	public List<CustDetailInfo> findByParam4CustInfo(String custNo, Map<String,Object> param) {
		
		List<CustDetailInfo> custDetailInfos = repository.findByCustNo(custNo);
		
		if(CollectionUtils.isEmpty(param))
			return custDetailInfos;
		
		List<CustDetailInfo> finalCustDetailInfos = new ArrayList<>();
		
		for (CustDetailInfo custDetailInfo : custDetailInfos) {
			if(ReflectUtil.filterEntityByParam(custDetailInfo, param))
				finalCustDetailInfos.add(custDetailInfo);
		}
		
		return finalCustDetailInfos;
	}

}
