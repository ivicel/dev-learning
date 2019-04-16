package com.mmall.util;

import com.mmall.common.Constants;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import javax.servlet.http.HttpSession;

public class UserUtils {
    public static ServerResponse check(HttpSession session) {
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCode(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getMessage());
        }
        if (!user.isAdmin()) {
            return ServerResponse.createByErrorCode(ResponseCode.PERMISSION_DENIED.getCode(),
                    ResponseCode.PERMISSION_DENIED.getMessage());
        }
        return null;
    }

}
