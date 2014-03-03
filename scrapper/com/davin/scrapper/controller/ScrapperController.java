package com.davin.scrapper.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.davin.scrapper.service.ScrapperService;
@Controller
public class ScrapperController {
	
	@Autowired
	private ScrapperService service;
	
	@RequestMapping(value = "/postDepart", method=RequestMethod.POST)
	public ModelAndView  scraping(@RequestParam("tipe") String optionFlight, @RequestParam("fromHidden") String from,@RequestParam("toHidden") String to,
			@RequestParam("from") String fromName, @RequestParam("to") String toName, @RequestParam("dateDeparture") String tanggal1, @RequestParam(required=false,value="returnDate") String tanggal2,
			@RequestParam("jumlahOrang") String jumlahOrang, @RequestParam("jumlahAnak") String jumlahAnak, @RequestParam("jumlahBayi") String jumlahBayi ) throws IOException  {
	
		return new ModelAndView("index", service.bookingList(from, to, fromName, toName, tanggal1, tanggal2, jumlahOrang, jumlahAnak, jumlahBayi)); 
		
	}
	
	@RequestMapping(value = "/getAllDeparture", method=RequestMethod.GET, headers="Accept=*/*")
	@ResponseBody
	public Map<String,Object> getAllDeparture(@RequestParam("page") String page,@RequestParam("rows") String rows,@RequestParam(required=false, value="sidx") String sidx,
			@RequestParam(required=false, value="sord") String sord, @RequestParam(required=false,value="searchTerm") String searchTerm) {
	
		return service.getDepartureList(page, rows, sidx, sord, searchTerm);
		
	}
	
}
