package com.xidian.faceToChild;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xidian.faceToChild.mapper.UserMapper;
import com.xidian.faceToChild.po.User;
import com.xidian.faceToChild.util.D;
import com.xidian.faceToChild.web.ApiResult;

public class UserTest {
	@Test
	public void testJackson() throws IOException {
		ObjectMapper objMapper = new ObjectMapper();
		ApiResult apiRs = new ApiResult();
		apiRs.setCode(200);
		apiRs.setMsg("success");
		// jsonתjava
		String rs = objMapper.writeValueAsString(apiRs);
		System.out.println(rs);

		// javaתjson
		ApiResult src = objMapper.readValue(rs, ApiResult.class);
		System.out.println(src);

	}

	@Test
	public void testSelect() {
		SqlSession session = D.getConn();
		UserMapper uMapper = session.getMapper(UserMapper.class);
		User user = uMapper.findUserByNickname("chichi");
		System.out.println(user);
		System.out.println("ok");
	}
}
