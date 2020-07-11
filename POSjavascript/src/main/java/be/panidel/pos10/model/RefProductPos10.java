package be.panidel.pos10.model;

public class RefProductPos10 {

	String id;
	String code;
	String group;

	@Override
	public String toString() {
		return "RefProductPos10 [id=" + id + ", code=" + code + ", group=" + group + "]";
	}

	public RefProductPos10(String id, String code, String group) {
		super();
		this.id = id;
		this.code = code;
		this.group = group;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
