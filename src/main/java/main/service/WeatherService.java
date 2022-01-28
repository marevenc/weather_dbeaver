package main.service;

import main.model.Weather;

public interface WeatherService {
    Weather read();
    Weather create();
}
