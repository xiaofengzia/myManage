package com.xiaofengzi.xfzzone.controller;

import com.xiaofengzi.xfzzone.db.domain.UserPhoto;
import com.xiaofengzi.xfzzone.db.domain.UserPhotoAlbum;
import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.common.XIAOFENGZI_ZONE;
import com.xiaofengzi.xfzzone.dto.home.PhotoReqDTO;
import com.xiaofengzi.xfzzone.service.interfaces.PhotoService;
import com.xiaofengzi.xfzzone.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = XIAOFENGZI_ZONE.BASE_PATH + "/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping("/insertOrUpdatePhotoAlbum")
    public TransResult insertOrUpdatePhotoAlbum(@RequestBody UserPhotoAlbum userPhotoAlbum){
        TransResult transResult = new TransResult();
        try {
            transResult = photoService.insertOrUpdatePhotoAlbum(userPhotoAlbum);
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }
    @RequestMapping("/selectPhotoAlbum/{accountNum}")
    public TransResult selectPhotoAlbum(@PathVariable String accountNum){
        TransResult transResult = new TransResult();
        try {
            if(StringUtil.isEmpty(accountNum)){
                transResult.failure("参数不全");
                return transResult;
            }
            transResult = photoService.selectPhotoAlbum(accountNum);
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }
    @RequestMapping("/insertPhoto")
    public TransResult insertPhoto(@RequestBody UserPhoto userPhoto){
        TransResult transResult = new TransResult();
        try {
            transResult = photoService.insertPhoto(userPhoto);
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }
    @RequestMapping("/selectPhoto")
    public TransResult selectPhoto(@RequestBody PhotoReqDTO photoReqDTO){
        TransResult transResult = new TransResult();
        try {
            String photoAlbumId = photoReqDTO.getPhotoAlbumId();
            int start = photoReqDTO.getStart();
            int count = photoReqDTO.getCount();
            if(StringUtil.isEmpty(photoAlbumId)){
                transResult.failure("参数不全");
                return transResult;
            }
            transResult = photoService.selectPhoto(photoAlbumId,start,count);
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }
    @RequestMapping("/deletePhoto")
    public TransResult deletePhoto(@RequestBody UserPhoto userPhoto){
        TransResult transResult = new TransResult();
        try {
            transResult = photoService.deletePhoto(userPhoto);
        }catch (Exception e){
            e.printStackTrace();
            transResult.failure();
        }
        return transResult;
    }
}
