package b.model;

public class MeasureObservableDecorator extends WeatherObservable implements Measurable {

    private final Measurer measurer;

    public MeasureObservableDecorator(Measurer measurer, WeatherObserver... observers) {
        super(observers);
        this.measurer = measurer;
    }

    @Override
    public String read() {
        String temperature = measurer.read();
        notify(temperature);
        return temperature;
    }

}
