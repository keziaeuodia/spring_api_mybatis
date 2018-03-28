package mybatis.controllers;

import mybatis.model.weather.AverageHumidity;
import mybatis.model.weather.WeatherRoot;
import mybatis.model.weather.WeatherSummary;
import mybatis.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")

public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping("/search")
    public WeatherRoot searchList(@RequestParam(value="q") String city,
                                  @RequestParam(value="persist", defaultValue = "false") boolean persist) {
        return weatherService.searchWeather(city, persist);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public WeatherSummary find (@PathVariable(value ="id") String id){
        return weatherService.findWeather(id);
    }

    @RequestMapping("/")
    public WeatherSummary[] showAll (){
        return weatherService.getAllWeather();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public WeatherSummary insert (@RequestBody WeatherSummary weather){
        return weatherService.insertWeather(weather);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public WeatherSummary update (@RequestBody WeatherSummary weather){
        return weatherService.updateById(weather,String.valueOf(weather.getId()));
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/{city}")
    public String deleteList (@PathVariable(value = "city") String city){
        return weatherService.deleteWeatherDataByCity(city);
    }

    public AverageHumidity average(@RequestParam(value="c1", required = true) String city1,
                                   @RequestParam(value="c2", required = true) String city2){
        return weatherService.averageHumidity(city1,city2);
    }

}
