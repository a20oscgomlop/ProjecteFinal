package com.example.WeSwingServer;

import items.DanceEventItem;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasesDades {
    public static void inserirEvent(String articleTitle, String country, String town, String date,String styles, String description){
        String consulta = "INSERT INTO EVENTS (title,country,town,date,styles,description) VALUES (?,?,?,?,?,?);";
        if(comprovarEvent(articleTitle,country,town,date,styles,description)){
            try {
                Connection conn = ConnexioBD.connexioBD();
                PreparedStatement sta = conn.prepareStatement(consulta);
                sta.setString(1,articleTitle);
                sta.setString(2,country);
                sta.setString(3,town);
                sta.setString(4,date);
                sta.setString(5,styles);
                sta.setString(6,description);
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

    public static boolean comprovarEvent(String articleTitle, String country, String town, String date,String styles, String description){
        String consulta = "SELECT * FROM EVENTS WHERE (title = ? && country = ? && town = ? && date = ? && styles = ? && description = ?);";
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setString(1,articleTitle);
            sta.setString(2,country);
            sta.setString(3,town);
            sta.setString(4,date);
            sta.setString(5,styles);
            sta.setString(6,description);
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

    public static List<DanceEventItem> totsEvents(){
        List<DanceEventItem> events = new ArrayList<>();
        String consulta = "SELECT * FROM EVENTS;";
        try{
            Connection conn = ConnexioBD.connexioBD();
            Statement sta = conn.createStatement();
            ResultSet resultat = sta.executeQuery(consulta);
            while(resultat.next()){
                events.add(new DanceEventItem(resultat.getString("title"),resultat.getString("country"),resultat.getString("town"),resultat.getString("date"),resultat.getString("styles"),resultat.getString("description"),""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return events;
    }
}
