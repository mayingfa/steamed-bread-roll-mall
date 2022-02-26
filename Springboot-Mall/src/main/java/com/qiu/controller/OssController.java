package com.qiu.controller;

import com.qiu.util.general.CommonResult;
import com.qiu.util.oss.AliyunOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Captain
 */
@Slf4j
@CrossOrigin
@RestController
public class OssController {
    @Autowired
    private AliyunOssUtil ossUtil;

    @RequestMapping("/uploadImage")
    public CommonResult upload(@RequestParam("name") String folderName,
                               @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)) {
                File newFile = new File(fileName);
                try (FileOutputStream os = new FileOutputStream(newFile)) {
                    os.write(file.getBytes());
                    file.transferTo(newFile);
                    String path = ossUtil.upload(folderName, newFile);
                    log.info("文件上传成功，路径：{}", path);
                    return new CommonResult(200, "上传成功", path);
                } catch (Exception e) {
                    log.error("文件上传失败", e);
                    return CommonResult.error("上传失败");
                } finally {
                    if (newFile.exists()) {
                        FileUtils.forceDelete(newFile);
                    }
                }
            }
        }
        return CommonResult.error("文件不存在");
    }
}