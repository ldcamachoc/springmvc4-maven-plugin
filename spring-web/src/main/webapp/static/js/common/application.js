/**
 * 
 */

function showDialog(title, message, typeButton, btnOKLabel, btnOKClass,
		btnCancelLabel, event) {
		
		BootstrapDialog.confirm({
		title : title,
		message : message,
		type : typeButton,
		draggable : false,
		closabe : true,
		btnOKLabel : btnOKLabel, // <-- Default value is 'OK',
		btnOKClass : btnOKClass, // <-- If you didn't specify it, dialog type will be used,
		btnCancelLabel : btnCancelLabel, // <-- Default value is 'Cancel',
		callback : function(result) {
			// result will be true if button was click, while it will be false if users close the dialog directly.
			if (result) {
				window.location.href = event.target.href;
			}
		}
	});
}
