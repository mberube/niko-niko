package com.pyxis.nikoniko.system.web.serverdriver;


public interface ServerDriverFactory {

    ServerDriver newServerDriver() throws Exception;

    void disposeServerDriver(ServerDriver serverDriver) throws Exception;
}
