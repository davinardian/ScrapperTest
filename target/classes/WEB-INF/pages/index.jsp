<%@ page import="org.springframework.jdbc.core.RowCallbackHandler" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scraper demo</title>
<link rel="stylesheet" type="text/css" media="screen" href="assets/resources/css/form.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="assets/resources/css/table.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="assets/resources/css/smoothness/jquery-ui-1.10.1.custom.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="assets/resources/css/smoothness/jquery.ui.combogrid.css"/>
<script type="text/javascript" src="assets/resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="assets/resources/jquery/jquery-ui-1.10.1.custom.min.js"></script>
<script type="text/javascript" src="assets/resources/plugin/jquery.ui.combogrid-1.6.3.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="assets/resources/datepicker/jquery.datetimepicker.css"/>
<script type="text/javascript" src="assets/resources/datepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="assets/resources/js/bookingForm.js"></script>
</head>
<body>
	<%-- <form action="/ScrapperTest/postDepart" method="post">
	<div>
		<div style="float:left"><input size="30" id="project" name="from"/></div><br/>&nbsp;<br/>
	</div>
	<!-- <input size="30" id="to" name="to"/> -->
	<input size="30" id="datetimepicker" name="tanggal"/>
	<input type="submit" value="submit"/>
	</form> --%>
	<!-- <button>Get JSON data</button> -->
	<div class="getUrl" id="http://<%= request.getServerName() %>:<%= request.getServerPort() %>/ScrapperTest/postDepart" ></div> 
	
	<h2>Search Flight</h2>
	
	<form id="bookingForm" action="/ScrapperTest/postDepart" method="post">
	
		<table border="0" cellpadding="1" cellspacing="1" width="400px">
		
			<tr>
				<td colspan="3" id="rb" align="left">
				<div class="compactTravel">
        			<input type="radio" onclick="changeTanggal(1);" id="pp" name="tipe" value="pp"> <label for="pp">Pulang Pergi</label>
					<input type="radio" onclick="changeTanggal(2);" id="ow" name="tipe" value="ow" checked><label for="ow">Sekali Jalan</label>
				</div>
				</td>
			</tr>
			<tr>
				<td>Dari</td>
				<td>:</td>
				<td>
					<input size="20" id="from" name="from" />
					<input type="hidden" id="fromHidden" name="fromHidden" />
				</td>
			</tr>
			<tr>
				<td>Ke</td>
				<td>:</td>
				<td>
					<input size="20" id="to" name="to" />
					<input type="hidden" id="toHidden" name="toHidden" />
				</td>
			</tr>
			<tr>
				<td>Tanggal Berangkat</td>
				<td>:</td>
				<td><input size="20" id="dateDeparture" name="dateDeparture"/></td>
			</tr>
			<tr id="hiddenReturnDate">
				<td>Tanggal Pulang</td>
				<td>:</td>
				<td><input size="20" id="returnDate" name="returnDate"/></td>
			</tr>
			<tr>
				<td>Jumlah Orang</td>
				<td>:</td>
				<td>
					<select id="jumlahOrang" name="jumlahOrang">
						<option value="1" selected>1 Dewasa</option>
						<option value="2">2 Dewasa</option>
						<option value="3">3 Dewasa</option>
						<option value="4">4 Dewasa</option>
						<option value="5">5 Dewasa</option>
						<option value="6">6 Dewasa</option>
						<option value="7">7 Dewasa</option>
						<option value="8">8 Dewasa</option>
						<option value="9">9 Dewasa</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Jumlah Anak</td>
				<td>:</td>
				<td>
					<select id="jumlahAnak" name="jumlahAnak">
						<option value="0" selected>0 Anak</option>
						<option value="1">1 Anak</option>
						<option value="2">2 Anak</option>
						<option value="3">3 Anak</option>
						<option value="4">4 Anak</option>
						<option value="5">5 Anak</option>
						<option value="6">6 Anak</option>
						<option value="7">7 Anak</option>
						<option value="8">8 Anak</option>
						<option value="9">9 Anak</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Jumlah Bayi</td>
				<td>:</td>
				<td>
					<select id="jumlahBayi" name="jumlahBayi">
						<option value="0" selected>0 Bayi</option>
						<option value="1">1 Bayi</option>
						<option value="2">2 Bayi</option>
						<option value="3">3 Bayi</option>
						<option value="4">4 Bayi</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2"></td>
				<td><input type="submit" value="Cari"/></td>
			</tr>
		</table>
	
	</form>
	
	<br/>
	
	<c:choose>
	<c:when test="${tableStatus=='print'}">
	
		<h3><div id="textBerangkat">Berangkat <c:out value="${fromName}"/> - <c:out value="${toName}"/> Tanggal <c:out value="${tanggalBerangkat}"/></div></h3>
	
		<table class="TFtable">
		<tr>
			<th>Depart/ Arrive</th>
			<th>Low Fare</th>
			<th>Hi-Flyer</th>
			<c:if test="${otherFareBerangkat!='empty'}">
				<th>Other-Flyer</th>
			</c:if>
		</tr>
		<c:forEach items="${berangkat}" var="itemsBerangkat" varStatus="counterBerangkat">				
					<tr>
						<td align="center">${itemsBerangkat.fromTo}<br/>${itemsBerangkat.eta}</td>
						<td>${itemsBerangkat.lowFare}</td>
						<td>${itemsBerangkat.hiFlyer}</td>
						<c:if test="${otherFareBerangkat!='empty'}">
							<td>${itemsBerangkat.otherFare}</td>
						</c:if>
					</tr>		
		</c:forEach>
		</table>
		<br/>
		<c:choose>
		<c:when test="${status=='pp'}">
	
		<h3><div id="textPulang">Pulang <c:out value="${toName}"/> - <c:out value="${fromName}"/> Tanggal <c:out value="${tanggalPulang}"/></div></h3>
	
		<table class="TFtable">
		<tr>
			<th>Depart/ Arrive</th>
			<th>Low Fare</th>
			<th>Hi-Flyer</th>
			<c:if test="${otherFarePulang!='empty'}">
				<th>Other-Flyer</th>
			</c:if>
		</tr>
		<c:forEach items="${pulang}" var="itemsPulang" varStatus="counterPulang">				
					<tr>
						<td align="center">${itemsPulang.fromTo}<br/>${itemsPulang.eta}</td>
						<td>${itemsPulang.lowFare}</td>
						<td>${itemsPulang.hiFlyer}</td>
						<c:if test="${otherFarePulang!='empty'}">
							<td>${itemsPulang.otherFare}</td>
						</c:if>
					</tr>		
		</c:forEach>
		</table>
	
		</c:when>
		</c:choose>
	</c:when>
	</c:choose>
	
	
</body>
</html>
