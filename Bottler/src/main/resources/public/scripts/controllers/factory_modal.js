app3.factory('modal', ['$compile','$rootScope','$http','$templateRequest','$location', function ($compile, $rootScope,$http,$templateRequest,$location,$scope,passdata) {
				  return function() {
				    var elm;
				    var modal = {
				      openarticle: function() {
				 
				        var html = '<div class="modal" ng-style="modalStyle"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><center><b>Articles</b><Center></div><div class="modal-body"> <div id="articlegrid" ui-grid="articleGrid" ui-grid-selection class="grid"></div></div><div class="modal-footer"><button id="buttonClose" class="btn btn-primary" ng-click="close()">Done</button></div></div></div></div>';
				        elm = angular.element(html);
				        angular.element(document.body).prepend(elm);
				 
				        $rootScope.close = function() {
				        	angular.forEach($rootScope.articleSelections,function(value,key){
			            		var articletobeadded ={"ordernumber":"ORD","articlenumber":0,"quantity":0};
				            	articletobeadded.ordernumber = $rootScope.ordernumber;
			            		articletobeadded.articlenumber =value.articlenumber;
			            		$rootScope.griddata.orderedarticle.push(articletobeadded);
			            		//$rootScope.gridOptions2.data.push(articletobeadded);
			            		
			            	});	
				          modal.close();
				        };
				        
				        
				 
				        $rootScope.modalStyle = {"display": "block"};
				 
				        $compile(elm)($rootScope);
				      },
				      
				      
				      close: function() {
				        if (elm) {
				          elm.remove();
				        }
				      },
				      
				      openorderunits: function() {
							 
					        var html = '<div class="modal" ng-style="modalStyle">  <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <h3>Order Units</h3> <div class="modal-body">  <div><label for="name" >Warehouse : {{inventoryNumber}} </label></div><div><label for="name" class="col-xs-4">Item 	  : {{im02gridselections[0].items}}</label></div> <div style="Clear:both;"></div> <label for="name" class="col-xs-4">Units:</label><div class="col-xs-2"> <input class="form-control" name ="Units" ng-model ="im02gridselections[0].quantityinTransit"  id="ex2" type="text"> </div>  <div style="Clear:both;"></div> <div class="modal-footer"> <button id="buttonClose" class="btn btn-primary" ng-click="close()">Done</button> </div> </div> </div> </div> </div> </div>';
					        elm = angular.element(html);
					        angular.element(document.body).prepend(elm);
					 
					        $rootScope.close = function() {
					        	/*var index = 0;
					        angular.forEach($rootScope.im02grid, function(value,key){
					        	if(value.items == $rootScope.im02gridselections[0].items){
					        	index = key;}
					        	
					        }) */
					        	
					        //$rootScope.im02gridselections[0].quantityinTransit = $rootScope.transitQty;
					        var datas  = $rootScope.im02gridselections[0];
	            		   	
		            	   	 var config = {
		            	                headers : {
		            	                    'Content-Type': 'application/json;'
		            	                }
		            	            };
		            	   	$http.post("inventory/im02/CREATE",datas,config) .success(function (data, status, headers, config) {
		            	   		$http.get("inventory/im02displayall/"+$rootScope.inventoryNumber).then(function (response){
			            			$rootScope.im02grid = response.data;
					        	});	
		            			
		            	} );
		            	    $rootScope.transitQty =0;
		            		
					          modal.close();
					        };
					        
					        
					 
					        $rootScope.modalStyle = {"display": "block"};
					 
					        $compile(elm)($rootScope);
					      },
					      
					openSkuselection: function() {
						$rootScope.intransitqty = 1;
								var valueee = $rootScope.intransitqty;
						        var html = '<div class="modal" ng-style="modalStyle" ng-controller ="stockController">{{modalStyle}}<div class="modal-dialog"><div class="modal-content"><div class="modal-header"></div><div class="modal-body"><div style="Clear:both;"><div id="grid" ng-if ="wizard" ui-grid="articleGridSku" ui-grid-selection ></div> <div ng-if ="!wizard"><div><label for="name" >Warehouse : {{inventoryNumber}} </label></div><div><label for="name" class="col-xs-4">Item 	  : {{skuselections[0].articlenumber}}</label></div> <div style="Clear:both;"></div> <label for="name" class="col-xs-4">Units:</label><div class="col-xs-2"> <input class="form-control" name ="Units" ng-change = "setintransit(intransitqty)" ng-model ="intransitqty" id="ex2" type="text"> </div></div>  <div style="Clear:both;"></div></div></div><div class="modal-footer"><button id="buttonClose" class="btn btn-primary" ng-disabled="wizard" ng-click="back()">Back</button><button id="buttonClose" class="btn btn-primary" ng-disabled="!wizard" ng-click="next()">Next</button><button id="buttonClose" class="btn btn-primary" ng-disabled="wizard" ng-click="close(intransitqty)">Finish</button></div></div></div></div>';
						        elm = angular.element(html);
						        angular.element(document.body).prepend(elm);
						        
						        $rootScope.back = function(){
						    		
						        	$rootScope.wizard = true;
						        	$rootScope.wizard1 = true;
						        }
						        
						        $rootScope.next = function(){
		
						        	$rootScope.wizard = false;
						        	$rootScope.wizard1 = true;
						        }
						 
						        $rootScope.close = function() {
						        	angular.forEach( $rootScope.skuselections,function(value,key){
						   
					            		var skutobeadded ={"inventoryNumber":"","items":"","inStockQty":0,"quantityinTransit":0,"transitStatus":"Initiated"};
						            	//articletobeadded.ordernumber = $rootScope.ordernumber;
						            	skutobeadded.items =value.articlenumber;
						            	skutobeadded.inventoryNumber = $rootScope.inventoryNumber;
						            	skutobeadded.quantityinTransit = $rootScope.transitQty;
					            		$rootScope.im02grid.push(skutobeadded);
					            		 var datas  = skutobeadded;
					            		   	
					            	   	 var config = {
					            	                headers : {
					            	                    'Content-Type': 'application/json;'
					            	                }
					            	            };
					            	   	$http.post("inventory/im02/CREATE",datas,config) .success(function (data, status, headers, config) {
					            	   		$http.get("inventory/im02displayall/"+$rootScope.inventoryNumber).then(function (response){
						            			$rootScope.im02grid = response.data;
								        	});	
					            			
					            	} );
					            				
					            		
					            	});	
						        	
						         modal.close();
						        	}
						        
						 
						        $rootScope.modalStyle = {"display": "block"};
						 
						        $compile(elm)($rootScope);
						      },
						      
						      NewRoute : function(){
						    	  
						    	  $templateRequest("Modal_newRoute.html").then(function(html){
						    		  
						    		  var data = html;
						    		  ////alert(data);
							    	  elm = angular.element(data);
						    	      angular.element(document.body).prepend(elm);
						    	      $rootScope.modalStyle = {"display": "block"};
						    	      $compile(elm)($rootScope);
						    	      
						    	   });
						    	  
						    	  $rootScope.CloseNewRoute = function(){
						    	  		modal.close();
						    	  		$rootScope.callRoute();
						    	  	};
						    	  
						      },
						      
						      checkInDialog : function(){
						    	  
						    	  	$templateRequest("Modal_checkInDialog.html").then(function(html){
						    	
							    	  elm = angular.element(html);
						    	      angular.element(document.body).prepend(elm);
						    	      $rootScope.modalStyle = {"display": "block"};
						    	      $compile(elm)($rootScope);
						    	      
						    	   });
						    	  	
						    	  	$rootScope.closeChkin = function(){
						    	  		modal.close();
						    	  	};
						    	  
						      },
				      
					OrdersInRoute : function(){
						
						$templateRequest("Modal_OrdersInRoute.html").then(function(html){
					    	
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeChkin = function(){
				    	  		modal.close();
				    	  	};
					},
					LoadTable : function(){
						
						$templateRequest("Modal_loadtable.html").then(function(html){
					    	//alert('hi');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeldtable = function(){
				    	  		modal.close();
				    	  	};
					},
					
					OperatorModal : function(){
						
						$templateRequest("Modal_operator.html").then(function(html){
					    	//alert('hi');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeoperatortable = function(){
				    	  		modal.close();
				    	  	};
					},
				      
					 showOrdersFromRoute :    
						 
						 function(){
							
							$templateRequest("Modal_showOrdersFromRoute.html").then(function(html){
						    	////alert('hiii');
						    	  elm = angular.element(html);
					    	      angular.element(document.body).prepend(elm);
					    	      $rootScope.modalStyle = {"display": "block"};
					    	      $compile(elm)($rootScope);
					    	      
					    	   });
					    	  	
					    	  	$rootScope.closeshowOrdersFromRoute = function(){
					    	  		modal.close();
					    	  	};
						},
					
					showNewCustomer :
						
						function(){
						
						$templateRequest("Modal_showNewCustomer.html").then(function(html){
					    	////alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeshowNewCustomer = function(){
				    	  		modal.close();
				    	  	};
						
						
					},
					NewDay :
						
						function(){
						
						$templateRequest("Modal_newday.html").then(function(html){
					    	////alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      //$rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closenewday = function(){
				    	  		modal.close();
				    	  		
				    	  		
				    	  	};
						
						
					},
					
					openroutesforbatch : function(){
						
						$templateRequest("Modal_openroutesforbatch.html").then(function(html){
					    //	//alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeopenroutesforbatch= function(){
				    	  		modal.close();
				    	  		
				    	  		
				    	  	};
						
						
					},
					opennewSKU : function(){
						
						$templateRequest("Modal_opennewSKU.html").then(function(html){
					    //	//alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeopenSKU= function(){
				    	  		modal.close();		    	  		
				    	  		
				    	  	};					
						
					},
					opennewInv : function(){
						
						$templateRequest("Modal_opennewInv.html").then(function(html){
					    //	//alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeopenInv= function(){
				    	  		modal.close();		    	  		
				    	  		
				    	  	};					
						
					},
					opennewCust : function(){
						
						$templateRequest("Modal_opennewCust.html").then(function(html){
					    //	//alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeopenCust= function(){
				    	  		modal.close();		    	  		
				    	  		
				    	  	};					
						
					},
					opennewCG : function(){
						
						$templateRequest("Modal_opennewCG.html").then(function(html){
					    //	//alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeopenCG= function(){
				    	  		modal.close();		    	  		
				    	  		
				    	  	};					
						
					},
					
					showOrdersFrompast : function(){
						
						$templateRequest("Modal_showOrdersFrompast.html").then(function(html){
					    //	//alert('hiii');
					    	  elm = angular.element(html);
				    	      angular.element(document.body).prepend(elm);
				    	      $rootScope.modalStyle = {"display": "block"};
				    	      $compile(elm)($rootScope);
				    	      
				    	   });
				    	  	
				    	  	$rootScope.closeshowOrdersFrompast= function(){
				    	  		modal.close();		    	  		
				    	  		
				    	  	};					
						
					}
					
					
					
				    
				    
				    };
				    
				    return modal;
				  };
				}]);