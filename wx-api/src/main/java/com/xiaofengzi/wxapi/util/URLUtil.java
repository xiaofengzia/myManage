/**
 * Copyright ®2010 ebiz Co. Ltd.
 * All rights reserved.
 * Package:  com.ebiz.platform.common.api.model
 * FileName: bare_field_name
 * create: 2011-6-22
 * Description:
 */
package com.xiaofengzi.wxapi.util;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @作者: Chen.yulong
 * @创建于: 2011-9-10
 * @概述: 表单提交工具类
 */
@SuppressWarnings("deprecation")
public class URLUtil {

    private static HttpClient httpClient = null;

    private static MultiThreadedHttpConnectionManager connectionManager = null;

    static {
        httpClient = new HttpClient();
        /** add by huodd ,at date 2016-01-25 安全多线程方案如下，如果需要可以用 */
        // 在HttpClient中使用多线程的一个主要原因是可以一次执行多个方法。在执行期间，每一个方法都使用一个HttpConnection实例。
        // 由于在同一时间多个连接只能安全地用于单一线程和方法和有限的资源，我们就必须确保连接分配给正确的方法。
        // 而MultiThreadedHttpConnectionManager完全可以代替我们完成这一项工作，这样我们就不必去考虑多线程带来安全的问题
        connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(4000);// 连接超时
        params.setSoTimeout(300000);// 读取超时
        params.setMaxTotalConnections(200);
         params.setDefaultMaxConnectionsPerHost(100);
        connectionManager.setParams(params);
        httpClient.setHttpConnectionManager(connectionManager);
        // 特别注意，无论执行的方法或是否也不例外被抛出。对于每一个HttpClient.executeMethod方法必须有一个method.releaseConnection()来释放连接
        // connectionManager.shutdown();
    }

