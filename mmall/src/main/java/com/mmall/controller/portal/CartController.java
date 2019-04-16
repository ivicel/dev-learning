package com.mmall.controller.portal;

import com.mmall.common.Constants;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.util.UserUtils;
import com.mmall.vo.CartVo;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/list")
    public ServerResponse<CartVo> getCartList(HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }

        User user = (User) session.getAttribute(Constants.CURRENT_USER);

        return cartService.getCartList(user.getId());
    }

    @PostMapping("/add")
    public String addToCart() {
        return null;
    }

    @PostMapping("/update")
    public String updateCart() {
        return null;
    }

    @PostMapping("delete-product")
    public String deleteProduct() {
        return null;
    }

    @PostMapping("/select-all")
    public String selectAll() {
        return null;
    }

    @PostMapping("unselect-all")
    public String unselectAll() {
        return null;
    }

    @PostMapping("/select")
    public String select() {
        return null;
    }

    @PostMapping("unselect")
    public String unselect() {
        return null;
    }

    @PostMapping("get-cart-product-count")
    public String getCartProductCount() {
        return null;
    }
}
