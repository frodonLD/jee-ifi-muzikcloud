$(document).ready(function(){
	var artistRemoveAsked = false;
	var genreRemoveAsked = false;
	var albumRemoveAsked = false;
	
	// ARTISTS ---
	$('.existingArtistList .addable').click(function(){
		e = $(this);
		artistToAdd = $(e).html();
		artistBox = e.closest(".artists").prev().children(".box");
		artistBoxHtml = artistBox.html();
		if (isYetMentionned(artistBox, artistToAdd)){
			artistToAdd = '<span class="bubble removable">'+artistToAdd+'</span>';
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
		if(!artistRemoveAsked){
			previousToAppend = $(".box.artistList .new_artist");
			if(!previousToAppend.length){
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
			if (isYetMentionned(artistBox, artistToAdd)){
				e.remove();
				artistBoxHtml = artistBox.html();
				artistToAdd = '<span class="bubble removable">'+artistToAdd+'</span>';
				artistBox.html(artistBoxHtml+artistToAdd);
			}
			e.remove();
		}
	});
	
	// GENRES ---
	$('.existingGenreList .addable').click(function(){
		e = $(this);
		genreToAdd = $(e).html();
		genreBox = e.closest(".genres").prev().children(".box");
		genreBoxHtml = genreBox.html();
		if (isYetMentionned(genreBox, genreToAdd)){
			genreToAdd = '<span class="bubble removable">'+genreToAdd+'</span>';
			genreBox.html(artistBoxHtml+genreToAdd);
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
		if(!genreRemoveAsked){
			previousToAppend = $(".box.genreList .new_genre");
			if(!previousToAppend.length){
				toAppend = '<input type="text" class="new_genre"/>';
				genreBox.html(genreBoxHtml+toAppend);
				child = genreBox.children('.new_genre');
				child.focus();
			}
		}
		genreRemoveAsked = false;
	});
	
	// ALBUMS ---
	$('.existingAlbumList .addable').click(function(){
		e = $(this);
		albumToAdd = $(e).html();
		albumBox = e.closest(".albums").prev().children(".box");
		albumBoxHtml = albumBox.html();
		if (isYetMentionned(albumBox, albumToAdd)){
			albumToAdd = '<span class="bubble removable">'+albumToAdd+'</span>';
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
		if(!albumRemoveAsked){
			previousToAppend = $(".box.albumList .new_album");
			if(!previousToAppend.length){
				toAppend = '<input type="text" class="new_album"/>';
				albumBox.html(albumBoxHtml+toAppend);
				child = albumBox.children('.new_abum');
				child.focus();
			}
		}
		albumRemoveAsked = false;
	});
	
	
	//SUBMIT ---
	$('#uploadForm').submit(function() {
		
		$('.content-to-add').each(function(){
			
			e = $(this); listArtist = listGenre = listAlbum = '';
			title = e.find('.title');
			year = e.find('.year');
			file = e.find('.file');
			listOfArtist = e.find('.box.artistList');
			listOfGenre = e.find('.box.genretList');
			listOfAlbum = e.find('.box.albumList');
			
			child = listOfArtist.children('.bubble');
			child.each(function(){
				listArtist += $(this).text()+'####';
			});
			
			
			child = listOfGenre.children('.bubble');
			child.each(function(){
				listGenre += $(this).text()+'####';
			});
			
			child = listOfAlbum.children('.bubble');
			child.each(function(){
				listAlbum += $(this).text()+'####';
			});
			
			recap = "Titre ==> "+title.val()+" <br/>Year ==> "+year.val()+" <br/>ListArtist = "+listArtist+"<br/>ListAlbum= "+listAlbum+" <br/>File ==> "+file.val();
			
			$('.hearder').html(recap);
			
			if(title.val() != '' && year.val() != '' && listArtist != '' && file != ''){
				/* 
				var request = $.ajax({
					  url: "/admin/addsongs",
					  type: "POST",
					  data: { "nom" : "Frodon" },
					});
				 */
					$.ajax({
					  type: "POST",
					  url: "/MuzikKloud/admin/addsongs",
					  dataType: 'json',
//					  data: { "nom" : "Frodon" },
//					  data: JSON.stringify({ titre: title.val(), date: parseInt(year.val()), artiste: listArtist, fichier: file, genre: listGenre, album: listAlbum }),
					  data: '{"titre": "un", "date":'+2014+', "artiste": "Artists", "fichier": "fichier", "genre": "Genres", "album": "Albums" }',
					  contentType: 'application/json',
					  mimeType: 'application/json',
					  success: function(data) {
					    if(data.status == 'OK') alert('Song has been added');
					    else alert('Failed adding song' + data.status + ', ' + data.errorMessage);
					  },
					  error:function(data, status, er) { 
					        alert("error: "+data+" status: "+status+" er:"+er);
					   }
					});
			} else{
				msg = "Erreur lors de l'enrégistrement, vérifiez les infos du fichier";
			}
		});
		return false;
	});
	
});



function isYetMentionned(box, toAdd){
	a = true; child = box.children('.bubble');
	child.each(function(){
		if($(this).text().trim() == toAdd.trim()){
			a = false;
		}
	});
	return a;
};

