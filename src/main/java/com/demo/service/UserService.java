package com.demo.service;

import com.demo.dto.ResponseDTO;
import com.demo.entity.User;

/**
 * @author Adeel.Asghar
 */

public interface UserService {

	ResponseDTO getAllUsers();

	User getUserById(String userId);

	ResponseDTO findOne(String userId);

	ResponseDTO saveUser(User user);

	ResponseDTO getAllUserSettings(String userId);

	String getUserSetting(String userId, String key);

	ResponseDTO saveUserSettings(String userId, String key, String value);
}