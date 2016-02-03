package projectx.hotes.aloxe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;  

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "calendarBean")
@ManagedBean(name = "calendarBean")
@Scope("session")
public class CalendarBean {  
  
    private Date date1= new Date();
    private Date date2;
    private Date datemax;
    private Date datemin;
    
    Calendar cal = Calendar.getInstance();  

	public Date getDatemin() {
		return datemin;
	}
	
	public void setDatemin(Date datemin) {
		this.datemin = datemin;
	}
    
	public Date getDatemax() {
		return datemax;
	}
	
	public void setDatemax(Date datemax) {
		this.datemax = datemax;
	}

	public String fillToday(){
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(new Date());
    }
	
	public void fillDates(){
		cal.setTime(date1);
		cal.add(Calendar.DATE, 1);
		
		if(date2 == null){
			date2 = new Date(cal.getTimeInMillis());
		}
		else if(date2.before(date1)){
			date2 = new Date(cal.getTimeInMillis());
		}
		
		datemin = new Date(cal.getTimeInMillis());
		
		cal.add(Calendar.MONTH, 1);
		datemax = new Date(cal.getTimeInMillis());
	}
    
	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate1() {  
        return date1;  
    }  
  
    public void setDate1(Date date1) {  
        this.date1 = date1;  
    }  
    
	public String datetoPattern(Date date){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
	
	public void handleDateSelect(SelectEvent event) {  
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));  
	}
	
	public void handleDate1Select(SelectEvent event) {  
		fillDates();
	}
	
}  
