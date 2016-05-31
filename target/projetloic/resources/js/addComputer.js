var errors = [];
var def = {
		name: "--",
		introduced: "--",
		discontinued: "--",
		company:"--"
}

/*options = options || {};
for (var opt in def)
    if (def.hasOwnProperty(opt) && !options.hasOwnProperty(opt))
        options[opt] = def[opt];
*/
function displayerror(e, f){
	$("."+e).removeClass("has-success").addClass("has-error");
	$("."+e+" .errmsg").html(f).show();
	errors[e]=true;
	console.log(errors);
}

function hideerror(e){
	$("."+e).removeClass("has-error").addClass("has-success");
	$("."+e+" .errmsg").empty().hide();
	errors[e]=null;
	console.log(errors);
}


$(function(){ 
	
    $("input[name=name]").focusout(function(){
    	if($(this).val().length == 0){
    		displayerror("name", options.name); 
    	}else{
    		hideerror("name");
    	}
    });
    
    var dateregex = '(0[1-9]|[12][0-9]|3[01])[\-](0[1-9]|1[012])[\-](19[7-9][0-9]|20[0-3][0-9])';
    $("input[name=introduced]").focusout(function(){
    	if(!$(this).val().match(dateregex) && $(this).val().length > 0){
    		displayerror("introduced", options.introduced); 
    	}else{
    		hideerror("introduced");
    	}
    });
    $("input[name=discontinued]").focusout(function(){
    	if(!$(this).val().match(dateregex) && $(this).val().length > 0){
    		displayerror("discontinued", options.discontinued); 
    	}else{
    		hideerror("discontinued");
    	}
    });
    
    $("#form").on("submit", function(e){
    	match = null;
    	$(errors).each(function(key, value)
        {
            if(value)
            {
                match = true;
            }
        });
    	if(match){
    		console.log("BLOCK")
    		e.preventDefault();
    	}
    });
    
});
