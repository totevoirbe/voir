package be.panidel.dao;



public class xmlGeneration {

//	private static final Logger log = Logger.getLogger(xmlGeneration.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//			Document document = DOMUtils.getDocumentBuilder().newDocument();
//			Node root = document.createElement(POSConstants.ROOT);
//			document.appendChild(root);
//
//			// products
//			Element productsNode = document.createElement(POSConstants.PRODUCTS);
//			root.appendChild(productsNode);
//			List<Product> products = ProductsDAO.instance().list();
//			for (Product product : products) {
//				
//				Element elementNode = document.createElement(POSConstants.PRODUCT);
//				productsNode.appendChild(elementNode);
//				
//				elementNode.setAttribute(POSConstants.ID,product.getId());
//				elementNode.setIdAttribute(POSConstants.ID, true);
//				
//				elementNode.appendChild(newNode(document, POSConstants.CODE,
//						product.getCode()));
//				elementNode.appendChild(newNode(document, POSConstants.NAME,
//						product.getName()));
//				elementNode.appendChild(newNode(document, POSConstants.DESCRIPTION,
//						product.getDescription()));
//				elementNode.appendChild(newNode(document, POSConstants.PRIX,
//						Integer.toString(product.getPrix())));
//				elementNode.appendChild(newNode(document, POSConstants.TVA,
//						Integer.toString(product.getTva())));
//			}
//			productsNode.setAttribute(POSConstants.INDEX, Integer.toString(products.size()+1));
//			
//			// payements mode
//			Element paymentsNode = document.createElement(POSConstants.PAYMENTS);
//			root.appendChild(paymentsNode);
//			List<Payement> payements = PayementModesDAO.instance().list();
//			for (Payement payement : payements) {
//				
//				Element elementNode = document.createElement(POSConstants.PAYMENT);
//				paymentsNode.appendChild(elementNode);
//				
//				elementNode.setAttribute(POSConstants.ID,payement.getId());
//				elementNode.setIdAttribute(POSConstants.ID, true);
//				
//				elementNode.appendChild(newNode(document, POSConstants.DESCRIPTION,
//						payement.getDescription()));
//			}
//			paymentsNode.setAttribute(POSConstants.INDEX, Integer.toString(payements.size()+1));
//			
//			// employees mode
//			Element employesNodes = document.createElement(POSConstants.EMPLOYEES);
//			root.appendChild(employesNodes);
//			List<Employee> employees = EmployeesDAO.instance().list();
//			for (Employee employee : employees) {
//				
//				Element employesNode = document.createElement(POSConstants.EMPLOYEE);
//				employesNodes.appendChild(employesNode);
//				
//				employesNode.setAttribute(POSConstants.ID,employee.getId());
//				employesNode.setIdAttribute(POSConstants.ID, true);
//				
//				employesNode.appendChild(newNode(document, POSConstants.CODE,
//						employee.getCode()));
//				employesNode.appendChild(newNode(document, POSConstants.NAME,
//						employee.getName()));
//				employesNode.appendChild(newNode(document, POSConstants.DESCRIPTION,
//						employee.getDescription()));
//			}
//			employesNodes.setAttribute(POSConstants.INDEX, Integer.toString(employees.size()+1));
//
//			DOMUtils.XMLFileWriter(document, POSConstants.xmlFile);
//			log.debug(DOMUtils.XMLToStringWriter(document));
		
		
	}
	
//	private static Node newNode(Document document, String tagName, String data){
//
//		Node child = document.createElement(tagName);
//
//		if(data==null) {
//			data = new String();
//		}
//		Node textNode = document.createTextNode(data);
//		child.appendChild(textNode);
//
//		return child;
//	}

}
