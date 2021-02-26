package dckj.arrange.common.util.poi;

import org.apache.poi.util.IOUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BaseDownloadUtil {


	public static boolean download(HttpServletResponse response, String filename) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			filename = filename + ".xls";
			String path = "template/" + filename;

			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.addHeader("charset", "utf-8");
			response.addHeader("Pragma", "no-cache");
			String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

			File cfgFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);

			inputStream = new FileInputStream(cfgFile);
			servletOutputStream = response.getOutputStream();
			IOUtils.copy(inputStream, servletOutputStream);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		return true;
	}

}
