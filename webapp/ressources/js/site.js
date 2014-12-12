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
	$(".vote").click(function(){
		e = $(this);
		var val = 1, gris = 0;
		$(this).removeClass("etoile-vide");
		e.addClass("etoile-pleine");
		e.prevAll(".vote").each(function() {
			val++;
			$(this).removeClass("etoile-vide");
			$(this).addClass("etoile-pleine");
		});
		e.nextAll(".vote").each(function() {
			gris++;
			$(this).removeClass("etoile-pleine");
			$(this).addClass("etoile-vide");
		});
		$(".comment").val(val);
		
	});
	
	$("#viewComments").click(function(){
		$(".commentBlock").toggle("slow");
	});

	$("#commentForm").submit(function(){
		e = $(this);
		idSong = e.find(".idSong").val();
		author = e.find(".pseudo").val();
		com = e.find(".comment").val();
		e.find(".pseudo").removeClass("error2");
		e.find(".voteDiv").removeClass("error2");
		if(idSong !="" && author != "" && com !="" && parseInt(com) > 0){
			$.ajax({
				type: "POST",
				url: "/MuzikKloud/commentsong",
				dataType: 'json',
				data: JSON.stringify({ 'idSong': parseInt(idSong), 'date': "", 'author': author, 'comment': com}),
				contentType: 'application/json',
				mimeType: 'application/json',
				success: function(data) {
					if(data.status == 'OK') {
						window.location.reload();
					}
					else {
						$(".notifications").html("<span class='error'>Vous avez déjà noté ce son</span>");
						$(".notifications").focus();
					}
				},
				error:function(data, status, er) { 
					alert("error: "+data+" status: "+status+" er:"+er);
				}
			});
		} else{
			if(author == "")
				e.find(".pseudo").addClass("error2");
			if(com =="" || parseInt(com) <= 0)
				e.find(".voteDiv").addClass("error2");
			
			
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




