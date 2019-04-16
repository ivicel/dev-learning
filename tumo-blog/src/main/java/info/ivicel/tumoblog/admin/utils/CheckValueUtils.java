package info.ivicel.tumoblog.admin.utils;

import info.ivicel.tumoblog.admin.entity.Article;
import java.util.Collection;

public class CheckValueUtils {

    public static boolean checkPgae(Integer a, Integer b) {
        return a != null && a != 0 && b != null && b != 0;
    }

    public static boolean checkObj(Article article) {
        return article != null;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
