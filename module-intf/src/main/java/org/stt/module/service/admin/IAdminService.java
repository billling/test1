package org.stt.module.service.admin;

import org.stt.module.dto.admin.AdminDto;

/**
 * user service
 * @author wangchao
 *
 */
public interface IAdminService {

    /**
     * create AdminDto
     * @param adminDto
     * @return
     */
    public AdminDto createAdmin(AdminDto adminDto);

    /**
     * select AdminDto by adminId
     * @param phone
     * @return UserDto
     */
    public AdminDto getAdminById(Integer adminId);

}
