$(document).ready(function() {
	
	var audioLoaded = false;
	var a;
	var audio;
	
	$('[data-toggle="tooltip"]').tooltip();
	setTimeout(function(){ 
		$("#myAudio").find("audio").remove();
		$(".off").css("visibility", "hidden"); 
		$(".tooltip.fade.left.in").remove();
	}, 14000);
	

	$(".off").click(function(){
		$("#myAudio").find("audio").remove();
		$(".tooltip.fade.left.in").remove();
		$(this).css("visibility", "hidden");
		a.remove();
	});
	
	

	$("ol li").each(function(){
		e = $(this).find("a");
		e.attr("data-src", e.attr("src"));
	});
	
	

    // Load in a track on click
    $("body").on("click", "ol li", function(e) {
      e.preventDefault();
      $(this).addClass('playing').siblings().removeClass('playing');
      audio.load($('a', this).attr('data-src'));
      audio.play();
    });
    // Keyboard shortcuts
    $(document).keydown(function(e) {
      var unicode = e.charCode ? e.charCode : e.keyCode;
         // right arrow
      if (unicode == 39) {
        var next = $('li.playing').next();
        if (!next.length) next = $('ol li').first();
        next.click();
        // back arrow
      } else if (unicode == 37) {
        var prev = $('li.playing').prev();
        if (!prev.length) prev = $('ol li').last();
        prev.click();
        // spacebar
      } else if (unicode == 32) {
        audio.playPause();
      }
    });
    
	//Gestion de la navigation sur le site
	$("body").on("click", ".navLink", function(e){
		e.preventDefault();
		el = $(this);
		//alert("Click sur "+el.html());
		$(".current").removeClass("current");
		el.closest('li').addClass("current");
		link = el.attr("href");
		if(el.hasClass("home")){
			//alert("HOMEEEE");
			window.location.href = link;
		} else{
			$("#myAudio").find("audio").remove();
			$.ajax({
				type: "POST",
				url: link,
				success: function(data) {
					$("#reloadContent").html(data);
				},
				error:function(data, status, er) { 
					alert("error: "+data+" status: "+status+" er:"+er);
				}
			});
		}
		
	});
	
	
	
	
	
	
	$("#coButton").click(function(){
		$(".notifError").html("");
	});
	$("body").on("click", ".vote", function(){
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
	
	$("body").on("click", "#viewComments", function(){
		$(".commentBlock").toggle("slow");
	});

	$("body").on("submit", "#commentForm", function(){
		e = $(this);
		idSong = e.find(".idSong").val();
		author = e.find(".pseudo").val();
		com = e.find(".comment").val();
		e.find(".pseudo").removeClass("error2");
		e.find(".voteDiv").removeClass("error2");
		console.log(parseInt(idSong));
		url = "/MuzikKloud/song?id="+idSong;
		if(idSong !="" && author != "" && com !="" && parseInt(com) > 0){
			$.ajax({
				type: "POST",
				url: "/MuzikKloud/commentsong",
				dataType: 'json',
				data: JSON.stringify({'idSong': parseInt(idSong), 'date': "", 'author': author, 'comment': com}),
				contentType: 'application/json',
				mimeType: 'application/json',
				success: function(data) {
					if(data.status == 'OK') {
						//window.location.reload();
						console.log("OK");
						$.ajax({
							type: "POST",
							url: url,
							success: function(data) {
								$("#reloadContent").html(data);
							},
							error:function(data, status, er) { 
								alert("error: "+data+" status: "+status+" er:"+er);
							}
						});
					}
					else {
						$(".notifications").html("<span class='error'>Vous avez d&eacute;j&agrave; not&eacute; ce son</span>");
						$(".notifications").focus();
					}
				},
				error:function(data, status, er) { 
					console.log("error: "+data+" status: "+status+" er:"+er);
					$.ajax({
						type: "POST",
						url: url,
						success: function(data) {
							$("#reloadContent").html(data);
						},
						error:function(data, status, er) { 
							alert("error: "+data+" status: "+status+" er:"+er);
						}
					});
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
	
	
	//Ajout à la playlist
	$("body").on("click", ".addPL", function(){
		e = $(this);
		link = e.closest("header").find(".plLink").val();
		name = e.closest("header").find(".plName").val();
		//alert("Add requested for Link:: "+link+" and Name::"+name+" !!");
		pL = $("#wrapper ol");
		htmlToAdd = '<li><a href="#" data-src="'+link+'">'+name+'</a></li>';
		pL.html(pL.html()+htmlToAdd);
		if(!audioLoaded){
			// Setup the player to autoplay the next track
		    a = audiojs.createAll({
		      trackEnded: function() {
		        var next = $('ol li.playing').next();
		        if (!next.length) next = $('ol li').first();
		        next.addClass('playing').siblings().removeClass('playing');
		        audio.load($('a', next).attr('data-src'));
		        audio.play();
		      }
		    });
		    
		    // Load in the first track
		    audio = a[0];
		        first = $('ol a').attr('data-src');
		    $('ol li').first().addClass('playing');
		    audio.load(first);
		    audioLoaded = true;
		}
		//alert($("#wrapper ol").html());
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




