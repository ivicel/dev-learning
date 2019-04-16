package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.mmall.common.ProductStatus;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.controller.Post;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import com.mmall.util.PropertiesUtils;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service("productService")
public class ProductServiceImpl implements IProductService {
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    @Override
    public ServerResponse<PageInfo<ProductListVo>> listProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectAll();
        List<ProductListVo> list = assembleProductListVo(products);
        PageInfo<ProductListVo> pageResult = new PageInfo<>(list);

        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getMessage());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }

        if (product.getStatus() != ProductStatus.ON_SALE.getCode()) {
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        ProductDetailVo vo = assembleProductDetailVo(product);

        return ServerResponse.createBySuccess(vo);
    }

    @Override
    public ServerResponse<PageInfo<ProductListVo>> searchProduct(Integer productId, String productName,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        final String finalProductName = StringUtils.isNotBlank(productName) ?
            "%" + productName + "%" : null;
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectProductByNameAndId(productId, finalProductName);
        List<ProductListVo> productList = assembleProductListVo(products);
        PageInfo<ProductListVo> pageResult = new PageInfo<>(productList);

        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int count = productMapper.updateByPrimaryKeySelective(product);
        return count > 0 ? ServerResponse.createBySuccessMessage("修改产品销售状态成功") :
                ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }

    @Override
    public ServerResponse<String> saveOrUpdateProduct(Product product) {
        int count;

        if (StringUtils.isNotBlank(product.getSubImages())) {
            String[] subImages = product.getSubImages().trim().split(",");
            if (subImages.length > 0) {
                product.setMainImage(subImages[0]);
            }
        }

        if (product.getId() == null) {
            count = productMapper.insertSelective(product);
            return count > 0 ? ServerResponse.createBySuccessMessage("新增产品成功") :
                    ServerResponse.createByErrorMessage("新增产品失败");
        } else {
            count = productMapper.updateByPrimaryKeySelective(product);
            return count > 0 ? ServerResponse.createBySuccessMessage("更新产品成功") :
                    ServerResponse.createByErrorMessage("更新产品失败");
        }
    }

    private ProductDetailVo assembleProductDetailVo(Product product) {
        ProductDetailVo vo = new ProductDetailVo();
        vo.setId(product.getId());
        vo.setCategoryId(product.getCategoryId());
        vo.setName(product.getName());
        vo.setSubtitle(product.getSubtitle());
        vo.setMainImage(product.getMainImage());
        vo.setSubImages(product.getSubImages());
        vo.setPrice(product.getPrice());
        vo.setStock(product.getStock());
        vo.setStatus(product.getStatus());
        vo.setImageHost(PropertiesUtils.getProperty("ftp.server.http.prefix", "http://www.example.com"));
        return vo;
    }

    private List<ProductListVo> assembleProductListVo(List<Product> products) {
        List<ProductListVo> list = Lists.newArrayListWithCapacity(products.size());
        for (Product product : products) {
            ProductListVo vo = new ProductListVo();
            vo.setId(product.getId());
            vo.setName(product.getName());
            vo.setCategoryId(product.getCategoryId());
            vo.setSubtitle(product.getSubtitle());
            vo.setMainImage(product.getMainImage());
            vo.setImageHost(PropertiesUtils.getProperty("ftp.server.http.prefix", "http://www.example.com"));
            vo.setPrice(product.getPrice());
            vo.setStatus(product.getStatus());
            list.add(vo);
        }
        return list;

    }
}
