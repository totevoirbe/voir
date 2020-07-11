var ticket;
var displayTotal;
var itemEditBodyQtyQty;
var itemEditBodyQtyQtyUp;
var itemEditBodyQtyQtyDown;
var itemEditBodyQtyValue;
var itemEditBodyQtyValueUp;
var itemEditBodyQtyValueDown;
var itemEditBodyDeleteModal;
var itemEditBodyUndeleteModal;
var itemEditModal;
var itemEditBodyValidateModal;
var initKbdBtn;
var initSessionBtn;
var kbdRemainStatusButton;
var kbdTakeAwayButton;
var kbdMainDiv;
var kbdSelButtons;
var kbdSessionButtons;
var guiView;
var nomClavierDiv;
var ticket_body;

function GuiView() {

	var currentSpanStatus; // session selection button
	var resumeCashSaleWindow;
	var resumeCashSaleDiv;
	var multipleSelection;

	this.init = function() {

		itemEditModal = $("#item-edit");
		itemEditBodyQtyQty = $("#item-edit-body-qty input");
		itemEditBodyQtyQtyUp = $("#item-edit-body-qty button:first-child");
		itemEditBodyQtyQtyDown = $("#item-edit-body-qty button:nth-child(2)");
		itemEditBodyQtyValue = $("#item-edit-body-value input");
		itemEditBodyQtyValueUp = $("#item-edit-body-value button:first-child");
		itemEditBodyQtyValueDown = $("#item-edit-body-value button:nth-child(2)");
		itemEditBodyValidateModal = $("#item-edit-body-validate-modal");
		itemEditBodyDeleteModal = $("#item-edit-body-delete-modal");
		itemEditBodyUndeleteModal = $("#item-edit-body-undelete-modal");

		initKbdBtn = $("#initKbdBtn");
		initSessionBtn = $("#initSessionBtn");
		kbdRemainStatusButton = $(".kbd-remain-status");
		kbdTakeAwayButton = $("#take-away-button");
		kbdMainDiv = $("#kbd-main-div");
		kbdSessionButtons = $(".kbd-session");
		nomClavierDiv = $("#nom-clavier-div")
		kbdSelButtons = $(".kbd-sel");

		ticketBody = $("#ticket_body")

		try {
			guiView.setButtons($('.kbd-lg'), "SDWNORMAL");
		} catch (err) {
			console.log("" + err);
		}

		$('[data-toggle="tooltip"]').tooltip();

		this.clear();
		this.selectSession();

	}

	this.clear = function() {
		displayTotal = new DisplayTotal();
		ticket.initModal();
		ticket.displayLongTicket(false);
	}

	this.showCashSaleResumeWindow = function(detail) {

		if (!this.resumeCashSaleWindow || this.resumeCashSaleWindow.closed) {

			this.resumeCashSaleWindow = window.open('guiResume.html', '',
					'width=1000,height=800,resizable=1');

			setTimeout(function() {
				guiView.resumeCashSaleDiv = $(
						guiView.resumeCashSaleWindow.document).find(
						'#sale-resume');
				guiView.resumeCashSaleDiv.html(detail);
			}, 300);

		}

		guiView.resumeCashSaleDiv.html(detail);
		this.resumeCashSaleWindow.focus();

	}

	this.showAdminWindow = function() {

		this.adminWindow = window.open('guiAdmin.html', '',
				'width=800,height=400,resizable=1');
		this.adminWindow.focus();

	}

	this.selectSession = function(selectSessionButton) {

		if (!selectSessionButton) {
			selectSessionButton = initSessionBtn[0];
		}

		kbdSessionButtons.css("background", "grey");
		kbdSessionButtons.css("color", "black");

		selectSessionButton.style.background = 'orange';
		selectSessionButton.style.color = 'white';

		var span = selectSessionButton.getElementsByTagName("span")[0];
		this.currentSpanStatus = span;

		commandCashSale.selectSession(selectSessionButton.value);

	}

	this.setMainKbd = function(name, selectKbdButton) {

		if (!name) {
			if (!currentSession.guiSession.currentKbdName) {
				currentSession.guiSession.currentKbdName = initKbdBtn[0].value;
				currentSession.guiSession.currentKbdButton = initKbdBtn[0];
			}
		} else {
			currentSession.guiSession.currentKbdName = name;
			currentSession.guiSession.currentKbdButton = selectKbdButton;
		}

		kbdSelButtons.css("background", "blue");
		kbdSelButtons.css("color", "white");

		currentSession.guiSession.currentKbdButton.style.background = 'green';
		currentSession.guiSession.currentKbdButton.style.color = 'white';

		kbdMainDiv.html("");

		var kbd = guiDao.getKbd(currentSession.guiSession.currentKbdName);

		nomClavierDiv.html(kbd.label);

		kbd.kbdRows.forEach(function(kbdRow) {

			var mainKbdRowDiv = document.createElement('div');
			mainKbdRowDiv.setAttribute('class', 'row');
			kbdMainDiv[0].appendChild(mainKbdRowDiv);

			kbdRow.kbdFcts.forEach(function(kbdFct) {
				var mainKbdButton = document.createElement('button');
				mainKbdButton.setAttribute('class',
						'btn btn-lg btn-primary kbd');
				mainKbdButton.setAttribute('type', 'button');
				mainKbdButton.setAttribute('id', kbdFct.keyId);
				mainKbdRowDiv.appendChild(mainKbdButton);
				guiView.setButton(mainKbdButton, name);

			})
		});

	}

	this.setButton = function(kbdButton, kbdName) {

		if (!kbdButton.id) {
			kbdButton.style.background = 'white';
			kbdButton.style.color = 'white';
			return;
		}

		var product = guiDao.getProductByCode(kbdButton.id);

		if (product) {

			if (product.htmlKeyLabel) {

				var operationSplit = product.htmlKeyLabel.split('<br>');
				if (operationSplit[0]) {
					product.htmlKeyLabel = operationSplit[0].substr(0, 7)
							+ '<br>';
				}
				if (operationSplit[1]) {
					product.htmlKeyLabel += operationSplit[1].substr(0, 7)
							+ '<br>';
				} else {
					product.htmlKeyLabel += '<br>';
				}
				kbdButton.innerHTML = product.htmlKeyLabel;
			}

			var kbdPrice = product.getPrice(kbdName);

			var span = document.createElement('span');
			span.appendChild(document.createTextNode(kbdPrice));
			span.setAttribute('class', 'badge');
			kbdButton.appendChild(span);

			kbdButton.addEventListener("click", function(event) {
				commandCashSale.addProduct(event.target.id);
			});

			var productType = guiDao.getProductTypeByName(product.type)
			if (productType) {
				kbdButton.style.background = productType.backgroundColor;
				kbdButton.style.color = productType.textColor;
			}
		}
	}

	this.setButtons = function(kbdButtons, kbdName) {

		for (var i = 0; i < kbdButtons.length; i++) {
			var kbdButton = kbdButtons[i];
			if (kbdButton.id) {
				guiView.setButton(kbdButton, kbdName);
			}
		}

	}

	this.setTakeAwayButton = function(consumptionPlace) {
		if (consumptionPlace == "TAKE_AWAY") {
			kbdTakeAwayButton.html("<sp><br>A Emporter<br><sp><br>");
			kbdTakeAwayButton.css({
				"background-color" : 'CadetBlue'
			});
			$('#calculator-pane').css({
				"background-color" : "CadetBlue"
			});
		} else {
			kbdTakeAwayButton.html("<sp><br>Sur place<br><sp><br>");
			kbdTakeAwayButton.css({
				"background-color" : 'DarkSeaGreen'
			});
			$('#calculator-pane').css({
				"background-color" : "white"
			});
		}
	}

	this.displayResume = function(cashSale) {

		var detail = '';
		if (cashSale.cashSaleStatus == "OPEN") {
			detail += 'Vente en cours : ';
		} else if (cashSale.cashSaleStatus == "DONE") {
			detail += 'Détail vente terminée : ';
		} else if (cashSale.cashSaleStatus == "CANCEL") {
			detail += 'Détail vente annulée : ';
		} else {
			detail += 'Aucune vente a afficher';
		}

		if (cashSale.cashSaleStatus == "OPEN"
				|| cashSale.cashSaleStatus == "DONE"
				|| cashSale.cashSaleStatus == "CANCEL") {
			detail += cashSale.cashSaleNbArticles;
			detail += ' produit(s) pour un total de '
			detail += cashSale.cashSaleTotal;
			detail += '<br>Payé : '
			detail += cashSale.cashSalePaymentTotal;
			if (cashSale.cashSaleRemainToPay
					&& cashSale.cashSaleRemainToPay == 0) {
				detail += '<br>Soldé.'
			} else if (cashSale.cashSaleRemainToPay
					&& cashSale.cashSaleRemainToPay > 0) {
				detail += '<br>Reste à payer : '
				detail += cashSale.cashSaleRemainToPay;
			} else if (cashSale.cashSaleRemainToPay
					&& cashSale.cashSaleRemainToPay < 0) {
				detail += '<br>A rendre : '
				detail += (-cashSale.cashSaleRemainToPay);
			}
			detail += "<br>Début : "
			detail += getDateAndTime(cashSale.openDate);
			if (cashSale.endDate) {
				detail += "<br>Fin : "
				detail += getDateAndTime(cashSale.endDate)
			}
		}

		var typeValues = new Array();

		var sandwichTypeValue = new TypeValue();
		var paniniTypeValue = new TypeValue();
		var saladeTypeValue = new TypeValue();
		var autreTypeValue = new TypeValue();

		cashSale.itemProducts
				.forEach(function(itemProduct) {

					var typeValue = checkTypeValue(typeValues,
							itemProduct.product.type);
					var unitPrice = itemProduct.unitPriceOnplace;
					if (cashSale.consumptionPlace !== "ON_PLACE") {
						unitPrice = itemProduct.unitPriceTakeaway;
					}

					if (typeValue) {

						typeValue.quantity += itemProduct.quantity;
						typeValue.total += unitPrice * itemProduct.quantity;

					} else {

						var newTypeValue = new TypeValue();

						newTypeValue.quantity = itemProduct.quantity;
						newTypeValue.type = itemProduct.product.type;
						newTypeValue.total = unitPrice * itemProduct.quantity;

						typeValues.push(newTypeValue);

					}

					if (itemProduct.product.type.toLowerCase().includes(
							"sandwich")) {
						sandwichTypeValue.quantity += itemProduct.quantity;
						sandwichTypeValue.total += unitPrice
								* itemProduct.quantity;
					} else if (itemProduct.product.type.toLowerCase().includes(
							"panini")) {
						paniniTypeValue.quantity += itemProduct.quantity;
						paniniTypeValue.total += unitPrice
								* itemProduct.quantity;
					} else if (itemProduct.product.type.toLowerCase().includes(
							"salad")) {
						saladeTypeValue.quantity += itemProduct.quantity;
						saladeTypeValue.total += unitPrice
								* itemProduct.quantity;
					} else {
						autreTypeValue.quantity += itemProduct.quantity;
						autreTypeValue.total += unitPrice
								* itemProduct.quantity;
					}

				});

		detail += "<br><br>";
		detail += sandwichTypeValue.quantity;
		detail += ' sandwiches pour un total de ';
		detail += sandwichTypeValue.total;

		detail += "<br>";
		detail += paniniTypeValue.quantity;
		detail += ' panini pour un total de ';
		detail += paniniTypeValue.total;

		detail += "<br>";
		detail += saladeTypeValue.quantity;
		detail += ' salade pour un total de ';
		detail += saladeTypeValue.total;

		detail += "<br>";
		detail += autreTypeValue.quantity;
		detail += ' divers pour un total de ';
		detail += autreTypeValue.total;

		detail += "<br><br>";

		typeValues.forEach(function(typeValue) {
			detail += "<br>"
			detail += typeValue.quantity;
			detail += ' ';
			detail += typeValue.type;
			detail += ' pour un total de ';
			detail += typeValue.total;
		});

		function TypeValue() {
			this.quantity = 0;
			this.type = "";
			this.total = 0;
		}

		function checkTypeValue(typeValues, type) {
			for (var index = 0; index < typeValues.length; index++) {
				var typeValue = typeValues[index];
				if (typeValue.type == type) {
					return typeValue;
				}
			}
		}

		guiView.showCashSaleResumeWindow(detail);

	}

	this.switchMultipleSelection = function(selectSessionButton) {

		if (this.multipleSelection) {
			this.multipleSelection = undefined;
			selectSessionButton.style.background = 'LightGrey';
			selectSessionButton.style.color = 'white';
		} else {
			this.multipleSelection = true;
			selectSessionButton.style.background = 'CadetBlue';
			selectSessionButton.style.color = 'white';
		}
		currentSession.cashSale.itemProducts.forEach(function(itemProduct) {
			itemProduct.itemSelected = undefined;
		});
	}
}

