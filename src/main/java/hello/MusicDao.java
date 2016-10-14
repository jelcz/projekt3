package hello;
//STEP 1. Import required packages

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MusicDao {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:6033/uczelnia";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public LinkedList<Genre> getGenres() {
        LinkedList<Genre> genreList = new LinkedList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM genres";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Genre genre = new Genre(rs.getInt("genreId"), rs.getString("genreName"));
                genreList.add(genre);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return genreList;
    }

    public LinkedList<Song> getSongs() {
        LinkedList<Song> trackList = new LinkedList<>();
        HashMap<String, Song> trackNamesList = new HashMap<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM tracks";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name

                String trackName = rs.getString("trackName");

                Song song = new Song(rs.getInt("trackId"), trackName, rs.getString("trackArtistName")
                        , rs.getString("trackTag1"), rs.getString("trackTag2"), rs.getString("trackTag3"), rs.getString("trackTag4")
                        , rs.getString("trackTag5"));
                if (!trackNamesList.containsKey(trackName)) {
                    trackList.add(song);
                    trackNamesList.put(trackName, song);
                }
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return trackList;
    }

    public LinkedList<Genre> getGenreClosestAssocs(String genreName) {
        LinkedList<Genre> genreList = new LinkedList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "select genreId, genreName from genres where (\n" +
                    "genreId in (\n" +
                    "select assocGenreIdTo from genresAssoc where (\n" +
                    "assocGenreIdFrom = (\n" +
                    "select genreId from genres where\n" +
                    "genreName = '" + genreName + "'" +
                    "))))OR(\n" +
                    "genreId in (\n" +
                    "select assocGenreIdFrom from genresAssoc where (\n" +
                    "assocGenreIdTo = (\n" +
                    "select genreId from genres where\n" +
                    "genreName = '" + genreName + "'))));";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Genre genre = new Genre(rs.getInt("genreId"), rs.getString("genreName"));

                genreList.add(genre);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return genreList;
    }

    public void truncateTable(String tableName){
        ArrayList<Genre> genreList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "TRUNCATE TABLE " + tableName;
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

    }

    public void createTable(String tableName, String fields){
        ArrayList<Genre> genreList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "TRUNCATE TABLE " + tableName;
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

    }

    public void insertIntoTracks(int trackId, String trackName, String trackArtistName, String trackTag1,
                                 String trackTag2, String trackTag3, String trackTag4, String trackTag5) {
        ArrayList<Genre> genreList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        trackName = trackName.replace("\'", "");
        trackArtistName = trackArtistName.replace("\'", "");
        trackTag1= trackTag1.replace("\'", "");
        trackTag2 = trackTag2.replace("\'", "");
        trackTag3 = trackTag3.replace("\'", "");
        trackTag4= trackTag4.replace("\'", "");
        trackTag5= trackTag5.replace("\'", "");

        trackName = trackName.replace("\"", "");
        trackArtistName = trackArtistName.replace("\"", "");
        trackTag1= trackTag1.replace("\"", "");
        trackTag2 = trackTag2.replace("\"", "");
        trackTag3 = trackTag3.replace("\"", "");
        trackTag4= trackTag4.replace("\"", "");
        trackTag5= trackTag5.replace("\"", "");

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;

            sql = "INSERT INTO songs (trackId, trackName, trackArtistName, trackTag1, trackTag2, trackTag3, trackTag4, trackTag5) " +
                    "VALUES ("+ trackId + ", '" + trackName + "', '" + trackArtistName + "', '"
                    + trackTag1 +  "', '" + trackTag2 +  "', '" + trackTag3 +  "', '" + trackTag4 +  "', '" + trackTag5
                    + "') ON DUPLICATE KEY UPDATE trackName = '" + trackName + "';";


            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void insertIntoGenres(int id, String name) {
        ArrayList<Genre> genreList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO genres (genreId, genreName) VALUES(" + id +", \'" + name + "\')";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void insertIntoGenresAssoc(int id, int idFrom, int idTo) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO genresAssoc (assocId, assocGenreIdFrom, assocGenreIdTo) VALUES("
                    + id + ", " + idFrom + ", " + idTo  + ")";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}