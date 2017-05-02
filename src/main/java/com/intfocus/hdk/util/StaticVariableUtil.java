package com.intfocus.hdk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class StaticVariableUtil {
	public static  String APPID ;
	public static  String SECRET ;
	public static  String SCAN_QRCODE_URL ;
	public static String CHECK_STORE_URL ;
	public static String SALE_DATA_UPLOAD_URL ;
	public static String BASE_URL ;
	
   static {
	   Properties prop =  new  Properties();
       try  {    
//           InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/weixinInfo.properties" ));    
           InputStream in = StaticVariableUtil.class.getResourceAsStream("/weixinInfo.properties");    
           prop.load(in);    
           APPID = prop.getProperty( "appid" ).trim();    
           SECRET = prop.getProperty( "secret" ).trim();  
           SCAN_QRCODE_URL = prop.getProperty( "scanQRCodeURL" ).trim(); 
           CHECK_STORE_URL = prop.getProperty( "checkStoreURL" ).trim(); 
           BASE_URL = prop.getProperty("baseURL" ); 
           SALE_DATA_UPLOAD_URL = prop.getProperty( "saleDataUploadURL" ).trim(); 
           
       }  catch  (IOException e) {    
           e.printStackTrace();    
       }    
   }
}