    /**
     * 提交数据
     *
     * @return 是否提交成功
     * @throws Exception
     */
    public static String originalPostData(String urlStr, Map<String, String> paramMap) throws Exception {
        UTF8PostMethod procPost = new UTF8PostMethod(urlStr);

        Set<String> keySet = paramMap.keySet();

        for (Object key : keySet) {
            String keyValue = String.valueOf(key);
            String value = (String) paramMap.get(keyValue);
            if (value == null) {
                value = "";
            }
            procPost.addParameter(keyValue, value);
            System.out.println("parameter:" + keyValue + "=" + value);
        }

        httpClient.executeMethod(procPost);

        // 读取内容
        String result = null;
        try {
            result = procPost.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    // modify by wangxulu 2013-05-21 begin

    /**
     * 提交数据
     *
     * @return 是否提交成功
     * @throws Exception
     */
    public static String postData(String urlStr, Map<String, String> paramMap, String charsetType) throws Exception {

        HttpClient _httpClient = new HttpClient();

        UTF8PostMethod procPost = new UTF8PostMethod(urlStr);

        if (paramMap != null) {
            Set<String> keySet = paramMap.keySet();

            for (Object key : keySet) {
                String keyValue = String.valueOf(key);
                String value = (String) paramMap.get(keyValue);
                if (value == null) {
                    value = "";
                }
                procPost.addParameter(keyValue, value);
            }
        }

        _httpClient.executeMethod(procPost);

        // 读取内容
        InputStream resInputStream = null;
        StringBuffer html = new StringBuffer();
        try {
            resInputStream = procPost.getResponseBodyAsStream();
            // 处理内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(resInputStream, charsetType));
            String tempBf = null;
            while ((tempBf = reader.readLine()) != null) {

                html.append(tempBf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resInputStream != null) {
                resInputStream.close();
            }
            if (_httpClient != null) {
                _httpClient.getHttpConnectionManager().closeIdleConnections(0);
            }
        }
        return new String(html.toString().getBytes(charsetType), charsetType);
    }

    /**
     * 提交数据With GBK
     *
     * @return 是否提交成功
     * @throws Exception
     */
    public static String postData(String urlStr, Map<String, String> paramMap) throws Exception {

        return postData(urlStr, paramMap, "GBK");

    }
    // modify by wangxulu 2013-05-21 end

    public static String postJsonData(String urlStr, String xmlData) throws Exception {
        return postJsonData(urlStr, xmlData, "GBK");
    }

    /**
     * 提交数据
     *
     * @return 是否提交成功
     * @throws Exception
     */
    public static String postJsonData(String urlStr, String xmlData, String charsetType) throws Exception {
        UTF8PostMethod post = new UTF8PostMethod(urlStr);
        post.setRequestBody(xmlData);
        httpClient.executeMethod(post);
        return getResponseXml(post.getResponseBodyAsStream());
    }

    public static String getResponseXml(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String demo = "";
        while ((demo = br.readLine()) != null) {
            buffer.append(demo);
        }
        String xml = buffer.toString();
        return xml;
    }

    /**
     * 提交数据With GBK
     *
     * @return 是否提交成功
     * @throws Exception
     */
    public static String postDataWithUTF8(String urlStr, Map<String, String> paramMap) throws Exception {

        return postData(urlStr, paramMap, "UTF-8");

    }

    /**
     * 提交数据
     *
     * @param urlStr
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String originalGetData(String urlStr, Map<String, String> paramMap) throws Exception {
        String param = "";
        int i = 0;
        if (paramMap.size() > 0) {
            // param += "?";
            Set<String> keySet = paramMap.keySet();
            for (Object key : keySet) {
                char c = '&';
                String keyValue = String.valueOf(key);
                String value = (String) paramMap.get(keyValue);
                if (value == null) {
                    value = "";
                }
                if (i == 0) {
                    c = '?';
                }
                param += (c + keyValue + "=" + value);
                i++;
            }
        }

        GetMethod procGet = new GetMethod(urlStr + param);
        httpClient.executeMethod(procGet);

        // 读取内容
        String result = null;
        try {
            result = procGet.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getDataUrl(String urlStr, Map<String, String> paramMap) throws Exception {
        String param = "";
        int i = 0;
        if (paramMap.size() > 0) {
            Set<String> keySet = paramMap.keySet();
            for (Object key : keySet) {
                char c = '&';
                String keyValue = String.valueOf(key);
                String value = (String) paramMap.get(keyValue);
                if (value == null) {
                    value = "";
                }
                if (i == 0) {
                    c = '?';
                }
                param += (c + keyValue + "=" + value);
                i++;
            }
        }

        return urlStr + param;
    }

    /**
     * @名称: 由UnionCode转为String
     * @作者: Chen.yulong
     * @创建于: 2011-8-29
     * @param unicode
     * @return
     * @描述:
     */
    public static String tozhCN(String unicode) {
        StringBuffer gbk = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) { // 注意要从 1 开始，而不是从0开始。第一个是空。
            int data = Integer.parseInt(hex[i], 16); // 将16进制数转换为 10进制的数据。
            gbk.append((char) data); // 强制转换为char类型就是我们的中文字符了。
        }
        // System.out.println("这是从 Unicode编码 转换为 中文字符了: " + gbk.toString());
        return gbk.toString();
    }

    /**
     *
     * @名称: 由String转为UnionCode
     * @作者: Chen.yulong
     * @创建于: 2011-9-9
     * @param zhStr
     * @return
     * @描述:
     */
    public static String toUnicode(String zhStr) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < zhStr.length(); i++) {
            char c = zhStr.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        // System.out.println(unicode.toString());
        return unicode.toString();
    }

    public static String postDataSetTimeoutTime(String urlStr, Map<String, String> paramMap, int timeOut,
                                                int connetTimeOut) throws Exception {
        return postDataSetTimeoutTime(urlStr, paramMap, timeOut, connetTimeOut, null);
    }

    public static String postDataSetTimeoutTime(String urlStr, Map<String, String> paramMap, int timeOut,
                                                int connetTimeOut, String timeOutMsg) throws Exception {
        HttpClient httpClient = new HttpClient();
        UTF8PostMethod procPost = new UTF8PostMethod(urlStr);
        Set keySet = paramMap.keySet();
        for (Object key : keySet) {
            String keyValue = String.valueOf(key);
            String value = (String) paramMap.get(keyValue);
            if (value == null) {
                value = "";
            }
            procPost.addParameter(keyValue, value);
        }

        // 如果传入的timeOut时间，设置超时时间,指的是连接上一个url，获取response的返回等待时间
        if (timeOut != 0) {
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeOut);
        }
        if (connetTimeOut != 0) {
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connetTimeOut);
        }
        try {
            httpClient.executeMethod(procPost);
        } catch (Exception e) {
            if (timeOutMsg == null || timeOutMsg.trim().length() <= 0) {
                throw e;
            } else {
                return timeOutMsg;
            }
        }

