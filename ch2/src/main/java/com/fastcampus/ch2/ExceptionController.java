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
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)		// 상태코드 지정 200 -> 500
	public String catcher(Exception ex, Model m) {
		System.out.println("catcher() in ExceptionController ");
		System.out.println("m = " + m);
		// m.addAttribute("ex", ex);	// Model 로 전달하지 않아도 됨 (error.jsp 페이지 isErrorPage="true"  설정하면 에러 정보 얻을 수 있음)
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main(Model m) throws Exception {
		m.addAttribute("msg", "message from ExceptionController.main()");	// 	@ExceptionHandler 메서드의 Model 객체로 전달 되지 않는다. 
		throw new Exception("예외가 발생했습니다.");
//		try {
//			throw new Exception("예외가 발생했습니다.");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//			return "error";
//		}		
	}
	
	
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new NullPointerException("예외가 발생했습니다2.");		
	}
	
//	@RequestMapping("/ex3")
//	public String main3() throws Exception {
//		throw new FileNotFoundException("예외가 발생했습니다3.");		
//	}
	
}
