package com.hdfc.capstone.EMS;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystem1Application {
	
//	public static void configureHttps() throws NoSuchAlgorithmException, KeyManagementException {
//	    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//	        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//	            return null;
//	        }
//	        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//	        }
//	        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//	        }
//	    } };
//	    SSLContext sc = SSLContext.getInstance("SSL");
//	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
//	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//	}


	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystem1Application.class, args);
	}

}
