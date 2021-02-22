package com.xiaofengzi.wxapi.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * 取得远程服务器文件的工具类
 * 
 * @author jinhao
 *
 */
public class HttpClientUtil {
	 final static Log logger = LogFactory.getLog(HttpClientUtil.class);
	
	/**
	 * 根据地址获取 流 二进制数据
	 * @param remoteAddress
	 * @return
	 * @throws Exception
	 */
	public static byte[] getWeixinRomoteFileStream(String remoteAddress) throws Exception{
		
		HttpClient httpClient = new HttpClient();
//		链接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);  
//		读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		byte[] result = null;
		GetMethod method = new GetMethod(remoteAddress);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		logger.info("____getWeixinRomoteFileStream__GetMethod="+method+"__设置超时时间m="+5000);
		
		long begin = System.currentTimeMillis();
		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(method);
			long end = System.currentTimeMillis();
			logger.info("____getWeixinRomoteFileStream__httpClient.executeMethod="+statusCode+"____请求耗时m="+(end -begin));
			
			if (statusCode == HttpStatus.SC_OK) {
					//读取内容
					result = method.getResponseBody();
					logger.info("获取文件流结束，用时：" + (begin - end)/1000);
			}
		} catch (Exception e) {
			logger.error("____getWeixinRomoteFileStream__error=",e);
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			throw e;
		} finally {
			//释放连接
			method.releaseConnection();
			logger.info("____getWeixinRomoteFileStream__连接被释放="+method);
			
			long end = System.currentTimeMillis();
			logger.info("____getWeixinRomoteFileStream__httpClient.executeMethod____请求耗时m="+(begin - end));
		}
		return result;
	}
	
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileEndWitsh(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		
		return fileEndWitsh;
	}
	
	public static String getWeixinRomoteFile(String localAddress, 
			String localFileName, String remoteAddress) throws Exception{
		
		/**
		 * 校验文件夹是否存在
		 */
		File path = new File(localAddress);
		if( !path.exists() ) {
			path.mkdirs();
		}
        // 获取问价扩展名
        String fileExt = ".jpg";//HttpClientUtil.getFileEndWitsh(method.getResponseHeader("Content-Type").getValue());
        localFileName = localFileName + fileExt;
        String localFilePath = localAddress + localFileName;
        File file = new File(localFilePath);
		if(file.exists()) {
		    logger.info("____getWeixinRomoteFile__保存文件["+localFilePath+"]，判断文件已存在，不需要进行下载");
		    return localFileName;
		}
        logger.info("____getWeixinRomoteFile__保存文件["+localFilePath+"]，判断文件未存在，需要下载");
		HttpClient httpClient = new HttpClient();
		/**add by huodd ,at date 2016-01-26;增加超时限制*/
//		链接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);  
//		读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		
		GetMethod method = new GetMethod(remoteAddress);
	    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		logger.info("____getWeixinRomoteFile__GetMethod="+method+"__设置超时时间m="+5000);
		OutputStream serverout = null;
		long begin = System.currentTimeMillis();
		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(method);
			long end = System.currentTimeMillis();
			logger.info("____getWeixinRomoteFile__httpClient.executeMethod="+statusCode+"____请求耗时m="+(end -begin));
			
			if (statusCode == HttpStatus.SC_OK) {
				logger.info("Content-Type为:"+method.getResponseHeader("Content-Type").getValue());
				String fileEnd = HttpClientUtil.getFileEndWitsh(method.getResponseHeader("Content-Type").getValue());
				if(StringUtils.isNotEmpty(fileEnd) && fileEnd.equals(".jpg")) {
					//读取内容
					byte[] responseBody = method.getResponseBody();
					serverout = new FileOutputStream(localFilePath);
					serverout.write(responseBody);
					serverout.flush();
					serverout.close();
					logger.info("____getWeixinRomoteFile__保存文件成功=" + localFileName);
				}else {
					logger.error("____getWeixinRomoteFile__返回类型不对，类型为：" + fileEnd);
					throw new Exception("返回类型不对，" + new String(method.getResponseBody()));
				}
			}
		} catch (Exception e) {
			logger.error("____getWeixinRomoteFile__error=",e);
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			throw e;
		} finally {
			if (serverout != null) {
				serverout.close();
			}
			//释放连接
			method.releaseConnection();
			if(httpClient.getHttpConnectionManager() != null) {
			    httpClient.getHttpConnectionManager().closeIdleConnections(0);
			}
			logger.info("____getWeixinRomoteFile__连接被释放="+method);
			
			long end = System.currentTimeMillis();
			logger.info("____getWeixinRomoteFile__httpClient.executeMethod____请求耗时m="+(begin - end));
		}
		
		return localFileName;
	}
	
	public static String getWeixinRomoteByte(String localAddress, 
            String localFileName, byte[] responseBody) throws Exception{
        
        /**
         * 校验文件夹是否存在
         */
        File path = new File(localAddress);
        if( !path.exists() ) {
            path.mkdirs();
        }
        // 获取问价扩展名
        String fileExt = ".jpg";//HttpClientUtil.getFileEndWitsh(method.getResponseHeader("Content-Type").getValue());
        localFileName = localFileName + fileExt;
        String localFilePath = localAddress + localFileName;
        File file = new File(localFilePath);
        if(file.exists()) {
            logger.info("____getWeixinRomoteByte__保存文件["+localFilePath+"]，判断文件已存在，不需要进行下载");
            return localFilePath;
        }
        logger.info("____getWeixinRomoteByte__保存文件["+localFilePath+"]，判断文件未存在，需要下载");

        OutputStream serverout = null;
        long begin = System.currentTimeMillis();
        try {
            //读取内容
            serverout = new FileOutputStream(localFilePath);
            serverout.write(responseBody);
            serverout.flush();
            serverout.close();
            logger.info("____getWeixinRomoteByte__保存文件成功=" + localFileName);

            
        } catch (Exception e) {
            logger.error("____getWeixinRomoteByte__error=",e);
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            throw e;
        } finally {
            if (serverout != null) {
                serverout.close();
            }
            
            long end = System.currentTimeMillis();
            logger.info("____getWeixinRomoteByte__httpClient.executeMethod____请求耗时m="+(begin - end));
        }
        
        return localFilePath;
    }
}
