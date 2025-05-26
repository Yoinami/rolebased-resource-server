package personal.yoinami.rolebasedresourceserver.component;

import jakarta.servlet.http.HttpServletRequest;

public class RefererRedirect {

    public static String sendRedirectTo(HttpServletRequest request) {
        String referrer = request.getHeader("referer");
        String redirect_url = (referrer != null) ? referrer : "";
        if(redirect_url.contains("?")) redirect_url = redirect_url.substring(0, redirect_url.indexOf("?"));
        redirect_url = "redirect:" + redirect_url.replaceFirst("https?://[^/]+", "");
        System.out.println(redirect_url.replaceFirst("https?://[^/]+", ""));

        return redirect_url;
    }

    public static String getRedirectUri(HttpServletRequest request) {
        String referrer = request.getHeader("referer");
        String redirect_url = (referrer != null) ? referrer : "";
        if(redirect_url.contains("?")) redirect_url = redirect_url.substring(0, redirect_url.indexOf("?"));
        redirect_url = redirect_url.replaceFirst("https?://[^/]+", "");

        return redirect_url;
    }

}
