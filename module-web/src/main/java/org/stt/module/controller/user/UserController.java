package org.stt.module.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stt.module.dto.common.ResponseDto;
import org.stt.module.dto.user.PasswordUpdateInputDto;
import org.stt.module.dto.user.UserDto;
import org.stt.module.dto.user.UserInputDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.exception.SystemException;
import org.stt.module.framework.annotation.JsonDto;
import org.stt.module.framework.annotation.ResponseForJson;
import org.stt.module.framework.annotation.ValidDto;
import org.stt.module.service.user.IUserService;
import org.stt.module.util.base.FrameWorkUtil;

/**
 * 用户
 */
@Controller
@RequestMapping(value = "/manage/user")
public class UserController {

    @Resource
    IUserService userService;

    @ResponseForJson
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseDto getUserById(@JsonDto final Integer id) {

        UserDto userDto = null;
        try {
        	userDto = userService.getUserById(id);
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(userDto, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(userDto, null);
    }
    
    @ResponseForJson
    @RequestMapping(value = "/userByUserId", method = RequestMethod.GET)
    public ResponseDto getUserByUserId(@JsonDto final UserInputDto userInputDto) {

        UserDto userDto = null;
        try {
        	userDto = userService.getUserByUserId(userInputDto.getUserId());
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(userDto, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(userDto, null);
    }
    
    @ResponseForJson
    @ValidDto
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDto createUser(@JsonDto final UserDto userDto) {

    	String rep = null;
        try {
             userService.createUser(userDto);
             rep = "SUCCESS";
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(rep, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(rep, null);
    }
    
    @ResponseForJson
    @ValidDto
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseDto updateUser(@JsonDto final UserDto userDto) {

    	String rep = null;
        try {
             userService.updateUser(userDto);
             rep = "SUCCESS";
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(rep, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(rep, null);
    }
    
    @ResponseForJson
    @ValidDto
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseDto deleteUser(@JsonDto final String userId) {

    	String rep = null;
        try {
             userService.deleteUser(userId);
             rep = userId;
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(rep, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(rep, null);
    }
    
    @ResponseForJson
    @ValidDto
    @RequestMapping(value = "/password",method = RequestMethod.PUT)
    public ResponseDto updatePassword(@JsonDto final PasswordUpdateInputDto passwordUpdateInputDto) {

    	String rep = null;
        try {
             userService.updatePassword(passwordUpdateInputDto);
             rep = "SUCCESS";
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(rep, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(rep, null);
    }

}
