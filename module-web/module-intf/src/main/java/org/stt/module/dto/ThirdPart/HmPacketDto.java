package org.stt.module.dto.ThirdPart;

import java.util.Date;

/**
 * HmPacket表
 */
public class HmPacketDto {

    private Long RecordID;

    private Date RecordTime;

    private String EPAddress;

    private Object PacketData;

    private Long HistoryRecordID;

    /**
     * @return the recordID
     */
    public Long getRecordID() {

        return RecordID;
    }

    /**
     * @param recordID the recordID to set
     */
    public void setRecordID(final Long recordID) {

        RecordID = recordID;
    }

    /**
     * @return the recordTime
     */
    public Date getRecordTime() {

        return RecordTime;
    }

    /**
     * @param recordTime the recordTime to set
     */
    public void setRecordTime(final Date recordTime) {

        RecordTime = recordTime;
    }

    /**
     * @return the ePAddress
     */
    public String getEPAddress() {

        return EPAddress;
    }

    /**
     * @param ePAddress the ePAddress to set
     */
    public void setEPAddress(final String ePAddress) {

        EPAddress = ePAddress;
    }

    /**
     * @return the packetData
     */
    public Object getPacketData() {

        return PacketData;
    }

    /**
     * @param packetData the packetData to set
     */
    public void setPacketData(final Object packetData) {

        PacketData = packetData;
    }

    /**
     * @return the historyRecordID
     */
    public Long getHistoryRecordID() {

        return HistoryRecordID;
    }

    /**
     * @param historyRecordID the historyRecordID to set
     */
    public void setHistoryRecordID(final Long historyRecordID) {

        HistoryRecordID = historyRecordID;
    }

}
