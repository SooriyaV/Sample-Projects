/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller('routesinbatchCtrl',function($scope,$rootScope,$cookies,$location,$http,modal){
	

$scope.batchdate = $cookies.get('batchDatetoroute');

$scope.addRoutestobatch = function(){
	var Mymodal = new modal();	
	Mymodal.openroutesforbatch();
}
$scope.settleinbatch = function(){
	var data = $scope.selectedroutes;
	var config = {
            headers : {
                'Content-Type': 'application/json;'
            }
        };
	$http.post("settlement/addroutes/"+$cookies.get('batchDatetoroute'),data,config).then(function(){
		
		$rootScope.callrouteinbatch();
		$rootScope.closeopenroutesforbatch();
		
	})
	
}


$rootScope.callrouteinbatch = function(){
	
	$http.get("settlement/displayroutes/"+ $cookies.get('batchDatetoroute')).then(function(response){
		
		$rootScope.routeinBatch = response.data;
		
	});


$scope.routeinBatchOptions = {
		enableRowSelection : true,
		enableRowHeaderSelection : true,
		multiSelect : false,
		enableSorting : true,
		enableFiltering : true,
		paginationPageSizes: [10,20,50],
	    paginationPageSize: 10,
	   columnDefs: [
{ name:'Route', field: 'route'},
{name: 'Order',field:'orders',enableFiltering:false},
{ name:'Status', field:'status'}
],
data :'routeinBatch',
onRegisterApi: function(gridApi){
    $scope.sm01Api = gridApi;
    $rootScope.sm01GridSelections =[];
    gridApi.selection.on.rowSelectionChanged($rootScope,function(rows){
    	////alert("hi11");
    	$rootScope.sm01GridSelections = gridApi.selection.getSelectedRows();
});
}
}
}
$rootScope.callrouteinbatch();

$scope.callchkinroutes = function(){
	
$http.get("route/routesinstatus/CHKIN").then(function(response){
		
		$scope.routeforModal = response.data;
		
	});


$scope.routesinchkinopt = {		enableRowSelection : true,
		enableRowHeaderSelection : true,
		multiSelect : true,
		enableSorting : true,
		enableFiltering : true,
		paginationPageSizes: [10,20,50],
	    paginationPageSize: 10,
	   columnDefs: [
{ name:'Route', field: 'routeName'},
{ name:'Driver', field:'driver'},
{name: 'Vehicle No.',field:'vehicle'},
{name: 'Status',field:'status'}

],
data :'routeforModal',
onRegisterApi: function(gridApi){
   // $scope.sm01Api = gridApi;
    $scope.selectedroutes =[];
    gridApi.selection.on.rowSelectionChanged($scope,function(rows){
    	////alert("hi11");
    	 $scope.selectedroutes = gridApi.selection.getSelectedRows();
});
}
}
	
	
	
	
}

$scope.callchkinroutes();

})
