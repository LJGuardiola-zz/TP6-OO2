package c.persistence.jdbc;

import c.model.Receipt;
import c.model.ReceiptsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcReceiptsRepository implements ReceiptsRepository {

    private final static JdbcConnectionManager CONNECTION_MANAGER = JdbcConnectionManager.getInstance();

    @Override
    public List<Receipt> getAll() {
        try (
                Connection connection = CONNECTION_MANAGER.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM RECEIPTS"
                )
        ) {

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Receipt> receipts = new ArrayList<>();
                while (resultSet.next()) {
                    receipts.add(
                            new Receipt(
                                    resultSet.getDouble("LITERS"),
                                    resultSet.getDouble("MOUNT"),
                                    resultSet.getString("EMAIL"),
                                    resultSet.getTimestamp("DATE").toLocalDateTime()
                            )
                    );
                }
                return receipts;
            }

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener los recibos", e);
        }
    }

    @Override
    public void save(Receipt receipt) {
        try (
                Connection connection = CONNECTION_MANAGER.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO RECEIPTS (LITERS, MOUNT, EMAIL, DATE) VALUES (?, ?, ?)"
                )
        ) {

            preparedStatement.setDouble(
                    1, receipt.getLiters()
            );
            preparedStatement.setDouble(
                    2, receipt.getMount()
            );
            preparedStatement.setString(
                    3, receipt.getEmail()
            );
            preparedStatement.setTimestamp(
                    3, Timestamp.valueOf(receipt.getDate())
            );
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo registrar el recibo", e);
        }
    }

}
