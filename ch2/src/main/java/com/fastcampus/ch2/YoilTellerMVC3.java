package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YoilTellerMVC3 {
    @RequestMapping("/getYoilMVC3") // http://localhost/ch2/getYoilMVC3  
    public ModelAndView main(  // ��ȯ Ÿ���� ModelAndView
    		@RequestParam(defaultValue="-1") int year, 
    		@RequestParam(defaultValue="-1") int month, 
    		@RequestParam(defaultValue="-1") int day
    ) {

    	 // 1. ModelAndView�� ����
         ModelAndView mv = new ModelAndView(); 
    	
    	// 2. ��ȿ�� �˻� 
    	if(!isValid(year, month, day)) {
     	   mv.setViewName("yoilError"); // View�� �̸��� ����
           return mv;
        }
    	
        // 3. ó��
      	char yoil = getYoil(year, month, day);

    	// 4. ModelAndView�� �۾��� ����� ���� 
      	mv.addObject("year",  year);     	
      	mv.addObject("month", month);     	
      	mv.addObject("day",   day);     	
      	mv.addObject("yoil", yoil);        
        
      	// 5. �۾� ����� ������ View�� �̸��� ���� 
      	mv.setViewName("yoil"); 
      	
      	// 6. ModelAndView�� ��ȯ
      	return mv;
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