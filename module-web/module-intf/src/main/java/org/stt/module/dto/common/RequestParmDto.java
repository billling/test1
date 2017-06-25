package org.stt.module.dto.common;

import java.util.Date;





import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.stt.module.annotations.JackJsonDateTimeFormat;
import org.stt.module.annotations.JackJsonDateTimeParse;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * common request input parm
 * @author wangchao
 *
 */
public class RequestParmDto {

    /**
     * user account eg:phone email
     */
    @NotBlank
    private String account;

    /**
     * sign
     */
    @NotBlank
    private String sign;

    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @NotNull
    private Date timeStamp;

    /**
     * business parm as a json
     */
    @NotBlank
    private String parmJson;

    public String getAccount() {

        return account;
    }

    public void setAccount(String account) {

        this.account = account;
    }

    public String getSign() {

        return sign;
    }

    public void setSign(String sign) {

        this.sign = sign;
    }

    public Date getTimeStamp() {

        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {

        this.timeStamp = timeStamp;
    }

    public String getParmJson() {

        return parmJson;
    }

    public void setParmJson(String parmJson) {

        this.parmJson = parmJson;
    }

}
