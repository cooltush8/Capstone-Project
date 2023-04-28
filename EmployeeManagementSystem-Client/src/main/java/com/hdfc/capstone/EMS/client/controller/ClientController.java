//package com.hdfc.capstone.EMS.client.controller;
//
//import java.security.spec.KeySpec;
//import java.util.Base64;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.hdfc.capstone.EMS.client.entity.Employee;
//
//
//@RestController
//@RequestMapping("/client")
//public class ClientController {
//	
//	
//	@Autowired
//	RestTemplate restTemplate;
//	String baseUrl = "https://localhost:8685/employee/" ;
//	
//	private static final String SECRET_KEY = "SecretKey";
//	private static final String SALTVALUE = "tushar" ;
//	 
////	 @GetMapping("/{employeeId}")
////	    public ResponseEntity<?> getEmployeeById(@PathVariable int employeeId) {
////	        String url = "http://localhost:8443/employee/" + employeeId;
////	        ResponseEntity<Employee> response = restTemplate.getForEntity(url, Employee.class);
////	        if (response.getStatusCode() == HttpStatus.OK) {
////	            return ResponseEntity.ok(response.getBody());
////	        } else {
////	            return ResponseEntity
////	                    .status(response.getStatusCode())
////	                    .body(response.getBody());
////	        }
////	    }
//	
//	@GetMapping("/employeeId/{employeeID}")
//	public Employee getByEmpID(@PathVariable int employeeID)throws Exception{
//		
//		Employee employee = restTemplate.getForObject(baseUrl + employeeID , Employee.class);
//		String decrypted = ClientController.decrypt(employee.getDateOfBirth());
//		System.out.print(decrypted);
//		employee.setDateOfBirth(decrypted);
//		return employee;
//		
//	}
//	
//	public static String decrypt(String strToDecrypt) throws Exception {
//		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//		IvParameterSpec ivspec = new IvParameterSpec(iv);
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//		KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);
//		SecretKey tmp = factory.generateSecret(spec);
//		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
//		return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//	}
//
//	
//	
//
//}





package com.hdfc.capstone.EMS.client.controller;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hdfc.capstone.EMS.client.entity.Employee;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;
    
    private static final String SECRET_KEY = "SecretKey";
    private static final String SALT_VALUE = "tushar";
    private static final byte[] IV = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final String KEY_SPEC_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 65536;
    private static final int KEY_SIZE = 256;

    @GetMapping("/employeeId/{employeeID}")
    public Employee getByEmpID(@PathVariable int employeeID) throws Exception {

        Employee employee = restTemplate.getForObject("https://localhost:8685/employee/" + employeeID, Employee.class);
        String decryptedDateOfBirth = decrypt(employee.getDateOfBirth());
        employee.setDateOfBirth(decryptedDateOfBirth);
        return employee;

    }

    private static String decrypt(String strToDecrypt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_SPEC_ALGORITHM);
        KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT_VALUE.getBytes(), ITERATIONS, KEY_SIZE);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

}

