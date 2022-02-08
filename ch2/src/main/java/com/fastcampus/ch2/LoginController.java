package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {
	//@GetMapping("/login")
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}
	
	/*
	 * @GetMapping("/logout") public String logout(HttpSession session) {
	 * session.invalidate(); return "redirect:/"; }
	 */

	@PostMapping("/login")	
	public String login(String id, String pwd, boolean rememberId, String toURL, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("id="+id);
		System.out.println("pwd="+pwd);
		System.out.println("rememberId="+rememberId);
		// 1. id�� pwd�� Ȯ��
		if(!loginCheck(id, pwd)) {
			// 2-1   ��ġ���� ������, loginForm���� �̵�
			String msg = URLEncoder.encode("id �Ǵ� pwd�� ��ġ���� �ʽ��ϴ�.", "utf-8");
			
			return "redirect:/login/login?msg="+msg;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		// 2-2. id�� pwd�� ��ġ�ϸ�,
		if(rememberId) {
		//     1. ��Ű�� ����
			Cookie cookie = new Cookie("id", id); // ctrl+shift+o �ڵ� import
//		       2. ���信 ����
			response.addCookie(cookie);
			
		} else {
// 		       1. ��Ű�� ����
			Cookie cookie = new Cookie("id", id); // ctrl+shift+o �ڵ� import
			cookie.setMaxAge(0); // ��Ű�� ����
//		       2. ���信 ����
			response.addCookie(cookie);
		}
//		3. Ȩ���� �̵�
		toURL = toURL==null || toURL.equals("") ? "/" : toURL; 
		
		
		return "redirect:"+toURL;
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}