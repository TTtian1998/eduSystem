package edusystem.org.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 田泽鑫
 * @date 2019/11/13
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    // 作业附件在本机上的存储路径
    private String uploadFolder;
    // 作业附件在网页端的访问路径
    private String previewPath;
    // 学生上传的文件在本机上的存储路径
    private String resumeFolder;
    // 学生上传的文件在网页端的访问路径
    private String resumePath;
    public String getUploadFolder() {
        return uploadFolder;
    }

    public void setUploadFolder(String uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }

    public String getResumeFolder() {
        return resumeFolder;
    }

    public void setResumeFolder(String resumeFolder) {
        this.resumeFolder = resumeFolder;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }
}