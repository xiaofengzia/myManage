package com.xiaofengzi.xfzzone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.xiaofengzi.xfzzone.db.dao.*"})
public class XfzApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(XfzApiApplication.class, args);
	}

}
