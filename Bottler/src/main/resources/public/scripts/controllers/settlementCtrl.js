/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller('settlementCtrl',function($scope,$rootScope,$location,$cookies,$http,modal){

	$scope.batchDate = "";
	$scope.started = false;
	
	$scope.saveNewDay = function(){
		//alert($scope.batchDate);
		$http.get("settlement/sm01/"+$scope.batchDate+"/CREATE").then(function(response){
			$rootScope.callSettlement();
		});
		$rootScope.closenewday();
		
	}
	
	$scope.newDay = function(){
		
		var Mymodal = new modal();
		Mymodal.NewDay();
	}
	
	$scope.browse = function(){
		if($rootScope.sm01GridSelections.length >1){
			//alert("Please choose just One record to browse at a time");
			}
		
	else{
		$cookies.put('batchDatetoroute',$rootScope.sm01GridSelections[0].batchDate);
		$location.path('/routesinbatch');
		
	}
		$scope.started = false;
	}
	
	$scope.settleDay = function(){
		
		$http.get("settlement/settle/"+$rootScope.sm01GridSelections[0].batchDate).then(function(response){
			$rootScope.callSettlement();
		})
		$scope.started = false;
		
	}
	
	
	$rootScope.callSettlement = function(){
		////alert('hi');
		$http.get("settlement/sm01/"+"2000-01-01"+"/DISPLAY").then(function (response) {
		      $rootScope.settlementGrid = response.data;
		});
		
	$scope.SettlementGridOptions = {
			enableRowSelection : true,
			enableRowHeaderSelection : true,
			multiSelect : false,
			enableSorting : true,
			enableFiltering : true,
			paginationPageSizes: [10,20,50],
		    paginationPageSize: 10,
		   columnDefs: [
	{ name:'BatchDate', field: 'batchDate'},
	{name: 'Status',field:'status'},
	{ name:'Routes', field:'routes',enableFiltering:false},
	{name: 'Orders',field:'orders',enableFiltering:false},

	],
	data :'settlementGrid',
	onRegisterApi: function(gridApi){
	    $scope.sm01Api = gridApi;
	    $rootScope.sm01GridSelections =[];
	    gridApi.selection.on.rowSelectionChanged($rootScope,function(rows){
	    	////alert("hi11");
	    	$rootScope.sm01GridSelections = gridApi.selection.getSelectedRows();
	    	
	    	if($rootScope.sm01GridSelections.length > 1 || $rootScope.sm01GridSelections.length==0 ){
	    		$scope.started = false;
	    		
	    	}
	    	else if($rootScope.sm01GridSelections[0].status == "SETTLED"){
	    		
	    		$scope.started = false;
	    	}
	    	
	    	else{
	    		$scope.started = true;
	    	}
	});
	}
	}
	}
	
	
	$rootScope.callSettlement();
	
	
//$scope.settlementgripOptions	
	
	
});

