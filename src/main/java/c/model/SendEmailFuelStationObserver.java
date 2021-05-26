package c.model;

public class SendEmailFuelStationObserver implements FuelStationObserver {

    private final EmailService emailService;

    public SendEmailFuelStationObserver(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void update(Receipt receipt) {
        emailService.send(
                "sales@ypzw.com",
                receipt.getEmail(),
                "Pago registrado",
                "Se registró su pago de $" + receipt.getMount() + ". Gracias por su compra!"
        );
    }
}
