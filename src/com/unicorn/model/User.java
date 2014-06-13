package com.unicorn.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="minicontroller")
public class User {

	@Id
	private String id;
	private String userId;
	private String password;
	private String name;
	private String email;
	
	private List<Device> devices;
	
	public User(){};
	
	public User(String userId, String password, String name,String email){
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public void addDevice(String deviceId, String macAddr, String status){
		addDevice(new Device(deviceId,macAddr, status));
	}
	
	public void addDevice(Device device){
		devices.add(device);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Device> getDevices() {
		return devices;
	}
	
	
}
