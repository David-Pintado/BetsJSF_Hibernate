package dataAccess;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Admin;
import domain.Erabiltzailea;
import domain.Event;
import domain.Pertsona;
import domain.Question;
import eredua.HibernateUtil;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess implements DataAccessInterface {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;
	private Session session;


	//ConfigXML c=ConfigXML.getInstance();

	/*
	public DataAccess(boolean initializeMode)  {

		//System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open();

		if (initializeMode)
			initializeDB();
		 
	}
	*/
	public DataAccess()  {	
		open();
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		session.beginTransaction();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  

			Event ev1=new Event("Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event("Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event("Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event("Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event("Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event("Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event("Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event("Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


			Event ev17=new Event("Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event("Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

			}
			
			Admin admin = new Admin("admin","pass",new Date("1990/10/12"));
			Erabiltzailea erab = new Erabiltzailea("erab", "erab", new Date("2002/08/06"));


			//session.persist(q1);
			//session.persist(q2);
			//session.persist(q3);
			//session.persist(q4);
			//session.persist(q5);
			//session.persist(q6);
			
			session.persist(admin);
			session.persist(erab);

			session.persist(ev1);
			session.persist(ev2);
			session.persist(ev3);
			session.persist(ev4);
			session.persist(ev5);
			session.persist(ev6);
			session.persist(ev7);
			session.persist(ev8);
			session.persist(ev9);
			session.persist(ev10);
			session.persist(ev11);
			session.persist(ev12);
			session.persist(ev13);
			session.persist(ev14);
			session.persist(ev15);
			session.persist(ev16);
			session.persist(ev17);
			session.persist(ev18);
			session.persist(ev19);
			session.persist(ev20);			

			session.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		System.out.println(db+" "+event);

		session.beginTransaction();
		Query q= session.createQuery("from Event where eventNumber= :zbkia");
		q.setParameter("zbkia", event.getEventNumber());
		Event ev = (Event) q.list().get(0);

		//Event ev = session.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist("ErrorQueryAlreadyExist");

		
		Question que = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		session.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		session.getTransaction().commit();
		return que;

	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		session.beginTransaction();
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=:date");
		query.setParameter("date", date);
		List<Event> events = query.list();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		session.getTransaction().commit();
		return res;
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents() {
		System.out.println(">> DataAccess: getAllEvents");
		session.beginTransaction();
		System.out.println(">> DataAccess: getAllEvents");
		Vector<Event> res = new Vector<Event>();
		Query query = session.createQuery("FROM Event");
		List<Event> events = query.list();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		session.getTransaction().commit();
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		session.beginTransaction();
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :hasData :bukData");   
		query.setParameter("hasData", firstDayMonthDate);
		query.setParameter("bukData", lastDayMonthDate);
		List<Date> dates = query.list();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		session.getTransaction().commit();
		return res;
	}
	
	public boolean existitzenDa(String izena, String pasahitza) {
		session.beginTransaction();
		Pertsona p = (Pertsona) session.get(Pertsona.class, izena);
		session.getTransaction().commit();
		if(p == null) {
			return false;
		}else {
			return p.pasahitzaZuzena(pasahitza);
		}
	}
	
	//REGISTER METHODS -----------------------------------------------------
	
	public Pertsona getErabiltzailea(String izena) {
		session.beginTransaction();
		Pertsona p = (Pertsona) session.get(Pertsona.class, izena);
		session.getTransaction().commit();
		return p;
	}
	
	
	public boolean adinaDu(Date jaiotzeData) {
		Date egunData = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int d1 = Integer.parseInt(formatter.format(jaiotzeData));                            
	    int d2 = Integer.parseInt(formatter.format(egunData));                          
	    int age = (d2 - d1) / 10000;                                                        
		System.out.println(age);
		return (age>=18);
	}
	
	public Pertsona erregistratu(String izena, String pasahitza, Date jaiotzeData) {
		// Aztertu ea aurretik existitzen den erabiltzailea izen horrekin
		System.out.println(">> DataAccess: registering...");
		Pertsona e = this.getErabiltzailea(izena);
		if(e == null) {
			// Erabiltzailerik ez da existitzen
			// Aztertu ea adina >= 18 den
			boolean adinaNahikoa = this.adinaDu(jaiotzeData);
			if(adinaNahikoa) {
				Pertsona er = this.sortuErabiltzailea(izena,pasahitza,jaiotzeData);
				return er;
			} else return null;
		} else return null;
	}

	public Pertsona sortuErabiltzailea(String izena, String pasahitza, Date jaiotzeData) {
		session.beginTransaction();
		// TODO: Soilik Erabiltzaileak sortu daitezke.
		Pertsona er = new Erabiltzailea(izena, pasahitza, jaiotzeData);
		session.persist(er);
		session.getTransaction().commit();
		System.out.println(">> DataAccess: registered!");
		return er;
	}
	
	
	//CREATE EVENT METHODS -----------------------------------------------------
	
	
	
	public Event sortuGertaera(Date data, String deskripzioa) {
		boolean exists= this.gertaeraExistitzenDa(data, deskripzioa);
		if (!exists) {
			Event event = this.createEvent(data, deskripzioa);
			event.toString();
			return event;
		}else {
			return null;
		}
	}
	
	private boolean gertaeraExistitzenDa(Date data, String deskripzioa) {
		session.beginTransaction();
		System.out.println(">> DataAccess: searching if event exist...");


		Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate=:date AND ev.description= :desc");   
		query.setParameter("date", data);
		query.setParameter("desc", deskripzioa);
		
		List<Event> eventsList = query.list();
		if(eventsList.isEmpty()) {
			System.out.println(">> DataAccess: searched");
			session.beginTransaction();
			return false;
		}else{
			System.out.println(">> DataAccess: searched");
			session.beginTransaction();
			return true;
		}
		
		
	}
		
	private Event createEvent(Date data, String deskripzioa) {
		System.out.println(">> DataAccess: Creating events");
		session.beginTransaction();
		Event event = new Event(deskripzioa, data);
		session.persist(event);
		event.toString();
		session.getTransaction().commit();
		System.out.println(">> DataAccess: event created!");
		return event;
	}
	
	
	// DELETE EVENTS METHOD -----------------------------------------------------
	
	
	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccess: Deleting events");
		session.beginTransaction();
		Event evDB = (Event) session.get(Event.class, ev.getEventNumber());
		if (evDB == null)
			return false;
		List<Question> galderak = evDB.getQuestions();
		for (Question q : galderak) {
			session.delete(q);
		}
		session.delete(evDB);
		session.getTransaction().commit();
		System.out.println(">> DataAccess: events deleted!");
		return true;
	}

	
	//DB METHODS -----------------------------------------------------------
	
	
	@Override
	public void open(){
		System.out.println("Opening DataAccess instance");
		//session = HibernateUtil.getSessionFactory().getCurrentSession();
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		session.beginTransaction();
		Event ev = (Event) session.get(Event.class, event.getEventNumber());
		session.getTransaction().commit();
		return ev.DoesQuestionExists(question);

	}
	public void close(){
		System.out.println("Closing DataBase");
		HibernateUtil.getSessionFactory().close();
		session = null;
		System.out.println("DataBase closed");
	}



	@Override
	public void emptyDatabase() {
		System.out.println("Deleting DataBase");
		session.beginTransaction();
		session.createQuery("DELETE FROM Event");
		session.createQuery("DELETE FROM Question");
		session.getTransaction().commit();
		System.out.println("DataBase deleted");

	}

}

