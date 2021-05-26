package b.model;

public class ConsoleWeatherObserver implements WeatherObserver {
    @Override
    public void update(String temperature) {
        int temp;
        try {
            temp = Integer.parseInt(
                    temperature.replace("c", "")
            );
        } catch (NumberFormatException e) {
            throw new RuntimeException("La temperatura no tiene un formato válido", e);
        }
        if (temp < 12) {
            System.out.println("Hace frio, se encenderá la caldera");
        } else if (temp > 17) {
            System.out.println("Hace calor, se encenderá el aire acondicionado");
        }
    }
}