function Ticket() {

	this.longTicket = false;
	this.ticketTableDiv = $('#ticket_table')[0];
	this.ticketBodyDiv = $("#ticket_body")[0];

	this.initModal = function() {

		this.modalValueEngineQty = new modalValueEngine(itemEditBodyQtyQty);
		this.modalValueEngineValue = new modalValueEngine(itemEditBodyQtyValue);

	}

	this.displayLongTicket = function(forceLong) {

		if (this.longTicket === true || forceLong === false) {
			this.longTicket = false;
			this.ticketBodyDiv.style.height = "335px";
		} else {
			this.longTicket = true;
			this.ticketBodyDiv.style.height = "880px";
		}

	}

	this.scrollDownTicket = function() {

		var scrollTop = this.ticketBodyDiv.scrollTop;
		this.ticketBodyDiv.scrollTop = scrollTop += 35;

	}

	this.scrollUpTicket = function() {

		var scrollTop = this.ticketBodyDiv.scrollTop;
		scrollTop -= 20;
		this.ticketBodyDiv.scrollTop = scrollTop;

	}

	this.display = function() {

		var firstRowIndex = 1;

		this.displayLongTicket(false);
		this.ticketBodyDiv.scrollTop = 0;

		while (this.ticketTableDiv.rows.length - 1) {
			this.ticketTableDiv.deleteRow(1);
		}

		if (currentSession.cashSale.itemProducts
				&& currentSession.cashSale.itemProducts.length > 0) {
			currentSession.cashSale.itemProducts.forEach(function(itemProduct) {

				var row = ticket.ticketTableDiv.insertRow(firstRowIndex);

				var qtyCell = row.insertCell(0);
				var linkQtyCell = document.createElement('a');
				qtyCell.appendChild(linkQtyCell);
				linkQtyCell.innerHTML = itemProduct.quantity;
				linkQtyCell.addEventListener("click", function(event) {
					currentSession.cashSale.lastItem = itemProduct;
					ticket.display();
					showModalProduct(itemProduct);
				});

				var labelCell = row.insertCell(1);
				var linkLabelCell = document.createElement('a');
				labelCell.appendChild(linkLabelCell);
				if (itemProduct.product) {
					linkLabelCell.innerHTML = itemProduct.product
							.getTicketLabel(itemProduct.priceCategory);
				}
				linkLabelCell.addEventListener("click", function(event) {
					commandCashSale.selection(itemProduct);
				});

				var priceCell = row.insertCell(2);
				var linkPriceCell = document.createElement('a');
				priceCell.appendChild(linkPriceCell);
				if (currentSession.cashSale.consumptionPlace == "ON_PLACE") {
					linkPriceCell.innerHTML = itemProduct.unitPriceOnplace;
				} else {
					linkPriceCell.innerHTML = itemProduct.unitPriceTakeaway;
				}
				linkPriceCell.addEventListener("click", function(event) {
					currentSession.cashSale.lastItem = itemProduct;
					showModalProduct(itemProduct);
					ticket.display();
				});

				if (itemProduct.deleted) {
					row.className = 'ticket_item_deleted';
				}
				if (itemProduct.itemSelected) {
					labelCell.className = 'ticket_item_selected';
					qtyCell.className = 'ticket_item_selected';
					priceCell.className = 'ticket_item_selected';
				}
				if (itemProduct === currentSession.cashSale.lastItem) {
					labelCell.style.fontWeight = "900";
					qtyCell.style.fontWeight = "900";
					priceCell.style.fontWeight = "900";
				}
			});
		}

		if (currentSession.cashSale.itemPayments
				&& currentSession.cashSale.itemPayments.length > 0) {
			currentSession.cashSale.itemPayments
					.forEach(function(itemPayment) {

						var row = ticket.ticketTableDiv
								.insertRow(firstRowIndex);

						var qtyCell = row.insertCell(0);
						var linkQtyCell = document.createElement('a');
						qtyCell.appendChild(linkQtyCell);
						linkQtyCell.innerHTML = itemPayment.quantity;
						linkQtyCell.addEventListener("click", function(event) {
							currentSession.cashSale.lastItem = itemPayment;
							showModalPaymentMethod(itemPayment);
							ticket.display();
						});

						var labelCell = row.insertCell(1);
						var linkLabelCell = document.createElement('a');
						labelCell.appendChild(linkLabelCell);
						linkLabelCell.innerHTML = itemPayment.paymentMethod.ticketLabel;
						linkLabelCell.addEventListener("click",
								function(event) {
									commandCashSale.selection(itemPayment);
								});

						var priceCell = row.insertCell(2);
						var linkPriceCell = document.createElement('a');
						priceCell.appendChild(linkPriceCell);
						linkPriceCell.innerHTML = itemPayment.unitPrice;
						linkPriceCell
								.addEventListener(
										"click",
										function(event) {
											currentSession.cashSale.lastItem = itemPayment;
											showModalPaymentMethod(itemPayment);
											ticket.display();
										});

						if (itemPayment.quantity * itemPayment.unitPrice >= 0) {
							linkLabelCell.style.color = 'green';
							linkQtyCell.style.color = 'green';
							linkPriceCell.style.color = 'green';
						} else {
							linkLabelCell.style.color = 'red';
							linkQtyCell.style.color = 'red';
							linkPriceCell.style.color = 'red';
						}

						if (itemPayment.deleted) {
							row.className = 'ticket_item_deleted';
						}

						if (itemPayment.itemSelected) {
							labelCell.className = 'ticket_item_selected';
							qtyCell.className = 'ticket_item_selected';
							priceCell.className = 'ticket_item_selected';
						}

						if (itemPayment === currentSession.cashSale.lastItem) {
							labelCell.style.fontWeight = "900";
							qtyCell.style.fontWeight = "900";
							priceCell.style.fontWeight = "900";
						}

					});
		}

		if (guiView.multipleSelection) {
			var selectdCashSale = commandCashSale.computeSelectedCashSale(currentSession.cashSale);
			displayTotal.displaySaleStatus(selectdCashSale);
		} else {
			displayTotal.displaySaleStatus(currentSession.cashSale);
		}
		

	}

}

