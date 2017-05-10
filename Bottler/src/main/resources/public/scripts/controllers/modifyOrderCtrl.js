/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller('orderEditCtrl',function($scope,$cookies,$rootScope,$location,$http,modal,passdata){ 

//$rootScope.gridOptions2 = {};
	if($cookies.get('ordereditflag')== "true"){
		//alert('hi11'+$cookies.get('ordereditflag'));
$scope.checknow = true;
	}
	else{
		$scope.checknow = false;	
	}
//$scope.checknow=$cookies.get('ordereditflag');
var myModal = new modal();
$rootScope.griddata ={};
$scope.setLessQtyFlag = false;
//$rootScope.griddata.invnumber = 40;

$scope.im02values = [];
/*if(!angular.isUndefined($rootScope.griddata.invnumber)){
$http.get("inventory/im02displayall/"+$rootScope.griddata.invnumber).then(function (response){
		
		$scope.im02values = response.data;
	});
}*/	

$scope.getim02 = function(){
	
	/*$http.get("inventory/im02displayall/"+$rootScope.griddata.invnumber).then(function (response){
		
		$scope.im02values = response.data;
	});*/
	$http.get("inventory/im02displayall/"+$rootScope.griddata.invnumber)
	.then(function(response) {
		$scope.im02values	 = response.data;
	});
	
}


if($cookies.get('checkorderno') == "NEW"){
	////alert($cookies.get('checkorderno'));
	$scope.disabling = false;
	$rootScope.griddata.ordernumber = "NEW";
	$rootScope.griddata.orderedarticle =[];
	$rootScope.griddata.status = "NEW";
	$rootScope.griddata.invnumber = "";
	$scope.checknow = true;
}
else{
	//alert($cookies.get('checkorderno'));
$http.get("fetchbyId/"+$cookies.get('checkorderno'))
.then(function(response) {
	//console.log(response.data.orderedarticle);
	$rootScope.griddata = response.data;
	$rootScope.griddata.totalamount = 0;
	$http.get("inventory/im02displayall/"+$rootScope.griddata.invnumber).then(function (response){
		
		$scope.im02values = response.data;
	});
//	//alert($rootScope.griddata.status);
	
	//$rootScope.gridOptions2.data = $rootScope.griddata.orderedarticle;
});

/*if($rootScope.griddata.status!="NEW"){
$rootScope.gridOptions2.columnDefs.
}*/
}

$scope.routes = [];
var rtedefault = {'routeName' : "",'status':'','driver':"",'vehicle':""};
var configforroute = {
        headers : {
            'Content-Type': 'application/json;'
        }
    };
$http.post("route/rt01/AVAILABLE",rtedefault,configforroute)
.then(function(response) {
	angular.forEach(response.data,function(value,key){
		this.push(value.routeName);
	},$scope.routes);
});

$scope.inventory = [];
$http.get("inventory/im01displayall")
.then(function(response) {
	angular.forEach(response.data,function(value,key){
		this.push(value.inventoryNumber);
	},$scope.inventory);
});

$scope.customerss = [];
$http.get("customer/cm01displayall")
.then(function(response) {
	angular.forEach(response.data,function(value,key){
		this.push(value.custloc);
	},$scope.customerss);
});


$scope.disabling = true;

$scope.getRate = function(rowEntity) {
	var articleRate = 0;
	angular.forEach($scope.articlelist,function(value,key){
		if(value.articlenumber == rowEntity.articlenumber ){
			articleRate=value.price;
		}
	});
	rowEntity.amount = articleRate*rowEntity.quantity;
    return articleRate;
  }
$scope.getName = function(rowEntity) {
	var articleName = "";
	angular.forEach($scope.articlelist,function(value,key){
		if(value.articlenumber == rowEntity.articlenumber ){
			articleName=value.articlename;
		}
	});
	
    return articleName;
  }

$scope.Rowcalculate = function(rowEntity) {	
	var articleRate = 0;
	angular.forEach($scope.articlelist,function(value,key){
		if(value.articlenumber == rowEntity.articlenumber ){
			articleRate=value.price;
		}
	});
	
	$rootScope.griddata.totalamount = $scope.calculate($scope.griddata.orderedarticle);
    return rowEntity.quantity*articleRate;
  }
$scope.calculate = function(amountarray) {	
	var articleRate = 0;
	angular.forEach(amountarray,function(value,key){
		////alert(value.amount);
		articleRate += value.amount;
	});
	
    return articleRate;
  }
$scope.getAvailableUnits = function(rowEntity){
	var availableUnits = 0;
	angular.forEach($scope.im02values,function(value,key){
		////alert(value.items);
		if(value.items == rowEntity.articlenumber ){
			////alert(value.InStockQty);
			availableUnits = value.inStockQty;
			if((value.inStockQty - rowEntity.quantity)<0 ){
				$scope.setLessQtyFlag = true;
			}
		}
		
	});	
	return availableUnits;
}	
//alert($cookies.get('ordereditflag'));
$rootScope.checknow = function(){
	$http.get("fetchbyId/"+$cookies.get('checkorderno'))
	.then(function(response) {
		//alert(response.data.status);
		if(response.data.status == "NEW"){
		return false;
	}
	else{
	return false;}
	});
}




$rootScope.gridOptions2 = {
				        enableSorting: true,
				        columnDefs: [
				         { name:'ArticleNumber', field: 'articlenumber',enableCellEdit: false/* ,cellTemplate:'articlesinorder.html'*/},
				         { name:'ArticleName',enableCellEdit: false,cellTemplate: '<div style="margin-left:100px;"> \
                             {{grid.appScope.getName(row.entity)}} \
                             </div>'}, 
                         {name: 'Available Units',enableCellEdit:false,cellTemplate: '<div>\
                        	 {{grid.appScope.getAvailableUnits(row.entity)}}\
                        	 </div>'},
				         { name:'Quantity', field:'quantity',enableCellEdit:$scope.checknow},
				         {name:'Rate',field:'rate',enableCellEdit: false, cellTemplate: '<div> \
                             {{grid.appScope.getRate(row.entity)}} \
                             </div>'},
				         {name :'Amount',field:'amount',enableCellEdit: false,cellTemplate: '<div> \
                             {{grid.appScope.Rowcalculate(row.entity)}} \
                             </div>'}
				        ],
				      data :'griddata.orderedarticle',
				        onRegisterApi: function(gridApi){
				            $scope.gridApiGrid2 = gridApi;
				            gridApi.selection.on.rowSelectionChanged($scope,function(rows){
				            	
				              $scope.mainGridArticlesSelections = gridApi.selection.getSelectedRows();
				            });
				            gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef){
				            	////alert(rowEntity.amount);
				            	//$rootScope.griddata.totalamount = $scope.calculate($scope.griddata.orderedarticle);
				            	
				            	 var config = {
				                         headers : {
				                             'Content-Type': 'application/json;'
				                         }
				                     };
				            	/*$http.post("fetch",datas,config) .success(function (data, status, headers, config) {
				            		//alert("yes");}).
				            		 error(function (data, status, header, config) {
				            			 //alert("No");});*/
				    				
				    			} );
				            	 	
				           
				        }
				      };
	  

