package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Flight;
import model.Seat;
import system.DBConnection;

public class SeatOperations {

    private final DBConnection conn = new DBConnection();
    private final Connection con = conn.connDb();
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private final Flight flight;

    public SeatOperations(Flight flight) {
        this.flight = flight;
    }

    public boolean isSeatAvailable(String koltuknumarasi) {
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
    
    public Seat[] getSeats() {
        String query = "SELECT ID, KOLTUKNUMARASI, KOLTUKTURU FROM KOLTUKLAR WHERE REZERVEDURUMU = 0";
        try {
            ArrayList<Seat> seatList = new ArrayList<>();
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String koltuknumarasi = rs.getString(2);
                String koltukturu = rs.getString(3);
                Seat seat = new Seat(id, koltuknumarasi, koltukturu, this.flight);
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
