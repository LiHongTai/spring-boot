package com.pingan.ph.cis.appbusiness.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pingan.ph.cis.appbusiness.constant.NodeConstant;
import com.pingan.ph.cis.appbusiness.entity.AccountInfo;
import com.pingan.ph.cis.appbusiness.entity.CustDetailInfo;
import com.pingan.ph.cis.appbusiness.entity.CustInfo;
import com.pingan.ph.cis.appbusiness.repository.CustInfoRepository;
import com.pingan.ph.cis.appbusiness.utils.ReflectUtil;

@Component
@SuppressWarnings("unchecked")
public class CustInfoDao {

	private static final String CUST_NO = "custNo";
	
	@Autowired
	private CustInfoRepository repository;

	@Autowired
	private CustDetailInfoDao custDetailInfoDao;

	@Autowired
	private AccountInfoDao accountInfoDao;

	public List<CustInfo> findByParam(Map<String,Object> param){
		if(CollectionUtils.isEmpty(param))
			return new ArrayList<>();
		//TODO 只能知道具体的主键值，才可以根据特定的方法查询具体信息
		if(!param.containsKey(CUST_NO))
			return new ArrayList<>();
		String custNo = (String) param.get(CUST_NO);
		List<CustInfo> custInfos = repository.findByCustNo(custNo);
		
		List<CustInfo> finalCustInfos = new ArrayList<>();
		for (CustInfo custInfo : custInfos) {
			if(ReflectUtil.filterEntityByParam(custInfo, param))
				finalCustInfos.add(custInfo);
		}
		List<Map<String,Object>> childrenNodeList = (List<Map<String, Object>>) param.get(NodeConstant.CHILDREN_NODE);
		if(CollectionUtils.isEmpty(childrenNodeList)) 
			return finalCustInfos;
		
		for(CustInfo custInfo : finalCustInfos) {
			obtainCustInfoChildrenNodeList(custInfo,childrenNodeList);
		}
		
		return finalCustInfos;
	}
	
	public List<CustInfo> findByCustNo(String custNo, List<Map<String, Object>> childrenNodeList) {
		List<CustInfo> custInos = repository.findByCustNo(custNo);
		for (CustInfo custInfo : custInos) {
			obtainCustInfoChildrenNodeList(custInfo, childrenNodeList);
		}
		return custInos;
	}

	public CustInfo findOneByCustNo(String custNo) {
		return repository.findOneByCustNo(custNo);
	}
	
	private void obtainCustInfoChildrenNodeList(CustInfo custInfo, List<Map<String, Object>> childrenNodeList) {
		if (CollectionUtils.isEmpty(childrenNodeList))
			return;

		for (Map<String, Object> childrenNode : childrenNodeList) {
			obtainCustInfoChildrenNode(custInfo, childrenNode);
		}
	}

	private void obtainCustInfoChildrenNode(CustInfo custInfo, Map<String, Object> childrenNode) {
		if (CollectionUtils.isEmpty(childrenNode))
			return;
		
		for (Map.Entry<String, Object> node : childrenNode.entrySet()) {
			String nodeName = node.getKey();
			Map<String,Object> nodeParam = (Map<String, Object>) node.getValue();
			if (NodeConstant.CUST_DETAIL_INFO.equals(nodeName)) {
				obtainCustDetailChildrenNode(custInfo,nodeParam,custDetailInfoDao);
			}
			
			if(NodeConstant.ACCOUNT_INFO.equals(nodeName)) {
				obtainAccountChildrenNode(custInfo,nodeParam,accountInfoDao);
			}
		}
	}
	
	private void obtainCustDetailChildrenNode(CustInfo custInfo, Map<String, Object> nodeParam,
			CustDetailInfoDao custDetailInfoDao) {
		List<CustDetailInfo> custDetailInfos = custDetailInfoDao.findByParam4CustInfo(custInfo.getCustNo(),nodeParam);
		custInfo.setCustDetailInfos(custDetailInfos);
	}

	private void obtainAccountChildrenNode(CustInfo custInfo,Map<String,Object> nodeParam,AccountInfoDao accountInfoDao) {
		List<AccountInfo> accountInfos = accountInfoDao.findByParam4CustInfo(custInfo.getCustNo(),nodeParam);
		custInfo.setAccountInfos(accountInfos);
	}
}
