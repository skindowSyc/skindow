package com.skindow;

import com.skindow.mapper.UserMapper;
import com.skindow.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SkindowProviderApplicationTests {
	@Autowired
	UserMapper userMapper;
	@Test
	public void contextLoads() {
		List<User> list = new ArrayList<>(100);
		User user = null;
		for (int i = 0 ; i < 100 ; i++)
		{
			user = new User();
			user.setId(i);
			user.setAddress("武汉" + i);
			user.setAge(i);
			user.setCountry("中国");
			user.setName("skindow" + i);
			list.add(user);
			user = null;
		}
		int i = userMapper.updateUserBatch(list);
		log.info("总共批量更新了{}条用户信息",i);
	}

}
