package model;

public class Ticket {

    private int id;
    private String pnr;
    private User user;
    private Flight flight;
    private Seat seat;
    private double ucret;

    public Ticket(int id, String pnr, User user, Flight flight, Seat seat, double ucret) {
        super();
        this.id = id;
        this.pnr = pnr;
        this.user = user;
        this.flight = flight;
        this.seat = seat;
        this.ucret = ucret;
    }
    
    public Ticket() {
    	
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

	public double getUcret() {
		return ucret;
	}

	public void setUcret(double ucret) {
		this.ucret = ucret;
	}
}
