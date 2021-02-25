package com.xiaofengzi.xfzzone.controller;

import com.xiaofengzi.xfzzone.db.domain.OssObject;
import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.common.XIAOFENGZI_ZONE;
import com.xiaofengzi.xfzzone.service.impl.StorageService;
import com.xiaofengzi.xfzzone.service.interfaces.WxStorageService;
import com.xiaofengzi.xfzzone.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * 对象存储服务
 */
@RestController
@RequestMapping(XIAOFENGZI_ZONE.BASE_PATH+"/storage")
public class StorageController {
    private final Log logger = LogFactory.getLog(StorageController.class);

    @Autowired
    private StorageService storageService;
    @Autowired
    private WxStorageService wxstorageService;

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String filePath = DateUtil.formatDate(new Date(),"yyyy/MM/dd");
        TransResult tr = new TransResult();
        try{
            Map res = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename,"bucket",filePath);
            tr.success();
            tr.setObject(res);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            tr.exception();
        }

        return tr;
    }

    @PostMapping("/uploadFile")
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String filePath = DateUtil.formatDate(new Date(),"yyyy/MM/dd");
        TransResult tr = new TransResult();
        try{
            Map res = storageService.storeFile(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename,"bucket",filePath);
            tr.success();
            tr.setObject(res);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            tr.exception();
        }

        return tr;
    }

    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/fetch/{key:.+}")
    public ResponseEntity<Resource> fetch(@PathVariable String key) {
        OssObject ossObject = wxstorageService.findByKey(key);
        if (key == null) {
            return ResponseEntity.notFound().build();
        }
        if (key.contains("../")) {
            return ResponseEntity.badRequest().build();
        }
        String type = ossObject.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = storageService.loadAsResource(ossObject.getPath());
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }

    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/download/{key:.+}")
    public ResponseEntity<Resource> download(@PathVariable String key) {
        OssObject ossObject  = wxstorageService.findByKey(key);
        if (key == null) {
            return ResponseEntity.notFound().build();
        }
        if (key.contains("../")) {
            return ResponseEntity.badRequest().build();
        }

        String type = ossObject.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = storageService.loadAsResource(ossObject.getPath());
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(mediaType).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