$http.get("article/am01displayall").then(function (response){
	$rootScope.articlelist = response.data;
	$rootScope.articlelistproper=response.data;
	$scope.article = $scope.articlelist[0];
	
} );



$rootScope.openArticleSelection = function() {
	
	var valuestobeignored = [];	
	var articleArray = [];
	$rootScope.articlelistproper = [];
	
	angular.forEach($rootScope.griddata.orderedarticle,function(value,key){

		this.push(value.articlenumber);
	},valuestobeignored);
	
	
	
	angular.forEach($rootScope.articlelist,function(value,key){					
		
		if(valuestobeignored.indexOf(value.articlenumber)==-1){						
			this.push(value);	
			}			
	},articleArray);
	$rootScope.articlelistproper = articleArray;
	 myModal.openarticle();
	  };

	  
$rootScope.articleGrid = {
				 multiselect:true,
			        enableSorting: true,
			        columnDefs: [
			         { name:'ArticleNumber', field: 'articlenumber'/* ,cellTemplate:'articlesinorder.html'*/},
			          { name:'ArticleName', field:'articlename'}
			        ],
			        data :'articlelistproper',
			        onRegisterApi: function(gridApi){
			            $rootScope.gridApi = gridApi;
			            $rootScope.articleSelections =[];
			            gridApi.selection.on.rowSelectionChanged($rootScope,function(rows){
			            	$rootScope.articleSelections = gridApi.selection.getSelectedRows();
			            	$rootScope.ordernumber = $rootScope.griddata.ordernumber;
			            	
			            	/*angular.forEach($rootScope.mySelections,function(value,key){
			            		var articletobeadded ={"ordernumber":"ORD","articlenumber":0,"quantity":0};
				            	articletobeadded.ordernumber = ordernumber;
			            		articletobeadded.articlenumber =value.articlenumber;
			            		$rootScope.griddata.orderedarticle.push(articletobeadded);
			            	});*/
			            	
			            	
			            });
			    
			            	 	
			           
			        }
			      };	


$scope.saveOrder = function(){
	//alert($scope.setLessQtyFlag);
	 var datas  = $rootScope.griddata;
   	
   	 var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
   	$http.post("fetch",datas,config) .success(function (data, status, headers, config) {
   		$http.get("fetch").then(function (response){
		$rootScope.gridOptions1.data = response.data;
		
} );
   		}).
   		 error(function (data, status, header, config) {
   			 //alert("No");
   			 });
   	$location.path("/orders");
	 
	 
};

$rootScope.deleteArticles = function(){
		
	 angular.forEach($scope.gridApiGrid2.selection.getSelectedRows(), function (data, index) {
		 $rootScope.griddata.orderedarticle.splice($rootScope.griddata.orderedarticle.lastIndexOf(data), 1);
	});
};








});
			