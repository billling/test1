package org.stt.module.service.sessions;

import org.stt.module.dto.sessions.SessionsInputDto;
import org.stt.module.dto.sessions.SessionsOutputDto;

public interface ISessionsService {
	
	SessionsOutputDto userLogin(SessionsInputDto sessionsInputDto);
	
	String userLogout();
}
