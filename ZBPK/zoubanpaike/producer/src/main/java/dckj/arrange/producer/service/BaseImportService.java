package dckj.arrange.producer.service;

import org.springframework.web.multipart.MultipartFile;

public interface BaseImportService {

    StringBuffer importData(MultipartFile file, String taskId, String schoolCode);
}
