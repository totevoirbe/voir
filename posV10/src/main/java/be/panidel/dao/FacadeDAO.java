package be.panidel.dao;

public class FacadeDAO {

	private EmployeesDAO employeesDAO;
	private PayementModesDAO payementModesDAO;
	private PayementItemDAO payementItemDAO;
	private ProductsDAO productsDAO;
	private RawProductsDAO rawProductsDAO;
	private ProductItemDAO productItemDAO;
	private CompanyDAO companyDAO;
	private KeyboardLayoutDAO keyboardLayoutDAO;
	private GroupsDAO groupsDAO;
	private SalesDAO salesDAO;
	private OperationUnitDAO operationUnitDAO;

	private static FacadeDAO instance;
	private static Object lock = new Object();

	private FacadeDAO() {
	}

	public static FacadeDAO instance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new FacadeDAO();
				instance.employeesDAO = new EmployeesDAO();
				instance.payementModesDAO = new PayementModesDAO();
				instance.groupsDAO = new GroupsDAO();
				instance.productsDAO = new ProductsDAO();
				instance.rawProductsDAO = new RawProductsDAO();
				instance.companyDAO = new CompanyDAO();
				instance.keyboardLayoutDAO = new KeyboardLayoutDAO();
				instance.salesDAO = new SalesDAO();
				instance.payementItemDAO = new PayementItemDAO();
				instance.productItemDAO = new ProductItemDAO();
				instance.operationUnitDAO = new OperationUnitDAO();
			}
		}
		return instance;
	}

	public EmployeesDAO getEmployeesDAO() {
		return employeesDAO;
	}

	public PayementModesDAO getPayementModesDAO() {
		return payementModesDAO;
	}

	public ProductsDAO getProductsDAO() {
		return productsDAO;
	}

	public RawProductsDAO getRawProductsDAO() {
		return rawProductsDAO;
	}

	public KeyboardLayoutDAO getKeyboardLayoutDAO() {
		return keyboardLayoutDAO;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public GroupsDAO getGroupsDAO() {
		return groupsDAO;
	}

	public SalesDAO getSalesDAO() {
		return salesDAO;
	}

	public PayementItemDAO getPayementItemDAO() {
		return payementItemDAO;
	}

	public ProductItemDAO getProductItemDAO() {
		return productItemDAO;
	}

	public OperationUnitDAO getOperationUnitDAO() {
		return operationUnitDAO;
	}
}
