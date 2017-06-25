package org.stt.module.dao.thirdPart;

import java.util.List;

import org.stt.module.dto.ThirdPart.HmPacketDto;

/**
 * 查询外部数据库操作
 */
public interface IThirdPartSearchDao {

    public List<HmPacketDto> selectByRecordID(Long RecordID);

}
