package com.lzh.conf.security.model;

/**
 * Token Scope 用于区分是否为Refresh Token
 *
 * @author htian
 */
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return this.name();
    }
}
