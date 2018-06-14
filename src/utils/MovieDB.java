package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDB {

    private OracleConnection oracle = new OracleConnection();

    // This returns the movie's info (duration, genre and censor) given a movieTitle
    public MovieInfo getMovieInfo (String movieTitle) {
        MovieInfo result = null;
        try {
            oracle.connect();
            // This PreparedStatement works:
            PreparedStatement ps = oracle.conn.prepareStatement(
                    "SELECT Title, Duration, Genre, Censor FROM Movie " +
                            "WHERE Title = ?");
            ps.setString(1, movieTitle);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int duration = rs.getInt("Duration");
                String genre = rs.getString("Genre");
                String censor = rs.getString("Censor");
                result = new MovieInfo(duration, genre, censor);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } return result;
    }
}
