package com.qiu.controller;

import com.qiu.util.general.CommonResult;
import com.qiu.util.oss.AliyunOssUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@CrossOrigin
@Controller
public class OssController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AliyunOssUtil ossUtil;

    public OssController(AliyunOssUtil ossUtil) {
        this.ossUtil = ossUtil;
    }

    @RequestMapping("/uploadImage")
    @ResponseBody
    public CommonResult upload(@RequestParam("name") String folderName, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)) {
                File newFile = new File(fileName);
                try (FileOutputStream os = new FileOutputStream(newFile)) {
                    os.write(file.getBytes());
                    // 把file里的内容复制到奥newFile中
                    file.transferTo(newFile);
                    // 图片回显地址: http://yiyige.oss-cn-hangzhou.aliyuncs.com/images/a.jpg
                    String path = ossUtil.upload(folderName, newFile);
                    logger.info("path:{}", path);
                    return new CommonResult(200, "上传成功", path);
                } catch (Exception e) {
                    e.printStackTrace();
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