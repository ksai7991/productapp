package com.krishna.first.productmanage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;

	
	public List<Product> listAll(String uname){
		if(uname.equals("admin")) {
			return repo.findAll();
		}
		return repo.findByUname(uname);
	}
	public void save(Product product) {
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication auth=context.getAuthentication();
		product.setUname(auth.getName());
		repo.save(product);
	}
	public Product get(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id) {
		repo.deleteById(id);
	}


}
