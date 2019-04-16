package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import java.util.List;

public interface IProductService {

    ServerResponse<PageInfo<ProductListVo>> listProduct(int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo<ProductListVo>> searchProduct(Integer productId, String productName,
            Integer pageNum, Integer pageSize);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<String> saveOrUpdateProduct(Product product);
}
