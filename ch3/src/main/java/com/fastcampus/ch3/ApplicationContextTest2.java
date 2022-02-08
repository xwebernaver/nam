package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

@Component
@Scope("prototype")
class Door2 {}
@Component class Engine2 {}
@Component class TurboEngine2 extends Engine2 {}
@Component class SuperEngine2 extends Engine2 {}

@Component
class Car2 {
    @Value("red") String color;
    @Value("100") int oil;
   // @Autowired
    Engine2 engine2;
   // @Autowired
    Door2[] doors2;

    // 위 속성에 주입(@Autowired) 하지 않고 생성자에 주입한다. (생성자에 주입하는것을 추천)
    // String color, int oil 은 주입할 빈이 없기때문에 오류 표시됨
    // 그래서 @Value를 붙여서 값을 초기화 시킨다.
     @Autowired // (생성자가 하나 일 경우 @Autowired 안 붙여도 주입이 된다.)
    public Car2(@Value("red") String color, @Value("100") int oil, Engine2 engine2, Door2[] doors2) {
        this.color = color;
        this.oil = oil;
        this.engine2 = engine2;
        this.doors2 = doors2;
    }

    // 생성자가 두개의 경우 @Autowired 붙인다. (어느 생성자에 주입할지 모르기때문)
    Car2(){}

    @Override
    public String toString() {
        return "Car2{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine2=" + engine2 +
                ", doors2=" + Arrays.toString(doors2) +
                '}';
    }
}

@Component
@PropertySource("setting.properties")
class SysInfo {
    @Value("#{systemProperties['user.timezone']}")
    String timeZone;
    @Value("#{systemEnvironment['APPDATA']}")
    String currDir;
    @Value("${autosaveDir}")
    String autosaveDir;
    @Value("${autosaveInterval}")
    int autosaveInterval;
    @Value("${autosave}")
    boolean autosave;

    @Override
    public String toString() {
        return "SysInfo{" +
                "timeZone='" + timeZone + '\'' +
                ", currDir='" + currDir + '\'' +
                ", autosaveDir='" + autosaveDir + '\'' +
                ", autosaveInterval=" + autosaveInterval +
                ", autosave=" + autosave +
                '}';
    }
}

public class ApplicationContextTest2 {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//      Car car = ac.getBean("car", Car.class); // 타입을 지정하면 형변환 안해도됨. 아래의 문장과 동일
        Car2 cara  = (Car2) ac.getBean("car2"); // 이름으로 빈 검색

        System.out.println("cara = " + cara);

        System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));

        Map<String, String> map = System.getenv();
        System.out.println("map = " + map);

        Properties properties = System.getProperties();
        System.out.println("properties = " + properties);


    }
}

/* [src/main/resources/config.xml]
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
    <context:component-scan base-package="com.fastcampus.ch3"/>
</beans>
*/

/* [실행결과]
car = Car{color='red', oil=100, engine=com.fastcampus.ch3.Engine@df6620a, doors=[com.fastcampus.ch3.Door@205d38da]}
car2 = Car{color='red', oil=100, engine=com.fastcampus.ch3.Engine@df6620a, doors=[com.fastcampus.ch3.Door@205d38da]}
ac.getBeanDefinitionNames() = [org.springframework.context.annotation.internalConfigurationAnnotationProcessor, org.springframework.context.annotation.internalAutowiredAnnotationProcessor, org.springframework.context.annotation.internalRequiredAnnotationProcessor, org.springframework.context.annotation.internalCommonAnnotationProcessor, org.springframework.context.event.internalEventListenerProcessor, org.springframework.context.event.internalEventListenerFactory, car, door, engine, superEngine, turboEngine]
ac.getBeanDefinitionCount() = 11
ac.containsBeanDefinition("car") = true
ac.containsBean("car") = true
ac.getType("car") = class com.fastcampus.ch3.Car
ac.isSingleton("car") = true
ac.isSingleton("car") = false
ac.isPrototype("door") = true
ac.isTypeMatch("car", Car.class) = true
ac.findAnnotationOnBean("car", Component.class) = @org.springframework.stereotype.Component(value="")
ac.getBeanNamesForAnnotation(Component.class) = [car, door, engine, superEngine, turboEngine]
ac.getBeanNamesForType(Car.class) = [engine, superEngine, turboEngine]
*/