package com.xiaofengzi.xfzzone.dto.home;

import com.xiaofengzi.xfzzone.db.domain.UserPhoto;

public class PhotoResDTO extends UserPhoto {

    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
