package b;

import b.model.ConsoleWeatherObserver;
import b.model.FileWeatherObserver;
import b.model.Measurer;
import b.service.WeatherChannelService;

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
