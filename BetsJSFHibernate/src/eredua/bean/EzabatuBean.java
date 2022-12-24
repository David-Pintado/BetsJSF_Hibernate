package eredua.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;

public class EzabatuBean {

	private Date data;
	private List<Event> motakEvent=new ArrayList<Event>();
	private Event motaEvent;
	private BLFacade facadeBL;
	private SelectEvent event;

	public EzabatuBean() {
		this.facadeBL = FacadeBean.getBusinessLogic();
		this.event=null;
	}

	public SelectEvent getEvent() {
		return event;
	}
	public void setEvent(SelectEvent event) {
		this.event = event;
	}
	public List<Event> getMotakEvent() {
		return motakEvent;
	}
	public void setMotakEvent(List<Event> motakEvent) {
		this.motakEvent = motakEvent;
	}
	public Event getMotaEvent() {
		return motaEvent;
	}
	public void setMotaEvent(Event motaEvent) {
		this.motaEvent = motaEvent;
	}
	public  BLFacade getFacadeBL() {
		return this.facadeBL;
	}
	public  void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.motakEvent.clear();
		//this.data = data;
	}

	public void onDateSelect(SelectEvent event) {
		motakEvent.clear();
		this.data = (Date) event.getObject();
		this.motaEvent = null;
		for (Event e:facadeBL.getEvents((Date)event.getObject())){
			motakEvent.add(e);
		}
		this.setEvent(null);
	}

	public void onEventSelect(SelectEvent event) {
		this.setEvent(event);
		this.motaEvent = (Event)event.getObject();
	}

	public String onEventSelect2() {
		if(this.event==null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROREA EZABATZEAN: Ez duzu gertaerarik aukeratu"));
		}else {
			Boolean b = this.facadeBL.removeEvent(this.motaEvent);
			if (b) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Gertaera eta haren galderak ondo ezabatuta"));
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ERROREA EZABATZEAN: Ez da existitzen gertaera hori DBan"));
			}
			this.motakEvent.clear();
		}
		this.motakEvent.clear();
		this.data = null;
		this.motaEvent = null;
		return "again";
	}
	
	public String close() {
		motakEvent.clear();
		this.motaEvent = null;
		this.data= null;
		return "returnDelete";
		
	}

}
