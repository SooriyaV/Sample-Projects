function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
   // document.getElementById("test").style.marginLeft = "50px";
    //document.getElementsByClassName("grid").style.width = "50px"
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    
}

function theme(){
	 document.getElementById("main").style.background-color = "red";
	
}