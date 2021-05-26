package a;

import a.model.ConsoleWeatherObserver;
import a.model.FileWeatherObserver;
import a.model.Measurer;
import a.service.WeatherChannelService;

public class Main {
    public static void main(String[] args) {
        Measurer measurer = new Measurer(
                new WeatherChannelService(),
                new ConsoleWeatherObserver(),
                new FileWeatherObserver("reg.txt")
        );
        String temperature = measurer.readTemperature();
        System.out.println("La temperatura es de " + temperature);
    }
}
