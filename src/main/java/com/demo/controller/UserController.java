package com.demo.controller;
import com.demo.dto.ResponseDTO;
import com.demo.service.UserService;
import com.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adeel.Asghar
 */

@RestController
@RequestMapping(value = "/user/")
public class UserController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseDTO addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return  userService.saveUser(user);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseDTO getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseDTO getUser(@PathVariable String userId) {
		return userService.findOne(userId);
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		return userService.getAllUserSettings(userId);
	}

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		return userService.getUserSetting(userId, key);
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public ResponseDTO addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		return userService.saveUserSettings(userId, key, value);
	}
}