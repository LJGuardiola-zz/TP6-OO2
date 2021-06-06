package b;

import b.model.*;
import b.service.WeatherChannelService;

public class Main {
    public static void main(String[] args) {
        Measurable measurable = new MeasureObservableDecorator(
                new Measurer(
                        new WeatherChannelService()
                ),
                new ConsoleWeatherObserver(),
                new FileWeatherObserver("reg.txt")
        );

        String temperature = measurable.read();
        System.out.println("La temperatura es de " + temperature);
    }
}
