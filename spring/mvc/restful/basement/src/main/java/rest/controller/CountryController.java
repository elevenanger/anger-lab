package rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.model.Country;

import java.util.Arrays;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/7/23 16:32
 * description : 使用 rest 端点返回 DTO 实例
 */
@RestController
public class CountryController {

    @GetMapping(path = "/country/china")
    public Country country() {
        /*
        返回 Country 实例
        默认情况下 Spring 创建字符串表示对象
        将其格式化为 JSON 格式
         */
        return Country.of("China", 140_005_000_0);
    }

    @GetMapping(path = "/country/all")
    public List<Country> all() {
        return Arrays.asList(
            Country.of("China", 140_005_000_0),
            Country.of("Japan", 126_300_000)
        );
    }

    @GetMapping("/response/country/china")
    public ResponseEntity<Country> china() {
        Country country = Country.of("China", 140_005_000_0);
        /*
        使用 ResponseEntity 实例包裹对象返回
        可以定义 HttpStatue
        http header
        http body
        等信息
         */
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .header("continent", "Asia")
            .body(country);
    }
}
