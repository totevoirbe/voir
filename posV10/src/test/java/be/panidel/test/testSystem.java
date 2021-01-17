package be.panidel.test;

import java.util.Map;

public class testSystem {

	public static void main(String[] args) {
		Map<String,String> sys = System.getenv();
		for (String sysProp : sys.keySet()) {
			System.out.println(sysProp+"["+sys.get(sysProp)+"]");
		}
		
	}
	
}
