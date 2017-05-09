app3.factory('passdata',function($rootScope){
	
		 var passdata = {
				 
			 setOrderNumber : function(value){
				
				$rootScope.Order = value;
				
			},
			
		 	setInventoryNumber : function(value){
		 		
		 		$rootScope.inventoryNumber = value;
		 	},
		 	
		 	setTransitQty : function(value){
		 		
		 		$rootScope.transitQty = value;
		 	},
		 	
		 	setGroupName : function(value){
		 		
		 		$rootScope.CustomerGroup = value;
		 	}
		 	
			
				 
		 }
		
		return passdata;
	}
	)