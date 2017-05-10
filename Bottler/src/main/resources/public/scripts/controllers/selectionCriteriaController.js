/*
 Author Sooriya
 Email sooriya.v@outlook.com*/
app3.controller('selectionCriteriaController',function($scope,$rootScope,$http,modal){
	$scope.customersample =[];
	$scope.identifier ='';
	$scope.cm03array = [];
	$scope.iterator = 0;
	$scope.operator =["AND","OR"];
	$scope.operatorInput ="";
	this.testadd ="OR";
	var myModal = new modal();
	$scope.isAdd = false;

	$scope.initialize = function(){
		
		var cm03obj = {"key":"","values":"","operator":"","appender":""}	;
		cm03obj.appender = "START";
		$scope.cm03array.push(cm03obj);
		$http.get('customergroup/cm03displayall/GROUP1/DISPLAY').success(function (data){
			if(data!=""){
			$scope.cm03array = data;
			$scope.loadgroup();
			}
		});
		
		
	}

	$scope.initialize();
$http.get('customer/cm01displayall').success(function (data){
	var count =0;
		angular.forEach(data,function(value,key){			
			if(count == 0){
			angular.forEach(value,function(value1,key1){
				count =1;
				this.push(key1);
				
			},$scope.customersample);
			}	
		});
	});

$scope.customerGroup =[];

$scope.checkdirty = function(){
	
	
		$scope.loadgroup();
	
}

$scope.addWithOperator = function(){
	
	$scope.isAdd = true;
	
}
$scope.add = function(){
	$scope.iterator++;
	var cm03obj = {"key":"","values":"","operator":"","appender":""}	;
	////alert(this.testadd);
	cm03obj.appender = $scope.operatorInput ;
	$scope.isAdd = false;
	$scope.cm03array.push(cm03obj);
	/*if(!($scope.operatorInput == "FINISH")){
	$scope.cm03array.push(cm03obj);
	}
	else{
		$scope.loadgroup();	
	}*/
	$scope.operatorInput = "";
}

$scope.remove = function(){
	
	$scope.cm03array.splice($scope.cm03array.length-1,1);
	$scope.loadgroup();	
	
}

$scope.loadgroup = function(){

	var datas = $scope.cm03array;
	var config = {
            headers : {
                'Content-Type': 'application/json;'
            }
        };
	$http.post("customer/criteria",datas,config) .success(function (data, status, headers, config) {
		$scope.customerGroup = data;
		});
	
}


$scope.saveData = function(){
	var datas = $scope.cm03array;
	var config = {
            headers : {
                'Content-Type': 'application/json;'
            }
        };
	
	$http.post("customergroup/cm03/GROUP1/SAVE",datas,config) .success(function (data, status, headers, config) {
		//$scope.customerGroup = data;
		});
	
}
$scope.customergridoptions = 
{
		enableRowSelection : true,
		enableRowHeaderSelection : true,
		multiSelect : true,
		enableSorting : true,
		enableFiltering : true,
		paginationPageSizes: [1, 50, 75],
	    paginationPageSize: 25,
	   columnDefs: [
{ name:'Reference No.', field: 'custloc'/* ,cellTemplate:'articlesinorder.html'*/},
{ name:'Customer Name', field:'custname'},
],
data :'customerGroup'

};

	
	
});

