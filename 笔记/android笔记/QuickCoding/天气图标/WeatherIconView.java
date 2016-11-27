package com.vgaw.weathericontest;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Weather Icon View allows you to create Weather Icon for your Android application
 * It's based on Erik Flowers project located at: https://github.com/erikflowers/weather-icons
 * This project is open-source and can be found at: https://github.com/pwittchen/WeatherIconView
 * @author Piotr Wittchen
 */
public class WeatherIconView extends TextView {
    private final static String PATH_TO_WEATHER_FONT = "weather.ttf";

    public WeatherIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets(), PATH_TO_WEATHER_FONT));
    }
}