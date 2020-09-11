	var localStorageQueue = new LocalStorageQueue();
	var guiDao = new GuiDao();

	function addButton() {

		var cashSaleModel = createCashSale();

		console.log("[html.addButton]push:" + cashSaleModel);
		/*
		 * var posIndex = localStorageQueue.pushLocalStorage(cashSaleModel,
		 * listLocalStorageQueue);
		 */
		var posIndex = guiDao.submitCashSale(cashSaleModel);

		console.log("[html.addButton]has been pushed:" + posIndex);
		this.listLocalStorageQueue();

	}

	function clearButton() {

		localStorageQueue.clearLocalStorage();
		this.listLocalStorageQueue();

	}

	function listButton() {

		this.listLocalStorageQueue();

	}

	function listLocalStorageQueue() {

		var actionList = localStorageQueue.getAll();

		console.log("<br><br>[html.listLocalStorageQueue]action list size "
				+ actionList.length);
		$(messageDiv).html(
				"[html.listLocalStorageQueue]action list size "
						+ actionList.length + "<br><br>");

		for (var i = 0; i < actionList.length; i++) {
			var action = actionList[i];
			console.log("local storage " + i + " : " + action);
			$(messageDiv).html(
					$(messageDiv).html() + "local storage " + i + " : "
							+ action + "<br>");
		}
	}

	function testSale() {
		$("#adminStatus").html("");

		commandCashSale.switchSession("session1");
		commandCashSale.newCashSale();

		var product1 = getRandomProduct();
		var product2 = getRandomProduct();
		var product3 = getRandomProduct();
		var product4 = getRandomProduct();
		var product5 = getRandomProduct();

		guiDao.products.push(product1);
		guiDao.products.push(product2);
		guiDao.products.push(product3);
		guiDao.products.push(product4);
		guiDao.products.push(product5);

		commandCashSale.addProduct(product1.code);
		commandCashSale.addProduct(product3.code);
		commandCashSale.addProduct(product4.code);
		commandCashSale.addProduct(product2.code);
		commandCashSale.addProduct(product2.code);
		commandCashSale.addProduct(product1.code);
		commandCashSale.addProduct(product5.code);

		commandCashSale.endCashSale();

	}

	function sleep(milliseconds) {
		var start = new Date().getTime();
		for (var i = 0; i < 1e7; i++) {
			if ((new Date().getTime() - start) > milliseconds) {
				break;
			}
		}
	}


	function readXml() {

		function processData(data) {
			document.write("<tr><td>");
			document.write(data);
			document.write("</td><td>");
		}

		function handler() {

			if (this.status == 200 && this.responseXML != null) {

				console.log(this.responseXML);

				$xml = $(this.responseXML)

				var theDocumentList =  $xml.find('document');

				var documentList = {
					date : $(theDocumentList).attr('date'),
					documents : new Array()
				};

				for (let aDocument of theDocumentList) {

					var document = {
							source : $(aDocument).attr('source'),
							personnel : $(aDocument).find('personnel').text(),
							date : $(aDocument).find('date').text(),
							company : $(aDocument).find('company').text(),
							computername : $(aDocument).find('computername').text(),
							takeonplace : $(aDocument).find('takeonplace').text(),
							cancelled : $(aDocument).attr('cancelled'),
							itemList : new Array(),
							paymentList : new Array()
					};

					for (let aList of $(aDocument).find('item')) {
	
						var item = {
								quantity : $(aList).find('quantity').text()/100,
								product : $(aList).find('product').text(),
								description : $(aList).find('description').text(),
								unitPrice : $(aList).find('unitPrice').text()/100,
								tvaTakeAway : $(aList).find('tvaTakeAway').text()/100,
								tvaTakeOnPlace : $(aList).find('tvaTakeOnPlace').text()/100
						};
						
						document.itemList.push(item);
	
					};
	
					for (let aList of $(aDocument).find('payement')) {
	
						var payment = {
								quantity : $(aList).find('quantity').text()/100,
								description : $(aList).find('description').text(),
								mode : $(aList).find('mode').text(),
								value : $(aList).find('value').text()/100
						};
	
						document.paymentList.push(payment);
	
					};

					documentList.documents.push(document);

				};


				console.log(documentList);

			} else {
				console.error(this.status);
			}

		}

		var request = new XMLHttpRequest();
		request.onload = handler;
		request.open("POST", "*.xml");
		request.send();
	}
	
	
	
	function read() {
		
		if (window.XMLHttpRequest) var request = new XMLHttpRequest();
		else var request = new ActiveXObject('Microsoft.XMLHTTP');

		request.open('POST', '20170712DAGORUE.xml', true);
		request.send();

		request.onreadystatechange = function() {
		    if (request.readyState === 4) {
		        alert(request.responseXML.getElementsByTagName('a')[1].childNodes[0].nodeValue);
		    }
		}
	}
