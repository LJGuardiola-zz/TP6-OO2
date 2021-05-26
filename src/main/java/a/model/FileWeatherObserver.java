package a.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static java.time.LocalDateTime.now;

public class FileWeatherObserver implements WeatherObserver {

    private final File file;

    public FileWeatherObserver(String file) {
        this.file = new File(file);
    }

    @Override
    public void update(String temperature) {
        try (Writer writer = new FileWriter(file, true)) {
            writer.write(temperature + " " + now() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo registrar la temperatura", e);
        }
    }

}