function DisplayTotal() {

	this.jQueryRemainValueDiv = $("#calc_result");
	this.remainValueDiv = this.jQueryRemainValueDiv[0];

	this.displayCalcResult = function(value) {

		if (value.length > 16) {
			alert("longueur maximum atteinte");
		}

		this.remainValueDiv.style.textAlign = 'right';
		this.remainValueDiv.innerHTML = 'Calc:' + value;

		if (value > 0) {
			this.remainValueDiv.style.color = 'blue';
		} else if (value == 0) {
			this.remainValueDiv.style.color = 'green';
		} else if (value < 0) {
			this.remainValueDiv.style.color = 'red';
		} else {
			this.remainValueDiv.style.color = 'grey';
		}

	}

	this.displaySaleStatus = function(cashSale) {

		var detail = '';
		var kbdRemainStatusBackgroundColor;
		var kbdRemainStatusTextColor;
		this.remainValueDiv.style.textAlign = 'right';
		this.remainValueDiv.style.backgroundColor = 'white';

		if (cashSale.cashSaleRemainToPay > 0
				&& cashSale.cashSaleStatus == "OPEN") {
			this.remainValueDiv.style.color = 'blue';
			kbdRemainStatusBackgroundColor = 'blue';
			kbdRemainStatusTextColor = 'white';
			this.remainValueDiv.innerHTML = detail + 'Total:'
					+ cashSale.cashSaleTotal + ' - A payer:'
					+ cashSale.cashSaleRemainToPay;
		} else if (cashSale.cashSaleRemainToPay == 0
				&& cashSale.cashSaleStatus == "OPEN"
				&& cashSale.itemProducts.length + cashSale.itemPayments.length != 0) {
			this.remainValueDiv.style.color = 'white';
			this.remainValueDiv.style.backgroundColor = 'green';
			kbdRemainStatusBackgroundColor = 'green';
			kbdRemainStatusTextColor = 'white';
			this.remainValueDiv.innerHTML = detail + 'Total:'
					+ cashSale.cashSaleTotal + ' payé - click';
			this.remainValueDiv.style.textAlign = 'center';
		} else if (cashSale.cashSaleRemainToPay < 0
				&& cashSale.cashSaleStatus == "OPEN") {
			this.remainValueDiv.style.color = 'red';
			this.remainValueDiv.style.backgroundColor = 'aqua';
			kbdRemainStatusBackgroundColor = 'red';
			kbdRemainStatusTextColor = 'white';
			this.remainValueDiv.innerHTML = detail + 'Total:'
					+ cashSale.cashSaleTotal + ' - A rendre:'
					+ (-cashSale.cashSaleRemainToPay);
			this.jQueryRemainValueDiv.on('click', function(e) {
				commandCashSale.addPayment('cash');
				commandCashSale.endCashSale()
			});
		} else {
			this.remainValueDiv.style.color = 'grey';
			kbdRemainStatusBackgroundColor = 'grey';
			kbdRemainStatusTextColor = 'white';
			if (cashSale.cashSaleStatus == "OPEN") {
				this.remainValueDiv.innerHTML = detail + '-:'
						+ cashSale.cashSaleRemainToPay;
			} else {
				this.remainValueDiv.innerHTML = '---';
			}
		}

		for (var index = 0; index < kbdRemainStatusButton.length; index++) {
			var kbdRemainStatusItem = kbdRemainStatusButton[index];
			kbdRemainStatusItem.style.background = kbdRemainStatusBackgroundColor;
			kbdRemainStatusItem.style.color = kbdRemainStatusTextColor;
		}

		if (guiView.currentSpanStatus) {
			if (currentSession && currentSession.cashSale) {
				if (currentSession.cashSale.cashSaleStatus == "OPEN") {
					guiView.currentSpanStatus.innerHTML = 'vente en cours';
					guiView.currentSpanStatus.style.backgroundColor = 'green';
					guiView.currentSpanStatus.style.color = 'white';
				} else if (currentSession.cashSale.cashSaleStatus == "DONE") {
					guiView.currentSpanStatus.innerHTML = 'vente terminée';
					guiView.currentSpanStatus.style.backgroundColor = 'white';
					guiView.currentSpanStatus.style.color = 'black';
				} else if (currentSession.cashSale.cashSaleStatus == "CANCEL") {
					guiView.currentSpanStatus.innerHTML = 'vente annulée';
					guiView.currentSpanStatus.style.backgroundColor = 'red';
					guiView.currentSpanStatus.style.color = 'white';
				} else {
					guiView.currentSpanStatus.innerHTML = "-";
					guiView.currentSpanStatus.style.backgroundColor = 'white';
					guiView.currentSpanStatus.style.color = 'white';
				}
			} else {
				guiView.currentSpanStatus.innerHTML = "-";
			}
		}
		guiView.setTakeAwayButton(cashSale.consumptionPlace);
	}

	this.getInnerHTML = function() {
		if (this.remainValueDiv.innerHTML === this.noSale) {
			return "";
		} else {
			return this.remainValueDiv.innerHTML;
		}
	}

}

