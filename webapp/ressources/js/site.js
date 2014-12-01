$(document).ready(function() {
	var artistRemoveAsked = false;
	var genreRemoveAsked = false;
	var albumRemoveAsked = false;
	
	$('[data-toggle="tooltip"]').tooltip();
	// ARTISTS ---
	$('.existingArtistList .addable').click(function() {
		e = $(this);
		artistToAdd = $(e).html();
		artistBox = e.closest(".artists").prev().children(".box");
		artistBoxHtml = artistBox.html();
		if (isNotYetMentionned(artistBox, artistToAdd) && artistToAdd != '') {
			artistToAdd = '<span title="Supprimer" class="bubble removable">'+artistToAdd+'</span>';
			artistBox.html(artistBoxHtml+artistToAdd);
		}
	});
	
	$('.artistList').on('click', '.removable', function() {
		artistRemoveAsked = true;
		e = $(this);e.remove();
	});
	
	$('.box.artistList:not(".box.artistList.bubble")').click(function() {
		e = $(this);
		artistBox = $(this);
		artistBoxHtml = artistBox.html();
		if(!artistRemoveAsked) {
			previousToAppend = $(".box.artistList .new_artist");
			if(!previousToAppend.length) {
				toAppend = '<input type="text" class="new_artist"/>';
				artistBox.html(artistBoxHtml+toAppend);
				child = artistBox.children('.new_artist');
				child.focus();
			}
		}
		artistRemoveAsked = false;
	});
	
	$('.box').on('keypress', '.new_artist, .new_genre, .new_album', function(c) {
		e = $(this); code = (c.keyCode ? c.keyCode : c.which);
		separator1 = ','.charCodeAt(0); separator2 = ';'.charCodeAt(0);
		if(code == separator1 || code == separator2) {
			artistToAdd = e.val();
			artistBox = e.closest(".box");
			artistBoxHtml = artistBox.html();
			if (isNotYetMentionned(artistBox, artistToAdd) && artistToAdd != '') {
//				alert("Ok!! Should add normally!");
				e.remove();
				artistBoxHtml = artistBox.html();
				artistToAdd = '<span title="Supprimer" class="bubble removable">'+artistToAdd+'</span>';
				artistBox.html(artistBoxHtml+artistToAdd);
//				alert("Result should be ==> "+artistBox.html());
			}
			e.remove();
		}
	});
	
	$('.box').on('blur', '.new_artist, .new_genre, .new_album', function() {
		//$(this).remove();
	});
	
	// GENRES ---
	$('.existingGenreList .addable').click(function() {
		e = $(this);
		genreToAdd = $(e).html();
		genreBox = e.closest(".genres").prev().children(".box");
		genreBoxHtml = genreBox.html();
		if (isNotYetMentionned(genreBox, genreToAdd)) {
			genreToAdd = '<span title="Supprimer" class="bubble removable">'+genreToAdd+'</span>';
			genreBox.html(genreBoxHtml+genreToAdd);
		}
	});
	
	$('.genreList').on('click', '.removable', function() {
		genreRemoveAsked = true;
		e = $(this);e.remove();
	});
	
	$('.box.genreList:not(".box.genreList.bubble")').click(function() {
		e = $(this);
		genreBox = $(this);
		genreBoxHtml = genreBox.html();
		if(!genreRemoveAsked) {
			previousToAppend = $(".box.genreList .new_genre");
			if(!previousToAppend.length) {
				toAppend = '<input title="Supprimer" type="text" class="new_genre"/>';
				genreBox.html(genreBoxHtml+toAppend);
				child = genreBox.children('.new_genre');
				child.focus();
			}
		}
		genreRemoveAsked = false;
	});
	
	// ALBUMS ---
	$('.existingAlbumList .addable').click(function() {
		e = $(this);
		albumToAdd = $(e).html();
		albumBox = e.closest(".albums").prev().children(".box");
		albumBoxHtml = albumBox.html();
		if (isNotYetMentionned(albumBox, albumToAdd) && albumToAdd != '' && albumBoxHtml <= 0) {
			albumToAdd = '<span title="Supprimer" class="bubble removable">'+albumToAdd+'</span>';
			albumBox.html(albumBoxHtml+albumToAdd);
		}
	});
	
	$('.albumList').on('click', '.removable', function() {
		albumRemoveAsked = true;
		e = $(this);e.remove();
	});
	
	$('.box.albumList:not(".box.albumList.bubble")').click(function() {
		e = $(this);
		albumBox = $(this);
		albumBoxHtml = albumBox.html();
		if(!albumRemoveAsked) {
			previousToAppend = $(".box.albumList .new_album");
			if(!previousToAppend.length && albumBoxHtml <= 0) {
				toAppend = '<input type="text" class="new_album"/>';
				albumBox.html(albumBoxHtml+toAppend);
				child = albumBox.children('.new_album');
				child.focus();
			}
		}
		albumRemoveAsked = false;
	});
	
	// ALL THE DROPABLES
	$('.line.dropable').click(function() {
		e = $(this); infosBox = e.nextAll('.line.infos').first();
		infosBox.slideToggle( "slow" );
		if (infosBox.hasClass('showable')) {
			infosBox.addClass('hiddable');
			infosBox.removeClass('showable');
		} else if (infosBox.hasClass('hiddable')) {
			infosBox.addClass('showable');
			infosBox.removeClass('hiddable');
		}
	});
	
	//SUBMIT ---
	$('#uploadForm').submit(function() {
		$('.notifications').html('');
		$('.content-to-add').each(function() {
			e = $(this); var listArtist = [], listGenre = [];
			title = e.find('.title');
			year = e.find('.year');
			file = e.find('.file');
			listOfArtist = e.find('.box.artistList');
			listOfGenre = e.find('.box.genreList');
			listOfAlbum = e.find('.box.albumList');
			
			child = listOfArtist.children('.bubble');
			child.each(function() {
				listArtist.push($(this).text());
			});
			child = listOfGenre.children('.bubble');
			child.each(function() {
				listGenre.push($(this).text());
			});
			album = listOfAlbum.text();
			if(title.val() != '' && year.val() != '' && listArtist.length >0 && file.val() != '') {
				$.ajax({
					type: "POST",
					url: "/MuzikKloud/admin/addsongs",
					dataType: 'json',
					data: JSON.stringify({ 'titre': title.val(), 'date': parseInt(year.val()), 'artiste': "", 'fichier': file.val(), 'genres': listGenre, 'albums': album, 'artistes':listArtist }),
					contentType: 'application/json',
					mimeType: 'application/json',
					success: function(data) {
						if(data.status == 'OK') {
							e.remove();
							msg = 'Ajout de '+title.val()+' effectue avec succes!';
							toAdd = '<span class="success">'+msg+'</span>';
							$('.notifications').html($('.notifications').html() + toAdd);
							if($('.content-to-add').length <= 0){
								window.location.href = "/MuzikKloud/admin/songs";
							}
						}
						else {
							msg = 'Ajout de '+title.val()+' echoue. <b>'+data.errorMessage+'</b>';
							toAdd = '<span class="error">'+msg+'</span>';
							e.addClass('errorBox');
							$('.notifications').html($('.notifications').html() + toAdd);
						}
					},
					error:function(data, status, er) { 
						alert("error: "+data+" status: "+status+" er:"+er);
					}
				});
			} else{
				msg = "Erreur lors de l'enregistrement, verifiez les infos du fichier";
				toAdd = '<span class="error">'+msg+'</span>';
				e.addClass('errorBox');
				$('.notifications').html($('.notifications').html() + toAdd);
				e.addClass('errorBox');
				if(title.val() == '') {
					title.addClass('errorBox');
				}
				if(listArtist.length <= 0) {
					listOfArtist.closest('.line').prev('.dropable').find('.songAttribute').addClass('error');
				}
			}
		});
		
		return false;
	});
	
	
});



function isNotYetMentionned(box, toAdd) {
	a = true; child = box.children('.bubble');
	child.each(function() {
		if($(this).text().trim() == toAdd.trim()) {
			a = false;
		}
	});
	return a;
};

