package mybatis.services;

import mybatis.mappers.WeatherMapper;
import mybatis.model.weather.AverageHumidity;
import mybatis.model.weather.Weather;
import mybatis.model.weather.WeatherList;
import mybatis.model.weather.WeatherRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherMapper weatherMapper;

    public WeatherRoot searchWeather(String city) {

        String fQuery = "http://api.openweathermap.org/data/2.5/forecast?q="+city+"&APPID=";

        WeatherRoot list = restTemplate.getForObject(
                fQuery, WeatherRoot.class);

        return list;
    }

    public AverageHumidity averageHumidity(String city1, String city2){

        WeatherRoot response1 = searchWeather(city1);
        WeatherRoot response2 = searchWeather(city2);

        double sum1 = 0, sum2 = 0;

        for(WeatherList x : response1.getList()){
            double y = x.getMain().getHumidity();
            sum1 += y;
        }

        for (WeatherList x : response2.getList()){
            double y = x.getMain().getHumidity();
            sum2 += y;
        }

        AverageHumidity avg = new AverageHumidity();
        avg.setAvg1(sum1/response1.getList().length);
        avg.setAvg2(sum2/response2.getList().length);

        return avg;
    }

}
