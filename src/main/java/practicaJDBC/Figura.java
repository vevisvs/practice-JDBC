package practicaJDBC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class Figura {
        private static final Logger logger = LogManager.getLogger(Figura.class);
        private final static String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "log4j2.xml";

        private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS FIGURA;";
        private static final String CREATE_TABLE
                = "CREATE TABLE FIGURA (ID INT PRIMARY KEY, NOMBRE VARCHAR (50) NOT NULL, COLOR VARCHAR (50) NOT NULL)";

        private static final String INSERT_FIGURA = "INSERT INTO FIGURA ( ID, NOMBRE, COLOR) VALUES (1, 'CIRCULO', 'ROJO');";
        private static final String INSERT_FIGURA1 = "INSERT INTO FIGURA ( ID, NOMBRE, COLOR) VALUES (2, 'TRIANGULO', 'AZUL');";
        private static final String INSERT_FIGURA2 = "INSERT INTO FIGURA ( ID, NOMBRE, COLOR) VALUES (3, 'CUADRADO', 'AMARILLO');";



        private static final String SELECT_ALL = "SELECT * FROM FIGURA";

        public static void main(String[] args) throws SQLException {
            var connection = obtenerConexion();
            var statement = connection.createStatement();
            statement.execute(DROP_TABLE_IF_EXISTS);
            statement.execute(CREATE_TABLE);
            statement.execute(INSERT_FIGURA);
            statement.execute(INSERT_FIGURA1);
            statement.execute(INSERT_FIGURA2);
            mostrarFigura(connection);


            mostrarFigura(connection);

            connection.close();
        }

        private static void mostrarFigura(Connection connection) throws SQLException {
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                // System.out.println("ID: " + resultSet.getInt(1) + " NOMBRE: " + resultSet.getString(2) + " TIPO: " + resultSet.getString(3));
                logger.info("ID: " + resultSet.getInt(1) + " NOMBRE: " + resultSet.getString(3) + " COLOR: " + resultSet.getString(2));
            }
        }

        private static void startLogger() throws IOException {
            var source = new ConfigurationSource(new FileInputStream(log4jConfigFile));
            Configurator.initialize(null, source);
        }

        private static Connection obtenerConexion() throws SQLException {
            return DriverManager.getConnection("jdbc:h2:~/digital1");
        }
    }


