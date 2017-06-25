package org.stt.module.thirdPart;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.stt.module.base.BaseTest;
import org.stt.module.dao.thirdPart.IThirdPartSearchDao;
import org.stt.module.dto.ThirdPart.HmPacketDto;
import org.testng.annotations.Test;

public class ThirdPartTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartTest.class);

    @Resource
    IThirdPartSearchDao thirdPartSearchDao;

    @Test
    public void testSelect() {

        final List<HmPacketDto> list = thirdPartSearchDao.selectByRecordID(313610L);
        if (CollectionUtils.isEmpty(list)) {
            LOGGER.info("no result");
        } else {
            ;
            LOGGER.info("HistoryRecordID is  " + list.get(0).getHistoryRecordID());
        }
    }
}
