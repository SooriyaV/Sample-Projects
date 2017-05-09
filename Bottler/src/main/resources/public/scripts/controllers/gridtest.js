var app1 = angular.module('myApp', ['ngRoute','ui.grid']);
app1.controller('gridco343ntroller',function($scope, $http) {
	$http.get("fetch").then(function (response) {
	      $scope.myData = response.data;
	       });
});