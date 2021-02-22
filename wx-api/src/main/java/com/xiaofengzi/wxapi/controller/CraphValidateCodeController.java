package com.xiaofengzi.wxapi.controller;

import com.xiaofengzi.wxapi.controller.BaseController;
import com.xiaofengzi.wxapi.dto.common.ValidateCode;
import com.xiaofengzi.wxapi.dto.common.XIAOFENGZI_ZONE;
import com.xiaofengzi.wxapi.service.interfaces.CraphValidateCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @name: CraphValidateCodeController
 * @description: 图形验证码controller
 * @author: D.Z
 * @created: 2018-09-28 20:44
 **/
@RestController
@RequestMapping(value = XIAOFENGZI_ZONE.BASE_PATH + "/graphValidateCode")
public class CraphValidateCodeController extends BaseController {

    @Resource
    private CraphValidateCodeService craphValidateCodeService;

    @GetMapping(value = "/{token}/{tm}")
    public void graphValidateCode(HttpServletResponse response, @PathVariable String token, @PathVariable String tm) throws IOException {
        logger.info("发送图形验证码开始");
        long start = System.currentTimeMillis();
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ValidateCode vCode = craphValidateCodeService.generated(token);

        vCode.write(response.getOutputStream());
        logger.info("生成验证码耗时-----:" + (System.currentTimeMillis() - start) + "ms");
        logger.info("发送图形验证码开始");
        return ;
    }

}
