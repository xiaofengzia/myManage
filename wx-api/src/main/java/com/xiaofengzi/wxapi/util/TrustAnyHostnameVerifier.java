package com.xiaofengzi.wxapi.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class TrustAnyHostnameVerifier implements HostnameVerifier {

	public boolean verify(String arg0, SSLSession arg1) {
		return true;
	}

}
