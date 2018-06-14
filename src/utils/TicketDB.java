package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDB {

    private OracleConnection oracle = new OracleConnection();

    // This returns the number of tickets show given the Movie Title and the start time of the Showtime
    public int ticketSoldPerMoviePerShowtime (String movieTitlle, String showTime) {
        int ticketSold = -1;
        try {
            oracle.connect();
            PreparedStatement ps = oracle.conn.prepareStatement(
                    "SELECT COUNT(TICKET_NUM) FROM Ticket " +
                    "WHERE TITLE = ? AND START_TIME = ?");
            ps.setString(1, movieTitlle);
            ps.setString(2, showTime);
            ResultSet rs = ps.executeQuery();
            // DO NOT USE FETCH SIZE, GET RESULT FROM rs
            ticketSold = rs.getFetchSize();
            System.out.println(ticketSold);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } return ticketSold;
    }


}
