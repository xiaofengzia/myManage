package com.xiaofengzi.xfzzone.db.bo.interfaces;

import com.xiaofengzi.xfzzone.db.domain.UserPhoto;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoAlbum;

import java.util.List;

public interface PhotoBO {

    public int insertOrUpdatePhotoAlbum(UserPhotoAlbum userPhotoAlbum);

    public List<UserPhotoAlbum> selectPhotoAlbum(String accountNum);

    public int insertPhoto(UserPhoto userPhoto);

    public List<UserPhoto> selectPhoto(String photoAlbumId,int start,int count);

    public int deletePhoto(UserPhoto userPhoto);
}
