package dckj.arrange.common.util;

import dckj.arrange.common.config.Global;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class DownloadUtil {

    public static boolean download(HttpServletResponse response, String filename) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            filename = filename + ".xls";
            String path = Global.getDownloadPath() + "/"+filename;

//            String path="D:\\zbpk\\producer\\src\\main\\resources\\template\\download\\教师信息表.xls";
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

//            File cfgFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
            File cfgFile = ResourceUtils.getFile( path);

            inputStream = new FileInputStream(cfgFile);
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            log.info("-----*----"+e.getMessage());
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
                log.info("-----*----"+e.getMessage());
            }
        }
        return true;
    }

}
