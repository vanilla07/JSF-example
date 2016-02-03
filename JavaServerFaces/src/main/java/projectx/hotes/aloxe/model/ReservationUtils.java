package projectx.hotes.aloxe.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationUtils {

	public static long getNbNights(Date dateIn, Date dateOut){
		long temps=0;
		if((dateIn!=null && dateOut!=null) && dateOut.after(dateIn)){
			temps=dateOut.getTime()-dateIn.getTime();
			temps=temps/1000/60/60/24;
		}
		return temps;
	}
	
	public static List<Date> getPeriodDates(Date dateIn, Date dateOut) {
		Calendar cal = Calendar.getInstance();
		long temps=getNbNights(dateIn, dateOut);
		List<Date> datesSel = new ArrayList<Date>();
		cal.setTime(dateIn);
		datesSel.add(new Date(cal.getTimeInMillis()));
		if(temps > 1){
			for(int i = (int)(temps-1); i>0; i--){
				cal.add(Calendar.DATE, 1);
				datesSel.add(new Date(cal.getTimeInMillis()));
			}
		}
		return datesSel;
	}
	
}
