
package domain;

import java.util.Date;
import javax.persistence.Entity;

@Entity 
public class Erabiltzailea extends Pertsona {
	
	private double saldoa;

	
	public Erabiltzailea() {
		super();
		this.saldoa = 0;
	}
	
	public Erabiltzailea(String izena, String pasahitza, Date jaiotzeData) {
		super(izena,pasahitza,jaiotzeData);
		this.saldoa = 0;
	}

	public double getSaldoa() {
		return saldoa;
	}

	public void setSaldoa(double saldoa) {
		this.saldoa = saldoa;
	}

}
