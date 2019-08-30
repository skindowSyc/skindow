package com.skindow.mongoDao;

import com.skindow.MongoDBService.MongoUserService;
import com.skindow.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/8/30.
 */
@Component
public class MongoUserDao implements MongoUserService{
    @Autowired
    private MongoTemplate mongoTemplate;

    /** 保存用户
     * @param user 用户对象
     * @param collectionName 集合名称
     */
    public void saveUser(User user,String collectionName) throws Exception {
        if (user == null)
        {
            throw new Exception("user is null");
        }
         mongoTemplate.save(user,collectionName);
    }

    /** 通过名称查询用户
     * @param name 用户名称
     * @param collectionName 集合名称
     * @return
     */
    public User findUserByName(String name,String collectionName) throws Exception {
        if (name == null || name.isEmpty())
        {
            throw new Exception("user is empty");
        }
        Query query=new Query(Criteria.where("name").is(name));
        User user = mongoTemplate.findOne(query,User.class,collectionName);
        return user;
    }

    /**更新对象 用过id进行更新
     * @param user 必传id
     * @param collectionName 集合名称
     * @throws Exception
     */
    public void updateUser(User user,String collectionName) throws Exception {
        if (user == null  || user.getId() == null)
        {
            throw new Exception("user is null or id is null");
        }
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        if (user.getAge() != null)
        {
            update.set("age", user.getAge());
        }
        if (user.getAge() != null)
        {
           update.set("age", user.getAge());
        }
        if (user.getAddress() != null)
        {
            update.set("address",user.getAddress());
        }
        if (user.getCountry() != null)
        {
            update.set("country",user.getCountry());
        }
        mongoTemplate.updateFirst(query, update, User.class,collectionName);
    }

    /** 通过id 名称进行删除
     * @param id 用户id
     * @param colliectionName 集合名称
     */
    public void deleteTestById(Integer id,String colliectionName) throws Exception {
        if (id == null)
        {
            throw new Exception("id is null");
        }
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class, colliectionName);
    }

}
