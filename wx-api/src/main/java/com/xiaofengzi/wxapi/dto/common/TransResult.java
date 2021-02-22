package com.xiaofengzi.wxapi.dto.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * 交易结果实体类
 * 
 * @author fusq
 * @date 2010/07/12
 * @version 1.0
 */
@XStreamAlias("transResult")
public class TransResult<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8825195237255216250L;

	/**
     * 需要返回的业务对象
     */
    private T object;

    /**
     * 交易返回码
     * 3:token无效
     * 2:交易处理中
     * 1:交易处理成功
     * 0:交易处理失败
     * -1:交易处理发生内部异常
     */
    private String resultCode;

    private String resultInfo;
    
    /**
     * 和第三方服务交互返回交易描述
     */
    private String resultInfoDesc = "";
    /**
     * 提交返回订单在redis的状态  支付中判断状态   
     * 防止重复提交
     * @return
     */
    private String cacheStatus;
    public String getCacheStatus() {
		return cacheStatus;
	}

	public void setCacheStatus(String cacheStatus) {
		this.cacheStatus = cacheStatus;
	}

	public String getResultInfoDesc() {
        return resultInfoDesc;
    }

    public void setResultInfoDesc(String resultInfoDesc) {
        this.resultInfoDesc = resultInfoDesc;
    }

    /**
     * 执行期间发生异常,封装异常消息返回给调用端
     * 
     * @param ex
     */
    public void exception(Exception ex) {
        this.setResultCode("-1");
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        this.setResultInfo(sw.toString());
    }

    /**
     * 执行交易发生异常
     */
    public void exception() {
        this.setResultCode("-1");
        this.setResultInfo("交易处理异常");
    }

    /**
     * 执行交易失败
     * 并发发生异常情况，失败指发生逻辑校验未通过的情况
     */
    public void failure() {
        this.failure("交易处理失败");
        this.setResultCode("0");
    }

    /**
     * 执行交易失败
     * 并发发生异常情况，失败指发生逻辑校验未通过的情况
     */
    public void failure(String errorMsg) {
        this.setResultCode("0");
        this.setResultInfo(errorMsg);
    }

    /**
     * 判断服务是否发生异常
     * 
     * @return true 发生异常
     */
    public boolean hasException() {
        if (this.resultCode.equals("-1")) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 判断交易是否成功
     * 
     * @return true 交易成功 , false 交易失败(非异常)
     */
    public boolean isSuccess() {
        if ("1".equals(resultCode)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 执行交易成功
     */
    public void success() {
        this.setResultCode("1");
        this.setResultInfo("交易处理成功");
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }
    
	/**
     * 执行期间发生异常,封装异常消息返回给调用端
     * 
     * @param ex
     */
    public void addException(Exception ex) {
        this.setResultCode("-1");
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        this.setResultInfoDesc(sw.toString());
    }
}
