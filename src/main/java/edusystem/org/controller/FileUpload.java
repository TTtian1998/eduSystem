package edusystem.org.controller;

import edusystem.org.config.AppConfig;
import edusystem.org.entity.Homework;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 田泽鑫
 * @date 2019/11/13
 */
@RestController
@RequestMapping("/files")
@CrossOrigin(allowCredentials = "true")
public class FileUpload {
    @Autowired
    private AppConfig appConfig;
    public static String urls = " ";
    public static String submitUrl = "";

    @PostMapping("/upload")
    public boolean fileUpload(MultipartFile file) throws IOException {
        if (file.getSize() == 0){
            return false;
        }
        //如果不是图片格式不予处理
//        if (!"image/jpeg".equals(file.getContentType())&&!"image/png".equals(file.getContentType())){
//            return false;
//        }
        System.err.println("文件是否为空 ： " + file.isEmpty());
        System.err.println("文件的大小为 ：" + file.getSize());
        System.err.println("文件的媒体类型为 ： " + file.getContentType());
        System.err.println("文件的名字： " + file.getName());
        System.err.println("文件的originName为： " + file.getOriginalFilename());
        //保存作业附件名称
        urls = file.getOriginalFilename();
        File newFile = new File(appConfig.getUploadFolder() + file.getOriginalFilename());
        file.transferTo(newFile);
        return true;
    }

    @PostMapping("/submitFiles")
    public boolean submitFiles(MultipartFile file) throws IOException {
        if (file.getSize() == 0){
            return false;
        }
        System.err.println("文件是否为空 ： " + file.isEmpty());
        System.err.println("文件的大小为 ：" + file.getSize());
        System.err.println("文件的媒体类型为 ： " + file.getContentType());
        System.err.println("文件的名字： " + file.getName());
        System.err.println("文件的originName为： " + file.getOriginalFilename());

//        //如果不是word格式不予处理
//        if (!"application/msword".equals(file.getContentType())){
//            return false;
//        }

        submitUrl = file.getOriginalFilename();
        File newFile = new File(appConfig.getResumeFolder() + file.getOriginalFilename());
        file.transferTo(newFile);
        return true;
    }
}
