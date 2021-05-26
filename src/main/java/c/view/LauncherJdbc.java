package c.view;

import c.model.ReceiptsRepository;
import c.persistence.jdbc.JdbcReceiptsRepository;

public class LauncherJdbc extends Launcher {

    private final ReceiptsRepository repository = new JdbcReceiptsRepository();

    @Override
    public ReceiptsRepository getRepository() {
        return repository;
    }

}
