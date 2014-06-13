package com.unicorn.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unicorn.model.Device;
import com.unicorn.model.User;



@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository {

	User getUser(String userId);
	List<User> getAllUsers();
	List<Device> findDevicesByUserId(String userId);
	void registerUser(User user);
	void updateUser(User user);
	void registerDeviceForUser(String userId, String deviceId, String macAddr);
	String checkDeviceStatus(String userId, String deviceId);
	User updateDeviceStatus(String userId, String deviceId, String status);
	
}
