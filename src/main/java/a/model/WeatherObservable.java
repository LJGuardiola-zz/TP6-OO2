package a.model;

import java.util.List;

import static java.util.Arrays.asList;

public class WeatherObservable {

    private final List<WeatherObserver> observers;

    public WeatherObservable(WeatherObserver... observers) {
        this.observers = asList(observers);
    }

    protected void notify(String temperature) {
        observers.forEach(
                observer -> observer.update(temperature)
        );
    }

}
