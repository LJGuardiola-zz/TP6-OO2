package c.persistence.file;

import c.model.Receipt;
import c.model.ReceiptsRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static c.model.Receipt.DATE_FORMATTER;
import static java.lang.Double.parseDouble;
import static java.time.LocalDateTime.parse;

public class FileReceiptsRepository implements ReceiptsRepository {

    private final File file;

    public FileReceiptsRepository(String file) {
        this.file = new File(file);
    }

    @Override
    public List<Receipt> getAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(line -> line.split(", "))
                    .map(str -> new Receipt(
                            parseDouble(str[0]), parseDouble(str[1]), str[2], parse(str[3], DATE_FORMATTER)
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo leer el archivo de ventas.", e);
        }
    }

    @Override
    public void save(Receipt receipt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(
                    receipt.getLiters() + ", " + receipt.getMount() + ", " + receipt.getEmail() + ", " + receipt.getFormattedDate() + "\n"
            );
        } catch (IOException e) {
            throw new RuntimeException("No se pudo escribir en el archivo de ventas.", e);
        }
    }

}
