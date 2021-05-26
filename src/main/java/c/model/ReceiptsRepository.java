package c.model;

import java.util.List;

public interface ReceiptsRepository {
    List<Receipt> getAll();
    void save(Receipt receipt);
}
