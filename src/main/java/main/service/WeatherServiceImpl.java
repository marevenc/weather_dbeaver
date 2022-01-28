package main.service;

import main.model.Weather;
import main.model.WeatherLoader;
import main.model.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private WeatherLoader weatherLoader = new WeatherLoader();

    @Override
    public Weather read() {
        Weather weather = null;

        if(weatherRepository == null){
            return create();
        } else {
            Iterable<Weather> weatherIterable = weatherRepository.findAll();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date(System.currentTimeMillis());
            String currentDateSQL = dateFormat.format(currentDate);

            for(Weather weatherDB : weatherIterable){
                if(weatherDB.getDate().equals(currentDateSQL)){
                    weather = weatherDB;
                }
            }
            if(weather == null){
                weather = create();
            }
            return weather;
        }
    }

    @Override
    public Weather create() {
        String weatherFromSite = null;
        try {
            weatherFromSite = WeatherLoader.getWeatherFromSite("https://yandex.ru");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Weather weather = new Weather(new Date(System.currentTimeMillis()), weatherFromSite);
        weatherRepository.save(weather);
        return weather;
    }
}
