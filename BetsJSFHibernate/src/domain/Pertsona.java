package domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Pertsona {
	@Id 
	private String izena;
	private String pasahitza;
	private Date jaiotzeData;

	public Pertsona() {
		this.pasahitza = null;
		this.izena = null;
		this.jaiotzeData = null;
	}

	public Pertsona(String izena, String pasahitza, Date jaiotzeData) {
		this.izena = izena;
		this.pasahitza = pasahitza;
		this.jaiotzeData = jaiotzeData;
	}


	public String toString() {
		return "Izena: " + this.izena + " Adina: " + this.jaiotzeData;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}

	public boolean pasahitzaZuzena(String pasahitza) {
		return (pasahitza.equals(this.pasahitza));
	}

}