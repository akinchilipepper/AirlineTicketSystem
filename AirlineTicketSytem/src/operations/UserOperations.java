package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	public static String generateRandomUniquePNR() throws SQLException {
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
	
	public static boolean updateTelNumber(User user, String telNumber) {
		String query = "UPDATE YOLCULAR SET TELNO = ? WHERE ID = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, telNumber);
			ps.setInt(2, user.getId());
			int result = ps.executeUpdate();			
			return result > 0;
		} catch (SQLException ex) {
			Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	
	public static boolean updateMail(User user, String mail) {
		String query = "UPDATE YOLCULAR SET E_POSTA = ? WHERE ID = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, mail);
			ps.setInt(2, user.getId());
			int result = ps.executeUpdate();			
			return result > 0;
		} catch (SQLException ex) {
			Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	
	public static boolean updatePassword(User user, String password) {
		String query = "UPDATE YOLCULAR SET PAROLA = ? WHERE ID = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, password);
			ps.setInt(2, user.getId());
			int result = ps.executeUpdate();			
			return result > 0;
		} catch (SQLException ex) {
			Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
}
