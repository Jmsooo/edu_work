package com.lagou.testFastJSON;

import com.alibaba.fastjson.JSON;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFastJSON {

    //Java对象转换为JSON
    @Test
    public void JavaBeanToJSON(){
        Person person = new Person("小斌",25, DateUtils.getDateFormart());

        //使用JSON对象， 将person对象转换为JSON数据
        String jsonString = JSON.toJSONString(person);
        System.out.println("jsonString = " + jsonString);
    }

    //Java集合转换为JSON
    @Test
    public void ListToJSON(){
        Person person1 = new Person("小吴",25, DateUtils.getDateFormart());
        Person person2 = new Person("小斌",21, DateUtils.getDateFormart());
        Person person3 = new Person("小张",30, DateUtils.getDateFormart());

        ArrayList<Person> people = new ArrayList<>();

        Collections.addAll(people,person1,person2,person3);

        String jsonString = JSON.toJSONString(people);

        System.out.println("jsonString = " + jsonString);
    }

    //JSON转Java对象
    @Test
    public void JSONToJavaBean(){
        String json = "{\"USERNAME\":\"小斌\",\"AGE\":25,\"BIRTHDAY\":\"2022-02-10 12:58:04\"}";

        Person person = JSON.parseObject(json, Person.class);
        System.out.println("person = " + person);
    }

    //JSON转List集合
    @Test
    public void JSONToList(){
        String json = "[{\"USERNAME\":\"小吴\",\"AGE\":25,\"BIRTHDAY\":\"2022-02-10 13:01:46\"},{\"USERNAME\":\"小斌\",\"AGE\":21,\"BIRTHDAY\":\"2022-02-10 13:01:46\"},{\"USERNAME\":\"小张\",\"AGE\":30,\"BIRTHDAY\":\"2022-02-10 13:01:46\"}]";

        List<Person> people = JSON.parseArray(json, Person.class);
        System.out.println("people = " + people);
    }

}
