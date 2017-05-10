/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller('pastorderctrl',function($scope,$cookies,$http,modal,$rootScope){
	
	$scope.pastorders = [];
	
	$http.get('ax01/displayall').then(function (response) {
	
		$scope.pastorders = response.data;
		
	});
	
	
	$scope.OrdersInRouteGridOptions = {
			enableRowSelection : true,
			enableRowHeaderSelection : true,
			multiSelect : false,
			enableSorting : true,
			enableFiltering : true,
			paginationPageSizes: [5, 10, 20],
		    paginationPageSize:10,
		   columnDefs: [
   { name:'Orders', field: 'ordernumber' ,cellTemplate: '<div class="ui-grid-cell-contents" ng-controller="pastorderctrl"><a href ="" ng-click="showOrder(COL_FIELD)">{{ COL_FIELD }}</a></div>'},
   {name: 'Ordered Date', field:'ordereddate'},
   {name:'Status',field:'status'}
    
  ],
  data: 'pastorders'
	};
	
	$scope.showOrder = function(ordernumber){
		//alert(ordernumber);
		$rootScope.pastorderno = 	ordernumber;
		var MyModal = new modal();
		MyModal.showOrdersFrompast();
	}
	
});