/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
 
app3.controller('customersCtrl', function($scope, $http) {
			$http.get("fetch").then(function (response) {
			      $scope.myData = response.data;
			       });
			$scope.getit =function(){ $http.get("fetch").then(function (response) {
		      $scope.myData = response.data;
		       });
			}
		  $scope.txt="f";
		  $scope.value1="hi";
		  $scope.increment = false;
		  $scope.postit =function() {
			  $scope.article = {"articles":$scope.txt};
			  $http.post("fetch", $scope.article).success(function(data, status) {
		          $scope.hello = data;
		          $scope.getit();
		          $scope.increment = false;
		      })
		  };
		  $scope.add = function(){
			  $scope.increment = true;
		  }
		  $scope.value = function(orderno){
			  $http.get("fetchbyId/"+orderno).then(function (response) {
			      $scope.ordart = response.data;
		  })
		  }
		  $scope.addRow = function(){
			  $scope.ordart.push('','','','');
			  
		  }
		});