package operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Airport;
import model.Flight;
import model.Seat;
import system.DBConnection;

public class SeatOperations {

    private final DBConnection conn = new DBConnection();
    private final Connection con = conn.connDb();
    private Statement st = null;
    private ResultSet rs = null;
    private final Flight flight;

    public SeatOperations(Flight flight) {
        this.flight = flight;
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

    public Airport[] getAirports() {
        String query = "SELECT "
                + "H.ID, "
                + "S.SEHIR, "
                + "H.HAVAALANI, "
                + "H.IATAKODU, "
                + "K.KULLANIM "
                + "FROM "
                + "HAVAALANLARI H "
                + "JOIN SEHIRLER S ON S.ID = H.SEHIRID"
                + "JOIN KULLANIM K ON K.ID = H.KULLANIMID";
        try {
            ArrayList<Airport> airportList = new ArrayList<>();
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String sehir = rs.getString(2);
                String havaalani = rs.getString(3);
                String iatakodu = rs.getString(4);
                String kullanim = rs.getString(5);
                Airport airport = new Airport(id, sehir, havaalani, iatakodu, kullanim);
                airportList.add(airport);
            }

            Airport[] airports = airportList.toArray(Airport[]::new);

            return airports;

        } catch (SQLException ex) {
            Logger.getLogger(SeatOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
