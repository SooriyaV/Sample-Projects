app3.controller('ordersInRouteCtrl',function($scope,$cookies,$http,modal){
	
	$scope.routeId = $cookies.get('routeFromMain');
	//$scope.hii ="hi";
	$scope.OrderrouteGrid = [];
	
	$http.get('ordersinroute/'+$scope.routeId).then(function (response) {
	
		$scope.OrderrouteGrid = response.data;
		
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
   { name:'Orders', field: 'ordernumber' ,cellTemplate: '<div class="ui-grid-cell-contents" ng-controller="ordersInRouteCtrl"><a href ="" ng-click="showOrder(COL_FIELD)">{{ COL_FIELD }}</a></div>'},
   {name: 'Ordered Date', field:'ordereddate'},
   {name:'Status',field:'status'}
    
  ],
  data: 'OrderrouteGrid'
	};
	
	$scope.showOrder = function(ordernumber){
		//alert(ordernumber);
		$cookies.put('orderfromRoute',ordernumber)		
		var MyModal = new modal();
		MyModal.showOrdersFromRoute();
	}
	
});