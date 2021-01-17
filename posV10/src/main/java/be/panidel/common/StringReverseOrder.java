package be.panidel.common;

public class StringReverseOrder implements Comparable<StringReverseOrder> {

	String value;

	public StringReverseOrder(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(StringReverseOrder o) {

		int compareRes = o.toString().compareTo(value.toString());

		return -compareRes;
	}

	@Override
	public String toString() {
		return value;
	}
}