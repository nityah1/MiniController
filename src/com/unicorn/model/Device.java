package com.unicorn.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
	 
@XmlRootElement
public class Device implements Serializable{

	private String deviceId;
	private String macAddr;
	private String status;

	public Device(){};
	
	public Device(String deviceId, String macAddr, String status){
		super();
		this.deviceId = deviceId;
		this.macAddr = macAddr;
		this.status = status;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Device[deviceId=%s, macAddr='%s', status='%s']",
                deviceId, macAddr, status);
    }

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
