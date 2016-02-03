package projectx.hotes.aloxe.services;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import projectx.hotes.aloxe.model.Reservation;

@Transactional
public interface ReservationService {

	public void create(Reservation resa);
	
	public List<Reservation> getAllReservations();
	
	public List<Reservation> getRoomReservations(int idRoom);

	public List<Reservation> getStatusRoomReservations(int idRoom, int status);
	
	public List<Date> getAllReservationsDates();
	
	public List<Date> getRoomReservationsDates(int idRoom);

	public List<Date> getStatusRoomReservationsDates(int idRoom, int status);
	
	public void archivage();

	public void delete(Reservation resa) ;
	
}
