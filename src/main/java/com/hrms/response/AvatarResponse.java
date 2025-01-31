/**
 * 包含 AvatarResponse 类，用于封装头像响应信息。  
 */
package com.hrms.response;

/**
 * AvatarResponse 类：用于封装头像 URL 的响应信息。  
 * 主要用于不同层或系统之间的数据传输。  
 */
public class AvatarResponse {
    /**
     * 存储头像的 URL。  
     */
    private String avatarUrl;

    /**
     * 构造函数，用于初始化带有头像 URL 的 AvatarResponse 对象。  
     *
     * @param avatarUrl 头像的 URL。  
     */
    public AvatarResponse(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取头像的 URL。  
     *
     * @return 返回头像的 URL。  
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置头像的 URL。  
     *
     * @param avatarUrl 要设置的头像 URL。  
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}