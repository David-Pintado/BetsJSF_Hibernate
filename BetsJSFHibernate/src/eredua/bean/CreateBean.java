package eredua.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateBean {

	private Date data;
	private List<Event> motakEvent=new ArrayList<Event>();
	private Event motaEvent;
	private static BLFacade facadeBL;
	private String question;
	private float betMinimun;
	private SelectEvent event;

	public CreateBean() {
		CreateBean.facadeBL = FacadeBean.getBusinessLogic();
		this.event=null;
	}

	public SelectEvent getEvent() {
		return event;
	}
	public void setEvent(SelectEvent event) {
		this.event = event;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public float getBetMinimun() {
		return betMinimun;
	}
	public void setBetMinimun(float betMinimun) {
		this.betMinimun = betMinimun;
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
	public static BLFacade getFacadeBL() {
		return facadeBL;
	}
	public static void setFacadeBL(BLFacade facadeBL) {
		CreateBean.facadeBL = facadeBL;
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
		if(this.getEvent()==null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROREA SORTZEAN: Ez duzu gerterarik aukeratu"));
		}else if(this.question.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROREA SORTZEAN: Galdera null da"));
		}else {
			try {
				Question q = facadeBL.createQuestion((Event) this.event.getObject(), question, betMinimun);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Galdera ondo sortu da: " + q.toString()));
				
			} catch (EventFinished e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ERROREA SORTZEAN: Gertaera jadanik amaituta zegoen"));
			} catch (QuestionAlreadyExist e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ERROREA SORTZEAN: Galdera jadanik existitzen da"));
			}
		}
		this.motakEvent.clear();
		this.data = null;
		this.motaEvent = null;
		return "againQuery";
	}
	
	public String close() {
		motakEvent.clear();
		this.motaEvent = null;
		this.data= null;
		return "returnCreate";
		
	}

}
