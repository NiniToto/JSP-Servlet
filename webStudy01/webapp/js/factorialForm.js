/**
 * 
 */
$.fn.factorialForm = function(object){
	
	var element = this.on("submit", function(event){
		event.preventDefault();
		if(object.init){
			object.init();
		}
		if(object.validation && !object.validation()){
			return false;
		}
		
		var inputTag = $(this).find(":input"); 
		
		let obj = {};
		let url = this.action;
		let method = this.method;
		
		let value = inputTag.value;
		
		$.ajax({
			url : url,
			method : method,
			data : value,
			success : object.success
		})
		return false;
	});
	return this;
}
