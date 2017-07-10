package com.zero.common.util.http;


import javax.net.ssl.X509TrustManager;

/**
 * 证书信任管理(https请求）
 * @author Administrator
 *
 */
public class MyX509TrustManager implements X509TrustManager {

	 public java.security.cert.X509Certificate[] getAcceptedIssuers(){
     	return null;
     }
     public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType){
     }

     public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){

     }

}
