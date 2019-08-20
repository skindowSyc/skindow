package com.skindow.service;

import com.skindow.pojo.User;

/**
 * Created by Administrator on 2019/8/20.
 */
public interface UserService {
    public String setUser(User user);
    public User getUser(String id);
}
