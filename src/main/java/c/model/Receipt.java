package c.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.time.LocalDateTime.now;

public class Receipt {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final double mount;
    private final double liters;
    private final String email;
    private final LocalDateTime date;

    public Receipt(double liters, double mount, String email) {
        this(liters, mount, email, now());
    }

    public Receipt(double liters, double mount, String email, LocalDateTime date) {

        if (liters <= 0)
            throw new RuntimeException("La cantidad de combustible debe ser mayor a cero.");
        this.liters = liters;

        if (mount <= 0)
            throw new RuntimeException("La cantidad de combustible debe ser mayor a cero.");
        this.mount = mount;

        if (email == null || email.isEmpty())
            throw new RuntimeException("Debe ingresar el email.");
        if (!validEmail(email))
            throw new RuntimeException("El email ingresado no es válido.");
        this.email = email;

        if (date.isAfter(now()))
            throw new RuntimeException("La fecha debe ser del pasado.");
        this.date = date;

    }

    public double getLiters() {
        return liters;
    }

    public double getMount() {
        return mount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFormattedDate() {
        return date.format(DATE_FORMATTER);
    }

    public String getEmail() {
        return email;
    }

    private boolean validEmail(String email) {
        return Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        ).matcher(email).find();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Double.compare(receipt.mount, mount) == 0 && Double.compare(receipt.liters, liters) == 0 &&
                date.equals(receipt.date) && email.equals(receipt.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mount, liters, date, email);
    }

}
