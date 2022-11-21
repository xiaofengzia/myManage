package com.xiaofengzi.xfzzone.config.storage.config;

import com.xiaofengzi.xfzzone.config.storage.LocalStorage;
import com.xiaofengzi.xfzzone.service.impl.StorageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {

    private final StorageProperties properties;

    public StorageAutoConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public StorageService storageService() {
        StorageService storageService = new StorageService();
        String active = this.properties.getActive();
        storageService.setActive(active);
        storageService.setStorage(localStorage());

        return storageService;
    }

    @Bean
    public LocalStorage localStorage() {
        LocalStorage localStorage = new LocalStorage();
        StorageProperties.Local local = this.properties.getLocal();
        localStorage.setAddress(local.getAddress());
        localStorage.setStoragePath(local.getStoragePath());
        return localStorage;
    }

}
