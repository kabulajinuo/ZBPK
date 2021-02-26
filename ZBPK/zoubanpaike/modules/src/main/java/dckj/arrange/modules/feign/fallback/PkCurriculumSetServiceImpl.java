package dckj.arrange.modules.feign.fallback;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkCurriculumSet;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.modules.feign.IPkCurriculumSetService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程设置表 服务实现类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@Service
public class PkCurriculumSetServiceImpl implements IPkCurriculumSetService {

    @Override
    public ApiResult getCurriculumSetList(BaseReq param) {
        return null;
    }

    @Override
    public ApiResult setCurriculumSetList(List<PkCurriculumSet> param) {
        return null;
    }
}
