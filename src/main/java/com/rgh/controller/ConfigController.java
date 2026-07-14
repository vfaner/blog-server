package com.rgh.controller;

import com.rgh.constant.Constant;
import com.rgh.dto.SysConfigDto;
import com.rgh.service.SysConfigService;
import com.rgh.util.ResultUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.PATH_PREFIX + "/sys")
@Tag(name = "网站配置信息接口", description = "网站配置信息相关的接口")
public class ConfigController {

    @Autowired
    private SysConfigService configService;

    @GetMapping("/read")
    public ResultUtil configRead(){
        return configService.configRead();
    }

    @PutMapping("/write")
    public ResultUtil configWrite(@RequestBody SysConfigDto sysConfigDto){
        return configService.configWrite(sysConfigDto);
    }

}
