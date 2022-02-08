package com.fastcampus.ch3.diCopy1;


import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car{ }
class SportsCar extends Car {}
class Truck extends Car { }
class Engine {}




public class Main1 {
    public static void main(String[] args) throws Exception {

        Car car = getCar();     //Car car = (Car)getObject("car");
        System.out.println("car = " + car);


        Engine engine = (Engine) getObject("engine");
        System.out.println("engine = " + engine);
    }

    // 좀더 유연한 방식 (car 타입의 메서드 뿐 아니라 다양한 종류의 타입을 사용할 수 있다)
    static Object getObject(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty(key));

        return clazz.newInstance();
    }

    static Car getCar() throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));
        Class clazz = Class.forName(p.getProperty("car"));

        return (Car)clazz.newInstance();
    }
}
