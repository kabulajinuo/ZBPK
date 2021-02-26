package dckj.arrange.modules.feign.fallback;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkClassSchedule;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkClassSchedulereq;
import dckj.arrange.modules.feign.IPkClassScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 总课程表 服务实现类
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-19
 */
@Service
public class PkClassScheduleServiceImpl implements IPkClassScheduleService {


    @Override
    public ApiResult selectClassSubjectTea(Map<String, Object> map) {
        return null;
    }


    @Override
    public ApiResult insertClassSchedule(List<PkClassSchedulereq> pkClassSchedules) {
        return null;
    }

    @Override
    public ApiResult deleteClassSchedule(PkClassSchedulereq pkClassSchedule) {
        return null;
    }

    @Override
    public ApiResult selectClassSchedule(PkClassSchedule pkClassSchedules) {
        return null;
    }

    @Override
    public ApiResult selectCurriculumNum(PkClassSchedule pkClassSchedules) {
        return null;
    }

    @Override
    public ApiResult createClassSchedule(BaseReq param) {
        return null;
    }

    @Override
    public ApiResult getAllSchedule(BaseReq param) {
        return null;
    }

    @Override
    public ApiResult getClassSchedule(BaseReq param) {
        return null;
    }


}
