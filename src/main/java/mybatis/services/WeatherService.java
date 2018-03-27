package mybatis.services;

import mybatis.mappers.WeatherMapper;
import mybatis.model.weather.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherMapper weatherMapper;

    public WeatherRoot searchWeather(String city, boolean persist) {

        String fQuery = "http://api.openweathermap.org/data/2.5/forecast?q="+city+"&APPID=";

        WeatherRoot list = restTemplate.getForObject(
                fQuery, WeatherRoot.class);
        
        if (persist){
            saveWeatherData(list,city);
        }

        return list;
    }

    public WeatherSummary[] getAllWeather(){
        return weatherMapper.getAllWeather();
    }

    private void saveWeatherData(WeatherRoot list, String city) {

        WeatherSummary obj = new WeatherSummary();

        obj.setCity(city);
        obj.setDatetime(list.getList()[0].getDt());
        obj.setHumidity(list.getList()[0].getMain().getHumidity());
        obj.setTemp(list.getList()[0].getMain().getTemp());

        weatherMapper.insertWeather(obj);

    }

    public String deleteWeatherDataByCity(String city){
        weatherMapper.deleteWeatherDataByCity(city);
        return city;
    }

    public void updateTempById(int id, double temp){
        weatherMapper.updateTempById(temp, id);
    }

    public AverageHumidity averageHumidity(String city1, String city2){

        WeatherRoot response1 = searchWeather(city1, false);
        WeatherRoot response2 = searchWeather(city2, false);

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
