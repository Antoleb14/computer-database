var errors = []; 
function displayerror(e){
	$("."+e).removeClass("has-success").addClass("has-error");
	errors[e]=true;
	console.log(errors);
}

function hideerror(e){
	$("."+e).removeClass("has-error").addClass("has-success");
	errors[e]=null;
	console.log(errors);
}


$(function(){ 
	
    $("input[name=name]").focusout(function(){
    	if($(this).val().length == 0){
    		displayerror("name"); 
    	}else{
    		hideerror("name");
    	}
    });
    
    var dateregex = '(0[1-9]|[12][0-9]|3[01])[\-](0[1-9]|1[012])[\-](19[7-9][0-9]|20[0-3][0-9])';
    $("input[name=introduced]").focusout(function(){
    	if(!$(this).val().match(dateregex) && $(this).val().length > 0){
    		displayerror("introduced"); 
    	}else{
    		hideerror("introduced");
    	}
    });
    $("input[name=discontinued]").focusout(function(){
    	if(!$(this).val().match(dateregex) && $(this).val().length > 0){
    		displayerror("discontinued"); 
    	}else{
    		hideerror("discontinued");
    	}
    });
    
    $("#form").on("submit", function(e){
    	if(jQuery.inArray(true, errors) != -1) {
    		e.preventDefault();
    	}
    });
    
});
