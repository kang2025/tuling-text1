/**
 * JwtResponse类用于封装JWT（JSON Web Token）相关的响应信息
 * 它的主要作用是将生成的JWT令牌传递给客户端
 */
package com.hrms.response;

public class JwtResponse {
    // token变量用于存储JWT令牌
    private String token;

    /**
     * 构造函数，用于初始化JwtResponse对象
     * @param token JWT令牌，用于客户端后续的请求认证
     */
    public JwtResponse(String token) {
        this.token = token;
    }

    /**
     * 获取JWT令牌
     * @return 返回当前对象存储的JWT令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置JWT令牌
     * @param token 新的JWT令牌，用于替换旧的令牌
     */
    public void setToken(String token) {
        this.token = token;
    }
}
