package eredua.bean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import domain.Pertsona;

public class RegisterBean {

	private String izena;
	private String pasahitza;
	private Date jaiotzeData;
	private BLFacade facadeBL;


	public RegisterBean() {
		this.setFacadeBL(FacadeBean.getBusinessLogic());
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

	public BLFacade getFacadeBL() {
		return facadeBL;
	}


	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}


	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}


	public String erregistratu(){
		if(this.jaiotzeData==null || this.izena.equals("") || this.pasahitza.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Daturen bat ez da sartu, ezin zara erregistratu"));
		}else {
			if(!this.facadeBL.existitzenDa(this.izena, this.pasahitza)) {
				Pertsona p = this.facadeBL.erregistratu(this.izena, this.pasahitza, this.jaiotzeData);
				if(p==null) {
					if(!this.facadeBL.adinaDu(this.jaiotzeData)) {
						FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Ez duzu adina nahikorik, ezin zara erregistratu"));
					}else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Dagoeneko erabiltzaile hori existitzen da, probatu beste bat"));
					}
				}else {
					return "registered";
				}
				
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Dagoeneko erabiltzaile hori existitzen da, probatu beste bat"));
			}
			
		}
		return null;
		
	}
}
