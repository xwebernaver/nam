package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YoilTellerMVC {
    @RequestMapping("/getYoilMVC") // http://localhost/ch2/getYoilMVC
    public String main(int year, int month, int day, Model model) {
 
    	// ModelAndView mv = new ModelAndView();
    	
        // 1. ��ȿ�� �˻�
    	if(!isValid(year, month, day)) 
    		return "yoilError";  // ��ȿ���� ������, /WEB-INF/views/yoilError.jsp�� �̵�
    	
        // 2. ó��
    	char yoil = getYoil(year, month, day);

        // 3. Model�� �۾� ��� ����
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("yoil", yoil);
    	
		/*
		 * mv.addObject("year", year); mv.addObject("month", month); mv.addObject("day",
		 * day); mv.addObject("yoil", yoil);
		 * 
		 * mv.setViewName("yoil");
		 * 
		 * return mv;
		 */
    	
    	
        // 4. �۾� ����� ������ View�� �̸��� ��ȯ
        return "yoil"; // /WEB-INF/views/yoil.jsp	// return ������ RequestMapping url ���� �������� �ڵ� ����
    }
    
    private char getYoil(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " �Ͽ�ȭ�������".charAt(dayOfWeek);
    }
    
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // ������ üũ 
    }
}