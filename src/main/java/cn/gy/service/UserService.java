package cn.gy.service;

import cn.gy.mapper.ItemMapper;
import cn.gy.mapper.UserMapper;
import cn.gy.model.Item;
import cn.gy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private ItemMapper itemMapper;

    public User getUserInfo(){
        User user=userMapper.findUserInfo();
        //User user=null;
        return user;
    }

    public int addUserInfo(List<User> users){
        int user=userMapper.insertUserInfoList(users);
        //User user=null;
        return user;
    }

    public int addItemInfo(List<Item> items){
        int item=itemMapper.insertItemInfoList(items);
        //User user=null;
        return item;
    }
}
