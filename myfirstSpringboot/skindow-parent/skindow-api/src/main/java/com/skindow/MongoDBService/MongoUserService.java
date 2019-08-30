package com.skindow.MongoDBService;

import com.skindow.pojo.User;

/**
 * Created by Administrator on 2019/8/30.
 */
public interface MongoUserService {
    /** 保存用户
     * @param user 用户对象
     * @param collectionName 集合名称
     */
    public void saveUser(User user, String collectionName) throws Exception;

    /** 通过名称查询用户
     * @param name 用户名称
     * @param collectionName 集合名称
     * @return
     */
    public User findUserByName(String name,String collectionName) throws Exception;


    /**更新对象 用过id进行更新
     * @param user 必传id
     * @param collectionName 集合名称
     * @throws Exception
     */
    public void updateUser(User user,String collectionName) throws Exception;

    /** 通过id 名称进行删除
     * @param id 用户id
     * @param colliectionName 集合名称
     */
    public void deleteTestById(Integer id,String colliectionName) throws Exception;

}
