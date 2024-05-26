package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressBookController {
	
	@Autowired
	//@Qualifier("addressBookManager")
	private IAddressBookManager am; //loose coupling
	
	
	//allAddress()
	@GetMapping(value="/")
	public String home(Model model) {

		model.addAttribute("items", am.allAddress());
		//return name of the view that will show the data (model)
		return "normal";
	}
	
	//addAddress()
	@GetMapping(value="/add")
	public String add(Model model) {
		
		//return name of the view that will show the data (model)
		return "homes";
	}
	
	//Post
	@PostMapping(value="/add")
	public ModelAndView input1(String fname, String lname, String address, String email) {
			
		ModelAndView mv = new ModelAndView("addResult");
		String result;
		//조건처리
		boolean b = am.addAddress(fname, lname, address, email);
		
		if(b) result = "The address was added successfully!";
		else result = "The address was not added...";
		
		mv.addObject("result", result);

		return mv;
	}
	
	//lastToAddress()
	//Get(Path variable)
	@GetMapping("/search/lastname/{lname}")
	public ModelAndView search1(@PathVariable String lname) {
			
		ModelAndView mv = new ModelAndView("normal");

		mv.addObject("items", am.lastToAddress(lname));
			
		return mv;
	}
	
	//emailToAddress()
	//Get(Path variable)
	@GetMapping("/search/email/{email}")
	public ModelAndView search2(@PathVariable String email) {
				
		ModelAndView mv = new ModelAndView("normal");

		mv.addObject("items", am.emailToAddress(email));
				
		return mv;
	}
	
	//remove()
	//Get(Query parameter)
	@GetMapping(value="/remove")
	public String remove1(@RequestParam(name="email", required=true) String email, Model model) {
			
		boolean b = am.remove(email);
		String result;

		if(b) result = "Removed one address!";
		else result = "The address was not removed...";
			
		model.addAttribute("result", result);
			
			
		return "removeResult";
	}
	
	//removeAll()
	@GetMapping(value="/remove_all")
	public String remove2(Model model) {

		am.removeAll();
		
		String result = "Removed all addresses!";
			
		model.addAttribute("result", result);
		
		return "removeResult";
	}
	
	
	
}
