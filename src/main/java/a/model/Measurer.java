package a.model;

public class Measurer extends WeatherObservable {

    private final WeatherService weatherService;

    public Measurer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Measurer(WeatherService weatherService, WeatherObserver... observers) {
        super(observers);
        this.weatherService = weatherService;
    }

    public String readTemperature() {
        String temperature = weatherService.temperature();
        notify(temperature);
        return temperature;
    }

}
