package com.hdfc.capstone.EMS.service;





import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.capstone.EMS.entity.Employee;
import com.hdfc.capstone.EMS.repository.EmployeeRepository;
import com.hdfc.capstone.EMS.vo.EmployeeVO;



@Service
public class EmployeeService implements IEmployeeService {
	

	@Autowired
	EmployeeRepository repo;
	
	private static final String SECRET_KEY = "SecretKey";
	private static final String SALTVALUE = "tushar" ;
	@Override
	public EmployeeVO getByEmployeeId(int employeeId) throws Exception {
		Employee employee = repo.findById(employeeId).orElse(null);
		if(employee != null) {
			
			String encrypted=EmployeeService.encrypt(employee.getDateOfBirth().toString());
			System.out.println("Encrypted value = "+encrypted);
			
			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setEmployeeId(employee.getEmployeeId());
			employeeVO.setEmployeeName(employee.getEmployeeName());
			employeeVO.setDateOfBirth(encrypted);
			
			return employeeVO;
		}
		else {
			throw new Exception("Invalid EmployeeID");
		}
	}
	
	public static String encrypt(String strToEncrypt) throws Exception {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
		/* Returns encrypted value. */
		return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));

	}
}