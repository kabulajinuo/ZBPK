package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.common.model.response.PkClassListRes;

import java.util.List;

/**
 * <p>
 * 班级表 Mapper 接口
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-14
 */
public interface PkClassMapper extends BaseMapper<PkClass> {

    List<PkClassListRes> getClassList(PkClassListReq param);

}
