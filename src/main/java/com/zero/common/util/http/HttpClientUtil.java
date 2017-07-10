package com.zero.common.util.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static String doGet(String url){
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		if(url.indexOf("https")>=0){  //访问https
			enableSSL(httpclient);
		}
		HttpGet httpGet = new HttpGet(url);
		try{
			httpGet.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8"); 
			HttpConnectionParams.setConnectionTimeout(httpGet.getParams(), 5000);  //链接超时
			HttpConnectionParams.setSoTimeout(httpGet.getParams(), 5000); //链接后获取数据超时
			//取得HttpResponse  
			HttpResponse httpResponse = httpclient.execute(httpGet);
			result = EntityUtils.toString(httpResponse.getEntity());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//httpGet.releaseConnection();
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	/**
	 * 
	 * @param url
	 * @param map 传递参数
	 * @param body 内容
	 * @return
	 */
	public static String doPost(String url ,Map<String,String> map,String body){
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		if(url.indexOf("https")>=0){
			enableSSL(httpclient);   //绕过https证书验证
		}
		HttpPost httppost = new HttpPost(url);
		
		try{
			HttpConnectionParams.setConnectionTimeout(httppost.getParams(), 5000);  //链接超时
			HttpConnectionParams.setSoTimeout(httppost.getParams(), 5000);

			if(map !=null){
				List<NameValuePair> params = new ArrayList<NameValuePair>(); //保存传递参数
				//设置字符集
				HttpEntity httpentity = new UrlEncodedFormEntity(params, "UTF-8");
				for(String key : map.keySet()){
					params.add(new BasicNameValuePair(key,map.get(key)));
				}
				httppost.setEntity(httpentity);
			}	
			
			if(body!=null && !body.equals("")){
				StringEntity entity = new StringEntity(body,"UTF-8");
				//entity.setContentType("text/html;charset=UTF-8");
				//entity.setContentEncoding("UTF-8");
				httppost.setEntity(entity);
			}
			
			HttpResponse httpresponse = httpclient.execute(httppost);
			result = EntityUtils.toString(httpresponse.getEntity());
		}catch(Exception e){
			 e.printStackTrace();
		}finally{
			//httppost.releaseConnection();
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	
	
	
	
	
	/** 
     * 重写验证方法，取消检测ssl 
     */  
    private static MyX509TrustManager  truseAllManager = new MyX509TrustManager();
    /** 
     * 访问https的网站 
     * @param httpclient 
     */  
    private static void enableSSL(HttpClient httpclient){  
        //调用ssl  
	     try {  
            SSLContext sslcontext = SSLContext.getInstance("TLS");  
            sslcontext.init(null, new TrustManager[] { truseAllManager }, null);  
            
			SSLSocketFactory sf = new SSLSocketFactory(sslcontext);  
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
            Scheme https = new Scheme("https", sf, 443);  
            httpclient.getConnectionManager().getSchemeRegistry().register(https);  
	     }catch (Exception e) {  
	        e.printStackTrace();  
	     }  
    }  
    
    
    public static void main(String args){
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("account", "5302");
    	map.put("password", "X65hxcZXOhY=");
    	map.put("sms_content", "内容");
    	map.put("phones", "18256982997");
    	String result = doPost("http://112.122.11.191 /api.ered?reqCode= smsSendLess",map,"");
    	System.out.println("-----result--"+result);
    }
}
