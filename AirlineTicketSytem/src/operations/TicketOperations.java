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
    
    public static boolean deleteTicket(Ticket ticket) {
    	String query = "DELETE FROM BILETLER WHERE ID = ?";
    	try {
    		ps = con.prepareStatement(query);
    		ps.setInt(1, ticket.getId());
    		int result = ps.executeUpdate();
    		return result > 0;
    	} catch (SQLException e) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public static boolean ticketBooking(Flight flight, User user, Seat seat) {
		String findIdQuery = "SELECT ID FROM KOLTUKLAR WHERE KOLTUKNUMARASI = ?";
        String insertTicketQuery = "INSERT INTO BILETLER (YOLCUID, UCUSID, KOLTUKID, PNR)"
                + "VALUES (?,?,?,?)";
        String setReservedSeat = "UPDATE KOLTUKLAR SET REZERVEDURUMU = 1 WHERE KOLTUKNUMARASI = ?";
        String pnr;
        Boolean durum = SeatOperations.isSeatAvailable(seat.getKoltuknumarasi());
        if(durum) {
        	try {
                pnr = UserOperations.generateRandomUniquePNR();
            } catch (SQLException e) {
                Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, e);
                pnr = null;
            }
            try {
            	ps = con.prepareStatement(findIdQuery);
            	ps.setString(1, seat.getKoltuknumarasi());
            	rs = ps.executeQuery();
            	int koltukId = -1;
            	if(rs.next()) {
            		koltukId = rs.getInt("ID");
            	}
            	
            	ps = con.prepareStatement(insertTicketQuery);
                ps.setInt(1, user.getId());
                ps.setInt(2, flight.getId());
                ps.setInt(3, koltukId);
                ps.setString(4, pnr);
                ps.executeUpdate();
            	
                ps = con.prepareStatement(setReservedSeat);
                ps.setString(1, seat.getKoltuknumarasi());
                ps.executeUpdate();
                
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
        	return false;
        }
    }
    
	public static boolean deleteTicket(int id) {
		String query = "DELETE FROM BILETLER WHERE ID = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			if(result > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException ex) {
    		Logger.getLogger(TicketOperations.class.getName()).log(Level.SEVERE, null, ex);
    		return false;
    	}
	}
}
