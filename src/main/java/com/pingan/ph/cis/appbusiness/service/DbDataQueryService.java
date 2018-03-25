package com.pingan.ph.cis.appbusiness.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface DbDataQueryService {
	public static final String SERVICE_ID = "dbDataQueryService";

	public List<?> process(JSONObject requestParam);
}
