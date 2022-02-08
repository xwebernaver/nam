package com.fastcampus.ch3.diCopy4;


import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component class Car{
    //@Autowired
    @Resource
    Engine engine;
    //@Autowired
    @Resource
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
@Component class SportsCar extends Car {}
@Component class Truck extends Car { }
@Component class Engine {}
@Component class Door{ }


class AppContext{
    Map map;

    AppContext(){
        map = new HashMap();
        doComponentScan();
        //doAutowired();
        doResource();


    }

    private void doResource() {
        // map에 저장된 객체의 iv 중에 @Resource가 붙어 있으면
        // map에서 iv 의 이름에 맞는 객체를 찾아 연결 (객체의 주소를 iv 저장)
        try {
            for (Object bean : map.values()){
                for(Field fld : bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Resource.class) != null) // Resource -> byName
                        fld.set(bean, getBean(fld.getName()));  // car.engine = obj;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        // map에 저장된 객체의 iv 중에 @Autowired가 붙어 있으면
        // map에서 iv 의 타입에 맞는 객체를 찾아 연결 (객체의 주소를 iv 저장)
        try {
            System.out.println("===========================");
            for (Object bean : map.values()){
                System.out.println("bean : " + bean);
                for(Field fld : bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Autowired.class) != null)      // Autowired-> byType (fld.getType())
                        fld.set(bean, getBean(fld.getType()));      // car.engine = obj;
                }
            }
            System.out.println("===========================");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    private void doComponentScan() {
        try {
            // 1. 패키지내의 클래스 목록을 가져온다.
            // 2. 반복문으로 클래슬르 하나씩 읽어와서 @Component이 붙어 있는지 확인
            // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
            ClassLoader classLoader = com.fastcampus.ch3.diCopy4.AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

            for(ClassPath.ClassInfo classInfo : set){
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if(component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key){
        return map.get(key);
    }

    Object getBean(Class clazz){
        for (Object obj : map.values()){
            if(clazz.isInstance(obj))
                return obj;
        }
        return null;
    }
}


public class Main4 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        Car car = (Car)ac.getBean("car");       // byName 으로 객체를 검색
        Door door = (Door)ac.getBean(Door.class);      // byType 으로 객체를 검색
        Engine engine = (Engine) ac.getBean("engine");

        // 수동으로 객체를 연결
        // 어노테이션을 사용하면 아래 코드 작성할 필요 없음. (@Autowired -> byType , @Resource -> byName 자동으로 연결)
//        car.engine = engine;
//        car.door = door;

        System.out.println("car = " + car);
        System.out.println("door = " + door);
        System.out.println("engine = " + engine);
    }
}
