package dckj.arrange.producer.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import dckj.arrange.common.entity.*;
import dckj.arrange.common.enums.DayEnum;
import dckj.arrange.common.enums.SectionEnum;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.*;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.common.model.request.PkClassSchedulereq;
import dckj.arrange.common.model.request.SelectClassSubjectTea;
import dckj.arrange.common.model.response.AllClassScheduleRes;
import dckj.arrange.common.model.response.ClassScheduleRes;
import dckj.arrange.common.model.response.PkClassListRes;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.mapper.*;
import dckj.arrange.producer.service.IPkClassScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 总课程表 服务实现类
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-19
 */
@Service
@Slf4j
public class PkClassScheduleServiceImpl extends ServiceImpl<PkClassScheduleMapper, PkClassSchedule> implements IPkClassScheduleService {

    @Autowired
    private PkClassScheduleMapper pkClassScheduleMapper;

    @Autowired
    private PkCurriculumPresetMapper pkCurriculumPresetMapper;

    @Autowired
    private PkClassMapper pkClassMapper;

    @Autowired
    private PkClassRoomMapper pkClassRoomMapper;

    @Autowired
    private PkBaseLessonMapper pkBaseLessonMapper;

    @Autowired
    private PkTeachingTaskMapper pkTeachingTaskMapper;

    @Autowired
    private PkTeacherClassCopyMapper pkTeacherClassCopyMapper;


    @Override
    public List<SelectClassSubjectTea> selectClassSubjectTea(Map<String, Object> map) {
            List<SelectClassSubjectTea> selectClassSubjectTeas = pkClassScheduleMapper.selectClassSubjectTea(map);
            return selectClassSubjectTeas;
    }

    @Override
    public List<SelectClassSubjectTea> selectTeacherNum(Map<String, Object> map) {
        List<SelectClassSubjectTea> selectClassSubjectTeas = pkClassScheduleMapper.selectTeacherNum(map);
        return selectClassSubjectTeas;
    }

    @Transactional
    @Override
    public void insertClassSchedule(List<PkClassSchedulereq> pkClassSchedule){
        for (PkClassSchedulereq pkClassSchedulereq : pkClassSchedule) {
            PkClassSchedule p=new PkClassSchedule();
            PkClassSchedule p1=new PkClassSchedule();
            p1.setTaskId(pkClassSchedulereq.getTaskId());
            p1.setWeek(pkClassSchedulereq.getWeek());
            p1.setSection(pkClassSchedulereq.getSection());
            p1.setClassId(pkClassSchedulereq.getClassId());
            p1.setClassName(pkClassSchedulereq.getClassName());

//            查询教室场地
            PkClassRoom pkClassRoom=new PkClassRoom();
            pkClassRoom.setTaskId(p1.getTaskId());
            pkClassRoom.setClassId(p1.getClassId());
            PkClassRoom pkClassRoom1 = pkClassRoomMapper.selectOne(pkClassRoom);

            p.setTaskId(pkClassSchedulereq.getTaskId());
            p.setWeek(pkClassSchedulereq.getWeek());
            p.setSection(pkClassSchedulereq.getSection());
            p.setClassId(pkClassSchedulereq.getClassId());
            p.setClassName(pkClassSchedulereq.getClassName());
            p.setSubjectId(pkClassSchedulereq.getSubjectId());
            p.setSubjectName(pkClassSchedulereq.getSubjectName());
            p.setTeacherId(pkClassSchedulereq.getTeacherId());
            p.setTeacherName(pkClassSchedulereq.getTeacherName());

            p.setSiteId(pkClassRoom1.getSiteId());
            p.setSiteName(pkClassRoom1.getSiteName());
            p.setRoomName(pkClassRoom1.getRoomName());
            p.setRoomId(pkClassRoom1.getRoomId());

            p.setIsPreinstall(1);
            p.setUpdateTime(new Date());
            PkClassSchedule pkClassSchedule1 = pkClassScheduleMapper.selectOne(p1);
            p.setId(pkClassSchedule1.getId());
            pkClassScheduleMapper.updateById(p);
            for (PkCurriculumPreset pkCurriculumPreset : pkClassSchedulereq.getPkList()) {
                PkCurriculumPreset preset= new PkCurriculumPreset();
                preset.setId(pkCurriculumPreset.getId());
                preset.setpCountClassSkNum(pkCurriculumPreset.getpCountClassSkNum());
                preset.setpTeacheaNum(pkCurriculumPreset.getTeacheaNum());
                preset.setUpdateTime(new Date());
                pkCurriculumPresetMapper.updateById(preset);
            }
        }
    }


