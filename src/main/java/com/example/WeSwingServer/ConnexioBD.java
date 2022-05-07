package com.example.WeSwingServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnexioBD {

    private static Properties properties = new Properties();
    /**
     * Mètode connexioBD que crea una connexió amb la base de dades i la retorna
     * @return Connection que representa la connexió amb la base de dades
     * @throws SQLException
     * @throws IOException
     */
    public static Connection connexioBD() throws SQLException, IOException {
        properties.load(new FileInputStream(new File("fitxers/dbConnection.properties")));
        Connection conn= DriverManager.getConnection(properties.get("dburl").toString(), properties.get("user").toString(), properties.get("pwd").toString());
        //System.out.println("S'ha connectat a la BD");
        return conn;
    }

    /**
     * Mètode que rep una connexió per paràmetre i la tanca
     * @param conn La connexió que es vol tancar
     * @throws SQLException
     */
    public static void tancaBD(Connection conn) throws SQLException {
        //System.out.println("Es tanca BD");
        conn.close();
    }

}