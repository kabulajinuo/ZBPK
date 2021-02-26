package dckj.arrange.modules.feign;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkClassSchedule;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkClassSchedulereq;
import dckj.arrange.modules.feign.fallback.PkClassScheduleServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 总课程表 服务类
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-19
 */
@FeignClient(name = "zbpk-producer-service", fallback = PkClassScheduleServiceImpl.class)
public interface IPkClassScheduleService {

    @RequestMapping(value = "/classSchedule/selectClassSubjectTea")
    ApiResult selectClassSubjectTea(Map<String, Object> map);

    @RequestMapping(value = "/classSchedule/insertClassSchedule")
    ApiResult insertClassSchedule(List<PkClassSchedulereq> pkClassSchedules);

    @RequestMapping(value = "/classSchedule/deleteClassSchedule")
    ApiResult deleteClassSchedule(PkClassSchedulereq pkClassSchedule);

    @RequestMapping(value = "/classSchedule/selectClassSchedule")
    ApiResult selectClassSchedule(PkClassSchedule pkClassSchedules);


    @RequestMapping(value = "/classSchedule/selectCurriculumNum")
    ApiResult selectCurriculumNum(PkClassSchedule pkClassSchedules);

    @RequestMapping(value = "/classSchedule/createClassSchedule")
    ApiResult createClassSchedule(@RequestBody BaseReq param);

    @RequestMapping(value = "/classSchedule/getAllSchedule")
    ApiResult getAllSchedule(@RequestBody BaseReq param);

    @RequestMapping(value = "/classSchedule/getClassSchedule")
    ApiResult getClassSchedule(@RequestBody BaseReq param);

}
