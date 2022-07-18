package domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/18 11:40
 * description :
 */
@Slf4j
class PersonTest {

    @Test
    void testFormat() {
        Person person = new Person();
        person.setDate(LocalDate.now());
        Child child = new Child();
        child.setChildDate(LocalDate.now());

        Child child2 = new Child();
        Child child3 = new Child();
        child2.setChildDate(LocalDate.now());
        child3.setChildDate(LocalDate.now());
        String json = JSON.toJSONString(person);
        log.info(json);
    }

    @Test
    void testParse() {
        Person person = JSON.parseObject("{\"date\":\"20220718\"}", Person.class);
        log.info(person.toString());

        JSONObject jsonObject = JSON.parseObject("{\"date\":\"20220718\"}");
        Person person1 = JSON.toJavaObject(jsonObject, Person.class);
        log.info(person1.toString());
    }

    private <T, S> T parse(String text, Class<T> type, Class<S> subType){
        JSONObject jsonObject = JSON.parseObject(text);
        log.info(jsonObject.toJSONString());
        T obj = JSON.toJavaObject(jsonObject, type);
        log.info(obj.toString());
        S subObj = jsonObject.getObject("child", subType);
        log.info("子对象:" + subObj.toString());
        JSONArray object = jsonObject.getJSONArray("child");
        log.info("json obj" + object.toJSONString());
        // JSONObject object = jsonObject.getJSONObject("child");
        return JSON.parseObject(jsonObject.toJSONString(), type);
    }

    @Test
    void testParseByGeneric() {

        String json = "{\"child\":[{\"childDate\":\"20220718\",\"grandSonList\":\"\"}," +
            "{\"childDate\":\"20220718\",\"grandSonList\":\"\"}," +
            "{\"childDate\":\"20220718\",\"grandSonList\":\"\"}]," +
            "\"date\":\"20220718\"}";

        //log.info(parse("{\"child\":{\"childDate\":\"20220718\"},\"date\":\"20220718\"}", Child.class).toString());
        log.info(parse(json, Person.class, List.class).toString());
    }
}