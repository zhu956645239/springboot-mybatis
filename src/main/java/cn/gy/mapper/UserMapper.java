package cn.gy.mapper;

import cn.gy.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

  /*  @Select("select sn,province,city from user_info;")*/
     User findUserInfo();
     int insertUserInfoList(List<User> users);
}
