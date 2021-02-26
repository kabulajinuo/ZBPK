package dckj.arrange.producer.service;

import com.baomidou.mybatisplus.service.IService;
import dckj.arrange.common.entity.PkCurriculumSet;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.response.CurriculumSetRes;

import java.util.List;

/**
 * <p>
 * 课程设置表 服务类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
public interface IPkCurriculumSetService extends IService<PkCurriculumSet> {

    CurriculumSetRes getCurriculumSetList(BaseReq param);

    void setCurriculumSetList(List<PkCurriculumSet> param);
}
