/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller('inventoryandstockctrl',function($scope,$http,passdata,$location,$rootScope,modal){
	
	$scope.singleselection = false;
	$scope.invgridSelections =[];
	$rootScope.callInv = function(){
	$http.get("inventory/im01displayall").then(function (response) {
	      $rootScope.im01grid = response.data;
    });
		
	$scope.im01gridoptions = 
	{multiselect:true,
    enableSorting: true,
    columnDefs: [
     { name:'Inventory Number', field: 'inventoryNumber'/* ,cellTemplate:'articlesinorder.html'*/},
      { name:'Inventory Name', field:'inventoryName'}
      ],
    data :'im01grid',
    
    onRegisterApi: function(gridApi){
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope,function(rows){
        	
          $scope.invgridSelections = gridApi.selection.getSelectedRows();
          $scope.im01selects=gridApi.selection.getSelectedRows();
      	if($scope.im01selects.length > 1 || $scope.im01selects.length == 0 ){
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
	}
	$rootScope.callInv();
	
	 $scope.newInv = function(){
			$rootScope.isNewInv = "NEW";
			$scope.singleselection = false;
			$scope.storevalue();
			var Mymodal = new modal();
			Mymodal.opennewInv();	
			
		}
		
		$scope.modifyInv = function(){
			$rootScope.isNewInv = "MODIFY";
			$scope.singleselection = false;
			$scope.storevalue();
			var Mymodal = new modal();
			Mymodal.opennewInv();	
			
		}
	
		$scope.storevalue = function(){
			if(angular.isUndefined($rootScope.isNewInv)){
				//alert('Undefined');
			}
			else{
			if($rootScope.isNewInv == "NEW"){
				$rootScope.invbean = {inventoryNumber:"",inventoryName:""};
				$rootScope.editinv = false;
			}
			else{
				$rootScope.invbean = $scope.im01selects[0];
				$rootScope.editinv = true;
			}
			}
			
		}
		
		$scope.saveInv = function(){
			$scope.singleselection = false;
			var data = $rootScope.invbean;
			var config = {
	                headers : {
	                    'Content-Type': 'application/json;'
	                }
	            };
			$http.post("inventory/im01/CREATE",data,config).then(function(){
				$rootScope.closeopenInv();
				$rootScope.callInv();
				
			});
			
			$rootScope.callInv();
		}
		
	
	
		$scope.removeInv = function(){
			angular.forEach($scope.im01selects,function(value,key){
				
				var data = value;
				var config = {
		                headers : {
		                    'Content-Type': 'application/json;'
		                }
		            };
				$http.post("inventory/im01/DELETE",data,config).then(function(){
					$rootScope.callInv();
				});
				
			});
		}
	
	
	
	
	
	
	
	
	
	
	$scope.tomanagestock = function(){
		if($scope.invgridSelections.length >1){
			
			//alert("You can only modify ONE data at a time. Please select just One data");
		}
		else if($scope.invgridSelections.length ==0){
			//alert("No data selected to Modify!!");
			
		}
		else{
		passdata.setInventoryNumber($scope.invgridSelections[0].inventoryNumber);
		$location.path("/stockininventory");		
		}
	}
			
	
});