    @Transactional
    @Override
    public void deleteClassSchedule(PkClassSchedulereq pk) {
        pkClassScheduleMapper.deleteById(pk.getId());
        for (PkCurriculumPreset pkCurriculumPreset : pk.getPkList()) {
            PkCurriculumPreset preset= new PkCurriculumPreset();
            preset.setId(pkCurriculumPreset.getId());
            preset.setpCountClassSkNum(pkCurriculumPreset.getpCountClassSkNum());
            preset.setpTeacheaNum(pkCurriculumPreset.getTeacheaNum());
            pkCurriculumPresetMapper.updateById(pkCurriculumPreset);
        }
    }

    @Override
    public List<PkClassSchedule> selectCurriculumNum(Map<String, Object> map) {
        List<PkClassSchedule> pkClassSchedules = pkClassScheduleMapper.selectCurriculumNum(map);
        return pkClassSchedules;
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createClassSchedule(BaseReq param) {
        //获取当前年级下所有班级以及行政班教室
        List<PkClassRoomDto> pcrdList = pkClassRoomMapper.getClassRoomList(param);
        if (EmptyUtil.isEmpty(pcrdList)) {
            throw new CheckedException("教室班级未设置！");
        }

        PkBaseLesson pbl = new PkBaseLesson();
        pbl.setTaskId(param.getTaskId());
        //查询课时设置信息
        pbl = pkBaseLessonMapper.selectOne(pbl);
        if (EmptyUtil.isEmpty(pbl)) {
            throw new CheckedException("课时还未设置!");
        }
        //开始排课
        startPaike(pcrdList, pbl, param);



    }

    @Override
    public List<PkClassSchedule> selectClassSchedule(Map<String, Object> map) {
        List<PkClassSchedule> pkClassSchedules = pkClassScheduleMapper.selectClassSchedule(map);
        return pkClassSchedules;
    }

    /**
     * 获取总课表
     * @param param
     * @return
     */
    @Override
    public AllClassScheduleRes getAllSchedule(BaseReq param) {
        AllClassScheduleRes acsr = new AllClassScheduleRes();
        List<ClassDto> cdList = pkClassRoomMapper.getCdList(param);
        if (EmptyUtil.isEmpty(cdList)) {
            throw new CheckedException("排课班级为空！");
        }
        for (ClassDto cd:cdList
             ) {
            PkBaseLesson pbl = new PkBaseLesson();
            pbl.setTaskId(param.getTaskId());
            pbl = pkBaseLessonMapper.selectOne(pbl);

            Integer endDay = pbl.getEndDay();

            //上午课节
            Integer morning = pbl.getMorning();
            //下午课节
            Integer afternoon = pbl.getAfternoon();

            List<DayDto> ddList = new ArrayList<>();
            for (int i = 1; i <= endDay; i++) {
                DayDto dd = new DayDto();
                dd.setDayNum(i);
                dd.setDay(DayEnum.parse(i).getMessage());
                ddList.add(dd);

                List<SectionDto> sdList = new ArrayList<>();

                //上午
                for (int j = 1; j < morning; j++) {
                    SectionDto sd = new SectionDto();
                    sd.setSectionNum(j);
                    sd.setSection(SectionEnum.parse(j).getMessage());

                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(i).getMessage());
                    pcs.setSection(SectionEnum.parse(j).getMessage());
                    pcs.setClassId(cd.getClassId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
                    String content = "";
                    if (!EmptyUtil.isEmpty(ptc)) {
                        content = ptc.getSubjectName() + " - " + ptc.getTeacherName() + "\n" + "(" + ptc.getSiteName() + " " + ptc.getRoomName() + ")";
                    }
                    sd.setContent(content);
                    sdList.add(sd);
                }

                //下午
                for (int j = 1; j <= afternoon; j++) {
                    SectionDto sd = new SectionDto();
                    sd.setSectionNum(j + morning);
                    sd.setSection(SectionEnum.parse(j + morning).getMessage());

                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(i).getMessage());
                    pcs.setSection(SectionEnum.parse(j + morning).getMessage());
                    pcs.setClassId(cd.getClassId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
                    String content = "";
                    if (!EmptyUtil.isEmpty(ptc)) {
                        content = ptc.getSubjectName() + " - " + ptc.getTeacherName() + "\n" + "(" + ptc.getSiteName() + " " + ptc.getRoomName() + ")";
                    }
                    sd.setContent(content);
                    sdList.add(sd);
                }

                dd.setSdList(sdList);

            }

            cd.setDdList(ddList);

        }

        acsr.setCdList(cdList);

        return acsr;
    }

    @Override
    public List<ClassScheduleRes> getClassSchedule(BaseReq param) {

        List<ClassScheduleRes> csrList = new ArrayList<>();

        PkBaseLesson pbl = new PkBaseLesson();
        pbl.setTaskId(param.getTaskId());
        pbl = pkBaseLessonMapper.selectOne(pbl);

        Integer endDay = pbl.getEndDay();

        //上午课节
        Integer morning = pbl.getMorning();
        //下午课节
        Integer afternoon = pbl.getAfternoon();

        for (int i = 1; i <= morning; i++) {
            ClassScheduleRes csr = new ClassScheduleRes();
            csr.setSection(SectionEnum.parse(i).getMessage());
            csr.setSectionNum(i);

            List<ClassDayDto> cddList = new ArrayList<>();
            for (int j = 1; j <= endDay; j++) {
                ClassDayDto cdd = new ClassDayDto();
                cdd.setDayNum(j);
                cdd.setDay(DayEnum.parse(j).getMessage());

                if (!EmptyUtil.isEmpty(param.getClassId())) {
                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(j).getMessage());
                    pcs.setSection(SectionEnum.parse(i).getMessage());
                    pcs.setClassId(param.getClassId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);

                    cdd.setContent(ptc.getSubjectName() + "-" + ptc.getTeacherName() + "\n(" + ptc.getSiteName() + " " + ptc.getRoomName() + ")");
                }else if (!EmptyUtil.isEmpty(param.getRoomId())) {
                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(j).getMessage());
                    pcs.setSection(SectionEnum.parse(i).getMessage());
                    pcs.setRoomId(param.getRoomId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
                    cdd.setContent(ptc.getSubjectName() + "-" + ptc.getTeacherName() + "\n" + ptc.getClassId());
                }else if (!EmptyUtil.isEmpty(param.getSubjectId()) && !EmptyUtil.isEmpty(param.getTeacherId())) {
                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(j).getMessage());
                    pcs.setSection(SectionEnum.parse(i).getMessage());
                    pcs.setSubjectId(param.getSubjectId());
                    pcs.setTeacherId(param.getTeacherId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
                    cdd.setContent(ptc.getSubjectName() + "-" + ptc.getClassName() + "\n(" + ptc.getSiteName() + " " + ptc.getRoomName() + ")");
                }

                cddList.add(cdd);

            }
            csr.setCddList(cddList);

            csrList.add(csr);
        }

        for (int i = 1; i <= afternoon; i++) {
            ClassScheduleRes csr = new ClassScheduleRes();
            csr.setSection(SectionEnum.parse(i + afternoon).getMessage());
            csr.setSectionNum(i + afternoon);

            List<ClassDayDto> cddList = new ArrayList<>();
            for (int j = 1; j <= endDay; j++) {
                ClassDayDto cdd = new ClassDayDto();
                cdd.setDayNum(j);
                cdd.setDay(DayEnum.parse(j).getMessage());
                if (!EmptyUtil.isEmpty(param.getClassId())) {
                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(j).getMessage());
                    pcs.setSection(SectionEnum.parse(i + afternoon).getMessage());
                    pcs.setClassId(param.getClassId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);

                    cdd.setContent(ptc.getSubjectName() + "-" + ptc.getTeacherName() + "\n(" + ptc.getSiteName() + " " + ptc.getRoomName() + ")");
                }else if (!EmptyUtil.isEmpty(param.getRoomId())) {
                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(j).getMessage());
                    pcs.setSection(SectionEnum.parse(i + afternoon).getMessage());
                    pcs.setRoomId(param.getRoomId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
                    cdd.setContent(ptc.getSubjectName() + "-" + ptc.getTeacherName() + "\n" + ptc.getClassId());
                }else if (!EmptyUtil.isEmpty(param.getSubjectId()) && !EmptyUtil.isEmpty(param.getTeacherId())) {
                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setTaskId(param.getTaskId());
                    pcs.setWeek(DayEnum.parse(j).getMessage());
                    pcs.setSection(SectionEnum.parse(i + afternoon).getMessage());
                    pcs.setSubjectId(param.getSubjectId());
                    pcs.setTeacherId(param.getTeacherId());

                    //查询已排课程
                    PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
                    cdd.setContent(ptc.getSubjectName() + "-" + ptc.getClassName() + "\n(" + ptc.getSiteName() + " " + ptc.getRoomName() + ")");
                }

                cddList.add(cdd);

            }
            csr.setCddList(cddList);
            csrList.add(csr);
        }

