$(document).ready(function() {
	$("#lookuserr").click(function() {
		var id = this.innerText
		$.ajax({
			url : "admin/showUser",
			data : "userid="+id,
			success:function(items){
                $("#lookusername")[0].innerText=id
                var str = '';
                items.forEach(function(e){  
                	time = e.querytime.values
                    str+="<p>"+e.name+"： "+time[0]+":"+time[1]+":"+time[2]+"</p>"
                })  

                $("#lookusersitem")[0].innerHTML=str
            }
		});
	});
});