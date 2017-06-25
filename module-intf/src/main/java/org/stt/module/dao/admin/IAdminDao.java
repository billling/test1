package org.stt.module.dao.admin;

import org.stt.module.dto.admin.AdminDto;

/**
 * admin dao
 */
public interface IAdminDao {

    /**
     * @param adminId
     * @return
     */
    public AdminDto getAdminById(final Integer adminId);

    /**
     * @param adminDto
     * @return
     */
    public int createAdmin(final AdminDto adminDto);
}
