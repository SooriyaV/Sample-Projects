app3.controller('showpast',function($scope,$cookies,$rootScope,$http,$location){

	$scope.orderdata = [];
	
//alert($rootScope.pastorderno);
$http.get("ax01/"+$rootScope.pastorderno)
	.then(function(response) {
		
		//console.log(response.data.orderedarticle);
		$scope.orderdata = response.data;
	});

$http.get("article/am01displayall").then(function (response){
	$scope.artlist = response.data;
	
} );

$scope.getRate = function(rowEntity) {
	var articleRate = 0;
	angular.forEach($scope.artlist,function(value,key){
		if(value.articlenumber == rowEntity.articlenumber ){
			articleRate=value.price;
		}
	});
	rowEntity.amount = articleRate*rowEntity.quantity;
    return articleRate;
  }
$scope.getName = function(rowEntity) {
	var articleName = "";
	angular.forEach($scope.artlist,function(value,key){
		if(value.articlenumber == rowEntity.articlenumber ){
			articleName=value.articlename;
		}
	});
	
    return articleName;
  }

$scope.Rowcalculate = function(rowEntity) {	
	var articleRate = 0;
	angular.forEach($scope.artlist,function(value,key){
		if(value.articlenumber == rowEntity.articlenumber ){
			articleRate=value.price;
		}
	});
	
	$scope.orderdata.totalamount = $scope.calculate($scope.orderdata.orderedarticle);
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

$scope.showArticles = {
        enableSorting: true,
        enableCellEdit: false,
        columnDefs: [
         { name:'ArticleNumber', field: 'articlenumber',enableCellEdit: false/* ,cellTemplate:'articlesinorder.html'*/},
         { name:'ArticleName',enableCellEdit: false,cellTemplate: '<div style="margin-left:100px;"> \
             {{grid.appScope.getName(row.entity)}} \
             </div>'},
         { name:'Quantity', field:'quantity'},
         {name:'Rate',field:'rate',enableCellEdit: false, cellTemplate: '<div> \
             {{grid.appScope.getRate(row.entity)}} \
             </div>'},
         {name :'Amount',field:'amount',enableCellEdit: false,cellTemplate: '<div> \
             {{grid.appScope.Rowcalculate(row.entity)}} \
             </div>'}
        ],
      data :'orderdata.orderedarticle'
}

	
	
	
	
	
	
	
	
	
})