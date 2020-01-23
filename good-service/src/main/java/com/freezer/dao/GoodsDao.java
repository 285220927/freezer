/**
 * @FileName: GoodsDao
 * @Author: zzc
 * @Date: 2019年12月28日 18:37:21
 * @Version V1.0.0
 */

package com.freezer.dao;

import freezer.dto.Goods;
import freezer.dto.GoodsCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsDao {
    @Select("select *, g.name as goodsName from goods as g")
    @Results(@Result(property = "goodsCategory", column = "cate_id", one = @One(select = "findCateById")))
    List<Goods> findGoods();

    @Select("select * from category where id = #{id}")
    @Results(@Result(property = "categoryName", column = "cate_name"))
    GoodsCategory findCateById(int id);
}
