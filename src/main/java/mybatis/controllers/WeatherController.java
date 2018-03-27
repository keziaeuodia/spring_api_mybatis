package mybatis.controllers;

import mybatis.model.weather.AverageHumidity;
import mybatis.model.weather.WeatherRoot;
import mybatis.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")

public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping("/search")
    public WeatherRoot searchList(@RequestParam(value="q") String city) {
        return weatherService.searchWeather(city);
    }

    @RequestMapping("/avg")
    public AverageHumidity average(@RequestParam(value="c1", required = true) String city1,
                                   @RequestParam(value="c2", required = true) String city2){
        return weatherService.averageHumidity(city1,city2);
    }

}
