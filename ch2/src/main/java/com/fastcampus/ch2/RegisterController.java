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

@Controller // ctrl+shift+o �ڵ� ����Ʈ
@RequestMapping("/register")
public class RegisterController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();		
		System.out.println("conversionService="+conversionService);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		//binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
		//binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	// Ư�� �ʵ常 ����
		//binder.addValidators(new UserValidator());  // UserValidator�� webDataBinder�� ���� validator�� ��� (�ڵ�)
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList="+validatorList);
	}
	
	
	//@RequestMapping(value="/add", method=RequestMethod.GET) // �ű�ȸ�� ����
//	@GetMapping("/add") // 4.3���� �߰�
//	public String register() {
//		return "registerForm";  // WEB-INF/views/registerForm.jsp
//	}
	/*
	 * �� ��Ʈ�ѷ� ��� �ϸ� Get ��û�� ó���Ѵ� (post �ȵ�) servlet-context.xml
	 * <view-controller path="/register/add" view-name="registerForm"/>
	 */
	
	// @RequestMapping(value="/register/save", method= {RequestMethod.GET, RequestMethod.POST})	// get, post �Ѵ� ���
	@RequestMapping(value="/save", method=RequestMethod.POST) // �ű�ȸ�� ����
	//@PostMapping("/save") 	// 4.3 ����	
	//public String save(@ModelAttribute("user") User user, BindingResult result, Model m) throws Exception {
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {  // @Valid �ڹ� ǥ�� Validator �̱� ������ ���̺� �������� �����;� �Ѵ�.		
		System.out.println("result=" + result);
		System.out.println("user=" + user);
		
		// ���� ���� - Validator�� ���� �����ϰ�, validator�� ���� ȣ��
		//UserValidator userValidator = new UserValidator();
		//userValidator.validate(user, result);	// BindingResult�� Errors�� �ڼ� �̱� ������ ����
		
		// User��ü�� r������ ��� ������ ������, registerForm�� �̿��ؼ� ������ ������� ��.
		if(result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1. ��ȿ�� �˻�
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id�� �߸��Է��ϼ̽��ϴ�.", "utf-8");
//			
//			m.addAttribute("msg", msg);
//			return "redirect:/register/add";  // URL ���ۼ�	// �ű�ȸ�� ����ȭ������ �̵�(redirect)
//			// return "forward:/register/add";
//		}
		
		// 2. DB�� �ű� ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
	
}