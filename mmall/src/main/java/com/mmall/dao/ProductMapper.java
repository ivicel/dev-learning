package com.mmall.dao;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductListVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    List<Product> selectProductByNameAndId(@Param("id") Integer id, @Param("name") String name);

    int updateByPrimaryKeySelective(Product product);

    int insertSelective(Product product);
}