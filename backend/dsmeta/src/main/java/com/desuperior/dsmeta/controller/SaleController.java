package com.desuperior.dsmeta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desuperior.dsmeta.entities.Sale;
import com.desuperior.dsmeta.service.SMsService;
import com.desuperior.dsmeta.service.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	private SaleService service;
	
	@Autowired
	private SMsService smsService;
	
//	@GetMapping
//	public List<Sale> findsales(){
//		
//		return service.findSales();
//	}

	@GetMapping
	public Page<Sale> findSales(
			@RequestParam(value="minDate", defaultValue= "") String minDate, 
			@RequestParam(value="maxDate", defaultValue= "") String maxDate, 
			Pageable pageable){
		
		return service.findSales(minDate, maxDate, pageable);
	}
	
	@GetMapping("/{id}/notification")
	public void notifySms(@PathVariable Long id) {
		
		smsService.sendSms(id);		
	}
}
