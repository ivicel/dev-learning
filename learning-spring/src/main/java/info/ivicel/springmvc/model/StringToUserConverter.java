package info.ivicel.springmvc.model;

import org.springframework.core.convert.converter.Converter;

public class StringToUserConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        System.out.println(source);
        User user = new User();
        if (source != null) {
            String[] items = source.split(":");
            user.setUsername(items[0]);
            user.setPassword(items[1]);
        }
        return user;
    }
}
