var app3=angular.module('hello', [ 'ngRoute','ngAnimate','ui.grid','ui.grid.edit','ui.grid.selection','ui.grid.resizeColumns','ui.grid.pagination','ui.grid.cellNav','ui.grid.autoResize','googlechart','ngCookies','uiGmapgoogle-maps']);

app3.service('APIInterceptor', function($window){
	
	var service = this;
	service.responseError = function(response) {
		//alert(response.status);
        if (response.status === 401) {
        	$window.location.href = '/index.html'
        }
        return response;
    };

});
 