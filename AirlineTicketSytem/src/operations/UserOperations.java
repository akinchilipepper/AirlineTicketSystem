package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Flight;
import model.Seat;
import model.User;
import system.DBConnection;

public class UserOperations {

	private static final int PNR_LENGTH = 6;
	private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final Random random = new Random();
	private static final DBConnection conn = new DBConnection();
	private static Connection con = conn.connDb();
	private static PreparedStatement ps;
	private static ResultSet rs;

	public static boolean login(String email, String password) {
		String query = "SELECT * FROM YOLCULAR WHERE E_POSTA = ? AND PAROLA = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException ex) {
			Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public static boolean addUser(String ad, String soyad, String telno, String eposta, String cinsiyet, String dtarihi,
			String parola) {
		String query = "INSERT INTO YOLCULAR" + "(AD, SOYAD, TELNO, E_POSTA, CINSIYET, DOGUMTARIHI, PAROLA)" + "VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?)";
		try {

			con = conn.connDb();

			ps = con.prepareStatement(query);
			ps.setString(1, ad);
			ps.setString(2, soyad);
			ps.setString(3, telno);
			ps.setString(4, eposta);
			ps.setString(5, cinsiyet);
			ps.setString(6, dtarihi);
			ps.setString(7, parola);

			ps.executeUpdate();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public static User setUser(String email) {
		String query = "SELECT * FROM YOLCULAR WHERE E_POSTA = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setAd(rs.getString("AD"));
				user.setSoyad(rs.getString("SOYAD"));
				user.setTelno(rs.getString("TELNO"));
				user.setE_posta(rs.getString("E_POSTA"));
				user.setCinsiyet(rs.getString("CINSIYET"));
				user.setDogumtarihi(rs.getString("DOGUMTARIHI"));
				user.setParola(rs.getString("PAROLA"));

				return user;
			} else {
				return null;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private static String generateRandomUniquePNR() throws SQLException {
		String pnr;
		String sql = "SELECT COUNT(*) FROM BILETLER WHERE PNR = ?";
		try {
			ps = con.prepareStatement(sql); 
			do {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < PNR_LENGTH; i++) {
					int index = random.nextInt(CHARACTERS.length());
					sb.append(CHARACTERS.charAt(index));
				}
				pnr = sb.toString();

				ps.setString(1, pnr);
				rs = ps.executeQuery(); 
				if (rs.next()) {
					int count = rs.getInt(1);
					if (count == 0) {
						return pnr;
					}
				}
			} while (true);
		}catch(SQLException ex){
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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

	public static boolean mailCheck(String eposta) {
		String sorgu = "SELECT * FROM YOLCULAR WHERE E_POSTA = ?";
		try {
			ps = con.prepareStatement(sorgu);
			ps.setString(1, eposta);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException ex) {
			Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
}
