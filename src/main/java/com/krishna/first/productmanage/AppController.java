package com.krishna.first.productmanage;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class AppController{
	
	@Autowired
	private ProductService service;
	@Autowired
	private Userservice uservice;
	
	@GetMapping({"/"})
	public ModelAndView viewHomePage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<Product> listProducts=service.listAll(authentication.getName());
		ModelAndView model=new ModelAndView("index");
		model.addObject("listProducts", listProducts);
		return model;
	}
	@GetMapping({"/new"})
	public String newProductForm(Model model) {
		Product product=new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name="id") Long id) {
		ModelAndView mav=new ModelAndView("edit_product");
		Product product=service.get(id);
		mav.addObject("product", product);
		return mav;
	}

	@GetMapping("/delete/{id}") 
	public String deleteProduct(@PathVariable(name="id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	@GetMapping("/login")
	public String getLoginpage() {
		return "login";
	}
	@GetMapping("/signup")
	public String getSignuppage(Model model) {
		Users user=new Users();
		model.addAttribute("user",user);
		return "registration";

	}	
	@RequestMapping(value="/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Users user){
		uservice.saveuser(user);
		return "redirect:/login?success";
		}


	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
