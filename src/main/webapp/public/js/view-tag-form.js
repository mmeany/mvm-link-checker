// Add validation for the form

(function() {
	"use strict";

	$('#tag-form').validate({
		rules : {
			tag : {
				minlength : 3,
				maxlength : 40,
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
