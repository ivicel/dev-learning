package info.ivicel.tumoblog.admin.crypt;

import java.util.regex.Pattern;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BCryptCredentials implements CredentialsMatcher {
    private Pattern BCRYPT_PATTERN = Pattern
            .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    private final Logger logger = LoggerFactory.getLogger(BCrypt.class);

    public static String encode(CharSequence plain) {
        return BCrypt.hashpw(plain.toString(), BCrypt.gensalt());
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String tokenCredentials = String.valueOf((char[])token.getCredentials());
        String accountCredentials = String.valueOf(info.getCredentials());

        if (!BCRYPT_PATTERN.matcher(accountCredentials).matches()) {
            logger.warn("非 bcrypt 加密的密码");
            return false;
        }

        return BCrypt.checkpw(tokenCredentials, accountCredentials);
    }
}
