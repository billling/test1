package org.stt.module.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stt.module.dto.admin.AdminDto;
import org.stt.module.dto.common.ResponseDto;
import org.stt.module.exception.BusinessException;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.exception.SystemException;
import org.stt.module.framework.annotation.JsonDto;
import org.stt.module.framework.annotation.ResponseForJson;
import org.stt.module.framework.annotation.ValidDto;
import org.stt.module.service.admin.IAdminService;
import org.stt.module.util.base.FrameWorkUtil;

/**
 *
 * @author luyao
 *
 *         2015年7月21日
 */
@Controller
@RequestMapping(value = "/manage/admin")
public class AdminController {

    @Resource
    IAdminService adminService;

    /**
     * select AdminDto by adminId
     * @param adminId
     * @return
     */
    @ResponseForJson
    @RequestMapping(value = "/{adminId}", method = RequestMethod.GET)
    public ResponseDto getAdminById(@JsonDto final Integer adminId, final HttpSession httpSession) {

        AdminDto adminDto = null;
        try {
            adminDto = adminService.getAdminById(adminId);
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(adminDto, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(adminDto, null);
    }

    /**
     * create AdminDto
     * @param AdminDto
     * @return
     */
    @ResponseForJson
    @ValidDto
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDto createAdmin(@JsonDto final AdminDto adminDto, final HttpSession httpSession) {

        AdminDto resp = null;
        try {
            resp = adminService.createAdmin(adminDto);
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(resp, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(resp, null);
    }
    
    /**
     * test
     */
    @ResponseForJson
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseDto getTest() {

        AdminDto adminDto = new AdminDto();
        try {
        	adminDto.setName("测试");
        } catch (final BusinessException e) {
            return FrameWorkUtil.createResponseDto(adminDto, e.getExceptionType());
        } catch (final SystemException e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        } catch (final Exception e) {
            return FrameWorkUtil.createResponseDto(null, ExceptionEnum.EXECUTE_RUNTIME_EXCP.getExceptionType());
        }
        return FrameWorkUtil.createResponseDto(adminDto, null);
    }

}
