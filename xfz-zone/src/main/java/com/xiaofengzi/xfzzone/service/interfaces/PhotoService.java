package com.xiaofengzi.xfzzone.service.interfaces;

import com.xiaofengzi.xfzzone.db.domain.UserPhoto;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoAlbum;
import com.xiaofengzi.xfzzone.dto.common.TransResult;

import java.util.List;

public interface PhotoService {

    public TransResult insertOrUpdatePhotoAlbum(UserPhotoAlbum userPhotoAlbum);

    public TransResult selectPhotoAlbum(String accountNum);

    public TransResult insertPhoto(UserPhoto userPhoto);

    public TransResult selectPhoto(String photoAlbumId,int start,int count);

    public TransResult deletePhoto(UserPhoto userPhoto);
}
