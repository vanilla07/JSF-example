package projectx.hotes.aloxe.controller;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projectx.hotes.aloxe.model.Reservation;
import projectx.hotes.aloxe.services.ReservationService;
 
@Controller
@RequestMapping("/welcome.do")
public class HelloController {
 
	final static Logger logger = Logger.getLogger(HelloController.class);
	@Autowired 
	private ReservationService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		String msg = "In";
		model.addAttribute("message", msg);
		
		
		List<Reservation> allres = service.getAllReservations();
		Reservation resa = allres.get(allres.size()-1);
		
		logger.info("This is the msg : " + msg);
		
		model.addAttribute("name", resa.getClient_name());
		
		return "hello";
 
	}
 
}
