package org.stt.module.dto.admin;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.stt.module.annotations.JackJsonDateFormat;
import org.stt.module.annotations.JackJsonDateParse;
import org.stt.module.annotations.JackJsonDateTimeFormat;
import org.stt.module.annotations.JackJsonDateTimeParse;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * User Data Transfer Object
 * @author wangchao
 *
 */
public class AdminDto implements Serializable {

    /**
     * user name
     */
    @NotBlank
    private String name;

    /**
     * user age
     */
    @Min(value = 0)
    private Integer age;
    
    /**
     * user phone
     */
    @NotBlank
    private String phone;

    /**
     * birthday
     */
    @NotNull
    @JsonSerialize(using = JackJsonDateFormat.class)
    @JsonDeserialize(using = JackJsonDateParse.class)
    private Date birthday;

    /**
     * register timestamp
     */
    @NotNull
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    private Date registerTime;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    
    public String getPhone() {
    
        return phone;
    }

    
    public void setPhone(String phone) {
    
        this.phone = phone;
    }
}
