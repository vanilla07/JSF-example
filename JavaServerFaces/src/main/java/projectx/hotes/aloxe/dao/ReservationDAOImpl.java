package projectx.hotes.aloxe.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projectx.hotes.aloxe.model.Reservation;


@Repository
@Transactional
public class ReservationDAOImpl implements ReservationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void createResaDAO(Reservation resa) throws HibernateException {
		sessionFactory.getCurrentSession().save(resa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getAllResasDAO() throws HibernateException {
		return sessionFactory.getCurrentSession().createCriteria(Reservation.class).list();
	}

	@Override
	public void deleteResaDAO(Reservation resa) throws HibernateException {
		sessionFactory.getCurrentSession().delete(resa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getRoomResasDAO(int idRoom)throws HibernateException {
		return sessionFactory.getCurrentSession().createCriteria(Reservation.class).add(Restrictions.eq("room_id", new Integer(idRoom))).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getStatusRoomResasDAO(int idRoom, int status)throws HibernateException {
		return sessionFactory.getCurrentSession()
				.createCriteria(Reservation.class)
				.add(Restrictions.eq("room_id", new Integer(idRoom)))
				.add(Restrictions.eq("status", new Integer(status)))
				.list();
	}

	@Override
	public void archivage(Date date) throws HibernateException {
		Query query = sessionFactory.getCurrentSession().createQuery("update Reservation set status = :status" +
				" where date_out <= :date and status <> 2");
		query.setParameter("status", new Integer(2));
		query.setParameter("date", date);
		query.executeUpdate();
	}
	
}
