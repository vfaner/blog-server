package com.rgh.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgh.dto.SysConfigDto;
import com.rgh.service.SysConfigService;
import com.rgh.util.ResultUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Override
    public ResultUtil configRead() {
        SysConfigDto sysConfigDto = new SysConfigDto();
        try {
            ObjectMapper objMap = new ObjectMapper();
            objMap.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sysConfigDto = objMap.readValue(Files.newInputStream(Paths.get("src/main/resources/system.json")), SysConfigDto.class);
            return ResultUtil.success("读取成功", sysConfigDto);
        } catch (Exception ex) {
           return ResultUtil.fail(500, "读取失败", ex.getCause());
        }
    }

    @Override
    public ResultUtil configWrite(SysConfigDto sysConfigDto) {
        try {
            ObjectMapper objMap = new ObjectMapper();
            objMap.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode root = objMap.valueToTree(sysConfigDto);
            OutputStream outputStream = Files.newOutputStream(new File("src/main/resources/system.json").toPath());
            objMap.writeValue(outputStream, root);
        } catch (Exception ex) {
            System.out.println("error to read config file.");
            return ResultUtil.fail(500,"更新失败",ex.getCause());
        }
        return ResultUtil.success("更新成功",getSysConfig());
    }

    public SysConfigDto getSysConfig() {
        SysConfigDto sysConfigDto = new SysConfigDto();
        try {
            ObjectMapper objMap = new ObjectMapper();
            objMap.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sysConfigDto = objMap.readValue(Files.newInputStream(Paths.get("src/main/resources/system.json")), SysConfigDto.class);
            return sysConfigDto;
        } catch (Exception ex) {
            return sysConfigDto;
        }
    }
}
