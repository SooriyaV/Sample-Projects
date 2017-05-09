function accordion(){
	////alert('Hi');
    
	var acc = document.getElementsByClassName("accordion");

var i;

for (i = 0; i < acc.length; i++) {
	////alert('Hi');
    acc[i].onclick = function(){
        this.classList.toggle("active");
        this.nextElementSibling.classList.toggle("show");
  }
}}