package projectx.hotes.aloxe.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import projectx.hotes.aloxe.model.Reservation;
import projectx.hotes.aloxe.services.ReservationService;


@Component(value = "allres")
@ManagedBean(name = "allres")
@Scope("session")
public class AllResaBean{

	@Autowired
	private ReservationService testService;
	
	private ResDataModel resaModel; 
	
	private String tabresa;

	ArrayList<Reservation> resaList;  
	
	ArrayList<Date> datesList;
	
	public ArrayList<Date> getDatesList() {
		return datesList;
	}

	private Reservation selectedRes;
	
	private Reservation[] selectedResas;
	
	public void setSelectedRes(Reservation selectedRes) {
		this.selectedRes = selectedRes;
	}
	
	public void setSelectedResas(Reservation[] selectedResas) {
		this.selectedResas = selectedResas;
	}
	
	public Reservation[] getSelectedResas() {
		return selectedResas;
	}

	public ResDataModel getResaModel() {
		return resaModel;
	}

	public Reservation getSelectedRes() {
		return selectedRes;
	}

	public ArrayList<Reservation> getResaList() {
		return resaList;
	}

	public String getTabresa() {
		return tabresa;
	}
	
	public void fillResaModel() {
		fillResaList();
		fillDatesList();
		resaModel = new ResDataModel(resaList);
	}
	
	public void fillResaList(){
		resaList = (ArrayList<Reservation>) testService.getAllReservations();
	}
	
	public void fillDatesList(){
		datesList = (ArrayList<Date>) testService.getAllReservationsDates();
	}
	
	public String fillTabresa(int id){
		String buff = "[";
		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		cal.setTime(today);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		today = new Date(cal.getTimeInMillis());
		
		boolean coma = false;
		for (Date date : testService.getStatusRoomReservationsDates(id, 1)){
			if (date.after(today) || date.equals(today)) {
				cal.setTime(date);
				if(coma){ buff += "," ; }
				else{coma=true;}
				buff +=  "[" + cal.get(Calendar.DATE) + "," + (cal.get(Calendar.MONTH)+1) + "," + cal.get(Calendar.YEAR) + "]";
			}
		}
		buff += "]";
		return buff;
	}
	public String fillAllCalendars(){
		String buff = "{";
		for (int i=1 ; i <=4 ; i++){
			buff+= i + ":" + fillTabresa(i) + ",";
		}
		buff+="}";
		return buff;
	}
	
	public void delResa(){
		Reservation del = selectedRes;
		FacesContext context = FacesContext.getCurrentInstance();  
		if (del != null){
			testService.delete(del);
			fillResaModel();
			context.addMessage(null, new FacesMessage("Suppression ..."));
		}
		else{
			context.addMessage(null, new FacesMessage("Veuillez selectionner une ligne"));
		}
	}
	
	public void archivage(){
		testService.archivage();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Archivage des reservations anterieures a aujourd'hui"));
	}
	
}
