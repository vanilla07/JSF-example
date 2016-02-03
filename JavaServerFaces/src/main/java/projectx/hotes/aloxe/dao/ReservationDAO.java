package projectx.hotes.aloxe.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import projectx.hotes.aloxe.model.Reservation;

public interface ReservationDAO {

	public void createResaDAO(Reservation resa) throws HibernateException;

	public List<Reservation> getAllResasDAO() throws HibernateException;
	
	public List<Reservation> getRoomResasDAO(int idRoom) throws HibernateException;

	public List<Reservation> getStatusRoomResasDAO(int idRoom, int status) throws HibernateException;
	
	public void deleteResaDAO(Reservation resa) throws HibernateException;
	
	public void archivage(Date date)throws HibernateException;;
}