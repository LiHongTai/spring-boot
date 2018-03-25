package com.pingan.ph.cis.appbusiness.repositorydao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pingan.ph.cis.appbusiness.BaseTestSuit;
import com.pingan.ph.cis.appbusiness.constant.NodeConstant;
import com.pingan.ph.cis.appbusiness.dao.CustInfoDao;
import com.pingan.ph.cis.appbusiness.entity.CustInfo;

public class CustInfoDaoTest extends BaseTestSuit{

	@Autowired
	private CustInfoDao custInfoDao;
	
	@Test
	public void testFindByCustNoAddChildrenNodeList() {
		Map<String,Object> requestParam = new HashMap<>();
		requestParam.put("custNo", "cust001");
		List<Map<String,Object>> childrenNodeList = initChildrenNodeList();
		requestParam.put(NodeConstant.CHILDREN_NODE, childrenNodeList);
		
		JSONObject requestJsonObject = JSONObject.parseObject(JSON.toJSONString(requestParam));
		System.out.println("请求参数：" + JSON.toJSONString(requestJsonObject, true));
		
		List<CustInfo> custInfos = custInfoDao.findByCustNo("cust001", childrenNodeList);
		System.out.println("返回结果：" + JSON.toJSONString(custInfos, true));
		
	}
	
	private static List<Map<String,Object>> initChildrenNodeList(){
		List<Map<String,Object>> childrenNodeList = new ArrayList<>();
		
		childrenNodeList.add(createNodeParam(NodeConstant.CUST_DETAIL_INFO));
		childrenNodeList.add(createNodeParam(NodeConstant.ACCOUNT_INFO));
		return childrenNodeList;
	}



	private static Map<String, Object> createNodeParam(String nodeName) {
		Map<String,Object> node = new HashMap<>();
		Map<String,Object> nodeParam = new HashMap<>();
		node.put(nodeName, nodeParam);
		
		if(NodeConstant.ACCOUNT_INFO.equals(nodeName)) {
			List<Map<String,Object>> childrenNodeList = new ArrayList<>();
			Map<String,Object> childrenNode = new HashMap<>();
			Map<String,Object> childrenNodeParam = new HashMap<>();
			childrenNodeParam.put("bankCardNo", "zs-925910");
			childrenNode.put(NodeConstant.BANK_CARD_INFO, childrenNodeParam);
			
			childrenNodeList.add(childrenNode);
			
			nodeParam.put(NodeConstant.CHILDREN_NODE, childrenNodeList);
			nodeParam.put("accountNo", "account001");
		}
		if(NodeConstant.CUST_DETAIL_INFO.equals(nodeName)){
			nodeParam.put("custAge", "30");
		}
		return node;
	}
}
