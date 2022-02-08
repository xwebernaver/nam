package com.fastcampus.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller // ctrl+shift+o 자동 임포트
@RequestMapping("/register")
public class RegisterController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();		
		System.out.println("conversionService="+conversionService);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		//binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
		//binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	// 특정 필드만 적용
		//binder.addValidators(new UserValidator());  // UserValidator를 webDataBinder의 로컬 validator로 등록 (자동)
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList="+validatorList);
	}
	
	
	//@RequestMapping(value="/add", method=RequestMethod.GET) // 신규회원 가입
//	@GetMapping("/add") // 4.3부터 추가
//	public String register() {
//		return "registerForm";  // WEB-INF/views/registerForm.jsp
//	}
	/*
	 * 뷰 컨트롤러 등록 하면 Get 요청을 처리한다 (post 안됨) servlet-context.xml
	 * <view-controller path="/register/add" view-name="registerForm"/>
	 */
	
	// @RequestMapping(value="/register/save", method= {RequestMethod.GET, RequestMethod.POST})	// get, post 둘다 허용
	@RequestMapping(value="/save", method=RequestMethod.POST) // 신규회원 가입
	//@PostMapping("/save") 	// 4.3 부터	
	//public String save(@ModelAttribute("user") User user, BindingResult result, Model m) throws Exception {
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {  // @Valid 자바 표준 Validator 이기 때문에 메이븐 리포에서 가져와야 한다.		
		System.out.println("result=" + result);
		System.out.println("user=" + user);
		
		// 수동 검증 - Validator를 직접 생성하고, validator를 직접 호출
		//UserValidator userValidator = new UserValidator();
		//userValidator.validate(user, result);	// BindingResult는 Errors의 자손 이기 때문에 가능
		
		// User객체를 r검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함.
		if(result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1. 유효성 검사
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
//			
//			m.addAttribute("msg", msg);
//			return "redirect:/register/add";  // URL 재작성	// 신규회원 가입화면으로 이동(redirect)
//			// return "forward:/register/add";
//		}
		
		// 2. DB에 신규 회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
	
}