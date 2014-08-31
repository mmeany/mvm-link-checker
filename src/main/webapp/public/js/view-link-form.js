// Add validation for the form

(function() {
	"use strict";

	$('#link-form').validate({
		rules : {
			name : {
				minlength : 3,
				maxlength : 40,
				required : true
			},
			url : {
				minlength : 6,
				maxlength : 100,
				required : true
			},
			description : {
				minlength : 3,
				maxlength : 200,
				required : true
			}
		},
		highlight : function(label) {
			$(label).closest('.form-group').removeClass('success error valid');
			$(label).closest('.form-group').addClass('error');
		},
		success : function(label) {
			$(label).closest('.form-group').removeClass('success error valid');
			$(label).closest('.form-group').addClass('success');
			label.remove();
		}
	});

	// Enable styled multi-select drop-downs
	$('.multiselect').multiselect();
//	$(".chosen-select").chosen();
	
}());
