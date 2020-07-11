$(document)
		.ready(

				function() {

					localStorageQueue = new LocalStorageQueue();
					guiDao = new GuiDao();
					calculator = new Calculator();
					cashSaleEngine = new CashSaleEngine();
					commandCashSale = new CommandCashSale();
					sessions = new Array();
					guiView = new GuiView();
					ticket = new Ticket();

					localStorageQueue.listLocalStorageQueue();
					$(document)
							.on(
									'online',
									function() {
										console.log('online');
										localStorageQueue
												.purgeLocalStorage(localStorageQueue.removeLocalStorage);
									});

					$(document).on('offline', function() {
						console.log('offline');
					});

					guiDao.init();
					guiView.init();
					commandCashSale.init();

				});