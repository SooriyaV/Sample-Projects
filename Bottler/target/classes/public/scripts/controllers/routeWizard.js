app3.controller('routeWizard',function($scope,$rootScope,$http,modal,passdata){
	var MyModal = new modal();
	$scope.routeOrderGrid = [];
	$rootScope.loadgrid =[];
	$scope.showload = false;
	$scope.disableChangeLoadbtn = true;
;
	$scope.yes = function(){
		
		$rootScope.ChkIn = "Yes";
		$rootScope.closeChkin();
		MyModal.OrdersInRoute();
	}
	
	$scope.no = function(){
		
		$rootScope.ChkIn = "No";
		var routename = $rootScope.rt01GridSelections[0].routeName;
		$http.get('route/changestatus/CHKIN/'+routename).then(function (response) {$scope.callRoute();});
		$rootScope.closeChkin();
	}
	
$scope.close = function(){
		
	$rootScope.closeChkin();
	}
	
	$scope.callOrders = function(){
		var routename = $rootScope.rt01GridSelections[0].routeName;
		$http.get('route/ordersinRoute/'+routename).then(function (response) {
			angular.forEach(response.data,function(value,key){
				////alert(value);
				$scope.routebean={"ordernumber":""};
				$scope.routebean.ordernumber = value;
				$scope.routeOrderGrid.push($scope.routebean);
				
			});
			 
			});	
	}
	
	$scope.callLoad = function(){
		var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
		var datas = [];
	$http.post("LoadDiff/ld01/DISPLAY/"+$rootScope.Order,datas,config).then(function (response) {
		$rootScope.loadgrid = response.data;
	});
		
	}
	
	$scope.callOrders();
	$scope.routeOrderGridOptions = {
			enableRowSelection : true,
			enableRowHeaderSelection : true,
			multiSelect : true,
			enableSorting : true,
			enableFiltering : true,
			paginationPageSizes: [1, 50, 75],
		    paginationPageSize: 25,
		   columnDefs: [
   { name:'Orders', field: 'ordernumber'/* ,cellTemplate:'articlesinorder.html'*/},
    
  ],
  data: 'routeOrderGrid',
  onRegisterApi: function(gridApi){
      $scope.gridApiGrid2 = gridApi;
      gridApi.selection.on.rowSelectionChanged($scope,function(rows){
      	
        $rootScope.orderSelected = gridApi.selection.getSelectedRows();
        $scope.disableChangeLoadbtn = $rootScope.orderSelected.length== 0;
      
      });
	  
	  
	}
	}
	
	$scope.alterItems = function(){
		//$rootScope.closeChkin();
		  passdata.setOrderNumber( $rootScope.orderSelected[0].ordernumber);
		 // MyModal.LoadTable();
		  $scope.showload = true;
		  $scope.callLoad();
	}
	
	$scope.back = function(){
		 $scope.showload = false;
		 $scope.disableChangeLoadbtn = true;
		
	}
	
	$scope.saveLoad = function(){
		
		var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
		var datas = $rootScope.loadgrid;
	$http.post("LoadDiff/ld01/UPDATE/"+$rootScope.Order,datas,config).then(function (response) {
		$rootScope.loadgrid = response.data;
	});
		
	}
	
	$scope.loadgridoptions = {
			enableRowSelection : true,
			enableRowHeaderSelection : true,
			multiSelect : true,
			enableSorting : true,
			enableFiltering : true,
			paginationPageSizes: [1, 50, 75],
		    paginationPageSize: 25,
		   columnDefs: [
   { name:'Item', field: 'item'/* ,cellTemplate:'articlesinorder.html'*/},
   {name:'Actual load',field : 'actualUnits'},
   {name:'Outload Units',field : 'outloadUnits'},
   {name:'Inload Units',field :'inloadUnits'}
    
  ],
  data : 'loadgrid'}
	
	
});