package com.pingan.ph.cis.appbusiness.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.util.CollectionUtils;

public class ReflectUtil {

	public static boolean filterEntityByParam(Object entity, Map<String, Object> param) {
		if (CollectionUtils.isEmpty(param))
			return true;

		for (Map.Entry<String, Object> detailEntry : param.entrySet()) {
			String cloumnName = detailEntry.getKey();

			if (!isContain(entity.getClass(), cloumnName))
				continue;

			Object columnValue = detailEntry.getValue();
			Object fieldValue = getFieldValue(entity, cloumnName);
			if (!columnValue.equals(fieldValue))
				return false;
		}
		return true;
	}

	public static Object getFieldValue(Object entity, String fieldName) {
		Object fieldValue = null;
		try {
			Field field = entity.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			if (field != null)
				fieldValue = field.get(entity);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return fieldValue;
	}

	public static void setFiledValie(Object entity, String fieldName,Object fieldValue) {
		try {
			Field field = entity.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(entity, fieldValue);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
	}

	public static boolean isContain(Class<?> entityClass, String columnName) {
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			if (columnName.equals(fieldName))
				return true;
		}

		return false;
	}
}