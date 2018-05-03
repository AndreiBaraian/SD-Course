/**
 *  @author Dan Mitrea
 *  @author Varadi Robert
 *  @author Viorel Ieremias
 */

function backToMainPage(buttonId) {
	jQuery(buttonId).on('click', function() {
		window.location.replace("http://localhost:8080/hello");
	});
}

function backToListAccounts(buttonId) {
	jQuery(buttonId).on('click', function() {
		window.location.replace("http://localhost:8080/listAccounts")
	});
}