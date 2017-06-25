package org.stt.module.dao.user;

import org.stt.module.dto.user.UserDto;

public interface IUserDao {

	/**
	 * @param userId
	 * @return
	 */
	public UserDto getUserById(final Integer id);
	
	/**
	 * @param userId
	 * @return
	 */
	public UserDto getUserByUserId(final String userId);

	/**
	 * @param userDto
	 * @return
	 */
	public int createUser(final UserDto userDto);
	
	/**
	 * @param userDto
	 * @return
	 */
	public int updateUser(final UserDto userDto);
	
	/**
	 * @param userId
	 * @return
	 */
	public int deleteUser(final String userId);
	
	/**
	 * @param userDto
	 * @return
	 */
	public int updatePassword(final UserDto userDto);
}
