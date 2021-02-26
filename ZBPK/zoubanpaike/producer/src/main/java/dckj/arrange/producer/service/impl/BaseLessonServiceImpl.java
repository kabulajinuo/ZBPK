package dckj.arrange.producer.service.impl;

import dckj.arrange.common.entity.PkClassSchedule;
import dckj.arrange.common.entity.PkLessonDetail;
import dckj.arrange.common.enums.WeekEnum;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseLessonReq;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.common.model.response.PkClassListRes;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.PkBaseLessonMapper;
import dckj.arrange.producer.mapper.PkClassMapper;
import dckj.arrange.producer.mapper.PkClassScheduleMapper;
import dckj.arrange.producer.mapper.PkLessonDetailMapper;
import dckj.arrange.producer.service.BaseLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BaseLessonServiceImpl implements BaseLessonService {
    @Autowired
    private PkBaseLessonMapper pkBaseLessonMapper;
    @Autowired
    private PkLessonDetailMapper pkLessonDetailMapper;
    @Autowired
    private PkClassScheduleMapper pkClassScheduleMapper;
    @Autowired
    private PkClassMapper pkClassMapper;

    @Override
    public BaseLessonReq toGetLesson(BaseLessonReq baseLessonReq) {
        BaseLessonReq baseLessonReq1 = pkBaseLessonMapper.selectByIdAndSchoolCode(baseLessonReq);
        if (!EmptyUtil.isEmpty(baseLessonReq1)) {
            if (!EmptyUtil.isEmpty(baseLessonReq1.getPkLessonDetailList())) {
                baseLessonReq1.getPkLessonDetailList().stream().forEach(pkLessonDetail -> {
                    pkLessonDetail.setTaskId(baseLessonReq1.getTaskId());
                });
            }
        }
        return baseLessonReq1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toInsertLesson(BaseLessonReq baseLessonReq) {
        if (EmptyUtil.isEmpty(baseLessonReq.getPkLessonDetailList())) {
            throw new CheckedException("请设置课时详情");
        }
        List<PkLessonDetail> pkLessonDetailList = baseLessonReq.getPkLessonDetailList();
        EmptyUtil.validate(pkLessonDetailList);
        pkLessonDetailList.stream().forEach(pkLessonDetail -> {
            pkLessonDetail.setCreateTime(new Date());
        });
        //增加详情
        pkLessonDetailMapper.insertBatch(pkLessonDetailList);
        //增加基础
        baseLessonReq.setCreateTime(new Date());
        pkBaseLessonMapper.insert(baseLessonReq);
        //增加 节此信息 至总课程表
        toInsertLClassSchedule(baseLessonReq);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toUpdateLesson(BaseLessonReq baseLessonReq) {
        if (EmptyUtil.isEmpty(baseLessonReq.getPkLessonDetailList())) {
            throw new CheckedException("请设置课时详情");
        }
        List<PkLessonDetail> pkLessonDetailList = baseLessonReq.getPkLessonDetailList();
        EmptyUtil.validate(pkLessonDetailList);
        //修改详情 先删在添
        pkLessonDetailMapper.deleteByTaskId(baseLessonReq.getTaskId());
        pkLessonDetailList.stream().forEach(pkLessonDetail -> {
            pkLessonDetail.setCreateTime(new Date());
        });
        pkLessonDetailMapper.insertBatch(pkLessonDetailList);
        //修改基础
        baseLessonReq.setUpdateTime(new Date());
        pkBaseLessonMapper.updateById(baseLessonReq);
        //修改总课表
        Map paramMap = new HashMap();
        paramMap.put("task_id",baseLessonReq.getTaskId());
        pkClassScheduleMapper.deleteByMap(paramMap);
        toInsertLClassSchedule(baseLessonReq);
    }

    public void toInsertLClassSchedule(BaseLessonReq baseLessonReq) {
        PkClassListReq pkClassListReq = new PkClassListReq();
        pkClassListReq.setGradeId(baseLessonReq.getGradeId());
        pkClassListReq.setSchoolCode(baseLessonReq.getSchoolCode());
        //获取 年级下所有的 班级
        List<PkClassListRes> classList = pkClassMapper.getClassList(pkClassListReq);
        if(EmptyUtil.isEmpty(classList)){
            throw  new CheckedException("该年级未绑定班级");
        }
        //总共的星期
        int count = baseLessonReq.getEndDay();
        List<PkClassSchedule> paramList = new ArrayList<>();
        //外轮 星期
        for (int i = 1; i <=count; i++) {
            //班级
            for (PkClassListRes pkClassListRes : classList) {
                //节次
                for (PkLessonDetail lessonDetail : baseLessonReq.getPkLessonDetailList()) {
                    //主键 任务ID 星期几 第几节 班级ID 班级名字
                    PkClassSchedule pkClassSchedule = new PkClassSchedule();
                    pkClassSchedule.setId(IDGenerate.buildID());
                    pkClassSchedule.setTaskId(lessonDetail.getTaskId());
                    pkClassSchedule.setWeek(toGetWeekName(i));
                    pkClassSchedule.setSection(lessonDetail.getLessonName());
                    pkClassSchedule.setClassId(pkClassListRes.getId());
                    pkClassSchedule.setClassName(pkClassListRes.getClassName());
                    pkClassSchedule.setCreateTime(new Date());
                    pkClassSchedule.setIsPreinstall(0);
                    paramList.add(pkClassSchedule);
                }
            }
        }
        pkClassScheduleMapper.inertBatch(paramList);

    }

    public String toGetWeekName(int week) {
        String weekName = "";
        switch (week) {
            case 1:
                weekName = WeekEnum.Monday.getValue();
                break;
            case 2:
                weekName = WeekEnum.Tuesday.getValue();
                break;
            case 3:
                weekName = WeekEnum.Wednesday.getValue();
                break;
            case 4:
                weekName = WeekEnum.Thursday.getValue();
                break;
            case 5:
                weekName = WeekEnum.Friday.getValue();
                break;
            case 6:
                weekName = WeekEnum.Saturday.getValue();
                break;
            case 7:
                weekName = WeekEnum.Sunday.getValue();
                break;
            default:
                weekName = "无";
        }
        return weekName;
    }
}
