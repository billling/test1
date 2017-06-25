package org.stt.module.dao.user.impl;

import org.springframework.stereotype.Repository;
import org.stt.module.dao.BaseDao;
import org.stt.module.dao.user.IUserDao;
import org.stt.module.dto.user.UserDto;
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

	private static final String NAMESPACE = "stt.db1.user.";

	@Override
	public UserDto getUserById(Integer id) {
		return getSession(1).selectOne(NAMESPACE + "getUserById", id);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		return getSession(1).selectOne(NAMESPACE + "getUserByUserId", userId);
	}

	@Override
	public int createUser(UserDto userDto) {
		return getSession(1).insert(NAMESPACE + "createUser", userDto);
	}

	@Override
	public int updateUser(UserDto userDto) {
		return getSession(1).update(NAMESPACE + "updateUser", userDto);
	}

	@Override
	public int deleteUser(String userId) {
		return getSession(1).delete(NAMESPACE + "deleteUser", userId);
	}

	@Override
	public int updatePassword(UserDto userDto) {
		return getSession(1).update(NAMESPACE + "updatePassword", userDto);
	}

}
