package personal.yoinami.rolebasedresourceserver.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public String getAuthenticatedId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof OAuth2AuthenticationToken token &&
                token.getPrincipal() instanceof OAuth2User oAuth2User) {
            return String.valueOf(oAuth2User.getAttributes().get("sub"));
        }
        throw new IllegalStateException("User is not authenticated or malformed token");
    }

}
