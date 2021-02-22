package com.xiaofengzi.xfzzone.dto.login;

public class JumpDTO {

    /**
     * 跳转类型 app:回调app方法，url：直接跳转链接
     */
    private String type;

    /**
     * 跳转标识  分享完成- 01
     *        投保完成-02
     */
    private String flag;

    /**
     * 跳转链接
     */
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
