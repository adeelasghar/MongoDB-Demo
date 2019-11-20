package com.demo.service.impl;

import java.util.List;

import com.demo.dto.ResponseDTO;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import com.demo.util.ApiCode;
import com.demo.util.ApiConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Adeel.Asghar
 */

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseDTO getAllUsers() {
		try {
			LOG.info("Saving user.");
			List<User> usersLst =  mongoTemplate.findAll(User.class);
			LOG.info("Successfully Save.");
			return new ResponseDTO(usersLst, true, ApiConstants.SUSSESS_MESSGAE, ApiCode.SUCCESS);
		} catch (Exception ex) {
			return new ResponseDTO(null, false, ApiConstants.ERROR_MESSAGE, ApiCode.HTTP_500);
		}
	}

	@Override
	public User getUserById(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		return mongoTemplate.findOne(query, User.class);
	}

	@Override
	public ResponseDTO findOne(String userId) {
		try {
			User user = userRepository.findOne(userId);
			if(user != null) {
				return new ResponseDTO(user, true, ApiConstants.SUSSESS_MESSGAE, ApiCode.SUCCESS);
			}
			return new ResponseDTO(user, true, ApiConstants.USER_NOT_FOUND, ApiCode.HTTP_404);
		} catch (Exception ex) {
			return new ResponseDTO(null, false, ApiConstants.ERROR_MESSAGE, ApiCode.HTTP_500);
		}
	}

	@Override
	public ResponseDTO saveUser(User user) {
		try {
			LOG.info("Saving user.");
			mongoTemplate.save(user);
			LOG.info("Successfully Save.");
			return new ResponseDTO(user, true, ApiConstants.SUSSESS_MESSGAE, ApiCode.SUCCESS);
		} catch (Exception ex) {
			return new ResponseDTO(null, false, ApiConstants.ERROR_MESSAGE, ApiCode.HTTP_500);
		}
	}

	@Override
	public ResponseDTO getAllUserSettings(String userId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("userId").is(userId));
			User user = mongoTemplate.findOne(query, User.class);
			if(user != null) {
				return new ResponseDTO(user, true, ApiConstants.SUSSESS_MESSGAE, ApiCode.SUCCESS);
			}
			return new ResponseDTO(user, true, ApiConstants.USER_NOT_FOUND, ApiCode.HTTP_404);
		} catch (Exception ex) {
			LOG.info("Error in getAllUserSettings againt userId="+userId);
			return new ResponseDTO(null, false, ApiConstants.ERROR_MESSAGE, ApiCode.HTTP_500);
		}
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
	public ResponseDTO saveUserSettings(String userId, String key, String value) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("userId").is(userId));
			User user = mongoTemplate.findOne(query, User.class);
			if (user != null) {
				user.getUserSettings().put(key, value);
				mongoTemplate.save(user);
				return new ResponseDTO(user, true, ApiConstants.SUSSESS_MESSGAE, ApiCode.SUCCESS);
			} else {
				return new ResponseDTO(user, true, ApiConstants.USER_NOT_FOUND, ApiCode.HTTP_404);
			}
		} catch (Exception ex) {
			LOG.info("Error in addUserSetting againt userId="+userId+" and Key="+key);
			return new ResponseDTO(null, false, ApiConstants.ERROR_MESSAGE, ApiCode.HTTP_500);
		}
	}
}
