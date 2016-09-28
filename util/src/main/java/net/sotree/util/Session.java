package net.sotree.util;

import android.content.Context;
import android.os.Build;

import java.net.CookieHandler;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by lims on 2016. 4. 4..
 * 로그인 세션 관련 유틸
 */
public class Session {

    public static void init(Context context) {
        CookieHandler.setDefault(new java.net.CookieManager());

        createWebCookie(context);

        clear();
    }

    public static void setWebview(String url) {
        android.webkit.CookieManager webCookieManager = android.webkit.CookieManager.getInstance();
        java.net.CookieManager cookieManager = (java.net.CookieManager) CookieHandler.getDefault();

        CookieStore cookieStore = cookieManager.getCookieStore();
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        List<HttpCookie> cookieList = cookieStore.get(uri);
        for (HttpCookie cookie : cookieList) {
            String setCookie = cookie.toString() + "; domain=" + cookie.getDomain() + "; path=" + cookie.getPath();
            webCookieManager.setCookie(url, setCookie);
        }

        syncWebCookie(webCookieManager);
    }

    public static void clear() {
        android.webkit.CookieManager webCookieManager = android.webkit.CookieManager.getInstance();
        java.net.CookieManager cookieManager = (java.net.CookieManager) CookieHandler.getDefault();

        CookieStore cookieStore = cookieManager.getCookieStore();
        cookieStore.removeAll();

        removeWebCookie(webCookieManager);
        syncWebCookie(webCookieManager);
    }

    @SuppressWarnings("deprecation")
    private static void createWebCookie(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            android.webkit.CookieSyncManager.createInstance(context);
        }
    }

    @SuppressWarnings("deprecation")
    private static void removeWebCookie(android.webkit.CookieManager webCookieManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webCookieManager.removeAllCookies(null);
        } else {
            webCookieManager.removeAllCookie();
        }
    }

    @SuppressWarnings("deprecation")
    private static void syncWebCookie(android.webkit.CookieManager webCookieManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webCookieManager.flush();
        } else {
            android.webkit.CookieSyncManager.getInstance().sync();
        }
    }
}
