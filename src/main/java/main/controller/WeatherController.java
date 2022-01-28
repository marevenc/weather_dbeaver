package main.controller;

import main.service.WeatherServiceImpl;
import main.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    private WeatherServiceImpl service;

    @Autowired
    public WeatherController(WeatherServiceImpl service){
        this.service = service;
    }

    @GetMapping("/weather")
    public String index(Model model){
        Weather weather = service.read();
        model.addAttribute("currentWeather", weather);
        return "index";
    }
}
