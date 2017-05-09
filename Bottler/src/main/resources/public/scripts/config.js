app3.config(function($routeProvider, $httpProvider ,$locationProvider) {
 
	 $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	   	  
	$routeProvider.when('/orders', {
		templateUrl : 'ordergrid.html',
		controller : 'gridcontroller'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	}).when('/overview11', {
		templateUrl : 'overview.html',
		controller : 'navigation'
	}).when('/articleinnorder', {
		templateUrl : 'articleinorder.html',
		controller : 'gridcontroller'
	}).when('/inventory', {
			templateUrl : 'inventoryhome.html',
			controller : 'navigation'
	}).when('/customer', {
				templateUrl : '_customertab1.html',
				controller : 'customermodulecontroller'
	}).when('/customerEntry', {
		templateUrl : '_customertab1_Entry.html',
		controller : 'customermodulecontroller'
	}).when('/customergroup', {
		templateUrl : '_customertab2.html',
		controller : 'customerGroupController'
	}).when('/selectionCriteria', {
		templateUrl : '_selectionCriteria.html',
		controller : 'selectionCriteriaController'
	}).when('/inventoryhome', {
		templateUrl : '_inventorytab2.html',
		controller : 'inventoryandstockctrl'
	}).when('/stockininventory', {
		templateUrl : '_inventorytab2_entry.html',
		controller : 'stockController'
	}).when('/route', {
		templateUrl : '_routeMain.html',
		controller : 'routeController'
	}).when('/dashboard', {
		templateUrl : '_dashBoard.html',
		controller : 'GenericChartCtrl'
	}).when('/ordersinroute', {
		templateUrl : '_ordersinroute.html',
		controller : 'ordersInRouteCtrl'
	}).when('/settlement', {
		templateUrl : '_settlementEntry.html',
		controller : 'settlementCtrl'
	}).when('/routesinbatch', {
		templateUrl : '_routesinbatch.html',
		controller : 'routesinbatchCtrl'
	}).when('/pastorders', {
		templateUrl : 'pastorders.html',
		controller : 'pastorderctrl'
	}).
	when('/const', {
		templateUrl : 'UnderConstruction.html',
		controller : 'pastorderctrl'
	}).when('/ToW', {
		templateUrl : 'index.html',
		controller : 'navigation'
	}).when('/liveTrack', {
		templateUrl : '_routesLiveTracking.html',
		controller : 'livetrackctrl'
	}).
	otherwise('/');
	
	$httpProvider.interceptors.push('APIInterceptor');


   

  })