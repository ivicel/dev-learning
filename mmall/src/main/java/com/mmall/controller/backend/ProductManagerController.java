package com.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.util.PropertiesUtils;
import com.mmall.util.UserUtils;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/manage/product")
public class ProductManagerController {
    private IProductService productService;
    private IFileService fileService;

    @Autowired
    public ProductManagerController(IProductService productService, IFileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    /**
     * 获取产品列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse<PageInfo<ProductListVo>> listAll(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }

        return productService.listProduct(pageNum, pageSize);
    }

    /**
     * 搜索产品
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ServerResponse<PageInfo<ProductListVo>> searchProduct(String productName, Integer productId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }

        return productService.searchProduct(productId, productName, pageNum, pageSize);
    }

    /**
     * 上传产品图片
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ServerResponse<Map> uploadProductImage(@RequestParam("uploadFile") MultipartFile file,
            HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }
        String path = PropertiesUtils.getProperty("image.upload.path",
                session.getServletContext().getRealPath("upload"));
        String targetFileName = fileService.upload(path, file);
        String url = PropertiesUtils.getProperty("ftp.server.http.prefix", "");
        url += url.endsWith("/") ? targetFileName : "/" + targetFileName;

        Map<String, String> map = new HashMap<>();
        map.put("uri", targetFileName);
        map.put("url", url);
        return ServerResponse.createBySuccess(map);
    }

    /**
     * 获取产品详情
     * @param productId
     * @return
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ServerResponse<ProductDetailVo> getProductDetail(@RequestParam("productId") Integer productId,
            HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }
        return productService.getProductDetail(productId);
    }

    /**
     * 修改产品上下架状态
     * @param productId
     * @return
     */
    @RequestMapping(value = "set-sale-status", method = RequestMethod.POST)
    public ServerResponse<String> setSaleStatus(@RequestParam("productId") Integer productId, Integer status,
            HttpSession session) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getMessage());
        }

        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }

        return productService.setSaleStatus(productId, status);
    }

    /**
     * 新增或更新产品
     * @param product
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ServerResponse<String> saveProduct(Product product, HttpSession session) {
        if (product == null) {
            return ServerResponse.createByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getMessage());
        }

        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }

        return productService.saveOrUpdateProduct(product);
    }

    /**
     * 使用富文本编辑器上传产品详情
     * @return
     */
    @RequestMapping(value = "/richtext-upload", method = RequestMethod.POST)
    public ServerResponse<Map> richTextUpload(@RequestParam("uploadFile") MultipartFile file,
            HttpSession session, HttpServletResponse response) {
        ServerResponse serverResponse = UserUtils.check(session);
        if (response != null) {
            return serverResponse;
        }

        Map<String, String> map = new HashMap<>();
        String path = session.getServletContext().getRealPath("upload");
        String targetFilename = fileService.upload(path, file);
        if (StringUtils.isBlank(targetFilename)) {
            map.put("success", "false");
            map.put("msg", "上传失败");
        }

        String url = PropertiesUtils.getProperty("ftp.server.http.prefix") + targetFilename;
        map.put("success","true");
        map.put("msg","上传成功");
        map.put("file_path",url);
        response.addHeader("Access-Control-Allow-Headers","X-File-Name");

        return ServerResponse.createBySuccess(map);
    }


}
