package com.unicorn.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import com.unicorn.model.Device;
import com.unicorn.model.User;

@Service
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	MongoOperations mongoTemplate;
	
	@Override
	public User getUser(String userId) {
		Query sq = new Query(Criteria.where("userId").is(userId));
		User u = mongoTemplate.findOne(sq, User.class);
		return u;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> ul = mongoTemplate.find(new Query(), User.class);
		return ul;
	}
	

	@Override
	public void updateUser(User user) {
		Query sq = new Query(Criteria.where("id").is(user.getId()));
		Update u = new Update();
		u.set("userId", user.getUserId());
		u.set("password", user.getPassword());
		u.set("name", user.getName());
		u.set("email", user.getEmail());
		
		User updUser = mongoTemplate.findAndModify(sq, u, 
				new FindAndModifyOptions().returnNew(true), User.class);
		
	}

	@Override
	public List<Device> findDevicesByUserId(String userId) {
		User u = getUser(userId);
		return u.getDevices();
	}

	@Override
	public void registerUser(User user) {
		mongoTemplate.save(user);
		mongoTemplate.insert(user);		
		List<User> ulist = new ArrayList<User>();
		ulist.add(user);
		mongoTemplate.insert(ulist, User.class);
	}
	
	@Override
	public String checkDeviceStatus(String userId, String deviceId) {

		Query sq = new Query(Criteria.where("userId").is(userId).and("devices.deviceId").is(deviceId));
		List<User> userList = mongoTemplate.find(sq, User.class);
		for(User u:userList){
			List<Device> devices = u.getDevices();
			for (Device device : devices) {
				if (device.getDeviceId().equals(deviceId))
					return device.getStatus();
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateDeviceStatus(String userId, String deviceId, String status) {
		Query sq = new Query(Criteria.where("userId").is(userId).and("devices.deviceId").is(deviceId));
		Update u = new Update();
		u.set("devices.$.status", status);
		
		User updUser = mongoTemplate.findAndModify(sq, u, 
				new FindAndModifyOptions().returnNew(true), User.class);
		return updUser;
	}

	@Override
	public void registerDeviceForUser(String userId, String deviceId,
			String macAddr) {
		Device d = new Device(deviceId, macAddr, "OFF");
		Query sq = new Query(Criteria.where("userId").is(userId));
		Update u = new Update();
		u.addToSet("devices", d);
		User updUser = mongoTemplate.findAndModify(sq, u, 
				new FindAndModifyOptions().returnNew(true), User.class);
		System.out.println("Done");
	}

}
