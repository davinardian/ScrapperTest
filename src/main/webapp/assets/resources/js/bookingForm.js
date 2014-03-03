$(document).ready(function(){
	var getUrl = $('.getUrl').attr('id');
	var form = $("#bookingForm");
	
	form.submit(function(){
		if($('input[type=radio][name=tipe]:checked').val() == 'pp'){
			if(validateFrom() & validateTo() & validateTanggalPergi() & validateTanggalPulang()){
				return true;
			}else{
				return false;
			}
		}else{
			if(validateFrom() & validateTo() & validateTanggalPergi()){
				return true;
			}else{
				return false;
			}
		}

	});
	
	$( "#hiddenReturnDate" ).hide();
	
	$('#dateDeparture').datetimepicker({
		step:5,
		timepicker:false,
		format:'d-m-Y',
		formatDate:'d-m-Y'
		
	});
	
	$('#returnDate').datetimepicker({
		step:5,
		timepicker:false,
		format:'d-m-Y',
		formatDate:'d-m-Y'
		
	});
	
	$( "#from" ).combogrid({
		url: 'getAllDeparture',
		searchButton:true,
		resetButton:true,
		debug:true,
    	replaceNull: true,
		colModel: [{'columnName':'code','hidden':true,'width':'10','label':'Code'}, {'columnName':'name','width':'50','label':'From'},{'columnName':'country','width':'30','label':'Country'}],
		select: function( event, ui ) {
			$( "#from" ).val( ui.item.name );
			$( "#fromHidden" ).val( ui.item.code );
			return false;
		}
	});
	
	$( "#to" ).combogrid({
		url: 'getAllDeparture',
		searchButton:true,
		resetButton:true,
		debug:true,
    	replaceNull: true,
		colModel: [{'columnName':'code','hidden':true,'width':'10','label':'Code'}, {'columnName':'name','width':'50','label':'From'},{'columnName':'country','width':'30','label':'Country'}],
		select: function( event, ui ) {
			$( "#to" ).val( ui.item.name );
			$( "#toHidden" ).val( ui.item.code );
			return false;
		}
	});
		
});

function changeTanggal(param){
	if(param == 1){
		$( "#hiddenReturnDate" ).show();
	}else{
		$( "#hiddenReturnDate" ).hide();
	}
}

function validateFrom(){
	var validate=$("#from").val().length;
	if(validate < 1){
		$("#from").addClass("error");
		return false;
	}
	//if it's valid
	else{
		$("#from").removeClass("error");
		return true;
	}
}

function validateTo(){
	var validate=$("#to").val().length;
	if(validate < 1){
		$("#to").addClass("error");

		return false;
	}
	//if it's valid
	else{
		$("#from").removeClass("error");
		return true;
	}
}

function validateTanggalPergi(){
	var validate=$("#dateDeparture").val().length;
	if(validate < 1){
		$("#dateDeparture").addClass("error");

		return false;
	}
	//if it's valid
	else{
		$("#from").removeClass("error");
		return true;
	}
}

function validateTanggalPulang(){
	var validate=$("#returnDate").val().length;
	if(validate < 1){
		$("#returnDate").addClass("error");

		return false;
	}
	//if it's valid
	else{
		$("#from").removeClass("error");
		return true;
	}
}