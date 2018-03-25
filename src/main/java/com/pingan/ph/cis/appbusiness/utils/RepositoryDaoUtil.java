package com.pingan.ph.cis.appbusiness.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pingan.ph.cis.appbusiness.constant.CisConstant;

public class RepositoryDaoUtil {

	public RepositoryDaoUtil() {

	}

	public static Class<?> getRepositoryDaoClassType(String name,String nameType) {
		Map<String, Class<?>> repositoryDao = new HashMap<>();
		
		if(CisConstant.ENTITY.equals(nameType))
			repositoryDao = getRepositoryDaoClassType4EntityName();
		
		if(CisConstant.DB_TABLE.equals(nameType))
			repositoryDao = getRepositoryDaoClassType4DbTableName();
			
		return repositoryDao.get(name.toUpperCase());
	}

	public static Map<String, Class<?>> getRepositoryDaoClassType4DbTableName() {
		Map<String, Class<?>> repositoryDao4DbTableName = new HashMap<>();

		Map<String, String> entityName4DbTableName = EntityUtil.getEntityName4DbTableName();
		Map<String, Class<?>> repositoryDao4EntityName = getRepositoryDaoClassType4EntityName();
		for (Map.Entry<String, String> entity : entityName4DbTableName.entrySet()) {
			String dbTableName = entity.getKey();
			String entityName = entity.getValue();
			repositoryDao4DbTableName.put(dbTableName, (Class<?>) repositoryDao4EntityName.get(entityName));
		}

		return repositoryDao4DbTableName;
	}

	public static Map<String, Class<?>> getRepositoryDaoClassType4EntityName() {
		Map<String, Class<?>> repositoryDao4EntityName = new HashMap<>();
		try {
			List<String> repositoryDaoPaths = PackageUtil.getClasses(CisConstant.DAO_PACKAGE_PATH);
			for (String repositoryDaoPath : repositoryDaoPaths) {
				Class<?> className = Class.forName(repositoryDaoPath);
				String simpleName = className.getSimpleName();
				String entityName = simpleName.split(CisConstant.DAO_SPLIT_FLAG)[0];
				repositoryDao4EntityName.put(entityName, className);
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return repositoryDao4EntityName;
	}
}
