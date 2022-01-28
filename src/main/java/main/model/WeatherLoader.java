package main.model;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class WeatherLoader {
    public static String getWeatherFromSite(String SITE_URL) throws IOException {
        Document doc = Jsoup.connect(SITE_URL).get();
        Element weather = doc.select("div.weather__temp").first();
        return weather.text();
    }

}
