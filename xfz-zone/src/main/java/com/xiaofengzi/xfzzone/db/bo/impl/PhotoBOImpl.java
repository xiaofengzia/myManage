package com.xiaofengzi.xfzzone.db.bo.impl;

import com.github.pagehelper.PageHelper;
import com.xiaofengzi.xfzzone.db.bo.interfaces.PhotoBO;
import com.xiaofengzi.xfzzone.db.dao.generated.UserPhotoAlbumMapper;
import com.xiaofengzi.xfzzone.db.dao.generated.UserPhotoMapper;
import com.xiaofengzi.xfzzone.db.domain.UserPhoto;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoAlbum;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoAlbumExample;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoExample;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class PhotoBOImpl implements PhotoBO {

    @Resource
    private UserPhotoMapper userPhotoMapper;
    @Resource
    private UserPhotoAlbumMapper userPhotoAlbumMapper;

    @Override
    public int insertOrUpdatePhotoAlbum(UserPhotoAlbum userPhotoAlbum) {
        if(null == userPhotoAlbum.getId()){
            userPhotoAlbum.setAddTime(new Date());
            userPhotoAlbum.setModifiedTime(new Date());
            userPhotoAlbum.setIsDelete(0);
            return userPhotoAlbumMapper.insertSelective(userPhotoAlbum);
        }else{
            userPhotoAlbum.setModifiedTime(new Date());
            UserPhotoAlbumExample userPhotoAlbumExample = new UserPhotoAlbumExample();
            userPhotoAlbumExample.createCriteria().andIdEqualTo(userPhotoAlbum.getId());
            return userPhotoAlbumMapper.updateByExampleSelective(userPhotoAlbum,userPhotoAlbumExample);
        }
    }

    @Override
    public List<UserPhotoAlbum> selectPhotoAlbum(String accountNum) {
        UserPhotoAlbumExample userPhotoAlbumExample = new UserPhotoAlbumExample();
        userPhotoAlbumExample.createCriteria().andAccountNumEqualTo(accountNum).andIsDeleteEqualTo(0);
        List<UserPhotoAlbum> list = userPhotoAlbumMapper.selectByExample(userPhotoAlbumExample);
        return list;
    }

    @Override
    public int insertPhoto(UserPhoto userPhoto) {
        userPhoto.setModifiedTime(new Date());
        userPhoto.setModifiedTime(new Date());
        userPhoto.setIsDelete(0);
        return userPhotoMapper.insertSelective(userPhoto);
    }

    @Override
    public List<UserPhoto> selectPhoto(String photoAlbumId,int start,int count) {
        UserPhotoExample userPhotoExample = new UserPhotoExample();
        userPhotoExample.createCriteria().andIsDeleteEqualTo(0).andTypeEqualTo(photoAlbumId);
        PageHelper.startPage(start, count);
        return userPhotoMapper.selectByExample(userPhotoExample);
    }

    @Override
    public int deletePhoto(UserPhoto userPhoto) {
        userPhoto.setIsDelete(1);
        userPhoto.setModifiedTime(new Date());
        UserPhotoExample userPhotoExample = new UserPhotoExample();
        userPhotoExample.createCriteria().andIdEqualTo(userPhoto.getId());
        return userPhotoMapper.updateByExampleSelective(userPhoto,userPhotoExample);
    }
}
