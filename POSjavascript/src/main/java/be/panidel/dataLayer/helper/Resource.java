package be.panidel.dataLayer.helper;

import javax.xml.bind.annotation.XmlAttribute;

public class Resource {

	String name;
	String auth;
	String driverClassName;
	String maxIdle;
	String maxTotal;
	String maxWaitMillis;
	String password;
	String type;
	String url;
	String username;

	@XmlAttribute
	public String getAuth() {
		return auth;
	}

	@XmlAttribute
	public String getDriverClassName() {
		return driverClassName;
	}

	@XmlAttribute
	public String getMaxIdle() {
		return maxIdle;
	}

	@XmlAttribute
	public String getMaxTotal() {
		return maxTotal;
	}

	@XmlAttribute
	public String getMaxWaitMillis() {
		return maxWaitMillis;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlAttribute
	public String getPassword() {
		return password;
	}

	@XmlAttribute
	public String getType() {
		return type;
	}

	@XmlAttribute
	public String getUrl() {
		return url;
	}

	@XmlAttribute
	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "Resource [auth=" + auth + ", driverClassName=" + driverClassName + ", maxIdle=" + maxIdle
				+ ", maxTotal=" + maxTotal + ", maxWaitMillis=" + maxWaitMillis + ", name=" + name + ", password="
				+ password + ", type=" + type + ", url=" + url + ", username=" + username + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setMaxTotal(String maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setMaxWaitMillis(String maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}