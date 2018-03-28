package mybatis.mappers;

import mybatis.model.weather.Main;
import mybatis.model.weather.Weather;
import mybatis.model.weather.WeatherSummary;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface WeatherMapper {

    String GET_ALL_WEATHER = "SELECT temp FROM `mybatis-test`.`weather_summary`;";
    String INSERT_WEATHER = "INSERT INTO `mybatis-test`.`weather_summary`(`datetime`,`city`,`temp`,`humidity`)" +
            "VALUES(#{datetime}, #{city}, #{temp}, #{humidity});";
    String UPDATE_WEATHER = "UPDATE `mybatis-test`.`weather_summary` SET `city` = #{city}, `temp` = #{temp}, `humidity` = #{humidity} WHERE `id` = #{id}";
    String DELETE_WEATHER = "DELETE FROM `mybatis-test`.`weather_summary` WHERE `city` = #{city};";
    String FIND_BY_ID = "SELECT * FROM `mybatis-test`.`weather_summary` WHERE `id` = #{id};";
    String FIND_LATEST = "SELECT * FROM `mybatis-test`.`weather_summary` ORDER BY `id` DESC LIMIT 1";



    @Select(GET_ALL_WEATHER)
    public WeatherSummary[] getAllWeather();

    @Insert(INSERT_WEATHER)
    public void insertWeather (WeatherSummary obj);

    @Update(UPDATE_WEATHER)
    public int updateTempById(WeatherSummary weather);

    @Delete(DELETE_WEATHER)
    public void deleteWeatherDataByCity(String city);

    @Select(FIND_BY_ID)
    WeatherSummary findweather(String id);

    @Select(FIND_LATEST)
    WeatherSummary findLatestWeather();



}
