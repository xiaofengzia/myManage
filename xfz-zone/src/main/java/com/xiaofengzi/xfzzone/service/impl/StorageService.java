package com.xiaofengzi.xfzzone.service.impl;


import com.xiaofengzi.xfzzone.config.storage.LocalStorage;
import com.xiaofengzi.xfzzone.db.dao.generated.OssObjectMapper;
import com.xiaofengzi.xfzzone.db.domain.OssObject;
import com.xiaofengzi.xfzzone.util.TransNoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 */
@Service
public class StorageService {
    private String active;
    private LocalStorage storage;
    @Resource
    private OssObjectMapper ossObjectMapper;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public LocalStorage getStorage() {
        return storage;
    }

    public void setStorage(LocalStorage storage) {
        this.storage = storage;
    }

    /**
     * 存储一个文件对象
     * 文件名更改为 uuid
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      原文件名
     * @Param ossBucketName  空间名称
     * @Param filePath 文件路径
     */
    public Map store(InputStream inputStream, long contentLength, String contentType, String fileName,String ossBucketName,String filePath) {
        Map<String,String>  res = new HashMap<>();
        //1. 生成流水号
        String sn = TransNoUtil.getTransNo(ossBucketName);
        //2.生成文件名
         fileName = generateFilename(fileName);
        //3.文件路径
        String path = filePath +"/"+ fileName;
        //4.保存文件
        Path path1 = Paths.get(storage.getStoragePath()+"/"+filePath);
        storage.createDirectories(path1);
        storage.store(inputStream, fileName,path1);
        //5.生成链接
        String url = generateUrl(fileName);


        OssObject ossObject = new OssObject();
        ossObject.setSn(sn);
        ossObject.setPath(path);
        ossObject.setName(fileName);
        ossObject.setSize(Integer.parseInt(String.valueOf(contentLength)));
        ossObject.setSaveType("archive");
        ossObject.setType(contentType);
        ossObject.setUrl(url);
        ossObject.setAcl("02");
        ossObject.setAddTime(new Date());
        ossObject.setNum(0);
        ossObjectMapper.insertSelective(ossObject);

        res.put("url",url);
        res.put("id",ossObject.getId().toString());
        return res;
    }

    /**
     * 存储一个文件对象
     * 文件名不更改
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      原文件名
     * @Param ossBucketName  空间名称
     * @Param filePath 文件路径
     */
    public Map storeFile(InputStream inputStream, long contentLength, String contentType, String fileName,String ossBucketName,String filePath) {
        Map<String,String>  res = new HashMap<>();
        //1. 生成流水号
        String sn = TransNoUtil.getTransNo(ossBucketName);
        //2.生成文件名

        //3.文件路径
        String path = filePath +"/"+ fileName;
        //4.保存文件
        Path path1 = Paths.get(storage.getStoragePath()+"/"+filePath);
        storage.createDirectories(path1);
        storage.store(inputStream, fileName,path1);
        //5.生成链接
        String url = generateUrl(fileName);


        OssObject ossObject = new OssObject();
        ossObject.setSn(sn);
        ossObject.setPath(path);
        ossObject.setName(fileName);
        ossObject.setSize(Integer.parseInt(String.valueOf(contentLength)));
        ossObject.setSaveType("archive");
        ossObject.setType(contentType);
        ossObject.setUrl(url);
        ossObject.setAcl("02");
        ossObject.setAddTime(new Date());
        ossObject.setNum(0);
        ossObjectMapper.insertSelective(ossObject);

        res.put("url",url);
        res.put("id",ossObject.getId().toString());
        return res;
    }

    /**
     * 生成文件名
     * @param originalFilename
     * @return
     */
    private String generateFilename(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);
        return UUID.randomUUID()+suffix;
    }

    public Stream<Path> loadAll() {
        return storage.loadAll();
    }

    public Path load(String keyName) {
        return storage.load(keyName);
    }

    public org.springframework.core.io.Resource loadAsResource(String keyName) {
        return storage.loadAsResource(keyName);
    }

    public void delete(String keyName) {
        storage.delete(keyName);
    }

    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }
}
