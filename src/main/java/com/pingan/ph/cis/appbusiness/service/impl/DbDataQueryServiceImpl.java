package com.pingan.ph.cis.appbusiness.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.pingan.ph.cis.appbusiness.constant.CisConstant;
import com.pingan.ph.cis.appbusiness.constant.NodeConstant;
import com.pingan.ph.cis.appbusiness.service.DbDataQueryService;
import com.pingan.ph.cis.appbusiness.utils.ReflectUtil;
import com.pingan.ph.cis.appbusiness.utils.RepositoryDaoUtil;
import com.pingan.ph.cis.appbusiness.utils.SpringContextUtil;

@Service(value = DbDataQueryService.SERVICE_ID)
@SuppressWarnings("unchecked")
public class DbDataQueryServiceImpl implements DbDataQueryService {

	private static final String METHOD_NAME = "findByParam";
	private static final String METHOD_NAME_PREFIX = "findOneBy";

	@Override
	public List<?> process(JSONObject requestParam) {
		List<?> resultDataList = new ArrayList<>();
		if (CollectionUtils.isEmpty(requestParam))
			return resultDataList;
		try {
			// TODO 空值怎么处理
			String dbTableName = requestParam.getString(NodeConstant.NODE_NAME);
			Class<?> repositoryDaoClassType = RepositoryDaoUtil.getRepositoryDaoClassType(dbTableName,
					CisConstant.DB_TABLE);
			Method method = repositoryDaoClassType.getMethod(METHOD_NAME, Map.class);
			
			Map<String, Object> args = (Map<String, Object>) requestParam.get(dbTableName);
			
			Object result =  method.invoke(SpringContextUtil.getBean(repositoryDaoClassType), args);
			
			resultDataList = (List<?>) result;
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		} 
		
		return obtainParentNodeInfo(requestParam,resultDataList);
	}

	private List<?> obtainParentNodeInfo(JSONObject requestParam, List<?> childrenNodeDataList) {
		if (CollectionUtils.isEmpty(childrenNodeDataList))
			return new ArrayList<>();

		if (!requestParam.containsKey(NodeConstant.PARENT_NODE))
			return childrenNodeDataList;

		JSONObject parentNodeParam = (JSONObject) requestParam.get(NodeConstant.PARENT_NODE);
		if(CollectionUtils.isEmpty(parentNodeParam))
			return childrenNodeDataList;
		
		List<Object> parentNodeDataList = new ArrayList<>();
		try {
			String dbTableName = parentNodeParam.getString(NodeConstant.NODE_NAME);
			String parentKey = parentNodeParam.getString(NodeConstant.NODE_KEY);
			String childrenFiledName = parentNodeParam.getString(NodeConstant.CHILDREN_FILED_NAME);
					
			Map<String, List<?>> parentNodeKeyInfo = allotChildrenNodeDataByParentKey(childrenNodeDataList, parentKey);

			String methodName = METHOD_NAME_PREFIX + StringUtils.capitalize(parentKey);
			Class<?> repositoryDaoClassType = RepositoryDaoUtil.getRepositoryDaoClassType(dbTableName,
					CisConstant.DB_TABLE);
			Method method = repositoryDaoClassType.getMethod(methodName, String.class);
			
			for(Map.Entry<String, List<?>> dataEntry : parentNodeKeyInfo.entrySet()) {
				String args = dataEntry.getKey();
				List<?> fieldValue = dataEntry.getValue();
				Object entity = method.invoke(SpringContextUtil.getBean(repositoryDaoClassType), args);
				ReflectUtil.setFiledValie(entity, childrenFiledName, fieldValue);
				parentNodeDataList.add(entity);
			}

		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return obtainParentNodeInfo(parentNodeParam, parentNodeDataList);

	}

	private Map<String, List<?>> allotChildrenNodeDataByParentKey(List<?> childrenNodeDataList, String parentKey) {
		Map<String, List<?>> parentNodeKeyInfo = new HashMap<>();
		for (Object entity : childrenNodeDataList) {
			String fieldValue = (String) ReflectUtil.getFieldValue(entity, parentKey);
			List<Object> entities = getChildrenNoeDataList(parentNodeKeyInfo,fieldValue);
			entities.add(entity);
		}
		return parentNodeKeyInfo;

	}

	private List<Object> getChildrenNoeDataList(Map<String, List<?>> parentNodeKeyInfo, String fieldValue) {
		if(!parentNodeKeyInfo.containsKey(fieldValue))
			parentNodeKeyInfo.put(fieldValue, new ArrayList<>());
		
		return (List<Object>) parentNodeKeyInfo.get(fieldValue);
	}
}
