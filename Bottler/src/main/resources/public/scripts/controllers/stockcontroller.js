app3.controller('stockController',function($scope,$http,$rootScope,modal,passdata){
	$rootScope.im02grid =[];
	$rootScope.wizard =true;
	$rootScope.intransitqty = 0;
	$scope.status1 = "";
	$scope.invstatus =['Initiated','Scheduled','Received'];
	var myModal = new modal();
	$http.get("inventory/im02displayall/"+$rootScope.inventoryNumber).then(function (response) {
		$rootScope.im02grid = response.data;
    });
	
	
	$rootScope.im02gridoptions = 
	{multiselect:true,
    enableSorting: true,
    columnDefs: [
     { name:'Item Number', field: 'items'/* ,cellTemplate:'articlesinorder.html'*/},
      { name:'In Stock', field:'inStockQty'},
      { name:'In Transit', field:'quantityinTransit'},
      { name:'Status', field:'transitStatus'/*,cellTemplate : '<div class="ui-grid-cell-contents" ng-controller="stockController" ng-app="hello"><a href ="" ng-click="changeView(COL_FIELD)">{{ COL_FIELD }}</a></div>'*/}
    	  /*,
      editableCellTemplate: 'ui-grid/dropdownEditor',enableCellEditOnFocus:true, editDropdownValueLabel: 'status',
    	  editDropdownOptionsArray: [{status:'Initiated'},{status:'Scheduled'},{status:'Received'}]*/
      
      ],
    data :'im02grid',
    
    onRegisterApi: function(gridApi){
        $scope.im02Api = gridApi;
        $rootScope.im02gridselections =[];
        
        
        /*gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
        	
        	
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
        	$rootScope.im02gridselections = gridApi.selection.getSelectedRows();
        	//$scope.ordernumber = $rootScope.griddata.ordernumber;	            	
        });

        	 	
       
    }}
	
	$scope.orderUnits = function(){
		myModal.openorderunits();
		
	}
	
	$http.get("article/am01displayall").then(function (response){
		$scope.skulist = response.data;
		//$rootScope.skulistproper=response.data;
		
	} );
	
	$rootScope.newSku = function(){
		var valuestobeignored = [];	
		var articleArray = [];
		$rootScope.skulistproper = [];
		$rootScope.wizard = true;
		
		angular.forEach($rootScope.im02grid,function(value,key){
			this.push(value.items);
		},valuestobeignored);
		
		
		angular.forEach($scope.skulist,function(value,key){					
			
			if(valuestobeignored.indexOf(value.articlenumber)==-1){						
				this.push(value);	
				}			
		},articleArray);
		$rootScope.skulistproper = articleArray;
		myModal.openSkuselection();
	};
	
	
	$rootScope.articleGridSku = {
			enableRowSelection : true,
			enableRowHeaderSelection : true,
			multiSelect : false,
		        enableSorting: true,
		        columnDefs: [
		         { name:'ArticleNumber', field: 'articlenumber'},
		          { name:'ArticleName', field:'articlename'}
		        ],
		        data :'skulistproper',
		        onRegisterApi: function(gridApi){
		            $scope.gridApi = gridApi;
		            $rootScope.skuselections =[];
		            gridApi.selection.on.rowSelectionChanged($rootScope,function(rows){
		            	$rootScope.skuselections = gridApi.selection.getSelectedRows();
		            	//$scope.ordernumber = $rootScope.griddata.ordernumber;	            	
		            });
		    
		            	 	
		           
		        }
		      };	
	
	
	
	$rootScope.setintransit = function(value){
		
		passdata.setTransitQty(value);
	}
	
	
});