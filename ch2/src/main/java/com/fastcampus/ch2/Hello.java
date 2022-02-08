package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	
	int iv = 10;
	static int cv = 20;
	
	@RequestMapping("/hello")
	private void main() {
		System.out.println("hello main");
		System.out.println(cv);	// ����ƽ ���� ��� ����
		System.out.println(iv); // �ν��Ͻ� ���� ��� ����
	}
	
	public static void main2() {
		System.out.println("hello main2");
		System.out.println(cv);		// ����ƽ ���� ��� ����
		System.out.println(iv);		// �ν��Ͻ� ���� ��� �Ұ�
	}

}
