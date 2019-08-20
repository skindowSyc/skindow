package com.skindow.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.skindow.mapper.UserMapper;
import com.skindow.pojo.User;
import com.skindow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2019/8/20.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public String setUser(User user) {
        int insert = userMapper.insert(user);
        if (insert == 0)
        {
            return "fail";
        }
        return "success";
    }

    @Override
    public User getUser(String id) {
        User user = userMapper.selectByPrimaryKey(new Integer(id));
        return user;
    }
}
