package com.xiaofengzi.xfzzone.service.impl;

import com.xiaofengzi.xfzzone.db.bo.interfaces.PhotoBO;
import com.xiaofengzi.xfzzone.db.bo.interfaces.WxStorageBO;
import com.xiaofengzi.xfzzone.db.domain.OssObject;
import com.xiaofengzi.xfzzone.db.domain.UserPhoto;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoAlbum;
import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.home.PhotoResDTO;
import com.xiaofengzi.xfzzone.service.interfaces.PhotoService;
import com.xiaofengzi.xfzzone.util.BeanUtil;
import com.xiaofengzi.xfzzone.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoBO photoBO;
    @Autowired
    private WxStorageBO wxStorageBO;

    @Override
    public TransResult insertOrUpdatePhotoAlbum(UserPhotoAlbum userPhotoAlbum) {
        TransResult transResult = new TransResult();
        try{
            int result = photoBO.insertOrUpdatePhotoAlbum(userPhotoAlbum);
            if(result > 0){
                transResult.success();
            }else {
                transResult.failure();
            }
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }

    @Override
    public TransResult selectPhotoAlbum(String accountNum) {
        TransResult transResult = new TransResult();
        try{
            List<UserPhotoAlbum> list = photoBO.selectPhotoAlbum(accountNum);
            if(CollectionUtil.isNotEmpty(list)){
                transResult.success();
                transResult.setObject(list);
            }else{
                transResult.success();
                transResult.setObject(new ArrayList<>());
            }
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }

    @Override
    public TransResult insertPhoto(UserPhoto userPhoto) {
        TransResult transResult = new TransResult();
        try {
            int result = photoBO.insertPhoto(userPhoto);
            if(result > 0){
                transResult.success();
            }else {
                transResult.failure();
            }
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }

    @Override
    public TransResult selectPhoto(String photoAlbumId,int start,int count) {
        TransResult transResult = new TransResult();
        try {
            List<PhotoResDTO> resList = new ArrayList<>();
            List<UserPhoto> list = photoBO.selectPhoto(photoAlbumId,start,count);
            transResult.success();
            if(CollectionUtil.isNotEmpty(list)){
                for(UserPhoto item : list){
                    PhotoResDTO photoResDTO = new PhotoResDTO();
                    OssObject ossObject = new OssObject();
                    ossObject.setId(item.getId());
                    OssObject storage = wxStorageBO.findByExample(ossObject);
                    BeanUtil.copyBeanNoNullCover(item,photoResDTO);
                    photoResDTO.setUrl(storage.getUrl());
                    resList.add(photoResDTO);
                }
            }
            transResult.setObject(resList);
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }

    @Override
    public TransResult deletePhoto(UserPhoto userPhoto) {
        TransResult transResult = new TransResult();
        try {
            int result = photoBO.deletePhoto(userPhoto);
            if(result > 0 ){
                transResult.success();
            }else{
                transResult.failure();
            }
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }
}
