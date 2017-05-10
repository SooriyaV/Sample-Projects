/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
var locationapp=angular.module('locationapp', [ 'ngRoute','ngCookies','ngGeolocation']);

locationapp.controller('locationpostctrl',function($scope,$http,$interval,$geolocation){
    
	
	$scope.locationobj = {latitude:0.0,longitude:0.0,title:""}
	$scope.latitude = 0.0;
	$scope.longitude = 0.0;
	$scope.route = "A11";
	
	$interval(function(){$scope.locationmethod()},10000);

	$scope.locationmethod = function(){
		//alert('hi');
		var config = {
		        headers : {
		            'Content-Type': 'application/json;'
		        }
		    };
	    $geolocation.getCurrentPosition({
	        timeout: 6000,
	    enableHighAccuracy: true
	     }).then(function(position) {
	        $scope.myPosition = position;
	        alert($scope.myPosition.coords.latitude);
	        $scope.latitude = $scope.myPosition.coords.latitude;
	        $scope.longitude =  $scope.myPosition.coords.longitude;
	        $scope.locationobj.latitude = $scope.latitude;
			$scope.locationobj.longitude = $scope.longitude;
			$scope.locationobj.title = $scope.route;
			$http.post('location/postlocation',$scope.locationobj,config).then(function(){
				console.log('success');
			
			
		})
	     });
/*	$http.get('http://ip-api.com/json').then(function(response){
		alert(response.data.lat);
		$scope.latitude = response.data.lat;
		$scope.longitude = 	response.data.lon;
		$scope.locationobj.latitude = $scope.latitude;
		$scope.locationobj.longitude = $scope.longitude;
		$scope.locationobj.title = $scope.route;
		
		$http.post('location/postlocation',$scope.locationobj,config).then(function(){
			alert('success');
		
		
	})
	
		
	})*/
	
	}
	$scope.locationmethod();
	
})