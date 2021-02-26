package dckj.arrange.producer.service;

import com.baomidou.mybatisplus.service.IService;
import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.common.model.response.PkClassListRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 班级表 服务类
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-14
 */
public interface IPkClassService extends IService<PkClass> {

    void saveClass(PkClass param);

    List<PkClassListRes> getClassList(PkClassListReq param);

    void updateClass(PkClass param);

    void delClass(PkClass param);

    String toGetClassId(String className,String schoolCode);

    StringBuffer importClass(MultipartFile file, String schoolCode);
}
