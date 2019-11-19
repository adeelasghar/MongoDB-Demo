package com.demo.service.impl;

import java.util.List;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDALImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<User> getAllUsers() {
		return mongoTemplate.findAll(User.class);
	}

	@Override
	public User getUserById(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		return mongoTemplate.findOne(query, User.class);
	}

	@Override
	public User addNewUser(User user) {
		mongoTemplate.save(user);
		// Now, user object will contain the ID as well
		return user;
	}

	@Override
	public Object getAllUserSettings(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		User user = mongoTemplate.findOne(query, User.class);
		return user != null ? user.getUserSettings() : "User not found.";
	}

	@Override
	public String getUserSetting(String userId, String key) {
		Query query = new Query();
		query.fields().include("userSettings");
		query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("userSettings." + key).exists(true)));
		User user = mongoTemplate.findOne(query, User.class);
		LOG.info("user Setting Data = "+user);
		return user != null ? user.getUserSettings().get(key) : "Not found.";
	}

	@Override
	public String addUserSetting(String userId, String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		User user = mongoTemplate.findOne(query, User.class);
		if (user != null) {
			user.getUserSettings().put(key, value);
			mongoTemplate.save(user);
			return "Key added.";
		} else {
			return "User not found.";
		}
	}
}
