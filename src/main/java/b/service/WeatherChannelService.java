package b.service;

import b.model.WeatherService;

import java.util.Random;

public class WeatherChannelService implements WeatherService {
    @Override
    public String temperature() {
        return new Random().nextInt(100) + "c";
    }
}