        // 读取内容
        InputStream resInputStream = null;
        try {
            resInputStream = procPost.getResponseBodyAsStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 处理内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(resInputStream));
        String tempBf = null;
        StringBuffer html = new StringBuffer();
        while ((tempBf = reader.readLine()) != null) {

            html.append(tempBf);
        }
        return new String(html.toString().getBytes(), "GBK");
    }

    /**
     *
     * @名称: 获得请求的完整地址
     * @作者: chen.yulong
     * @创建于: 2012-11-22
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     * @描述:
     */
    public static String getFullReuqestUrl(String url, Map<String, String> paramMap) throws Exception {
        StringBuffer fullURL = new StringBuffer(url + "?");
        StringBuffer param = new StringBuffer();
        Set keySet = paramMap.keySet();
        for (Object key : keySet) {
            String keyValue = String.valueOf(key);
            String value = (String) paramMap.get(keyValue);
            if (value == null) {
                value = "";
            }
            param.append("&");
            param.append(keyValue);
            param.append("=");
            param.append(value);
        }
        fullURL.append(StringUtils.removeStart(param.toString(), "&"));
        return fullURL.toString();
    }

    /**
     * 提交数据
     *
     * @return 是否提交成功
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String alipayPostData(String urlStr, Map<String, String> paramMap) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod procPost = new PostMethod(urlStr);
        Set keySet = paramMap.keySet();
        for (Object key : keySet) {
            String keyValue = String.valueOf(key);
            String value = (String) paramMap.get(keyValue);
            if (value == null) {
                value = "";
            }
            procPost.addParameter(keyValue, value);
        }
        procPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=UTF-8");
        procPost.addRequestHeader("User-Agent", "Mozilla/4.0");
        httpClient.executeMethod(procPost);
        // 读取内容
        InputStream resInputStream = null;
        try {
            resInputStream = procPost.getResponseBodyAsStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 处理内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(resInputStream));
        String tempBf = null;
        StringBuffer html = new StringBuffer();
        while ((tempBf = reader.readLine()) != null) {

            html.append(tempBf);
        }
        return new String(html.toString().getBytes(), "GBK");

    }

    /**
     * 处理请求参数
     *
     * @名称:
     * @作者: meng.da
     * @创建于: 2011-7-20
     * @param request
     * @return
     * @描述:处理请求，取得表单提交参数列表
     */
    @SuppressWarnings(value = "unchecked")
    public static String handleUrl(HttpServletRequest request) {
        StringBuilder s = new StringBuilder();
        Map map = request.getParameterMap();
        if (map.size() > 0) {
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry element = (Map.Entry) iter.next();
                Object strKey = element.getKey();
                String[] value = (String[]) element.getValue();
                String valueStr = "";
                for (int i = 0; i < value.length; i++) {
                    valueStr += value[i];
                }
                if (!strKey.equals("submit")) {
                    s.append("&" + strKey + "=" + valueStr);
                }
            }
        }
        if (StringUtils.isNotBlank(s.toString())) {
            return request.getRequestURL() + "?" + s.substring(1, s.length());
        } else {
            return request.getRequestURL().toString();
        }
    }

    public static String getCookieValueByName(String name) {

        if (StringUtils.isEmpty(name) || httpClient.getState() == null) {
            return "";
        }
        for (Cookie cookie : httpClient.getState().getCookies()) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }

    /**
     * 跨域请求
     * @author whj
     * @date 2018年9月29日
     * @param urlStr 请求地址
     * @param xmlData 请求json报文
     * @return
     * @throws Exception
     */
    public static String postJsonDataCrossDomain(String urlStr, String xmlData) throws Exception {
        UTF8PostMethod post = new UTF8PostMethod(urlStr);
        post.setRequestBody(xmlData);
        post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        httpClient.executeMethod(post);
        return getResponseXml(post.getResponseBodyAsStream());
    }

    public static String postUploadFile(HttpEntity entity, String url){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        InputStream inputStream = null;
        try {

            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            if (response != null && entity != null) {
                entity = response.getEntity();
                inputStream = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    return body;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static String uploadFile(String url, String filePath) throws Exception {
        String response = "";
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }

        UTF8PostMethod httpPost = new UTF8PostMethod(url);
        try {
            // FilePart：用来上传文件的类,file即要上传的文件
            FilePart fp = new FilePart("file", file);
            Part[] parts = {fp};

            // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, httpPost.getParams());
            httpPost.setRequestEntity(mre);
            // ---------------------------------------------
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 由于要上传的文件可能比较大
            // ,
            // 因此在此设置最大的连接超时时间
            int status = client.executeMethod(httpPost);
            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = httpPost.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                br.close();
                inputStream.close();
                inputStream = null;
                response = stringBuffer.toString();
            } else {
                response = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                /** add by huodd ,at date 2016-01-25 释放链接 */
                httpPost.releaseConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }
    /**
     * 提交数据
     * @return 是否提交成功
     * @throws Exception
     */
    public static String postJsonDataNew(String urlStr, String xmlData, String charsetType) throws Exception {
        UTF8PostMethod post = new UTF8PostMethod(urlStr);
        post.setRequestHeader("Content-type", "application/json");
        post.setRequestBody(xmlData);
        httpClient.executeMethod(post);
        return getResponseXml(post.getResponseBodyAsStream());
    }

}

/**
 * 重写PostMethod以解决UTF-8编码问题
 *
 * @author adun
 */
class UTF8PostMethod extends PostMethod {
    public UTF8PostMethod(String url) {
        super(url);
    }

    @Override
    public String getRequestCharSet() {
        return "UTF-8";
    }

}
