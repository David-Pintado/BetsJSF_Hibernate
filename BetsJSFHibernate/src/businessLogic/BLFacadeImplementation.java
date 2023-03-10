package businessLogic;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;


import dataAccess.DataAccessInterface;
import domain.Question;
import domain.Event;
import domain.Pertsona;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation  implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		//ConfigXML c=ConfigXML.getInstance();
		
		/*if (c.getDataBaseOpenMode().equals("initialize")) {
			
		    dbManager=new DataAccessInterface(new ObjectDbDAOManager());
			dbManager.initializeDB();
			dbManager.close();
			}
		*/
	}
	
    public BLFacadeImplementation(DataAccessInterface da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		//ConfigXML c=ConfigXML.getInstance();
		
		//if (c.getDataBaseOpenMode().equals("initialize")) {
		da.emptyDatabase();
		da.open();
		da.initializeDB();
		da.close();

		//}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date)  {
		dbManager.open();
		List<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}
	
	
	public List<Event> getEvents()  {
		dbManager.open();
		List<Event>  events=dbManager.getEvents();
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		dbManager.open();
		List<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		//DataAccess dB4oManager=new DataAccess(false);

		//dB4oManager.close();
		dbManager.close();


	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

	@Override
	public boolean existitzenDa(String izena, String pasahitza) {
		dbManager.open();
		Boolean b =dbManager.existitzenDa(izena, pasahitza);
		dbManager.close();
		return b;
	}
	
	@Override
	public Pertsona getErabiltzailea(String izena) {
		dbManager.open();
		Pertsona p =dbManager.getErabiltzailea(izena);
		dbManager.close();
		return p;
	}

	@Override
	public Pertsona erregistratu(String izena, String pasahitza, Date jaiotzeData) {
		dbManager.open();
		Pertsona p =dbManager.erregistratu(izena, pasahitza, jaiotzeData);
		dbManager.close();
		return p;
	}

	@Override
	public boolean adinaDu(Date jaiotzeData) {
		dbManager.open();
		Boolean p =dbManager.adinaDu(jaiotzeData);
		dbManager.close();
		return p;
	}

	@Override
	public boolean removeEvent(Event ev) {
		dbManager.open();
		Boolean b =dbManager.removeEvent(ev);
		dbManager.close();
		return b;
	}

	@Override
	public Event sortuGertaera(Date data, String deskripzioa) {
		dbManager.open();
		Event e =dbManager.sortuGertaera(data, deskripzioa);
		dbManager.close();
		return e;
	}
	
	

}

