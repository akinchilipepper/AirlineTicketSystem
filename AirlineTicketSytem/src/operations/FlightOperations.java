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

    private final DBConnection conn = new DBConnection();
    private final Connection con = conn.connDb();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean isFlightAvailable(String source, String arrival, String time) {
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

    public Flight[] getFlights(String source, String arrival, String time) {

        String sourceQuery = "SELECT ID FROM HAVAALANLARI WHERE HAVAALANI = ?";
        String arrivalQuery = "SELECT ID FROM HAVAALANLARI WHERE HAVAALANI = ?";

        int sourceAirportID = -1;
        int arrivalAirportID = -1;
        String query = "SELECT "
                + "UC.ID, "
                + "HAT.YÖN, "
                + "UCAK.MODEL, "
                + "H1.HAVAALANI, "
                + "H2.HAVAALANI, "
                + "UC.KALKISTARIHI, "
                + "UC.VARISTARIHI,"
                + "UC.KALKISZAMANI, "
                + "UC.VARISZAMANI, "
                + "D.UCUSDURUMU,"
                + "UC.UCUSNO "
                + "FROM "
                + "UCUSLAR UC "
                + "JOIN "
                + "HATLAR HAT ON HAT.ID = UC.HATID "
                + "JOIN "
                + "UCAKLAR UCAK ON UCAK.ID = UC.UCAKID "
                + "JOIN "
                + "HAVAALANLARI H1 ON H1.ID = UC.KALKISYERIID "
                + "JOIN "
                + "HAVAALANLARI H2 ON H2.ID = UC.VARISYERIID "
                + "JOIN "
                + "DURUMLAR D ON D.ID = UC.DURUMUID "
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
                String hat = rs.getString(2);
                String ucak = rs.getString(3);
                String kalkisYeri = rs.getString(4);
                String varisYeri = rs.getString(5);
                String kalkisTarihi = rs.getString(6);
                String varisTarihi = rs.getString(7);
                String kalkisZamani = rs.getString(8);
                String varisZamani = rs.getString(9);
                String durum = rs.getString(10);
                String ucusNo = rs.getString(11);

                Flight flight = new Flight(id, hat, ucak, kalkisYeri, varisYeri, kalkisTarihi, varisTarihi, kalkisZamani, varisZamani, durum, ucusNo);

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
