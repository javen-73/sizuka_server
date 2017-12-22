package com.javen.sizuka.modules.version.service;

import com.javen.sizuka.model.Version;
import com.javen.sizuka.modules.version.mapper.VersionMapper;
import com.javen.sizuka.utils.ReturnDTO;
import com.javen.sizuka.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by javen on 2017/12/21.
 */
@Service
public class VersionService {
    @Autowired
    private VersionMapper versionMapper;

    public ReturnDTO lastVersion() {
        Version version = versionMapper.lastVersion();
        if (version == null || StringUtil.isBlank(version.getUpdate())) {
            return ReturnDTO.buildFaildReturnDTO("获取版本失败");
        }
        String vs = version.getUpdate();
        List<String> vsList = Arrays.asList(vs.split(","));
        Map<String, Object> result = new HashMap<>();
        result.put("version", version.getVersion());
        result.put("update", vsList);
        result.put("create_time", version.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return ReturnDTO.buildSuccessReturnDTO(result);
    }
}
