/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller('inventorygridcontroller',function($scope,$http,modal,$rootScope){
	
	$scope.singleselection = false;

	$scope.disableModify = true;
	
	$rootScope.callArticles = function(){
	$http.get("article/am01displayall").then(function (response) {
	      $rootScope.skugrid = response.data;
    });
	
	  $scope.customFn = function(rowEntity, units) {
	      return rowEntity.articlename + "hii";
	    }
	
	$scope.skugridoptions = 
	{multiselect:true,
    enableSorting: true,
    columnDefs: [
     { name:'ArticleNumber', field: 'articlenumber'/* ,cellTemplate:'articlesinorder.html'*/},
      { name:'ArticleName', field:'articlename'},
      {name : 'Price',field:'price'}
    ],
    data :'skugrid',
    onRegisterApi: function(gridApi){
        $scope.am01selects =[];
                gridApi.selection.on.rowSelectionChanged($scope,function(rows){
                	$scope.am01selects=gridApi.selection.getSelectedRows();
                	if($scope.am01selects.length > 1 || $scope.am01selects.length == 0 ){
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
	
	$rootScope.callArticles();
	
	$scope.newSKU = function(){
		$rootScope.isNew = "NEW";
		$scope.singleselection = false;
		$scope.storevalue();
		var Mymodal = new modal();
		Mymodal.opennewSKU();	
		
	}
	
	$scope.modifySKU = function(){
		$rootScope.isNew = "MODIFY";
		$scope.singleselection = false;
		$scope.storevalue();
		var Mymodal = new modal();
		Mymodal.opennewSKU();	
		
	}
	
	$scope.storevalue = function(){
		if(angular.isUndefined($rootScope.isNew)){
			//alert('Undefined');
		}
		else{
		if($rootScope.isNew == "NEW"){
			$rootScope.skubean = {articlenumber:"",articlename:"",price:0};
			$rootScope.editsku = false;
		}
		else{
			$rootScope.skubean = $scope.am01selects[0];
			$rootScope.editsku = true;
		}
		}
		
	}
	
	$scope.saveSKU = function(){
		$scope.singleselection = false;
		var data = $rootScope.skubean;
		var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
		$http.post("article/am01/CREATE",data,config).then(function(){
			$rootScope.closeopenSKU();
			$rootScope.callArticles();
			
		});
		
		$rootScope.callArticles();
	}
	
	
	$scope.removeSKU = function(){
		angular.forEach($scope.am01selects,function(value,key){
		var data = value;
		var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
		$http.post("article/am01/DELETE",data,config).then(function(){
			$rootScope.callArticles();
		});
		});
	}
	
	
	
});