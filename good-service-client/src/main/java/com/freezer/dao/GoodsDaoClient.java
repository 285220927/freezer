/**
 * @FileName: GoodsDaoClient
 * @Author: zzc
 * @Date: 2019年12月29日 11:27:51
 * @Version V1.0.0
 */

package com.freezer.dao;

import freezer.dto.Goods;
import freezer.dto.GoodsCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsDaoClient {

    @Select("select * from goods where id = #{id}")
    Goods findGoodById(int id);

    @Select("<script> select * from goods where id in <foreach collection='goodsIds' item='id' " +
            "open='(' separator=',' close=')'>#{id}</foreach></script>")
    @Results({
            @Result(column = "name", property = "goodsName"),
            @Result(column = "cate_id", property = "goodsCategory", one = @One(select = "findCategoryById"))
    })
    List<Goods> findGoodByIdList(@Param("goodsIds") List<String> otherGoodsId);

    @Select("select * from category where id = #{cateId}")
    @Results(@Result(column = "cate_name", property = "categoryName"))
    GoodsCategory findCategoryById(int cateId);
}
