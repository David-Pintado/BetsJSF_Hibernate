package eredua.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

public class QueryBean {

	private Date data;
	private List<Question> motakQuery=new ArrayList<Question>();
	private Question motaQuery;
	private List<Event> motakEvent=new ArrayList<Event>();
	private Event motaEvent;
	private boolean mvis1;
	private boolean mvis2;
	private boolean mvis3;
	
	public List<Question> getMotakQuery() {
		return motakQuery;
	}
	public void setMotakQuery(List<Question> motakQuery) {
		this.motakQuery = motakQuery;
	}
	public Question getMotaQuery() {
		return motaQuery;
	}
	public void setMotaQuery(Question motaQuery) {
		this.motaQuery = motaQuery;
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

	private static BLFacade facadeBL;

	public QueryBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		this.data = null;
	}
	public static BLFacade getFacadeBL() {
		return facadeBL;
	}
	public static void setFacadeBL(BLFacade facadeBL) {
		QueryBean.facadeBL = facadeBL;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.motakEvent.clear();
		this.motakQuery.clear();
		//this.data = data;
	}
	public boolean isMvis1() {
		return mvis1;
	}
	public void setMvis1(boolean mvis1) {
		this.mvis1 = mvis1;
	}
	public boolean isMvis2() {
		return mvis2;
	}
	public void setMvis2(boolean mvis2) {
		this.mvis2 = mvis2;
	}
	public boolean isMvis3() {
		return mvis3;
	}
	public void setMvis3(boolean mvis3) {
		this.mvis3 = mvis3;
	}
	public void onDateSelect(SelectEvent event) {
		motakEvent.clear();
		motakQuery.clear();
		this.data = (Date) event.getObject();
		this.motaEvent = null;
		this.motaQuery = null;
		for (Event e:facadeBL.getEvents((Date)event.getObject())){
			motakEvent.add(e);
		}
	}

	public void onEventSelect(SelectEvent event) {
		motakQuery.clear();
		this.motaQuery = null;
		for (Question e:((Event)event.getObject()).getQuestions()){
			motakQuery.add(e);
		}
		this.motaEvent = (Event)event.getObject();
	}
	
	public void onEventSelect2(SelectEvent event) {
		this.motaQuery = (Question)event.getObject();
	}
	
	public String close() {
		motakEvent.clear();
		motakQuery.clear();
		this.motaEvent = null;
		this.motaQuery = null;
		this.data= null;
		return "returnQuery";
		
	}
}
