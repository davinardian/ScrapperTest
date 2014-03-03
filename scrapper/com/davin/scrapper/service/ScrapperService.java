package com.davin.scrapper.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.davin.common.util.CheckNullEmpty;
import com.davin.common.util.DateUtil;
import com.davin.common.util.StringUtil;
import com.davin.model.dao.CommonDAO;
import com.davin.model.entity.Departure;
import com.davin.model.entity.FlightInformation;

@Component
public class ScrapperService {
	
	@Autowired
	private CommonDAO commonDAO;

	public Map<String,Object> bookingList(String from, String to, String fromName, String toName, String date1, String date2, String jumlahOrang, String jumlahAnak, String jumlahBayi) throws IOException {
		
		String day = DateUtil.day(date1);
		String month = DateUtil.month(date1);
		String year = DateUtil.year(date1);
		
		String day2, month2, year2, status;
		if(CheckNullEmpty.isNotNullOrEmpty(date2)){
			day2 = DateUtil.day(date2);
			month2 = DateUtil.month(date2);
			year2 = DateUtil.year(date2);
			status="pp";
		}else{
			day2 = day;
			month2 = month;
			year2 = year;
			status="ow";
		}
		
		Document doc = Jsoup.connect("http://booking.airasia.com/Select.aspx?__EVENTTARGET="+
				"&__EVENTARGUMENT="+
				"&__VIEWSTATE=%2FwEPDwUBMGQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFP0NvbnRyb2xHcm91cFNlbGVjdFZpZXckU3BlY2lhbE5lZWRzSW5wdXRTZWxlY3RWaWV3JENoZWNrQm94U1NSc2KF%2B3FBQndP4mQD4nrPT4PNXNaR"+
				"&pageToken="+
				"&MemberLoginSelectView%24HFTimeZone=420"+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24RadioButtonMarketStructure=RoundTrip"+
				"&ControlGroupAvailabilitySearchInputSelectView_AvailabilitySearchInputSelectVieworiginStation1="+from+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24TextBoxMarketOrigin1="+from+
				"&ControlGroupAvailabilitySearchInputSelectView_AvailabilitySearchInputSelectViewdestinationStation1="+to+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24TextBoxMarketDestination1="+to+
				"&oneWayOnly=1"+
				"&date_picker="+day+"%2F"+month+"%2F"+year+
				"&date_picker="+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListMarketDay1="+day+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListMarketMonth1="+year+"-"+month+
				"&date_picker="+day2+"%2F"+month2+"%2F"+year2+
				"&date_picker="+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListMarketDay2="+day2+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListMarketMonth2="+year2+"-"+month2+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListPassengerType_ADT="+jumlahOrang+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListPassengerType_CHD="+jumlahAnak+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListPassengerType_INFANT="+jumlahBayi+
				"&ControlGroupAvailabilitySearchInputSelectView%24MultiCurrencyConversionViewSelectView%24DropDownListCurrency=default"+
				"&ControlGroupAvailabilitySearchInputSelectView%24AvailabilitySearchInputSelectView%24DropDownListSearchBy=columnView"+
				"&ControlGroupAvailabilitySearchInputSelectView%24ButtonSubmit=Search")
				.data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(30000).get();
		  
		Map<String,Object> map = new HashMap<String, Object>();
		List<FlightInformation> list1 = new ArrayList<FlightInformation>();
		List<FlightInformation> list2 = new ArrayList<FlightInformation>();
		String otherFareBerangkat="empty"; String otherFarePulang="empty"; 
		
		for (org.jsoup.nodes.Element table : doc.select("table[id=fareTable1_4]")) {
			for (org.jsoup.nodes.Element tr : table.select("tr[class=rgRow]")) {
				org.jsoup.select.Elements result1 = tr.select("div[class=segmentStation]");
				//org.jsoup.select.Elements result2 = tr.select("div[class=hotspot]");
				org.jsoup.select.Elements result3 = tr.select("div[class=price]").select("span");
				
				FlightInformation f = new FlightInformation();
				int countResult1=result1.size();
				
				String text1 = result1.toString();
				String text3 = result3.toString().replace("<span>", "").replace("</span>", "").replace("<div></div>", "").replace("\n", "-");
				String[] text3Split = text3.split("-");
				if(countResult1 == 1){
					f.setFromTo(text1.substring(130, 146).replace("&nbsp;", "")+" - "+text1.substring(253, 270).replace("&nbsp;", ""));
				}else{
					f.setFromTo(text1.substring(130, 146).replace("&nbsp;", "")+" - "+text1.substring(290, 306).replace("&nbsp;", ""));
				}
				System.out.println(text1);
				f.setLowFare(text3Split[0]);
				f.setHiFlyer(text3Split[2]);
				if(text3Split.length > 4){
					f.setOtherFare(text3Split[4]);
					otherFareBerangkat = "available";
				}
				list1.add(f);
			}
		}
		
		if(CheckNullEmpty.isNotNullOrEmpty(date2)){
		for (org.jsoup.nodes.Element table : doc.select("table[id=fareTable2_4]")) {
			for (org.jsoup.nodes.Element tr : table.select("tr[class=rgRow]")) {
				org.jsoup.select.Elements result1 = tr.select("div[class=segmentStation]");
				//org.jsoup.select.Elements result2 = tr.select("div[class=hotspot]");
				org.jsoup.select.Elements result3 = tr.select("div[class=price]").select("span");
		
				FlightInformation f = new FlightInformation();
				int countResult1=result1.size();
				
				String text1 = result1.toString();
				String text3 = result3.toString().replace("<span>", "").replace("</span>", "").replace("<div></div>", "").replace("\n", "-");
				String[] text3Split = text3.split("-");
				if(countResult1 == 1){
					f.setFromTo(text1.substring(130, 146).replace("&nbsp;", "")+" - "+text1.substring(253, 270).replace("&nbsp;", ""));
				}else{
					f.setFromTo(text1.substring(130, 146).replace("&nbsp;", "")+" - "+text1.substring(290, 306).replace("&nbsp;", ""));
				}
				System.out.println(text1);
				f.setLowFare(text3Split[0]);
				f.setHiFlyer(text3Split[2]);
				if(text3Split.length > 4){
					f.setOtherFare(text3Split[4]);
					otherFarePulang = "available";
				}
				list2.add(f);
			}
		}
		}
		
		map.put("fromName", fromName);
		map.put("toName", toName);
		map.put("tanggalBerangkat", date1);
		map.put("tanggalPulang", date2);
		map.put("otherFareBerangkat", otherFareBerangkat);
		map.put("otherFarePulang", otherFarePulang);
		map.put("berangkat", list1);
		map.put("pulang", list2);
		map.put("status", status);
		map.put("tableStatus", "print");
		
		return map;
		
	} 
	
