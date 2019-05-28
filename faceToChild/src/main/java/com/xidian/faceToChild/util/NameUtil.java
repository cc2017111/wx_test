package com.xidian.faceToChild.util;

public class NameUtil {

	public static String convert2Caml(String varName) {
		//根据下划线进行分割     转化为驼峰表示法 
		String[] temp = varName.split("_");
		StringBuilder varSb = new StringBuilder();
		for (int i = 0; i < temp.length; i++) {
			//第一个单词不变
			if (i == 0) {
				varSb.append(temp[i]);
			}

			else {
				//第二个单词开始     每一个单词第一个字母转换成大写 
				varSb.append(firstUpper(temp[i]));
			}
		}
		return varSb.toString();
	}

	public static String firstUpper(String varName) {
		StringBuilder varSb = new StringBuilder();
		//第二个单词开始     每一个单词第一个字母转换成大写 
		String firstAlp = varName.substring(0, 1).toUpperCase();
		varSb.append(firstAlp);
		varSb.append(varName.substring(1, varName.length()));
		return varSb.toString();

	}

	//测试代码
	public static void main(String[] args) {
		String varName = "test_name";
		String rs = firstUpper(varName);
		System.out.println(rs);
		//获取class对象
		Class clz = NameUtil.class;
		System.out.println(clz);
	}
}
