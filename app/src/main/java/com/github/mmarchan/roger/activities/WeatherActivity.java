package com.github.mmarchan.roger.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.github.mmarchan.roger.R;
import com.github.mmarchan.roger.services.GeocodingService;
import com.github.mmarchan.roger.services.LocationService;
import com.github.mmarchan.roger.services.WeatherService;
import com.github.mmarchan.roger.tools.Location;
import com.github.mmarchan.roger.tools.Weather;

public class WeatherActivity extends AppCompatActivity {
    private final static int REQUEST_LOCATION = 44;

    private EditText place;
    private ToggleButton localisation;
    private Button findMeteo;
    private LocationService location;
    private WeatherService ws;
    private GeocodingService gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        place = findViewById(R.id.localisation_editText);
        localisation = findViewById(R.id.localisation_btn);
        findMeteo = findViewById(R.id.search_weather_btn);

        findMeteo.setOnClickListener(v -> {
            Location loc = null;
            if(localisation.isChecked()){
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                    return;
                }

                 loc = location.getLocation();
                place.setText("longitude : " + loc.getLongitude());

            }else{
                String location = place.getText().toString();
                loc = gs.getLocationByName(location);
            }

            Weather w = ws.getWeather(loc);
            place.setText("Weather : " + w.getWeather());

        });

        localisation.setOnClickListener(v -> {
                if(localisation.isChecked()){
                    place.setEnabled(false);
                    place.setInputType(InputType.TYPE_NULL);
                }else{
                    place.setEnabled(true);
                    place.setInputType(InputType.TYPE_CLASS_TEXT);
                }

            }
        );

        location = new LocationService(this);
        ws = new WeatherService(this);
        gs = new GeocodingService(this);
    }

}