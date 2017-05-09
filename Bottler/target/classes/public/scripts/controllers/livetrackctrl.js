app3.controller('livetrackctrl',function($scope,$interval,$http){
	//alert('hi');
	
$scope.lat = 12.9662232;
$scope.lon = 77.7259235;
$scope.flushtrack = function(){
	var config = {
	        headers : {
	            'Content-Type': 'application/json;'
	        }
	    };
$scope.locationobj = {latitude:0.0,longitude:0.0,title:""};	
$scope.locationobj.latitude = 12.9662232;
$scope.locationobj.longitude = 77.7259235;
$scope.locationobj.title = "A11";
$http.post('location/postlocation',$scope.locationobj,config).then(function(){
	console.log('success');

})
}
$scope.flushtrack();

$interval(function(){$http.get("location/getlocationdata/A11").then(function(response){
$scope.lat = response.data.latitude;
$scope.lon = response.data.longitude;
$scope.testloop();
});},10000)

$scope.testloop = function(){
	
	 
		 
	console.log('hi1');
	$scope.map = {
		      center: {
		    	  latitude: $scope.lat,
			        longitude: $scope.lon 
		      },
		      zoom: 16 };
	
	 $scope.options = {
		      scrollwheel: false
		    };
	 var createRandomMarker = function(i) {
		
			 //console.log(response.data.latitude);
			 var ret = {
				        latitude: $scope.lat,//response.data.latitude,
				        longitude: $scope.lon,//response.data.longitude ,
				        title: 'm' + i
				      };
			 
			 ret.icon = {
		               url: 'scripts/images/truck.png',
		               scaledSize: { width: 36, height: 48 }
		           };
			      ret["id"] = "A11";
			      return ret;
			 
	       
	       
	        
	    };
	    var markers = [];
	    /*for (var i = 0; i < 50; i++) {*/
	      markers.push(createRandomMarker(0))
	    /*}*/
	    $scope.randomMarkers = markers;
	
	
}	
	
	
$scope.testloop();	 
		   
	
})