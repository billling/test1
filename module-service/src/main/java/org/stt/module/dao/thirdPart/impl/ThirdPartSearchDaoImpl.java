package org.stt.module.dao.thirdPart.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.stt.module.dao.BaseDao;
import org.stt.module.dao.thirdPart.IThirdPartSearchDao;
import org.stt.module.dto.ThirdPart.HmPacketDto;

@Repository
public class ThirdPartSearchDaoImpl extends BaseDao implements IThirdPartSearchDao {

    private static final String NAMESPACE = "stt.db2.thirdPart.";

    @Override
    public List<HmPacketDto> selectByRecordID(final Long RecordID) {

        return getSession(2).selectList(NAMESPACE + "selectByRecordID", RecordID);
    }

}
