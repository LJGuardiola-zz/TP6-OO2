package c.model;

public class Sale {

    private final Fuel fuel;
    private final Integer liters;

    public Sale(Fuel fuel, Integer liters) {

        if (fuel == null)
            throw new RuntimeException("El tipo de combustible no puede ser nulo.");
        this.fuel = fuel;

        if (liters == null)
            throw new RuntimeException("Debe ingresar la cantidad de combustible.");
        if (liters <= 0)
            throw new RuntimeException("La cantidad de combustible debe ser mayor a cero.");
        this.liters = liters;

    }

    public double getFinalCost() {
        return fuel.getCost(liters);
    }

    public Receipt pay(String email) {
        return new Receipt(liters, getFinalCost(), email);
    }

}
