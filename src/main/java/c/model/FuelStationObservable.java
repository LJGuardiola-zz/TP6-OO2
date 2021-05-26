package c.model;

import java.util.List;

import static java.util.Arrays.asList;

public class FuelStationObservable {

    private final List<FuelStationObserver> observers;

    public FuelStationObservable(FuelStationObserver... observers) {
        this.observers = asList(observers);
    }

    protected void notify(Receipt receipt) {
        observers.forEach(
                observer -> observer.update(receipt)
        );
    }

}
