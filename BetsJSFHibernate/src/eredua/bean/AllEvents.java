package eredua.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;


import businessLogic.BLFacade;
import domain.Event;

public class AllEvents {

	private List<Event> motakEvent=new ArrayList<Event>();
	private Event motaEvent;
	private BLFacade facadeBL;

	public AllEvents() {
		facadeBL = FacadeBean.getBusinessLogic();
		for (Event e:facadeBL.getEvents()){
			motakEvent.add(e);
		}
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
	public BLFacade getFacadeBL() {
		return facadeBL;
	}
	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}
	
	public String close() {
		return "returnAll";
		
	}

}
