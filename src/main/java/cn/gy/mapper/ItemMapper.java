package cn.gy.mapper;

import cn.gy.model.Item;
import cn.gy.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

   /* @Select(" select name from item_info")*/
    public Item findItemInfo();


    int insertItemInfoList(List<Item> items);
}
