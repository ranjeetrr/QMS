function deleteCust()
{
	var queueId = document.getElementById("queueid");
	alert("delete");
	var url=hostURL+"rmv/"+queueId;
	$.ajax( {type: "GET",
		data: "{}",
		url: url,		
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		});
	
}


function getMessage()
{

var urlX= hostURL+"getAllRep/";    
$.ajax( {
			type: "GET",
			data: "{}",
			url: urlX,		
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			}).then(function(response)
					{
						var users = response;
						var a=[];
						$.each(users, function(index, user) 
						{
							a.push(user.first_name);
						});
			
						var z='<option value="NEXT AVAILABLE">NEXT AVAILABLE</option>';
						for(var i=0;i<a.length;i++)
						{
							z=z+'<option value="'+a[i]+'">'+a[i]+'</option>';
						}
						var url= hostURL;
						$("#myTableDataUpgrade").empty();
						$.ajax( 
								{
									type: "GET",
									data: "{}",
									url: url,		
									contentType: "application/json; charset=utf-8",
									dataType: "json",
									}).then(function(response)
											{
								var users = response;
								
								var fe = JSON.stringify(users);
								
								$.each(users, function(index, user) 
										{
											
											var i=new Date(user.qDate).toLocaleString();
											i=i.split(",");
											var m = '<div class="input-group">';
											var n = '<select class="input-control select2" id="sel2" onchange="getAndSetRep(this.options[this.selectedIndex].value,'+ user.queueId+')">';
											var o = '<option selected="selected"></option>';
											var p = '</select>';
											
											
											$("#myTableDataUpgrade").append
											('<tr><td>'+user.fName+'</td><td>'+user.type+'</td><td>'+user.queueId+'</td><td>'+user.reason+'</td><td>'+i[1]+'</td><td>'+user.sales_REP_ASSIGNED+'</td><td>'+user.status+'</td><td>'+m+n+o+z+p+'</td></tr>'); //class="glyphicon glyphicon-ok"
								
											//var repNameList;			                
											
											
										});
				        
							});
			});       
}


/*function getMessage()
{
	var usersX=JSON.parse(window.sessionStorage.getItem('RepNameList'));
	var users =JSON.parse(window.sessionStorage.getItem('UpdatedQueue'));
	
	var a=[];
	$.each(usersX, function(index, user) 
			{
				a.push(user.first_name);
			});
	var z='<option value="NEXT AVAILABLE">NEXT AVAILABLE</option>';
	for(var i=0;i<a.length;i++)
	{
		z=z+'<option value="'+a[i]+'">'+a[i]+'</option>';
	}
	$("#myTableDataUpgrade").empty();
	 	$.each(users, function(index, user) 
		{
	 		var i=new Date(user.qDate).toLocaleString();      	
			var m = '<div class="input-group">';
			var n = '<select class="input-control select2" id="sel2" onchange="getAndSetRep(this.options[this.selectedIndex].value,'+ user.queueId+')">';
			var o = '<option selected="selected"></option>';
			var p = '</select>';
			$("#myTableDataUpgrade").append
			('<tr><td>'+user.fName+'</td><td>'+user.type+'</td><td>'+user.queueId+'</td><td>'+user.reason+'</td><td>'+i+'</td><td>'+user.sales_REP_ASSIGNED+'</td><td>'+user.status+'</td><td>'+m+n+o+z+p+'</td></tr>'); //class="glyphicon glyphicon-ok"
		});
}
*/
function savechanges()
{
	//alert("inside save changes");
	var firstName=document.getElementById("firstName").value;
	var lastName=document.getElementById("lastName").value;
	var phone=document.getElementById("phone").value;
	var comment=document.getElementById("comment").value;
	var type=document.getElementById("selCust").value;
	var reason=document.getElementById("selReason").value;
	var salRep=document.getElementById("selRep").value;
	//alert("chosed sales "+salRep);
	//alert(firstName+lastName+phone);
	var url=hostURL+firstName+"/"+lastName+"/"+phone+"/"+reason+"/"+type+"/"+comment+"/"+salRep;
	//alert("url="+url);
	$.ajax( {type: "GET",
		data: "{}",
		url: url,		
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		}).then(function(response){
			
			var jsonString=JSON.stringify(response);
			window.sessionStorage.setItem('UpdatedQueue',jsonString);
			alert(window.sessionStorage.getItem('UpdatedQueue'));
		});
	//alert(window.sessionStorage.getItem('UpdatedQueue'));
	var url = "rep_queue.html";
	window.location.href = url;
}
function getOptionValue(optionSelected, idOnOption){
	//alert("option selected: "+optionSelected);
	//alert("id for which option is selected is: "+idOnOption );
	var url= hostURL+"rmv/"+idOnOption;
	alert(url);
	$("#myTableDataUpgrade").empty();
	$.ajax( {
		type: "GET",
		data: "{}",
		url: url,		
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		}).then(function(response){
			var jsonString=JSON.stringify(response);
			window.sessionStorage.setItem('UpdatedQueue',jsonString);
			
			//alert(window.sessionStorage.getItem('UpdatedQueue'));
		});
	getMessage();
}
function getAndSetRep(optionSelected,idOnOption)
{
	alert("Name selected was :"+optionSelected);
	alert("Id for whict rep needs to be changed: "+idOnOption);
	var url= hostURL+"newrep/"+optionSelected+"/"+idOnOption;
	//alert(url);
	$("#myTableDataUpgrade").empty();
	$.ajax( {
		type: "GET",
		data: "{}",
		url: url,		
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		}).then(function(response){
			var jsonString=JSON.stringify(response);
			window.sessionStorage.setItem('UpdatedQueue',jsonString);
			
			//alert(window.sessionStorage.getItem('UpdatedQueue'));
		});
	getMessage();
	location.reload();
}