        return csrList;
    }

    /**
     * 开始排课
     * @param pcrdList
     * @param pbl
     * @param param
     */
    private void startPaike(List<PkClassRoomDto> pcrdList,PkBaseLesson pbl, BaseReq param) {

        log.info("**************开始排课***************");
        //开始周期
        Integer startDay = pbl.getStartDay();
        //结束周期
        Integer endDay = pbl.getEndDay();
        //上午课节
        Integer morning = pbl.getMorning();
        //下午课节
        Integer afternoon = pbl.getAfternoon();



        //按班级排课
        for (PkClassRoomDto pcrd:pcrdList
        ) {

            //从周期的第一天开始排
            for (int i = 1; i <= endDay; i++) {
                Boolean returnTo = false;

                //从上午开始排
                for (int j = 1; j <= morning; j++) {
                    //查询课表(未预设课程)
                    List<PkClassSchedule> pcsList = pkClassScheduleMapper.selectList(new EntityWrapper<PkClassSchedule>()
                            .eq("task_id",param.getTaskId()).eq("class_id",pcrd.getClassId())
                            .eq("week", DayEnum.parse(i).getMessage()).eq("section", SectionEnum.parse(j).getMessage())
                            .eq("is_preinstall", 0));

                    //是否已排
                    if (EmptyUtil.isEmpty(pcsList)) {
                        continue;
                    }
                    param.setClassId(pcrd.getClassId());
                    //获取该班级所有上课老师，课程，教室
                    param.setLevel(1);
                    List<TeacherClassDto> importanTcd = pkTeacherClassCopyMapper.getTeacherClassList(param);    //高
                    if (!EmptyUtil.isEmpty(importanTcd)) {
                        // shuffle 打乱顺序
                        Collections.shuffle(importanTcd);
                        //处理高排课
                        Boolean isImportanPreinstall = disposeImportan(importanTcd, param, i, j, pcrd);
                        if (isImportanPreinstall) {
                            continue;
                        }
                    }else {
                        param.setLevel(2);
                        List<TeacherClassDto> middleTcd = pkTeacherClassCopyMapper.getTeacherClassList(param);      //中
                        if (!EmptyUtil.isEmpty(middleTcd)) {
                            // shuffle 打乱顺序
                            Collections.shuffle(middleTcd);
                            //处理中排课
                            Boolean isMiddlePreinstall = disposeMiddle(middleTcd, param, i, j, pcrd);
                            if (isMiddlePreinstall) {
                                continue;
                            }
                        }else {
                            param.setLevel(3);
                            List<TeacherClassDto> lowTcd = pkTeacherClassCopyMapper.getTeacherClassList(param);         //低
                            if (EmptyUtil.isEmpty(lowTcd)) {
                                continue;
                            }
                            // shuffle 打乱顺序
                            Collections.shuffle(lowTcd);
                            //处理低排课
                            Boolean isLowPreinstall = disposeMiddle(lowTcd, param, i, j, pcrd);
                            if (isLowPreinstall) {
                                returnTo = true;
                                break;
                            }
                        }

                    }

                }


                //排下午
                for (int j = 1; j <= afternoon; j++) {

                    //查询课表(未预设课程)
                    List<PkClassSchedule> pcsList = pkClassScheduleMapper.selectList(new EntityWrapper<PkClassSchedule>()
                            .eq("task_id",param.getTaskId()).eq("class_id",pcrd.getClassId())
                            .eq("week", DayEnum.parse(i).getMessage()).eq("section", SectionEnum.parse(j + morning).getMessage())
                            .eq("is_preinstall", 0));

                    if (EmptyUtil.isEmpty(pcsList)) {
                        continue;
                    }
                    param.setClassId(pcrd.getClassId());
                    //获取该班级所有上课老师，课程，教室
                    param.setLevel(2);
                    List<TeacherClassDto> middleTcd = pkTeacherClassCopyMapper.getTeacherClassList(param);      //中
                    if (!EmptyUtil.isEmpty(middleTcd)) {
                        // shuffle 打乱顺序
                        Collections.shuffle(middleTcd);
                        //处理中排课
                        Boolean isMiddlePreinstall = disposeMiddle(middleTcd, param, i, j + morning, pcrd);
                        if (isMiddlePreinstall) {
                            continue;
                        }
                    }else {
                        param.setLevel(3);
                        List<TeacherClassDto> lowTcd = pkTeacherClassCopyMapper.getTeacherClassList(param);         //低
                        if (EmptyUtil.isEmpty(lowTcd)) {
                            continue;
                        }
                        // shuffle 打乱顺序
                        Collections.shuffle(lowTcd);
                        //处理低排课
                        Boolean isLowPreinstall = disposeMiddle(lowTcd, param, i, j + morning, pcrd);
                        if (isLowPreinstall) {
                            returnTo = true;
                            break;
                        }

                    }

                }

                if (returnTo) {
                    continue;
                }
            }
        }




    }



    /**
     * 处理中课程
     * @param middleTcd
     * @param param
     * @param i
     * @param j
     * @param pcrd
     */
    private Boolean disposeMiddle(List<TeacherClassDto> middleTcd, BaseReq param, int i, int j, PkClassRoomDto pcrd) {

        for (TeacherClassDto tcd:middleTcd
             ) {

            //每周课程数
            Integer mustNumber = tcd.getMustNumber() + tcd.getChoiceNumber();

            //查询该老师已排课程
            List<PkClassSchedule> ptcList = pkClassScheduleMapper.selectList(new EntityWrapper<PkClassSchedule>()
                    .eq("task_id",param.getTaskId()).eq("is_preinstall", 1).eq("teacher_id", tcd.getTeacherId()));

            if (!EmptyUtil.isEmpty(ptcList)) {
                //剩下课程数
                mustNumber = mustNumber - ptcList.size();
            }

            //课程是否排完
            if (mustNumber <= 0) {
                continue;
            }

            PkClassSchedule pcs = new PkClassSchedule();
            pcs.setUpdateTime(new Date());
            pcs.setIsPreinstall(1);
            pcs.setRoomId(tcd.getRoomId());
            pcs.setRoomName(tcd.getRoomName());
            pcs.setSiteId(tcd.getSiteId());
            pcs.setSiteName(tcd.getSiteName());
            pcs.setSubjectId(tcd.getSubjectId());
            pcs.setSubjectName(tcd.getSubjectName());
            pcs.setTeacherId(tcd.getTeacherId());
            pcs.setTeacherName(tcd.getTeacherName());
            pkClassScheduleMapper.update(pcs, new EntityWrapper<PkClassSchedule>().eq("task_id",param.getTaskId()).eq("class_id", pcrd.getClassId())
                    .eq("week", DayEnum.parse(i).getMessage()).eq("section", SectionEnum.parse(j).getMessage()));

            pkTeacherClassCopyMapper.delete(new EntityWrapper<PkTeacherClassCopy>().eq("task_id",param.getTaskId()).eq("class_id", pcrd.getClassId()).eq("teacher_id", tcd.getTeacherId()));

            break;
        }

        PkClassSchedule pcs = new PkClassSchedule();
        pcs.setTaskId(param.getTaskId());
        pcs.setWeek(DayEnum.parse(i).getMessage());
        pcs.setSection(SectionEnum.parse(j).getMessage());
        pcs.setClassId(pcrd.getClassId());

        //查询已排课程
        PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
        if (ptc.getIsPreinstall() == 1) {
            return true;
        }else {
            return false;
        }


    }

    /**
     * 处理重要科目
     * @param importanTcd
     * @param param
     * @param i
     * @param j
     * @param pcrd
     */
    private Boolean disposeImportan(List<TeacherClassDto> importanTcd, BaseReq param, int i, int j, PkClassRoomDto pcrd) {
        //处理高的课程
        for (TeacherClassDto tcd:importanTcd
        ) {

            //跳转至下一科目
            Boolean next = false;

            //连堂次数
            Integer continuouNum = tcd.getContinuouNum();
            //每周课程数
            Integer mustNumber = tcd.getMustNumber();

            //查询该老师已排课程
            List<PkClassSchedule> ptcList = pkClassScheduleMapper.selectList(new EntityWrapper<PkClassSchedule>()
                    .eq("task_id",param.getTaskId()).eq("is_preinstall", 1).eq("teacher_id", tcd.getTeacherId()));

            if (!EmptyUtil.isEmpty(ptcList)) {
                //剩下课程数
                mustNumber = mustNumber - ptcList.size();
            }

            //课程是否排完
            if (mustNumber <= 0) {
                continue;
            }


            //处理连堂
            if (tcd.getContinuouNum() >= 1 ) {
                //连堂课程数
                Integer liantangNum = continuouNum * 2;

                for (int k = 1; k <= liantangNum; k++) {
                    //只处理当前一天的课程
                    if (k >= 3) {
                        break;
                    }
                    PkClassSchedule pcs = new PkClassSchedule();
                    pcs.setUpdateTime(new Date());
                    pcs.setIsPreinstall(1);
                    pcs.setRoomId(tcd.getRoomId());
                    pcs.setRoomName(tcd.getRoomName());
                    pcs.setSiteId(tcd.getSiteId());
                    pcs.setSiteName(tcd.getSiteName());
                    pcs.setSubjectId(tcd.getSubjectId());
                    pcs.setSubjectName(tcd.getSubjectName());
                    pcs.setTeacherId(tcd.getTeacherId());
                    pcs.setTeacherName(tcd.getTeacherName());
                    if (k % 2 ==1) {
                        pkClassScheduleMapper.update(pcs, new EntityWrapper<PkClassSchedule>().eq("task_id",param.getTaskId()).eq("class_id", pcrd.getClassId())
                                .eq("week", DayEnum.parse((k + 1)/ 2).getMessage()).eq("section", SectionEnum.parse(1).getMessage()));
                    }else {
                        pkClassScheduleMapper.update(pcs, new EntityWrapper<PkClassSchedule>().eq("task_id",param.getTaskId()).eq("class_id", pcrd.getClassId())
                                .eq("week", DayEnum.parse(k / 2).getMessage()).eq("section", SectionEnum.parse(2).getMessage()));
                    }


                    mustNumber --;

                }

                continuouNum --;

                PkTeachingTask ptt = new PkTeachingTask();
                ptt.setContinuouNum(continuouNum);

                pkTeachingTaskMapper.update(ptt, new EntityWrapper<PkTeachingTask>().eq("task_id",param.getTaskId()).eq("subject_id", tcd.getSubjectId())
                .eq("teacher_id", tcd.getTeacherId()));

                pkTeacherClassCopyMapper.delete(new EntityWrapper<PkTeacherClassCopy>().eq("task_id",param.getTaskId()).eq("class_id", pcrd.getClassId()).eq("teacher_id", tcd.getTeacherId()));

                break;
                //--------------重要科目连堂处理完-------------

            }else {
                if (j != 1) {
                    PkClassSchedule pcdle = new PkClassSchedule();
                    pcdle.setClassId(pcrd.getClassId());
                    pcdle.setTaskId(param.getTaskId());
                    pcdle.setWeek(DayEnum.parse(i).getMessage());
                    pcdle.setSection(SectionEnum.parse(j-1).getMessage());
                    PkClassSchedule pcse = pkClassScheduleMapper.selectOne(pcdle);
                    if (pcse.getSubjectId().equals(tcd.getSubjectId())) {
                        next = true;
                        break;
                    }
                }


                PkClassSchedule pcs = new PkClassSchedule();
                pcs.setUpdateTime(new Date());
                pcs.setIsPreinstall(1);
                pcs.setRoomId(tcd.getRoomId());
                pcs.setRoomName(tcd.getRoomName());
                pcs.setSiteId(tcd.getSiteId());
                pcs.setSiteName(tcd.getSiteName());
                pcs.setSubjectId(tcd.getSubjectId());
                pcs.setSubjectName(tcd.getSubjectName());
                pcs.setTeacherId(tcd.getTeacherId());
                pcs.setTeacherName(tcd.getTeacherName());
                pkClassScheduleMapper.update(pcs, new EntityWrapper<PkClassSchedule>().eq("task_id",param.getTaskId()).eq("class_id", pcrd.getClassId())
                        .eq("week", DayEnum.parse(i).getMessage()).eq("section", SectionEnum.parse(j).getMessage()));

                pkTeacherClassCopyMapper.delete(new EntityWrapper<PkTeacherClassCopy>().eq("task_id",param.getTaskId()).eq("class_id", pcrd.getClassId()).eq("teacher_id", tcd.getTeacherId()));

                mustNumber --;
                break;

                //--------------当前周期节次重要科目处理完-------------

            }


        }

        PkClassSchedule pcs = new PkClassSchedule();
        pcs.setTaskId(param.getTaskId());
        pcs.setWeek(DayEnum.parse(i).getMessage());
        pcs.setSection(SectionEnum.parse(j).getMessage());
        pcs.setClassId(pcrd.getClassId());

        //查询已排课程
        PkClassSchedule ptc = pkClassScheduleMapper.selectOne(pcs);
        if (ptc.getIsPreinstall() == 1) {
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");

        // shuffle 打乱顺序
        Collections.shuffle(list);
        for (String s:list
             ) {
            System.out.println(s);
        }
    }

    /**
     * 获取当前年级下所有班级
     * @param param
     */
    private List<PkClassListRes> getClassList(BaseReq param) {

        PkClassListReq pclr = new PkClassListReq();
        pclr.setGradeId(param.getGradeId());
        pclr.setSchoolCode(param.getSchoolCode());
        List<PkClassListRes> pcList = pkClassMapper.getClassList(pclr);
        if (EmptyUtil.isEmpty(pcList)) {
            throw new CheckedException("该年级下没有班级！");
        }
        return pcList;
    }

}
