package org.stt.module.service.admin.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stt.module.dao.admin.IAdminDao;
import org.stt.module.dto.admin.AdminDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.service.admin.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Resource
    IAdminDao adminDao;

    @Override
    public AdminDto getAdminById(final Integer adminId) {

        AdminDto adminDto = null;
        try {

            adminDto = adminDao.getAdminById(adminId);
        } catch (final Exception e) {
            LOGGER.error("AdminServiceImpl getAdminById error ", e);
            throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("查询失败"));
        }

        return adminDto;
    }

    @Override
    public AdminDto createAdmin(final AdminDto adminDto) {

        AdminDto resp = null;
        try {

            final int id = adminDao.createAdmin(adminDto);
            resp = adminDto;
        } catch (final Exception e) {
            LOGGER.error("AdminServiceImpl createAdmin error ", e);
            throw new BusinessException(ExceptionEnum.EXECUTE_DB_EXCP.setExceptionMsg("创建失败"));
        }

        return resp;
    }
}
