package b.model;

public class Measurer implements Measurable {

    private final WeatherService weatherService;

    public Measurer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public String read() {
        return weatherService.temperature();
    }

}
