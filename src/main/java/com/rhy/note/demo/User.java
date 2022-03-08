package com.rhy.note.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * @author: Herion Lemon
 * @date: 2022/2/15 20:38
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Service
public class User {
    public User() {
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private String age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String test(){
        return new String("test");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
