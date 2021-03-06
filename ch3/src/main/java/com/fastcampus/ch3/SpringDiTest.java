//package com.fastcampus.ch3;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
////@Component("engine")
//class Engine{}  // ("engine") 생량 가능. 빈 태그와 같다 <bean id="engine" class="com.fastcampus.ch3.Engine" />
//@Component class SuperEngine extends Engine {}
//@Component class TurboEngine extends Engine {}
//
//@Component
//class Door{}
//
//@Component
//class Car{
//    @Value("red") String color;     // setter 역할을 한다. (값), = color = "red"
//    @Value("100") int oil;          // setter 역할을 한다. (값)
//
//    /*
//    * Autowired 타입으로 (byType)으로 검색한 후 Qualifier 의 이름으로  찾는다.
//    * Resource 는 이름으로만 찾는다.
//    * # 이름이 자주 바뀔 수 있어 Autowired를 많이 사용한다.
//    * */
////    @Autowired
////    @Qualifier("superEngine")       // 같은 타입이 여러개면 후보중에 지정해준다.
//    @Resource(name="superEngine")        // 위 두개 Autowired, Qualifier 대신 Qualifier 하나로 사용가능, 단 @Resource 는 byName 으로 검색한다. (위 와 완전 똑같다고 볼 수 없다.)
//    Engine engine;                  // setter 역할을 한다. (참조),  @Autowired 는 타입으로 먼저 검색하고 여러개의 경우 이름으로 검색한다.
//    @Autowired Door[] doors;        // setter 열할을 한다. (참조), @Autowired 는 배열에 하나만 주입할 수 있다.
//
//    // 생성자 만들때는 기본 생성자도 꼭 만들어야함
//    public Car() { }
//
//    // 생성자를 통한 초기화
//    public Car(String color, int oil, Engine engine, Door[] doors) {
//        this.color = color;
//        this.oil = oil;
//        this.engine = engine;
//        this.doors = doors;
//    }
//
//    /* setter 함수가 있어야 Bean의 Property를 사용할 수 있다. */
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//
//public class SpringDiTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config-component-scan.xml");
//        //Car car = (Car)ac.getBean("car");       // byName
//        Car car = ac.getBean("car", Car.class);       // 위 아 같은 결과 (타입을 적어주면 캐스팅하지 않아도됨)
//        Car car2 = (Car) ac.getBean(Car.class);         // byType  , 싱글톤 방식이라 기본은 객체 생성을 한번만 한다.
//        //Engine engine = (Engine) ac.getBean("engine");    // byName
//        //Engine engine = (Engine)ac.getBean(Engine.class);   // byType
//        Engine engine = (Engine)ac.getBean("superEngine");   // byName 으로 찾는다 (위 Engine 클래스가 빈에 여러개 있을 경우 이름으로 찾는다)
//        Door door = (Door) ac.getBean("door");
//
//
///*
//        System.out.println("car=" + car);
//        System.out.println("car2=" + car2);         // 매번 다른 객체를 발당받는다. <bean id="car" class="com.fastcampus.ch3.Car" scope="prototype" />
//        System.out.println("engine=" + engine);
//        System.out.println("door=" + door);
//*/
//
///*
//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ac.getBean("door", Door.class), (Door)ac.getBean("door")});
//*/
///*
//        * 위 셋터 호출하지 않고 bean 설정으로 대체 가능
// */
//
///*
//        //<context:component-scan base-package="com.fastcampus.ch3">
//        // 컴퍼넌트 스캐닝사용시에는 setter 를 등록해야 한다. (bean 속성을 지정하지 않고 자동 생성하기 때문)
//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ac.getBean("door", Door.class), (Door)ac.getBean("door")});
//*/
//
//
//        System.out.println("car=" + car);
//
//
//
//
//    }
//}