function requestFullScreen(elem) {

	var requestMethod = elem.requestFullScreen || elem.webkitRequestFullScreen
			|| elem.mozRequestFullScreen || elem.msRequestFullScreen;
	if (requestMethod) {
		requestMethod.call(elem);
	} else if (typeof window.ActiveXObject !== undefined) {
		var wscript = new ActiveXObject("WScript.Shell");
		if (wscript !== null) {
			wscript.SendKeys("{F11}");
		}
	}
}

function toggleFullScreen(elem) {

	if ((document.fullScreenElement !== undefined && document.fullScreenElement === null)
			|| (document.msFullscreenElement !== undefined && document.msFullscreenElement === null)
			|| (document.mozFullScreen !== undefined && !document.mozFullScreen)
			|| (document.webkitIsFullScreen !== undefined && !document.webkitIsFullScreen)) {
		if (elem.requestFullScreen) {
			elem.requestFullScreen();
		} else if (elem.mozRequestFullScreen) {
			elem.mozRequestFullScreen();
		} else if (elem.webkitRequestFullScreen) {
			elem.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
		} else if (elem.msRequestFullscreen) {
			elem.msRequestFullscreen();
		}
	} else {
		if (document.cancelFullScreen) {
			document.cancelFullScreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.webkitCancelFullScreen) {
			document.webkitCancelFullScreen();
		} else if (document.msExitFullscreen) {
			document.msExitFullscreen();
		}
	}

}

