package c.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FuelStation extends FuelStationObservable {

    private final ReceiptsRepository repository;

    public FuelStation(ReceiptsRepository repository, FuelStationObserver... observers) {
        super(observers);
        this.repository = repository;
    }

    public double getFinalCost(Fuel fuel, Integer liters) {
        return new Sale(fuel, liters).getFinalCost();
    }

    public Receipt pay(Fuel fuel, Integer liters, String email) {
        Receipt receipt = new Sale(fuel, liters).pay(email);
        repository.save(receipt);
        notify(receipt);
        return receipt;
    }

    public List<Receipt> getReceipts(LocalDate start, LocalDate end) {
        if (start == null || end == null){
            throw new RuntimeException("Ingresa las fechas \"Desde\" y \"Hasta\"");
        }
        if (start.isAfter(end)){
            throw new RuntimeException("La fecha \"Desde\" debe ser menor a la fecha \"Hasta\"");
        }
        return repository.getAll().stream().filter(
                receipt -> isBetween(receipt.getDate().toLocalDate(), start, end)
        ).collect(Collectors.toList());
    }

    private boolean isBetween(LocalDate date, LocalDate start, LocalDate end) {
        return (date.equals(start) || date.isAfter(start)) && (date.equals(end) || date.isBefore(end));
    }

}
