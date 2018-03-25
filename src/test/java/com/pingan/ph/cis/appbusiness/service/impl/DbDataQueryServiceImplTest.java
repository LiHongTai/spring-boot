package com.pingan.ph.cis.appbusiness.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pingan.ph.cis.appbusiness.BaseTestSuit;
import com.pingan.ph.cis.appbusiness.constant.NodeConstant;
import com.pingan.ph.cis.appbusiness.service.DbDataQueryService;

public class DbDataQueryServiceImplTest extends BaseTestSuit{

	@Resource(name = DbDataQueryService.SERVICE_ID)
	private DbDataQueryService dataQueryService;
	
	@Test
	public void testProcess() {
		//从最高层向最低层 获取数据
		Map<String,Object> requestParam = new HashMap<>();
		requestParam.put(NodeConstant.NODE_NAME, NodeConstant.CUST_INFO);
		Map<String,Object> nodeParam = new HashMap<>();
		requestParam.put(NodeConstant.CUST_INFO, nodeParam);
		
		nodeParam.put("custNo", "cust001");
		List<Map<String,Object>> childrenNodeList = initChildrenNodeList();
		nodeParam.put(NodeConstant.CHILDREN_NODE, childrenNodeList);
		
		JSONObject requestJsonObject = JSONObject.parseObject(JSON.toJSONString(requestParam));
		System.out.println("请求参数：" + JSON.toJSONString(requestJsonObject, true));
		
		List<?> resultList = dataQueryService.process(requestJsonObject);
		System.out.println("返回结果：" + JSON.toJSONString(resultList, true));
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
			createAccountInfoNodeParam(nodeParam);
		}
		if(NodeConstant.CUST_DETAIL_INFO.equals(nodeName)){
			//nodeParam.put("custAge", "30");
		}
		return node;
	}

	@Test
	public void testProcess2() {
		Map<String,Object> requestParam = new HashMap<>();
		requestParam.put(NodeConstant.NODE_NAME, NodeConstant.ACCOUNT_INFO);
		Map<String,Object> nodeParam = new HashMap<>();
		createAccountInfoNodeParam(nodeParam);
		requestParam.put(NodeConstant.ACCOUNT_INFO, nodeParam);
		
		Map<String,Object> parentNodeParam = new HashMap<>();
		parentNodeParam.put(NodeConstant.NODE_NAME, NodeConstant.CUST_INFO);
		parentNodeParam.put(NodeConstant.NODE_KEY, "custNo");
		parentNodeParam.put(NodeConstant.CHILDREN_FILED_NAME, "accountInfos");
		
		//requestParam.put(NodeConstant.PARENT_NODE, parentNodeParam);
		

		JSONObject requestJsonObject = JSONObject.parseObject(JSON.toJSONString(requestParam));
		System.out.println("请求参数：" + JSON.toJSONString(requestJsonObject, true));
		
		List<?> resultList = dataQueryService.process(requestJsonObject);
		System.out.println("返回结果：" + JSON.toJSONString(resultList, true));
	}
	
	private static void createAccountInfoNodeParam(Map<String, Object> nodeParam) {
		List<Map<String,Object>> childrenNodeList = new ArrayList<>();
		Map<String,Object> childrenNode = new HashMap<>();
		Map<String,Object> childrenNodeParam = new HashMap<>();
		//childrenNodeParam.put("bankCardNo", "zs-925910");
		childrenNode.put(NodeConstant.BANK_CARD_INFO, childrenNodeParam);
		
		childrenNodeList.add(childrenNode);
		
		nodeParam.put(NodeConstant.CHILDREN_NODE, childrenNodeList);
		nodeParam.put("accountNo", "account001");
	}
}
