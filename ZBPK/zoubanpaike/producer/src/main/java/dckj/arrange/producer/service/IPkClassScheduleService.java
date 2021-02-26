package dckj.arrange.producer.service;

import com.baomidou.mybatisplus.service.IService;
import dckj.arrange.common.entity.PkClassSchedule;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkClassSchedulereq;
import dckj.arrange.common.model.request.SelectClassSubjectTea;
import dckj.arrange.common.model.response.AllClassScheduleRes;
import dckj.arrange.common.model.response.ClassScheduleRes;

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
public interface IPkClassScheduleService extends IService<PkClassSchedule> {

    List<SelectClassSubjectTea> selectClassSubjectTea(Map<String,Object> map);

    List<SelectClassSubjectTea>  selectTeacherNum(Map<String,Object> map);

    void insertClassSchedule(List<PkClassSchedulereq> pkClassSchedules);

    void deleteClassSchedule(PkClassSchedulereq pkClassSchedule);

    List<PkClassSchedule>selectCurriculumNum(Map<String,Object> map);

    void createClassSchedule(BaseReq param);

    List<PkClassSchedule>selectClassSchedule(Map<String,Object>map);

    AllClassScheduleRes getAllSchedule(BaseReq param);

    List<ClassScheduleRes> getClassSchedule(BaseReq param);


}
