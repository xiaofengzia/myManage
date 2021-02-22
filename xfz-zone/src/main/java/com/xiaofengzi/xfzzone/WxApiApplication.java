package com.xiaofengzi.xfzzone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.xiaofengzi.xfzzone.db.dao.*"})
public class WxApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxApiApplication.class, args);
	}

}
