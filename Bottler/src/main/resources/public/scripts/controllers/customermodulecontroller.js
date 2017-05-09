app3.controller('customermodulecontroller',function($scope,$http,$location,modal,$rootScope){
	
$scope.action = '';
$scope.singleselection = false;

	
$rootScope.creategrid = function(){
	$http.get("customer/cm01displayall").then(function (response) {
		$rootScope.customergrid = response.data;
});
	$scope.customergridoptions = 
	{
			enableRowSelection : true,
			enableRowHeaderSelection : true,
			multiSelect : true,
			enableSorting : true,
			enableFiltering : true,
			paginationPageSizes: [1, 50, 75],
		    paginationPageSize: 25,
		   columnDefs: [
   { name:'Reference No.', field: 'custloc'/* ,cellTemplate:'articlesinorder.html'*/},
    { name:'Customer Name', field:'custname'},
    {name: 'Email',field:'email'},
    {name: 'Phone',field:'phone1'}
    
  ],
  data :'customergrid',
  onRegisterApi: function(gridApi){
      $scope.cm01selects =[];
              gridApi.selection.on.rowSelectionChanged($scope,function(rows){

  
  $scope.cm01selects=gridApi.selection.getSelectedRows();
	if($scope.cm01selects.length > 1 || $scope.cm01selects.length == 0 ){
		$scope.singleselection = false;
		$scope.disableModify = true;
		
	}
	else{
		$scope.singleselection = true;
		$scope.disableModify = false;
	}
              });
  
	}
	}
};
$rootScope.creategrid();	
	
	$scope.newCust = function(){
		$rootScope.isNewCust = "NEW";
		$scope.singleselection = false;
		$scope.storevalue();
		var Mymodal = new modal();
		Mymodal.opennewCust();	
		
	}
	
	$scope.modifyCust = function(){
		$rootScope.isNewCust = "MODIFY";
		$scope.singleselection = false;
		$scope.storevalue();
		var Mymodal = new modal();
		Mymodal.opennewCust();	
		
	}
	
	$scope.storevalue = function(){
		if(angular.isUndefined($rootScope.isNewCust)){
			//alert('Undefined');
		}
		else{
		if($rootScope.isNewCust == "NEW"){
			$rootScope.custbean = {custloc:0,custname:"",address:"",email:"",phone1:0,phone2:0};
			$rootScope.editcust = false;
		}
		else{
			$rootScope.custbean = $scope.cm01selects[0];
			$rootScope.editcust = true;
		}
		}
		
	}
	
	$scope.saveCust = function(){
		$scope.singleselection = false;
		var data = $rootScope.custbean;
		var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
		$http.post("customer/cm01/CREATE",data,config).then(function(){
			$rootScope.closeopenCust();
			$rootScope.creategrid();
			
		});
		
		$rootScope.creategrid();
	}
	
	
	$scope.removeCust = function(){
		angular.forEach($scope.cm01selects,function(value,key){
			
			var data = value;
			var config = {
	                headers : {
	                    'Content-Type': 'application/json;'
	                }
	            };
			$http.post("customer/cm01/DELETE",data,config).then(function(){
				$rootScope.creategrid();
			});
			
		});
	}
	
	
	
});