function modalValueEngine(htmlInputValue) {

	this.value;
	this.operation;
	this.item;

	this.setValue = function(value) {
		this.value = value;
		this.htmlUpdate();
	}

	this.inc = function(incValue) {
		this.value += incValue;
		this.htmlUpdate();
	}

	this.dec = function(decValue) {
		this.value -= decValue;
		this.htmlUpdate();
	}

	this.htmlUpdate = function() {
		this.value = roundValue(this.value);
		htmlInputValue[0].value = this.value;
	}
}

function showModalProduct(itemProduct) {

	if (!commandCashSale.isOpen()) {
		alert("La vente est cloturée.");
		return;
	}

	var header = '<h2 class="modal-title">' + itemProduct.product.ticketLabel
			+ '</h2>';
	if (itemProduct.deleted) {
		header = '<h2 class="modal-title">' + itemProduct.product.ticketLabel
				+ '<red>(deleted)</red></h2>';
	}

	$("#item-edit-header").html(header);

	ticket.modalValueEngineQty.value = itemProduct.quantity;
	ticket.modalValueEngineQty.operation = itemProduct.product;
	ticket.modalValueEngineQty.item = itemProduct;
	ticket.modalValueEngineQty.htmlUpdate();

	ticket.modalValueEngineValue.value = itemProduct.unitPriceTakeaway;
	ticket.modalValueEngineValue.operation = itemProduct.product;
	ticket.modalValueEngineValue.item = itemProduct;
	ticket.modalValueEngineValue.itemType = "PRODUCT";
	ticket.modalValueEngineValue.htmlUpdate();

	if (itemProduct.deleted) {
		itemEditBodyDeleteModal.hide();
		itemEditBodyUndeleteModal.show();
	} else {
		itemEditBodyDeleteModal.show();
		itemEditBodyUndeleteModal.hide();
	}
	itemEditModal.modal("show");

}

