package info.ivicel.tumoblog;


import info.ivicel.tumoblog.admin.entity.Tag;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class TestFunction {
    @Test
    public void test() {
        List<Object> collect = Stream.of(Ex.of(1), Ex.of(2), Ex.of(3)).map((Ex ex) -> {ex.p();return ex;}).collect(Collectors.toList());
        System.out.println(collect);
    }

    static class Ex {
        public int increment(int n) {
            return n + 1;
        }
        public static Ex of(int n) {
            return new Ex();
        }
        public void p() {
            System.out.println("----");

        }
    }

}
