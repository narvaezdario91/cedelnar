$( document ).ready(function() {
	Dropzone.autoDiscover = false;
	var urlBase = $('#urlBase').val();

	$('#editUser').on('submit', function(event){
		var me = $(this);
		event.preventDefault();

		var myStack = {"dir1":"down", "dir2":"right", "push":"top"};


		var request = $.ajax({
			url: me.attr('action'),
			method: "POST",
			data: me.serialize()
		});
		request.done(function( data ) {
			new PNotify({
				title: "Exitoso",
				text: data.message,
				type: "success",
				styling: 'bootstrap3'
			});
			$('#profile_fullName').html(data.fullName);
			$('#profile_email').html(data.email);
			$('#edit-modal').modal('hide');
		});

		request.fail(function( data ) {
			new PNotify({
				title: "Error",
				text: data.responseText,
				type: "Error",
				styling: 'bootstrap3'
			});
		});
		return false;
	});

	var dropProfile = new Dropzone(".dropzone", {
		autoProcessQueue: false,
		maxFiles: 1,
		acceptedFiles: 'image/*',
		maxFilesize: 1,
		addRemoveLinks: true,
		dictRemoveFile: "Remover"
	}); 
	
	dropProfile.on("maxfilesexceeded", function(file) {
		dropProfile.removeFile(file);
    });
	
	dropProfile.on("success", function(file) {
		
		$(".photoProfileContainer").each(function(){
			$(this).load(location.href+" #"+$(this).attr('id'));
			$('#photoProfile-modal').modal('hide');
		});
    });
	
	$('#changePhotoProfile').on('click', function(event){
		dropProfile.processQueue();
	});
    
});