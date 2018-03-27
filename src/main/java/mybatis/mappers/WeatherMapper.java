package mybatis.mappers;

import mybatis.model.weather.Main;
import mybatis.model.weather.Weather;
import mybatis.model.weather.WeatherSummary;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface WeatherMapper {

    String GET_ALL_WEATHER = "SELECT temp FROM `mybatis-test`.`weather_summary`;";
    String INSERT_WEATHER = "INSERT INTO `mybatis-test`.`weather_summary`(`id`,`datetime`,`city`,`temp`,`humidity`)" +
            "VALUES(#{id}, #{datetime}, #{city}, #{temp}, #{humidity});";
    String UPDATE_WEATHER = "UPDATE `mybatis-test`.`weather_summary` " +
            "SET `temp` = #{arg0} WHERE `id` = #{arg1};";
    String DELETE_WEATHER = "DELETE FROM `mybatis-test`.`weather_summary` WHERE `city` = #{city};";


    @Select(GET_ALL_WEATHER)
    public WeatherSummary[] getAllWeather();

    @Insert(INSERT_WEATHER)
    public void insertWeather (WeatherSummary obj);

    @Update(UPDATE_WEATHER)
    public void updateTempById(double temp,int id);

    @Delete(DELETE_WEATHER)
    public void deleteWeatherDataByCity(String city);

}
