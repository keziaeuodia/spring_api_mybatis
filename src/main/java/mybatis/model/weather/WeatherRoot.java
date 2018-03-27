package mybatis.model.weather;

public class WeatherRoot {

    WeatherList [] list;
    City city;

    public WeatherList[] getList() {
        return list;
    }

    public void setList(WeatherList[] list) {
        this.list = list;
    }
}
