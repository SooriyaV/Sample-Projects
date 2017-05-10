/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller("GenericChartCtrl", function ($scope,$http) {
    $scope.myChartObject = {};
    $scope.days = 5;
  /*$scope.sales = 0;
	$scope.orders = 0;
	$scope.routes = 0;*/
    /*$scope.barData =[['Locale', 'Count']];*/
   $http.get("DashBoardCall").then(function(response){
		$scope.dataaa = response.data;
		$scope.sales = response.data.salestoday;
		$scope.orders = response.data.orders;
		$scope.routes = response.data.routesout;
		
		
	});
    $scope.myChartObject.type = "AreaChart";
    $scope.gatherData = function(){
    	$http.get("salesBar/"+$scope.days).then(function (response) {
    		$scope.barData =[['Date', 'Sales']];
    		angular.forEach(response.data,function(value,key){
    			
    			var temparray= [];
    			temparray.push(value.orddate);
    			temparray.push(value.totalSales);
    			this.push(temparray);
    			
    			
    		}, $scope.barData);
    		$scope.myChartObject.data = $scope.barData;
    		
    		
    		
    	});
    	
    	
    	
    }
    $scope.gatherData();
    
    $scope.daychange = function(){
    	if($scope.days!=""){
    	$scope.statement = "Sales for the last "+ $scope.days+" days";
    	$scope.gatherData();
    	$scope.myChartObject.options = {
    	        'title': $scope.statement,
    	        displayMode: 'regions',
    	        vAxis : {format:'currency'},
    	        animation: {duration: 1000, startup: true}
    	    };}}
    //$scope.daychange();
    $scope.statement = "Sales for the last "+ $scope.days+" days";
    $scope.onions = [
        {v: "Onions"},
        {v: 3},
    ];

    $scope.myChartObject.data =  $scope.barData;

    $scope.myChartObject.options = {
        'title': $scope.statement,
        displayMode: 'regions',
        vAxis : {format:'currency'},
        animation: {duration: 1000, startup: true}
    };
});