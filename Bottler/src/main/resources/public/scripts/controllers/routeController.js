app3.controller('routeController',function($scope,$rootScope,$http,$location,modal,$cookies){

$scope.statusCheckOut = false;
$scope.statusCheckIn = false;
$scope.view = false;
$scope.newroute = {"routeName":"","driver":"","vehicle":"","status":"NEW"};
var myModal = new modal();


	
$rootScope.callRoute = function(){
	////alert('hi');
	$http.get("route/rt01displayall").then(function (response) {
	      $rootScope.routeGrid = response.data;
	});
	
$scope.routeGridOptions = {
		enableRowSelection : true,
		enableRowHeaderSelection : true,
		multiSelect : false,
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
data :'routeGrid',

onRegisterApi: function(gridApi){
    $scope.rt01Api = gridApi;
    $rootScope.rt01GridSelections =[];
    
    
   /* gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
    	
    	
    	var rowCol = gridApi.cellNav.getFocusedCell();
    	
    	console.log(rowCol.col);
    	console.log(rowCol.col.MODEL_COL_FIELD);
    	console.log(rowCol.row);
    	////alert(rowCol.col.colDef.editDropdownOptionsArray[0].status);
    	if( colDef.name === 'Status' ){
    		console.log(newValue);
    		rowEntity.transitStatus = 'Scheduled';
    	}
    });*/
    
    gridApi.selection.on.rowSelectionChanged($rootScope,function(rows){
    	////alert("hi11");
    	$rootScope.rt01GridSelections = gridApi.selection.getSelectedRows();
    	if($rootScope.rt01GridSelections.length == 1){
    		$scope.view = true;
    	if($rootScope.rt01GridSelections[0].status == "NEW"){
    		$scope.statusCheckIn = false;
    		$scope.statusCheckOut = true;
    	}
    	
    	else if($rootScope.rt01GridSelections[0].status == "CHKOUT"){
    		$scope.statusCheckIn = true;
    		$scope.statusCheckOut = false;
    	}
    	
    	else if($rootScope.rt01GridSelections[0].status == "CHKIN"){
    		$scope.statusCheckIn = false;
    		$scope.statusCheckOut = false;
    	}}
    	else{
    		$scope.view = false;
    		$scope.statusCheckIn = false;
    		$scope.statusCheckOut = false;
    	}
    });
}

};	}

$rootScope.callRoute();

$scope.newRoute = function(){
	
	
}

$scope.checkOut = function(){
	var routename = $rootScope.rt01GridSelections[0].routeName;
	$http.get('route/changestatus/CHKOUT/'+routename).then(function (response) {	$rootScope.callRoute();});
	$scope.view = false;
	$scope.statusCheckIn = false;
	$scope.statusCheckOut = false;
	
};

$scope.checkIn = function(){
	myModal.checkInDialog();
	/*if($rootScope.ChkIn == "Yes"){
	var routename = $rootScope.rt01GridSelections[0].routeName;
	$http.get('route/changestatus/CHKIN/'+routename).then(function (response) {$scope.callRoute();});
	
	$scope.statusCheckIn = true;
	$scope.statusCheckOut = true;}*/
	
};

$scope.removeRoute = function(){
	myModal.trial();
}

$scope.newRoute = function(){
	$scope.view = false;
	$scope.statusCheckIn = false;
	$scope.statusCheckOut = false;
	myModal.NewRoute();
	
}

$scope.saveNewRoute = function(){
	
	var config = {
            headers : {
                'Content-Type': 'application/json;'
            }
        };
$http.post('route/rt01/CREATE',$scope.newroute,config).then(function (response) {

	$rootScope.CloseNewRoute();
	
	});
$scope.view = false;
$scope.statusCheckIn = false;
$scope.statusCheckOut = false;
}

$scope.viewOrders = function(){
	var routename = $rootScope.rt01GridSelections[0].routeName;
	$cookies.put('routeFromMain',routename);
	$location.path("/ordersinroute")
	
	
	
}
	
	
});

