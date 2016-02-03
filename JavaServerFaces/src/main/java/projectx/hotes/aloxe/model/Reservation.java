package projectx.hotes.aloxe.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="px_resa")
public class Reservation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7621221214643061077L;

	public Reservation() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pk;
	
	@Column
	private Date date_in;
	
	@Column
	private Date date_out;
	
	@Column
	private Date date_resa;
	
	@Column
	private String client_name;
	
	@Column
	private int nb_pers;
	
	@Column
	private float price;
	
	@Column
	private int room_id;

	@Column
	private int status;
	
	@Column 
	private int nb_nights;

	public int getNb_nights() {
		return nb_nights;
	}

	public void setNb_nights(int nb_nights) {
		this.nb_nights = nb_nights;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public Date getDate_in() {
		return date_in;
	}

	public void setDate_in(Date date_in) {
		this.date_in = date_in;
	}

	public Date getDate_out() {
		return date_out;
	}

	public void setDate_out(Date date_out) {
		this.date_out = date_out;
	}

	public Date getDate_resa() {
		return date_resa;
	}

	public void setDate_resa(Date date_resa) {
		this.date_resa = date_resa;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public int getNb_pers() {
		return nb_pers;
	}

	public void setNb_pers(int nb_pers) {
		this.nb_pers = nb_pers;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String datetoPattern(Date date){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
	
	public String idToRoom(int r){
		FacesContext context = FacesContext.getCurrentInstance();
	    ResourceBundle text = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
	    
	    return text.getString("room"+ r +".name");
	}
	
	public String statusToString(int r){
		FacesContext context = FacesContext.getCurrentInstance();
	    ResourceBundle text = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
	    
	    return text.getString("status"+ r +".name");
	}
}
