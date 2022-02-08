package com.fastcampus.ch2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MethodCall {
	
	public static void main(String[] args) throws Exception {
		HashMap map = new HashMap();
		System.out.println("before:" + map);
		
		ModelController mc = new ModelController();
		String viewName = mc.main(map);
		
		System.out.println("after :"+map);
		
		render(map, viewName);
	}
		
	
	static void render (HashMap map, String viewName) throws FileNotFoundException {
		String result = "";
		System.out.println("render map :"+map);
		System.out.println("render viewName :"+viewName);
		
		// 1. ���� ������ ���پ� �о �ϳ��� ���ڿ��� �����.
		Scanner sc = new Scanner(new File(viewName +".txt"));
		
		while(sc.hasNextLine()) {
			result += sc.nextLine() + System.lineSeparator();	// �ٱ��� (���� ������ ����)
		}
		
		System.out.println("render result :"+result);
		
		// 2. map�� ��� key�� �ϳ��� �о template�� ${key}�� value �ٲ۴�.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();
						
			System.out.println("render key :"+key);
			System.out.println("render map.get(key) :"+map.get(key));
			
			
			
			// 3. replace()�� key�� value ġȯ�Ѵ�.
			result = result.replace("${" + key +"}", (String)map.get(key));
			
			System.out.println("replace result :"+result);
		}
		
		// 4.������ ����� ���
		System.out.println(result);
		
	}
}


class ModelController {
	public String main(HashMap map) {
		map.put("id", "asdf");
		map.put("pwd", "1111");	
		
		return "txtView1";
	}
}



/*
 * [txtView1.txt] id=${id}, pwd=${pwd}
 * 
 * [txtView2.txt] id:${id} pwd:${pwd}
 * 
 * 
 * 
 * [������] before:{} after :{id=asdf, pwd=1111} [txtView2.txt] id:asdf pwd:1111
 */