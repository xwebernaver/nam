package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class Main {
	public void test() {
		System.out.println("test");
	}
	
	public static void main(String[] args) {
		Hello hello = new Hello();
		//hello.main();
		
		// Reflection API�� ��� - Ŭ���� ������ ��� �ٷ� �� �ִ� ������ ��� ����
		// java.lang.reflect ��Ű���� ����
		// Hello Ŭ������ Class ��ü(Ŭ������ ������ ��� �ִ� ��ü)�� ���´�.
		
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello)helloClass.newInstance();	// Class ��ü�� ���� ������ ��ü ����
		Method main = helloClass.getDeclaredMethod("main");		// ����� �޼��� ��������
		main.setAccessible(true); // private�� main()�� ȣ�Ⱑ���ϰ� �Ѵ�.  ( set Accessible : �׼��� ���� ����)
		
		main.invoke(hello);
	}
}
