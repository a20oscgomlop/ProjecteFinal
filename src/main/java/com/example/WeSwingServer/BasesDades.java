package com.example.WeSwingServer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasesDades {
    
    public static void inserirEvent(String titol, String link, String pais, String dia){
        String consulta = "INSERT INTO EVENTOS (titol,link,pais,dia) VALUES (?,?,?,?);";
        if(comprovarEvent(titol,link,pais,dia)){
            try {
                Connection conn = ConnexioBD.connexioBD();
                PreparedStatement sta = conn.prepareStatement(consulta);
                sta.setString(1,titol);
                sta.setString(2,link);
                sta.setString(3,pais);
                sta.setString(4,dia);
                sta.executeUpdate();
                sta.close();
                ConnexioBD.tancaBD(conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("Event ja existent");
        }
    }

    public static boolean comprovarEvent(String titol, String link, String pais, String dia){
        String consulta = "SELECT * FROM EVENTOS WHERE (titol = ? && link = ? && pais = ? && dia = ? );";
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setString(1,titol);
            sta.setString(2,link);
            sta.setString(3,pais);
            sta.setString(4,dia);
            ResultSet resultat = sta.executeQuery();
            if(!resultat.next()){
                sta.close();
                return true;
            }
            else{
                sta.close();
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
