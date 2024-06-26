package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Flight;
import model.Seat;
import system.DBConnection;

public class SeatOperations {

    private static final DBConnection conn = new DBConnection();
    private static final Connection con = conn.connDb();
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static boolean isSeatAvailable(String koltuknumarasi) {
    	String query = "SELECT K.KOLTUKNUMARASI "
    			+ "FROM "
    			+ "BILETLER B "
    			+ "JOIN "
    			+ "KOLTUKLAR K ON K.ID = B.KOLTUKID "
    			+ "WHERE KOLTUKNUMARASI = ?";
    	try {
    		ps = con.prepareStatement(query);
    		ps.setString(1,koltuknumarasi);
    		rs = ps.executeQuery();
    		if(rs.next())
    			return false;
    		else
    			return true;
    	} catch(SQLException ex) {
    		Logger.getLogger(SeatOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
    	}
    }
    
    public static Seat getSeat(Flight flight, int koltukId) {
    	String query = "SELECT ID, KOLTUKNUMARASI, KOLTUKTURU FROM KOLTUKLAR WHERE ID = ?";
    	Seat seat = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, koltukId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String koltuknumarasi = rs.getString(2);
                String koltukturu = rs.getString(3);
                seat = new Seat(id, koltuknumarasi, koltukturu, flight);
            }
            return seat;
        } catch (SQLException ex) {
            Logger.getLogger(SeatOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
    public static Seat[] getSeats(Flight flight) {
        String query = "SELECT ID, KOLTUKNUMARASI, KOLTUKTURU FROM KOLTUKLAR WHERE UCUSID = ? AND REZERVEDURUMU = 0";
        try {
            ArrayList<Seat> seatList = new ArrayList<>();
            ps = con.prepareStatement(query);
            ps.setInt(1, flight.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String koltuknumarasi = rs.getString(2);
                String koltukturu = rs.getString(3);
                Seat seat = new Seat(id, koltuknumarasi, koltukturu, flight);
                seatList.add(seat);
            }

            Seat[] seats = seatList.toArray(Seat[]::new);

            return seats;
        } catch (SQLException ex) {
            Logger.getLogger(SeatOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
