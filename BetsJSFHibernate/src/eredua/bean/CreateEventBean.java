package eredua.bean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;

public class CreateEventBean {

	private Date data;
	private BLFacade facadeBL;
	private String description;

	public CreateEventBean() {
		facadeBL = FacadeBean.getBusinessLogic();
	}

	public BLFacade getFacadeBL() {
		return facadeBL;
	}
	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		//this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void onDateSelect(SelectEvent event) {
		this.data = (Date) event.getObject();
	}

	public String onEventSelect() {
		if(this.getDescription().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROREA SORTZEAN: Ez duzu deskripziorik sartu"));
		}else if(this.data==null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROREA SORTZEAN: Data aukeratu mesedez"));
		}else {
			Event e = facadeBL.sortuGertaera(this.data, this.description);
			if(e!=null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Galdera ondo sortu da: " + e.toString()));
				
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ERROREA SORTZEAN: Gertaera jadanik exixtitzen da"));
			} 
		}
		this.data = null;
		return "againCreateEvent";
	}
	
	public String close() {
		this.data= null;
		return "returnCreateEvent";
		
	}

}
