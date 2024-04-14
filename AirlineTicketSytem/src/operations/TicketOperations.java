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
import model.Ticket;
import model.User;
import system.DBConnection;

public class TicketOperations {
	private static final DBConnection conn = new DBConnection();
    private static final Connection con = conn.connDb();
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    
    public static Ticket[] getTickets(User user) {
    	String query = "SELECT ID, UCUSID, KOLTUKID, PNR FROM BILETLER WHERE YOLCUID = ?";
    	try {
    		ps = con.prepareStatement(query);
    		ps.setInt(1, user.getId());
    		rs = ps.executeQuery();
    		ArrayList<Ticket> ticketList = new ArrayList<>();
    		while(rs.next()) {
    			int id = rs.getInt(1);
    			int ucusId = rs.getInt(2);
    			int koltukId = rs.getInt(3);
    			String pnr = rs.getString(4);
    			
    			Flight flight = FlightOperations.getFlight(ucusId);
    			Seat seat = SeatOperations.getSeat(flight, koltukId);
    			
    			Ticket ticket = new Ticket(id, pnr, user, flight, seat);
    			ticketList.add(ticket);
    		}
    		
    		Ticket[] tickets = ticketList.toArray(Ticket[]::new);
    		
    		return tickets;
    	} catch(SQLException ex) {
    		Logger.getLogger(TicketOperations.class.getName()).log(Level.SEVERE, null, ex);
    		return null;
    	}
    }
}
