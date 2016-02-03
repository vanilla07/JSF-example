package projectx.hotes.aloxe.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectx.hotes.aloxe.dao.ReservationDAO;
import projectx.hotes.aloxe.model.Reservation;
import projectx.hotes.aloxe.model.ReservationUtils;

@Service("testService")
@Transactional
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationDAO testDAO;
	
	@Override
	public void create(Reservation resa) {
		
		if((resa.getDate_in()!=null && resa.getDate_out()!=null) && resa.getDate_out().after(resa.getDate_in())){
			
			resa.setNb_nights((int)(ReservationUtils.getNbNights(resa.getDate_in(), resa.getDate_out())));
			
			if(resa.getRoom_id() == 1 || resa.getRoom_id() == 3){
				resa.setPrice(80*resa.getNb_nights());
			}
			else {
				resa.setPrice(90*resa.getNb_nights());
			}
		}
		
		resa.setStatus(1);
		testDAO.createResaDAO(resa);
		
	}
	
	@Override
	public void delete(Reservation resa) {
		testDAO.deleteResaDAO(resa);
	}

	@Override
	public List<Reservation> getAllReservations() {
		ArrayList<Reservation> buff = new ArrayList<Reservation>();
		for (Reservation res : testDAO.getAllResasDAO()){
			if (res.getDate_in() != null && res.getNb_nights()!=0){
				buff.add(res);
			}
		}
		return buff;
	}

	@Override
	public List<Date> getAllReservationsDates() {
		List<Reservation> resas = getAllReservations();
		return getDatesFromResas(resas);
	}
	
	@Override
	public List<Reservation> getRoomReservations(int idRoom) {
		return testDAO.getRoomResasDAO(idRoom);
	}

	@Override
	public List<Date> getRoomReservationsDates(int idRoom) {
		List<Reservation> resas = getRoomReservations(idRoom);
		return getDatesFromResas(resas);
	}
	
	@Override
	public List<Reservation> getStatusRoomReservations(int idRoom, int status) {
		return testDAO.getStatusRoomResasDAO(idRoom, status);
	}

	@Override
	public List<Date> getStatusRoomReservationsDates(int idRoom, int status) {
		List<Reservation> resas = getStatusRoomReservations(idRoom, status);
		return getDatesFromResas(resas);
	}
	
	private List<Date> getDatesFromResas(List<Reservation> resas){
		Calendar cal = Calendar.getInstance();
		List<Date> dates = new ArrayList<Date> ();
		for (Reservation res: resas){
			cal.setTime(res.getDate_in());
			dates.add(new Date(cal.getTimeInMillis()));
			if(res.getNb_nights() > 1){
				for(int i = res.getNb_nights()-1; i>0; i--){
					cal.add(Calendar.DATE, 1);
					dates.add(new Date(cal.getTimeInMillis()));
				}
			}
		}
		Collections.sort(dates);
		return dates;
	}

	@Override
	public void archivage() {
		testDAO.archivage(new Date());
	}
	
}