function showModalPaymentMethod(itemPaymentMethod) {

	if (!commandCashSale.isOpen()) {
		alert("La vente est cloturée.");
		return;
	}

	var header = '<h2 class="modal-title">'
			+ itemPaymentMethod.paymentMethod.ticketLabel + '</h2>';
	if (itemPaymentMethod.deleted) {
		header = '<h2 class="modal-title">'
				+ itemPaymentMethod.paymentMethod.ticketLabel
				+ '<red>(deleted)</red></h2>';
	}

	$("#item-edit-header").html(header);

	ticket.modalValueEngineQty.value = itemPaymentMethod.quantity;
	ticket.modalValueEngineQty.operation = itemPaymentMethod.paymentMethod;
	ticket.modalValueEngineQty.item = itemPaymentMethod;
	ticket.modalValueEngineQty.htmlUpdate();

	ticket.modalValueEngineValue.value = itemPaymentMethod.unitPrice;
	ticket.modalValueEngineValue.operation = itemPaymentMethod.paymentMethod;
	ticket.modalValueEngineValue.item = itemPaymentMethod;
	ticket.modalValueEngineValue.itemType = "PAYMENT";
	ticket.modalValueEngineValue.htmlUpdate();

	if (itemPaymentMethod.deleted) {
		itemEditBodyDeleteModal.hide();
		itemEditBodyUndeleteModal.show();
	} else {
		itemEditBodyDeleteModal.show();
		itemEditBodyUndeleteModal.hide();
	}
	itemEditModal.modal("show");

}

function showModalInform(message) {

	var header = '<h2 class="modal-title">Pour nformation !</h2>';

	$("#modal-inform-header").html(header);

	var formatedMessage = '<h2 class="modal-title">' + message + '</h2>';

	$("#modal-inform-confirmeMessage").html(formatedMessage);

	$("#inform_modal").modal("show");

}

function showModalConfirm(message, action) {

	var header = '<h2 class="modal-title">Confirmez vous ?</h2>';

	$("#modal-confirm-header").html(header);

	var formatedMessage = '<h2 class="modal-title">' + message + '</h2>';

	$("#modal-confirm-confirmeMessage").html(formatedMessage);

	$("#modalConfirm-validate").off('click');

	$("#modalConfirm-validate").click(action);

	$("#confirm_modal").modal("show");

}
