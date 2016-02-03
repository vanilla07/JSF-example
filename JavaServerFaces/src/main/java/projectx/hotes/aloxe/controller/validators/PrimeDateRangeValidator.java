package projectx.hotes.aloxe.controller.validators;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projectx.hotes.aloxe.model.ReservationUtils;
import projectx.hotes.aloxe.services.ReservationService;

@Component(value = "datevalidator")
@ManagedBean(name = "datevalidator")
@FacesValidator(value="dateValidator")
public class PrimeDateRangeValidator implements Validator {
     
	@Autowired
	private ReservationService testService;
	
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	
    	Calendar cal = Calendar.getInstance();
    	
    	Date today = new Date();
    	cal.setTime(today);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    	today = new Date(cal.getTimeInMillis());
    	
        if (value == null) {
            return;
        }
         
        //Leave the null handling of startDate to required="true"
        Object startDateValue = component.getAttributes().get("startDate");
        Object roomIDValue = component.getAttributes().get("room_id");
        Object maxDateValue = component.getAttributes().get("maxDate");
        
        if (startDateValue==null) {
            return;
        }
         
        Date startDate = (Date)startDateValue;
        Date maxDate = (Date)maxDateValue;
        Integer roomId = (Integer)roomIDValue;
        
        cal.setTime(startDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        startDate = new Date(cal.getTimeInMillis());
        
        Date endDate = (Date)value; 
        
        if (endDate.before(today)) {
            throw new ValidatorException(
            		new FacesMessage(FacesMessage.SEVERITY_ERROR,"Dates invalides","La date de depart est anterieure a la date d'aujourd'hui"));
        }
        
        if (startDate.before(today)) {
        	throw new ValidatorException(
        			new FacesMessage(FacesMessage.SEVERITY_ERROR,"Dates invalides","La date d'arrivee est anterieure a la date d'aujourd'hui"));
        }
        
        if (startDate.equals(endDate)) {
        	throw new ValidatorException(
        			new FacesMessage(FacesMessage.SEVERITY_ERROR,"Dates invalides","La date d'arrivee est egale a la date de depart"));
        }
        
        if (endDate.before(startDate)) {
            throw new ValidatorException(
            		new FacesMessage(FacesMessage.SEVERITY_ERROR,"Dates invalides","La date de depart est anterieure a la date d'arrivee"));
        }
        
        if (endDate.after(maxDate)) {
        	throw new ValidatorException(
        			new FacesMessage(FacesMessage.SEVERITY_ERROR,"Dates invalides","La periode selectionnee ne doit pas depasser 1 mois"));
        }
        
        List<Date> dates;
        if(roomId.intValue() == 0){
        	dates = testService.getAllReservationsDates();
        }
        else{
        	dates = testService.getRoomReservationsDates(roomId.intValue());
        }
		List<Date> datesSel = ReservationUtils.getPeriodDates(startDate, endDate);
		
		for (Date d : datesSel){
			if (dates.contains(d)){
				throw new ValidatorException(
	            		new FacesMessage(FacesMessage.SEVERITY_ERROR,"Dates invalides","Erreur: La periode selectionnee contient une date indisponible"));
			}
		}
        
    }
}