	@Transactional
	public Map<String, Object> getDepartureList(String page, String rows, String sidx, String sord, String searchTerm){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(CheckNullEmpty.isNullOrEmpty(sidx) || sidx.equals("name")){
			sidx = "depart2.NAME";
		}else if(sidx.equals("country")){
			sidx = "COUNTRY";
		}
		if(CheckNullEmpty.isNullOrEmpty(sord)){
			sord = "ASC";
		}
		
		List<Departure> allItem= commonDAO.listDeparture(sidx,sord,CheckNullEmpty.checkKeyword(searchTerm));
		int totalRow = allItem.size();
		int totalPages = 0;
		int limit = Integer.parseInt(rows);
		int pageRequested = Integer.parseInt(page);
		
		if( totalRow > 0 ) {
			totalPages = (int) Math.ceil((double)totalRow/(double)limit);
		} else {
			totalPages = 0;
		}
		
		if (pageRequested > totalPages) pageRequested=totalPages;
		int rowStart = pageRequested*limit-limit;
		int rowEnd = limit;
		
		List<Object> list = new ArrayList<Object>();
		if(totalPages!=0){
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Departure x : commonDAO.listDepartureLimitOffset(rowEnd, rowStart, sidx, sord, CheckNullEmpty.checkKeyword(searchTerm))){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("code", x.getCode());
				map2.put("name", x.getName());
				map2.put("country", x.getCountry());
				list.add(map2);
			}
			map.put("rows", list);
			
		}else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Departure x : allItem){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("code", x.getCode());
				map2.put("name", x.getName());
				map2.put("country", x.getCountry());
				list.add(map2);
			}
			map.put("rows", list);
		}

		return map;
	}
}
