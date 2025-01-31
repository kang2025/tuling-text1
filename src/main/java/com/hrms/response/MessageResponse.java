package com.hrms.response;

/**
 * MessageResponse类用于封装消息响应
 * 它主要用于存储和传输系统消息或API响应消息
 */
public class MessageResponse {
    /**
     * 消息内容
     */
    private String message;

    /**
     * 构造函数，用于初始化MessageResponse对象
     *
     * @param message 响应消息内容
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * 获取消息内容
     *
     * @return 消息内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置消息内容
     *
     * @param message 要设置的消息内容
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
