package com.rgh.service;

import com.rgh.dto.SysConfigDto;
import com.rgh.util.ResultUtil;

public interface SysConfigService {

    ResultUtil configRead();

    ResultUtil configWrite(SysConfigDto sysConfigDto);
}
