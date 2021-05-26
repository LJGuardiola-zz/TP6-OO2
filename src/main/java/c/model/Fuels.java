package c.model;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.LocalDateTime.now;

public enum Fuels implements Fuel {

    SUPER(90) {
        @Override
        double applyDiscount(double liters, double cost) {
            DayOfWeek dayOfWeek = now().getDayOfWeek();
            if (dayOfWeek.equals(SUNDAY)) {
                cost -= cost * 0.1;
            } else if (dayOfWeek.equals(SATURDAY) && liters > 20) {
                cost -= cost * 0.12;
            }
            return cost;
        }
    },

    COMUN(70) {
        @Override
        double applyDiscount(double liters, double cost) {
            int hour = now().getHour();
            if (hour >= 8 && hour <= 10) {
                cost -= cost * 0.05;
            }
            return cost;
        }
    };

    private final double price;

    Fuels(double price) {
        this.price = price;
    }

    @Override
    public double getCost(int liters) {
        return applyDiscount(liters, liters * price);
    }

    abstract double applyDiscount(double liters, double cost);

}
