package cc.ryanc.halo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecureUtils {
    public static String encode(CharSequence text) {
        return new BCryptPasswordEncoder().encode(text);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
