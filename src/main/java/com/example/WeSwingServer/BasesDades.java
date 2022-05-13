package com.example.WeSwingServer;

import items.DanceEventItem;
import items.DanceEventItemID;
import profile.Profile;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasesDades {
    public static void inserirEvent(String articleTitle, String country, String town, String date,String styles, String description, String organizer){
        String consulta = "INSERT INTO EVENTS (title,country,town,date,styles,description,organizer) VALUES (?,?,?,?,?,?,?);";
        if(comprovarEvent(articleTitle,country,town,date,styles,description,organizer)){
            try {
                Connection conn = ConnexioBD.connexioBD();
                PreparedStatement sta = conn.prepareStatement(consulta);
                sta.setString(1,articleTitle);
                sta.setString(2,country);
                sta.setString(3,town);
                sta.setString(4,date);
                sta.setString(5,styles);
                sta.setString(6,description);
                sta.setString(7,organizer);
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

    public static boolean comprovarEvent(String articleTitle, String country, String town, String date,String styles, String description, String organizer){
        String consulta = "SELECT * FROM EVENTS WHERE (title = ? && country = ? && town = ? && date = ? && styles = ? && description = ? && organizer = ?);";
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setString(1,articleTitle);
            sta.setString(2,country);
            sta.setString(3,town);
            sta.setString(4,date);
            sta.setString(5,styles);
            sta.setString(6,description);
            sta.setString(7,organizer);
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


    public static List<DanceEventItemID> totsEvents(){
        List<DanceEventItemID> events = new ArrayList<>();
        String consulta = "SELECT * FROM EVENTS;";
        try{
            Connection conn = ConnexioBD.connexioBD();
            Statement sta = conn.createStatement();
            ResultSet resultat = sta.executeQuery(consulta);
            while(resultat.next()){
                events.add(new DanceEventItemID(resultat.getInt("id"),resultat.getString("title"),resultat.getString("country"),resultat.getString("town"),resultat.getString("date"),resultat.getString("styles"),resultat.getString("description"),""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    public static List<DanceEventItemID> unEvent(String idEvent){
        List<DanceEventItemID> events = new ArrayList<>();
        String consulta = "SELECT * FROM EVENTS WHERE id = ?;";
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setInt(1,Integer.parseInt(idEvent));
            ResultSet resultat = sta.executeQuery();
            while(resultat.next()){
                events.add(new DanceEventItemID(resultat.getInt("id"),resultat.getString("title"),resultat.getString("country"),resultat.getString("town"),resultat.getString("date"),resultat.getString("styles"),resultat.getString("description"),""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    public static void updateUser(String username,String fullname, String birthday, String email, String gender, String country,String language, String description){
        String consulta = "UPDATE USER SET full_name = ? , birth_date = ? , email = ?, gender = ?, country = ?, language = ? ,description = ? WHERE username = ?;";
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setString(1,fullname);
            sta.setString(2,birthday);
            sta.setString(3,email);
            sta.setString(4,gender);
            sta.setString(5,country);
            sta.setString(6,language);
            sta.setString(7,description);
            sta.setString(8,username);
            sta.executeUpdate();
            sta.close();
            ConnexioBD.tancaBD(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertUser(String username,String fullname, String birthday, String email, String gender, String country,String language){
        String consulta = "INSERT INTO USER (username,full_name,birth_date,email,gender,country,language) VALUES (?,?,?,?,?,?,?);";
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setString(1,username);
            sta.setString(2,fullname);
            sta.setString(3,birthday);
            sta.setString(4,email);
            sta.setString(5,gender);
            sta.setString(6,country);
            sta.setString(7,language);
            sta.executeUpdate();
            sta.close();
            ConnexioBD.tancaBD(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Profile getUser(String username){
        String consulta = "SELECT * FROM USER WHERE username = ?;";
        Profile profile = null;
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setString(1,username);
            ResultSet resultat = sta.executeQuery();
            resultat.next();
            profile = new Profile(resultat.getString("username"),resultat.getString("full_name"),resultat.getString("birth_date"),resultat.getString("email"),resultat.getString("gender"),resultat.getString("country"),resultat.getString("language"),resultat.getString("description"));
            sta.close();
            ConnexioBD.tancaBD(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return profile;
    }

    public static List<Profile> getUserList(String fullname){
        String consulta = "SELECT * FROM USER WHERE full_name LIKE %?%";
        List<Profile> profiles = new ArrayList<>();
        try{
            Connection conn = ConnexioBD.connexioBD();
            PreparedStatement sta = conn.prepareStatement(consulta);
            sta.setString(1,fullname);
            ResultSet resultat = sta.executeQuery();
            while(resultat.next()){
                profiles.add(new Profile(resultat.getString("username"),resultat.getString("full_name"),resultat.getString("birth_date"),resultat.getString("email"),resultat.getString("gender"),resultat.getString("country"),resultat.getString("language"),resultat.getString("description")));
            }
            sta.close();
            ConnexioBD.tancaBD(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return profiles;
    }
}
