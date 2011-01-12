package com.pyxis.nikoniko.system.web;

import static com.pyxis.nikoniko.system.web.Environment.contextPath;
import static com.pyxis.nikoniko.system.web.Environment.serverHost;
import static com.pyxis.nikoniko.system.web.Environment.serverPort;
import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

import com.pyxis.nikoniko.system.web.page.HomePage;

public final class Routes {

    private static Map<Class<?>, String> urlMappings = new HashMap<Class<?>, String>();

    static {
        urlMappings.put(HomePage.class, "/");
    }

    private Routes() {
    }

    public static String urlFor(Class<?> pageClass) {
        return format("http://%s:%s%s%s", serverHost(), serverPort(), contextPath(), path(pageClass));
    }

    private static String path(Class<?> pageClass) {
        return urlMappings.get(pageClass);
    }
}
