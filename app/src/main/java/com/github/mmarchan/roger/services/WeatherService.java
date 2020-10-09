package com.github.mmarchan.roger.services;

import com.github.mmarchan.roger.tools.Location;
import com.github.mmarchan.roger.tools.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class WeatherService {
    private final static String KEY = "5698aeff5124dc5e586ef0bcbbba5ee5\n";

    public WeatherService(){
    }

    public Weather getWeather(Location location) {
        String queryUrl = "https://api.openweathermap.org/data/2.5/onecall?lat="+ location.getLatitude()+ "&lon="+ location.getLongitude()+"&exclude=hourly,daily&units=metric&appid="+KEY;
        Weather thisWeather = null;
        JSONObject request = null;
        request = getWeatherJSONAnswer(queryUrl);
        if((request) != null){
            double temp = 0;
            String weather = "";
            try {
                temp = request.getJSONObject("current").getDouble("temp");
                weather = request.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("description");
                thisWeather = new Weather(temp,weather);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            };
        }
        return thisWeather;
    }

    private JSONObject getWeatherJSONAnswer(String queryUrl){
        URL url = null;
        JSONObject result = new JSONObject();
        try{
            url = new URL(queryUrl);

            InputStream stream = null;
            HttpsURLConnection connection = null;

            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            // Do something with responseCode

            stream = connection.getInputStream();
            // Do something with the stream

            StringBuilder textBuilder = new StringBuilder();

            try (Reader reader = new BufferedReader(new InputStreamReader
                    (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }

            result = new JSONObject(textBuilder.toString());

            stream.close();
            connection.disconnect();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return result;
    }

}
