package projectx.hotes.aloxe.controller;
 
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import projectx.hotes.aloxe.model.Reservation;
import projectx.hotes.aloxe.services.ReservationService;

@Component(value = "editor")
@ManagedBean(name = "editor")
@Scope("session")
public class EditorBean {
 
	private String value = "This editor is provided by PrimeFaces";

	@Autowired
	private ReservationService testService;
	
	@Autowired
	private CalendarBean cal;
	
	private Reservation resa;
	
	private Map<String,Integer> rooms = new TreeMap<String, Integer>();
	private String name;
	private int nb_pers;
	private int room_id;
	
	public EditorBean() {
        room_id=4;
	}

	public void initRoomNames(){
		FacesContext context = FacesContext.getCurrentInstance();
	    ResourceBundle text = ResourceBundle.getBundle("languages.aloxe", context.getViewRoot().getLocale());
	    rooms.put(text.getString("room1.name"), 1);
	    rooms.put(text.getString("room2.name"), 2);
	    rooms.put(text.getString("room3.name"), 3);
	    rooms.put(text.getString("room4.name"), 4);
	}
	
	public Map<String, Integer> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, Integer> rooms) {
		this.rooms = rooms;
	}

	
	public int getNb_pers() {
		return nb_pers;
	}

	public void setNb_pers(int nb_pers) {
		this.nb_pers = nb_pers;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getValue() {
		return value;
	}
 
	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void saveResa(){
		FacesContext context = FacesContext.getCurrentInstance();
	    ResourceBundle text = ResourceBundle.getBundle("languages.aloxe", context.getViewRoot().getLocale());
	    String msg = text.getString("msg.save.booking");
		resa = new Reservation();
		resa.setDate_resa(new Date());
		resa.setClient_name(name);
		resa.setNb_pers(nb_pers);
		resa.setRoom_id(room_id);
		resa.setDate_in(cal.getDate1());
		resa.setDate_out(cal.getDate2());
		testService.create(resa);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}
	
}