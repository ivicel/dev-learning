package com.blog.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static List filterWhiter(List<String> list) {
        List<String> strs = new LinkedList<>();
        for (String s : list) {
            if (!isEmpty(s)) {
                strs.add(s);
            }
        }
        return strs;
    }
}
