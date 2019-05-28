package com.xidian.faceToChild.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.xidian.faceToChild.exception.InvalidParamException;

import jdk.internal.dynalink.beans.StaticClass;

public class V {

	public static void valid(HttpServletRequest req, String[] keys){
		for(String key : keys){
			String value = req.getParameter(key);
			if(value == null || value.length() == 0) {
				throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, key + "不能为空");
			}
		}
	}
	
	/**
	 * 自动创建对象实体
	 * @param req
	 * @param cls
	 * @param keys
	 * @return
	 */
	
	public static <T> T entity(HttpServletRequest req, Class<?> cls, String[] keys) {
		T instance = null;
		try {
			instance = (T)cls.newInstance();
			for(String key : keys) {
				String value = req.getParameter(key);
				System.out.println(key + " ======" + value);
				String param = NameUtil.convert2Caml(key);
				Field field = null;
				field = cls.getDeclaredField(param);
				param = NameUtil.firstUpper(param);
				String optName = "set" + param;
				Class<?> dataType = field.getType();
				
				String typeName = dataType.getTypeName();
				Object valueSet = null;
				if (typeName.equals("java.lang.Integer")) {
					valueSet = Integer.parseInt(value);
				}else if(typeName.equals("java.lang.Long")) {
					valueSet = Long.parseLong(value);
				}else if(typeName.equals("java.lang.Double")) {
					valueSet = Double.parseDouble(value);
				}else if(typeName.equals("java.util.Date")) {
					//TODO 转化为日期类型
				}else {
					valueSet = dataType.cast(value);
				}
				Method method = cls.getMethod(optName, dataType);
				method.invoke(instance, valueSet);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "实例化参数错误");
		}
		
		return instance;
	}
}
