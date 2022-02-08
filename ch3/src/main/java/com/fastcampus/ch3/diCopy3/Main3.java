package com.fastcampus.ch3.diCopy3;


import com.google.common.reflect.ClassPath;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


@Component class Car{
    Engine engine;
    Door door;
}
class SportsCar extends Car {}      // @Component 가 없으면 패키지내의 클래스를 가져올 수 없다.
@Component class Truck extends Car { }
@Component class Engine {}
@Component class Door{}

class AppContext{
    Map map;
    AppContext(){
        map = new HashMap();

        doComponentScan();
    }

    private void doComponentScan() {
        try {
            // 1. 패키지내의 클래스 목록을 가져온다.
            // 2. 반복문으로 클래슬르 하나씩 읽어와서 @Component이 붙어 있는지 확인
            // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for(ClassPath.ClassInfo classInfo : set){
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if(component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.newInstance());
                }
            }
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    Object getBean(String key){
        return map.get(key);
    }   // byName

    Object getBean(Class clazz){        // byType
        for (Object obj : map.values()){
            System.out.println("map.values() " + obj);
            if(clazz.isInstance(obj))       // 매개변수로 받은 클래스가 map 에 저장되어 있는 클래스와 같거나 그 자식 클래스라면 ...
                return obj;
        }
        return null;
    }
}


public class Main3 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        Car car = (Car)ac.getBean("car");       // byName 으로 객체를 검색
        Engine engine = (Engine) ac.getBean("engine");

        Car car2 = (Car)ac.getBean(Car.class);      // byType 으로 객체 검색

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);

        System.out.println("car2 = " + car2);
    }
}
