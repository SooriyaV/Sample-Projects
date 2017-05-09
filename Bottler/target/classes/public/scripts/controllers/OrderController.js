 app3.controller('gridcontroller',function($rootScope,$scope, $http,modal,$timeout,$location,passdata,$cookies) {
			
	 
$rootScope.gridOptions1 = {};		  
$scope.ordergridSelections =[];
			 
$http.get("fetch").then(function (response){
				$rootScope.gridOptions1.data = response.data;
				$scope.testdata = response.data;
				
} );
			
$rootScope.gridOptions1 = {
		enableRowSelection : true,
		enableRowHeaderSelection : true,
		multiSelect : true,
		enableSorting : true,
		enableFiltering : true,
		paginationPageSizes: [5, 10, 20],
	    paginationPageSize: 10,
			        columnDefs: [
			          { name:'Order', field: 'ordernumber' ,enableCellEdit: false,cellTemplate: '<div class="ui-grid-cell-contents" ng-controller="gridcontroller" ng-app="hello"><a href ="" ng-click="changeView(COL_FIELD)">{{ COL_FIELD }}</a></div>'},
			          { name:'Customer',field:'customernumber'},
			          { name:'Inventory', field:'invnumber'},
			          { name:'OrderedDate', field:'ordereddate', cellFilter: 'date:\'dd-MMM-yyyy\''},
			          {name:'Route',field:'routeName'},
			          { name:'Bill Amount', field: 'totalamount',enableFiltering:false },
			          {name:'Status',field:'status'}
			          
			         
			        ],
			        onRegisterApi: function(gridApi){
			            $scope.gridApi = gridApi;
			            gridApi.selection.on.rowSelectionChanged($scope,function(rows){
			            	
			              $scope.ordergridSelections = gridApi.selection.getSelectedRows();
			            });
			            gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef){
			            	 var config = {
			                         headers : {
			                             'Content-Type': 'application/json;'
			                         }
			                     };
			            	/*$http.post("fetch",$rootScope.gridOptions1.data,config) .success(function (data, status, headers, config) {
			            		//alert("yes");}).
			            		 error(function (data, status, header, config) {
			            			 //alert("No");});*/
			    				
			    			} );
			            	 	
			           
			        }
			      };

			 
$scope.getSelected = function(){
				 
				 
				 //alert($rootScope.gridOptions1.getSelectedCount());
				 
			 };
								

 $scope.changeView = function(orderno){
				 
				 $scope.finall =[];
				 
				 $http.get("fetchbyId/"+orderno)
				    .then(function(response) {
				    	
				    	
				    	console.log(response.data.orderedarticle);
				    	$rootScope.kk = response.data.orderedarticle;
				    	//console.log($scope.kk);
				    	$rootScope.griddata = response.data;
				    	//$rootScope.gridOptions2.data = response.data.orderedarticle;
				    	//console.log($rootScope.gridOptions2.data.orderedarticle);
				    	$scope.mydata = response.data;
				    	//alert(response.data.status);
				    	if(response.data.status == "NEW"){
							$cookies.put('ordereditflag',true);
						}
						else{
							$cookies.put('ordereditflag',false);
						}
				    	$cookies.put('checkorderno',orderno);
						
						$location.path('articleinnorder');
				    });
				 
			////alert($scope.kk.length);
				/*angular.forEach($rootScope.griddata.orderedarticle,function(value,key){
					this.push(value);
				},$scope.finall)*/
				 
				$scope.ordergridToggle = true;
				//passdata.setOrderNumber(orderno);
				
				console.log($scope.ordergridToggle);
				 
			 }
			 console.log($scope.ordergridToggle);
			 
			 var refresh = function() {
				    $scope.refresh = false;
				    //alert("hello");
				    $timeout(function() {
				      $scope.refresh = false;
				    }, 0);
				  };
				  
				  
			
			
			 
				
$scope.deleteOrders = function(){
				
										var url = "order/delete";
										var config = {
								                headers : {
								                    'Content-Type': 'application/json;'
								                }
								            };
										
										angular.forEach($scope.ordergridSelections,function(value,key){
										var data = value;	
										
										 $http.post(url, data, config)
								         .success(function (data, status, headers, config) {
								        	 $rootScope.gridOptions1.data = data;
								        	
								         })
								         .error(function (data, status, header, config) {
								         });
												
										});
										
										
										
									};
									
$scope.modifyData = function(){
	//var myPassData = new passdata();
	if($scope.ordergridSelections.length >1){
		
		//alert("You can only modify ONE data at a time. Please select just One data");
	}
	else if($scope.ordergridSelections.length ==0){
		//alert("No data selected to Modify!!");
		
	}
	else{
	//passdata.setOrderNumber($scope.ordergridSelections[0].ordernumber);
		$cookies.put('checkorderno',$scope.ordergridSelections[0].ordernumber);
		if($scope.ordergridSelections[0].status == "NEW"){
			$cookies.put('ordereditflag',true);
		}
		else{
			$cookies.put('ordereditflag',false);
		}
	$location.path("/articleinnorder");}
};

$scope.newData = function(){
	
	$cookies.put('checkorderno',"NEW");
	$cookies.put('ordereditflag',true);
	$location.path("/articleinnorder");
};
								  
				  

			});
				  

				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				
				 
				
 
 
 
 
 
 
 
 
 
 