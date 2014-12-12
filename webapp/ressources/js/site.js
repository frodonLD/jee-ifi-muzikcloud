$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
	$(".off").click(function(){
		var a = document.getElementsByTagName("audio")[0];
		$(".tooltip.fade.left.in").remove();
		$(this).css("visibility", "hidden");
		a.remove();
	});
	
	$("#coButton").click(function(){
		$(".notifError").html("");
	});
	

	$("#commentForm").submit(function(){
		e = $(this);
		idSong = e.find(".idSong").val();
		author = e.find(".pseudo").val();
		com = e.find(".comment").val();
		if(idSong !="" && author != "" && com !=""){
			$.ajax({
				type: "POST",
				url: "/MuzikKloud/commentsong",
				dataType: 'json',
				data: JSON.stringify({ 'idSong': parseInt(idSong), 'date': "", 'author': author, 'comment': com}),
				contentType: 'application/json',
				mimeType: 'application/json',
				success: function(data) {
					if(data.status == 'OK') {
						/*e.remove();
						msg = 'Ajout de '+title.val()+' effectue avec succes!';
						toAdd = '<span class="success">'+msg+'</span>';
						$('.notifications').html($('.notifications').html() + toAdd);
						if($('.content-to-add').length <= 0){
							$('.buttons').remove();
							setTimeout(function(){window.location.href = "/MuzikKloud/admin/songs";}, 2000);
						}*/
						alert("OK");
					}
					else {
						/*msg = 'Ajout de '+title.val()+' echoue. <b>'+data.errorMessage+'</b>';
						toAdd = '<span class="error">'+msg+'</span>';
						e.addClass('errorBox');
						$('.notifications').html($('.notifications').html() + toAdd);*/
						alert("KO");
					}
				},
				error:function(data, status, er) { 
					alert("error: "+data+" status: "+status+" er:"+er);
				}
			});
		}
		
		return false;
	});
});

function loginFormDisplay(){
	var a = document.getElementsByTagName("audio")[0];
	a.remove(); $(".off").css("visibility", "hidden");
	$(".tooltip.fade.left.in").remove();
	$(".notifError").html("");
	$("#coButton2").click();
}

function loginFormDisplayInError(){
	loginFormDisplay();
	$(".notifError").html("Login ou mot de passe incorrect.");
}




