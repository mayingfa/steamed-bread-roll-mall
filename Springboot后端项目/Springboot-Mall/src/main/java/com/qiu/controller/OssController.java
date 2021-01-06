package com.qiu.controller;
import com.qiu.util.general.CommonResult;
import com.qiu.util.oss.AliyunOssUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
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
    public CommonResult upload(@RequestParam("name") String folderName, @RequestParam("file") MultipartFile file) {
        String path = null;
        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                if (fileName!=null && !fileName.isEmpty()) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    //把file里的内容复制到奥newFile中
                    file.transferTo(newFile);
                    //图片回显地址:
                    //http://yiyige.oss-cn-hangzhou.aliyuncs.com/images/2019-10-21/6c964702b67d4eeb920e7f1f4358189b-dishu.jpg
                    path = ossUtil.upload(folderName,newFile);
                    logger.info("path=" + path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*返回json 数据*/
        CommonResult commonResult;
        if (path != null){
            commonResult = new CommonResult(200,"上传成功",path);
        }else{
            commonResult = CommonResult.error("上传失败！");
        }
        return commonResult;
    }
}