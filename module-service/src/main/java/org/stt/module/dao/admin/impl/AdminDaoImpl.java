package org.stt.module.dao.admin.impl;

import org.springframework.stereotype.Repository;
import org.stt.module.dao.BaseDao;
import org.stt.module.dao.admin.IAdminDao;
import org.stt.module.dto.admin.AdminDto;

@Repository
public class AdminDaoImpl extends BaseDao implements IAdminDao {

    private static final String NAMESPACE = "stt.db1.admin.";

    @Override
    public AdminDto getAdminById(final Integer adminId) {

        return getSession(1).selectOne(NAMESPACE + "getAdminById", adminId);
    }

    @Override
    public int createAdmin(final AdminDto adminDto) {

        return getSession(2).insert(NAMESPACE + "createAdmin", adminDto);
    }

}
