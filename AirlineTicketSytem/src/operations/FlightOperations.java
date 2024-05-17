package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Flight;
import system.DBConnection;

public class FlightOperations {

    private static final DBConnection conn = new DBConnection();
    private static final Connection con = conn.connDb();
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean isFlightAvailable(String source, String arrival, String time) {
        String sourceQuery = "SELECT ID FROM HAVAALANLARI WHERE HAVAALANI = ?";
        String arrivalQuery = "SELECT ID FROM HAVAALANLARI WHERE HAVAALANI = ?";

        int sourceAirportID = -1;
        int arrivalAirportID = -1;
        String query = "SELECT "
                + "H1.HAVAALANI, "
                + "H2.HAVAALANI, "
                + "UC.KALKISTARIHI "
                + "FROM "
                + "UCUSLAR UC "
                + "JOIN "
                + "HAVAALANLARI H1 ON H1.ID = UC.KALKISYERIID "
                + "JOIN "
                + "HAVAALANLARI H2 ON H2.ID = UC.VARISYERIID "
                + "WHERE "
                + "UC.KALKISYERIID = ? "
                + "AND UC.VARISYERIID = ? "
                + "AND UC.KALKISTARIHI = ?";
        try {
            ps = con.prepareStatement(sourceQuery);
            ps.setString(1, source);
            rs = ps.executeQuery();
            if (rs.next()) {
                sourceAirportID = rs.getInt("ID");
            }

            ps = con.prepareStatement(arrivalQuery);
            ps.setString(1, arrival);
            rs = ps.executeQuery();
            if (rs.next()) {
                arrivalAirportID = rs.getInt("ID");
            }

            ps = con.prepareStatement(query);
            ps.setInt(1, sourceAirportID);
            ps.setInt(2, arrivalAirportID);
            ps.setString(3, time);
            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(FlightOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public static String getFlightStatus(String flightNumber) {
		String query = "SELECT D.UCUSDURUMU "
				+ "FROM "
				+ "UCUSLAR U "
				+ "JOIN "
				+ "DURUMLAR D ON D.ID = U.DURUMID "
				+ "WHERE UCUSNO = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, flightNumber);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String flightStatus = rs.getString(1);
				return flightStatus;
			} else {
				return null;
			}
			
		} catch(SQLException ex) {
			Logger.getLogger(FlightOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
		}
    }
    
    public static Flight getFlight(int ucusid) {
    	String query = "SELECT "
                + "UC.ID, "
                + "UCAK.MODEL, "
                + "H1.HAVAALANI, "
                + "H2.HAVAALANI, "
                + "UC.KALKISTARIHI, "
                + "UC.VARISTARIHI,"
                + "UC.KALKISZAMANI, "
                + "UC.VARISZAMANI, "
                + "D.UCUSDURUMU,"
                + "UC.UCUSNO, "
                + "UC.BILETFIYATI  "
                + "FROM "
                + "UCUSLAR UC "
                + "JOIN "
                + "UCAKLAR UCAK ON UCAK.ID = UC.UCAKID "
                + "JOIN "
                + "HAVAALANLARI H1 ON H1.ID = UC.KALKISYERIID "
                + "JOIN "
                + "HAVAALANLARI H2 ON H2.ID = UC.VARISYERIID "
                + "JOIN "
                + "DURUMLAR D ON D.ID = UC.DURUMID "
                + "WHERE "
                + "UC.ID = ?";
    	Flight flight = null;
    	try {
    		ps = con.prepareStatement(query);
    		ps.setInt(1, ucusid);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			int id = rs.getInt(1);
                String ucak = rs.getString(2);
                String kalkisYeri = rs.getString(3);
                String varisYeri = rs.getString(4);
                String kalkisTarihi = rs.getString(5);
                String varisTarihi = rs.getString(6);
                String kalkisZamani = rs.getString(7);
                String varisZamani = rs.getString(8);
                String durum = rs.getString(9);
                String ucusNo = rs.getString(10);
                int biletFiyati = rs.getInt(11);

                flight = new Flight(id, ucak, kalkisYeri, varisYeri, kalkisTarihi, varisTarihi, 
                		kalkisZamani, varisZamani, durum, ucusNo, biletFiyati);
    		}
    		return flight;
    	} catch(SQLException ex) {
            Logger.getLogger(FlightOperations.class.getName()).log(Level.SEVERE, null, ex);
    		return null;
    	}
    }

    public static Flight[] getFlights(String source, String arrival, String time) {

        String sourceQuery = "SELECT ID FROM HAVAALANLARI WHERE HAVAALANI = ?";
        String arrivalQuery = "SELECT ID FROM HAVAALANLARI WHERE HAVAALANI = ?";

        int sourceAirportID = -1;
        int arrivalAirportID = -1;
        
        String query = "SELECT "
                + "UC.ID, "
                + "UCAK.MODEL, "
                + "H1.HAVAALANI, "
                + "H2.HAVAALANI, "
                + "UC.KALKISTARIHI, "
                + "UC.VARISTARIHI,"
                + "UC.KALKISZAMANI, "
                + "UC.VARISZAMANI, "
                + "D.UCUSDURUMU,"
                + "UC.UCUSNO, "
                + "UC.BILETFIYATI "
                + "FROM "
                + "UCUSLAR UC "
                + "JOIN "
                + "UCAKLAR UCAK ON UCAK.ID = UC.UCAKID "
                + "JOIN "
                + "HAVAALANLARI H1 ON H1.ID = UC.KALKISYERIID "
                + "JOIN "
                + "HAVAALANLARI H2 ON H2.ID = UC.VARISYERIID "
                + "JOIN "
                + "DURUMLAR D ON D.ID = UC.DURUMID "
                + "WHERE "
                + "UC.KALKISYERIID = ? "
                + "AND UC.VARISYERIID = ? "
                + "AND UC.KALKISTARIHI = ?";

        try {
            ps = con.prepareStatement(sourceQuery);
            ps.setString(1, source);
            rs = ps.executeQuery();
            if (rs.next()) {
                sourceAirportID = rs.getInt("ID");
            }

            ps = con.prepareStatement(arrivalQuery);
            ps.setString(1, arrival);
            rs = ps.executeQuery();
            if (rs.next()) {
                arrivalAirportID = rs.getInt("ID");
            }

            ps = con.prepareStatement(query);
            ps.setInt(1, sourceAirportID);
            ps.setInt(2, arrivalAirportID);
            ps.setString(3, time);
            rs = ps.executeQuery();

            ArrayList<Flight> flightList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt(1);
                String ucak = rs.getString(2);
                String kalkisYeri = rs.getString(3);
                String varisYeri = rs.getString(4);
                String kalkisTarihi = rs.getString(5);
                String varisTarihi = rs.getString(6);
                String kalkisZamani = rs.getString(7);
                String varisZamani = rs.getString(8);
                String durum = rs.getString(9);
                String ucusNo = rs.getString(10);
                int biletFiyati = rs.getInt(11);

                Flight flight = new Flight(id, ucak, kalkisYeri, varisYeri, kalkisTarihi, 
                		varisTarihi, kalkisZamani, varisZamani, durum, ucusNo, biletFiyati);

                flightList.add(flight);
            }

            Flight[] flights = flightList.toArray(Flight[]::new);

            return flights;
        } catch (SQLException ex) {
            Logger.getLogger(FlightOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
