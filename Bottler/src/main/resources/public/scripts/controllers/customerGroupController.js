app3.controller('customerGroupController',function($scope,$rootScope,$location,$http,passdata,modal){
	 $scope.singleselection = false;
	
	$rootScope.creategridCG = function(){
		$http.get("customergroup/cm02displayall").then(function (response) {
			$rootScope.customergroupgrid = response.data;
	});
		
		$scope.customergroupgridoptions = 
		{
				enableRowSelection : true,
				enableRowHeaderSelection : true,
				multiSelect : true,
				enableSorting : true,
				enableFiltering : true,
				paginationPageSizes: [1, 50, 75],
			    paginationPageSize: 25,
			   columnDefs: [
	    { name:'Group Name', field:'group'},
	    {name: 'Description',field:'description'}	    
	  ],
	  data :'customergroupgrid',
	  
	  onRegisterApi: function(gridApi){
	        $scope.cm02selects =[];
	                gridApi.selection.on.rowSelectionChanged($scope,function(rows){
	                	$scope.cm02selects=gridApi.selection.getSelectedRows();
	                	if($scope.cm02selects.length > 1 || $scope.cm02selects.length == 0 ){
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


		$rootScope.creategridCG();
		
		$scope.newCG = function(){
			$rootScope.isNewCG = "NEW";
			$scope.singleselection = false;
			$scope.storevalue();
			var Mymodal = new modal();
			Mymodal.opennewCG();	
			
		}
		
		$scope.modifyCG = function(){
			$rootScope.isNewCG = "MODIFY";
			$scope.singleselection = false;
			$scope.storevalue();
			var Mymodal = new modal();
			Mymodal.opennewCG();	
			
		}
		
		$scope.storevalue = function(){
			if(angular.isUndefined($rootScope.isNewCG)){
				//alert('Undefined');
			}
			else{
			if($rootScope.isNewCG == "NEW"){
				$rootScope.CGbean = {group:"",description:""};
				$rootScope.editcg = false;
			}
			else{
				$rootScope.CGbean = $scope.cm02selects[0];
				$rootScope.editcg = true;
			}
			}
			
		}
		
		$scope.saveCG = function(){
			$scope.singleselection = false;
			var data = $rootScope.CGbean;
			var config = {
	                headers : {
	                    'Content-Type': 'application/json;'
	                }
	            };
			$http.post("customergroup/cm02/CREATE",data,config).then(function(){
				$rootScope.closeopenCG();
				$rootScope.creategridCG();
				
			});
			
			$rootScope.creategridCG();
		}
		
		$scope.removeCG = function(){
			angular.forEach($scope.cm02selects,function(value,key){
				
				var data = value;
				var config = {
		                headers : {
		                    'Content-Type': 'application/json;'
		                }
		            };
				$http.post("customergroup/cm02/DELETE",data,config).then(function(){
					$rootScope.creategridCG();
				});
				
			});
		}
	
		
		$scope.selectionCriteria = function(){
			
			passdata.setGroupName($scope.cm02selects[0].group);
			$location.path('/selectionCriteria');
			
		}

})




