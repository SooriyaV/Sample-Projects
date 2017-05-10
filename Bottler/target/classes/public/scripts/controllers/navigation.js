/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3
  /*.controller('home', function($scope, $http) {
    $http.get('resource/').success(function(data) {
      $scope.greeting = data;
    })
  })*/
  .controller('navigation',function($rootScope, $scope, $http, $location,$window) {

		  var authenticate = function(credentials, callback) {

		    var headers = credentials ? {authorization : "Basic "
		        + btoa(credentials.username + ":" + credentials.password)
		    } : {};

		    $http.get('user', {headers : headers}).success(function(data) {
		    	////alert(data);
		      if (data.name) {
		        $rootScope.authenticated = true;
		      } else {
		        $rootScope.authenticated = false;
		      }
		      callback && callback();
		    }).error(function() {
		      $rootScope.authenticated = false;
		      callback && callback();
		    });

		  }

		 /* authenticate();*/
		  $scope.credentials = {};
		  $scope.login = function(){
			  
var sample = {'userId':"",'passWord':"",'authenticated':false};
sample.userId = $scope.credentials.username;
sample.passWord = $scope.credentials.password;
var config = {
        headers : {
            'Content-Type': 'application/json;'
        }
    };
var data = sample;			  
$http.post("doLogin/check",data,config).then(function(response){
				  
				  if(response.data.authenticated == true){
					  
					  $scope.doLogin();
				  }
				  else{
					  $scope.error = true;
				  }
				  
			  });
			  
		  }
		  $scope.doLogin = function() {
			 //n jn  authenticate();			  
		      authenticate($scope.credentials, function() {
		    	  //alert("hi");
		        if ($rootScope.authenticated) {
		        	
		        	$window.location.href = 'overview.html#/dashboard';
		          $scope.error = false;
		        } else {
		        	//alert("hiii");
		          $location.path("/ToW");
		          $scope.error = true;
		        }
		        });
		  }
		$scope.test = "testing...";
		        
		        $scope.logout = function() {
		        	
		        	  $http.post('logout', {}).success(function() {
		        	    $rootScope.authenticated = false;
		        	    $window.location.href = '/ToW';
		        	  }).error(function(data) {
		        		  $window.location.href = '/ToW';
		        	    $rootScope.authenticated = false;
		        	  });
		        	}  
		        
		        
		        $scope.modify = function(value){
		        	//alert("success" +value);
		        }
		})	