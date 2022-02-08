package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {				
		m.addAttribute("ex", ex);
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)		// �����ڵ� ���� 200 -> 500
	public String catcher(Exception ex, Model m) {
		System.out.println("catcher() in ExceptionController ");
		System.out.println("m = " + m);
		// m.addAttribute("ex", ex);	// Model �� �������� �ʾƵ� �� (error.jsp ������ isErrorPage="true"  �����ϸ� ���� ���� ���� �� ����)
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main(Model m) throws Exception {
		m.addAttribute("msg", "message from ExceptionController.main()");	// 	@ExceptionHandler �޼����� Model ��ü�� ���� ���� �ʴ´�. 
		throw new Exception("���ܰ� �߻��߽��ϴ�.");
//		try {
//			throw new Exception("���ܰ� �߻��߽��ϴ�.");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//			return "error";
//		}		
	}
	
	
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�2.");		
	}
	
//	@RequestMapping("/ex3")
//	public String main3() throws Exception {
//		throw new FileNotFoundException("���ܰ� �߻��߽��ϴ�3.");		
//	}
	
}
