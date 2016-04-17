package ie.kevinmay.ticketingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticController {

	@RequestMapping(value = "/swagger", method = RequestMethod.GET)
	   public String redirect() {
	     
	      return "redirect:/swagger/index.html";
	   }
	
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getIndexPage() {
        return "listTicketsAngular";
    }
	
	@RequestMapping(value = "/jasmine", method = RequestMethod.GET)
    public String getJasminePage() {
        return "jasmineTest";
    }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }
	
 	
}
