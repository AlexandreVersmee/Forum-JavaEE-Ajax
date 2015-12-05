$('.add-comment').on("click", function(){
	var id = $(this).attr('id');
	$('#comment-row'+id).toggle();
});

$('.hideComment').on("click", function(){
	var id = $(this).attr('id');
	$('#comment-row'+id).toggle();
});

$('.hideEdit').on("click", function(){
	var id = $(this).attr('id');
	$('#edit-row'+id).toggle();
});


/* Reopen popup */
var url = window.location.href;
var id = url.substring(window.location.href.lastIndexOf('/') + 1);
var ret = url.match("message");
console.log("ret = "+ret);
if (id > 0 && ret != null)
{
	$('#edit-row'+id).toggle();
}

