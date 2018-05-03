/**
 * @author Varadi Robert
 * @author Dan Mitrea 
 */
function dropDownExclude(sourceDropDown, targetDropDown) {
jQuery(sourceDropDown).on('change', function() {
		var assigned = false;
		$(targetDropDown + " option").each(function() {
			if ($(this).attr('value') == $(sourceDropDown).val()) {
				$(this).hide();
			} else {
				if(!assigned && $(targetDropDown).val() == $(sourceDropDown).val()) {
					$(targetDropDown).val($(this).val());
					assigned = true;
				}
				$(this).show();
			}
		});
	});
}
