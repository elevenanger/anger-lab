package rest.model;

import lombok.Data;

/**
 * author : anger
 * date : 2022/7/23 16:29
 * description : DTO 对象
 * 经过 rest 端点返回 DTO 实例
 */
@Data
public class Country {
    private String name;
    private int population;

    private Country() {}

    public static Country of(String name, int population) {
        Country country = new Country();
        country.setName(name);
        country.setPopulation(population);
        return country;
    }
}
