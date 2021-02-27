package com.xiaofengzi.xfzzone.dto.home;

import com.xiaofengzi.xfzzone.db.domain.UserPhotoAlbum;

public class PhotoAlbumResDTO extends UserPhotoAlbum {
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
