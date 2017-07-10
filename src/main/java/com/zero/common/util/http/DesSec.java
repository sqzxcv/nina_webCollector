package com.zero.common.util.http;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesSec {
    public static byte[] desCrypto(byte[] datasource, String password) {            
        try{
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        //现在，获取数据并加密
        //正式执行加密操作
        return cipher.doFinal(datasource);
        }catch(Throwable e){
                e.printStackTrace();
        }
        return null;
    }
    
    public static String des(String str){
    	//待加密内容  
    	
    	//密码，长度要是8的倍数  
    	String password = "12345678";  
    	byte[] result = desCrypto(str.getBytes(),password);  
    	return new String(result);

    }
    
}
