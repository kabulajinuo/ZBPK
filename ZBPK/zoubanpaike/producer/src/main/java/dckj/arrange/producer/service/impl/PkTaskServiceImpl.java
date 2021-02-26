package dckj.arrange.producer.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import dckj.arrange.common.entity.*;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.PkTaskCurriculumSetVo;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.*;
import dckj.arrange.producer.service.IPkClassService;
import dckj.arrange.producer.service.IPkTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 排课任务表 服务实现类
 * </p>
 *
 * @author jiangyong
 * @since 2019-08-17
 */
@Service
@Slf4j
public class PkTaskServiceImpl extends ServiceImpl<PkTaskMapper, PkTask> implements IPkTaskService {

    @Autowired
    private PkTaskMapper pkTaskMapper;

    @Autowired
    private PkCurriculumSetMapper pkCurriculumSetMapper;

    @Autowired
    private PkSubjectMapper pkSubjectMapper;

    @Autowired
    private PkBaseLessonMapper pkBaseLessonMapper;

    @Autowired
    private PkLessonDetailMapper pkLessonDetailMapper;

    @Autowired
    private IPkClassService iPkClassService;

    @Autowired
    private PkClassRoomMapper roomMapper;

    @Autowired
    private PkClassMapper pkClassMapper;


    /**
    * @Description:    创建排课任务
    * @Author:         jiangyong
    * @CreateDate:     2019/8/17 16:19
    * @UpdateUser:     jiangyong
    * @UpdateDate:     2019/8/17 16:19
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Transactional
    public void   InsertPkTask(PkTaskCurriculumSetVo pkTaskCurriculumSetVo){
            //添加排课任务
            PkTask pkTask= new PkTask();
            pkTask.setId(IDGenerate.buildID());
            pkTask.setTaskName(pkTaskCurriculumSetVo.getTaskName());
            pkTask.setGradeId(pkTaskCurriculumSetVo.getGradeId());
            pkTask.setGradeName(pkTaskCurriculumSetVo.getGradeName());
            int gradeIdCount = iPkClassService.selectCount(new EntityWrapper<PkClass>().eq("grade_id", pkTaskCurriculumSetVo.getGradeId()));
            Map<String,Object> objectMap =new HashMap<>();
            objectMap.put("grade_id", pkTaskCurriculumSetVo.getGradeId());
            List<PkClass> pkClasses = pkClassMapper.selectByMap(objectMap);
            for (PkClass pkClass : pkClasses) {
                PkClassRoom pkClassRoom=new PkClassRoom();
                pkClassRoom.setId(IDGenerate.buildID());
                pkClassRoom.setTaskId(pkTask.getId());
                pkClassRoom.setClassId(pkClass.getId());
                roomMapper.insert(pkClassRoom);
            }
            pkTask.setClassNumber(gradeIdCount);
            pkTask.setSchoolYear(pkTaskCurriculumSetVo.getSchoolYear());
            pkTask.setSemesterType(pkTaskCurriculumSetVo.getSemesterType());
            pkTask.setPkType(pkTaskCurriculumSetVo.getPkType());
            pkTask.setXkMode(pkTaskCurriculumSetVo.getXkMode());
            pkTask.setSchoolCode(pkTaskCurriculumSetVo.getSchoolCode());
            pkTask.setType(0);
            pkTask.setUserId(pkTaskCurriculumSetVo.getUserId());
            pkTask.setCreateTime(new Date());
            Map<String,Object> map=new HashMap<>();
            map.put("school_code",pkTaskCurriculumSetVo.getSchoolCode());
            //查询学校下的年级
            List<PkSubject> pkSubjects = pkSubjectMapper.selectByMap(map);
            if(pkSubjects.size()==0){
                throw new CheckedException("输入学校编号查询不到学校所设科目信息");
            }else {
                for (PkSubject pkSubject:pkSubjects) {
                    //默认课程设置
                    PkCurriculumSet pkCurriculumSet=new PkCurriculumSet();
                    pkCurriculumSet.setId(IDGenerate.buildID());
                    pkCurriculumSet.setSubjectId(pkSubject.getSubjectId());
//                    是否包含外语科目
                    String subjectName =pkSubject.getSubjectName();
                    boolean flag = subjectName.contains("外语");
                    if(
                            pkSubject.getSubjectName().equals("物理") ||
                            pkSubject.getSubjectName().equals("化学") ||
                            pkSubject.getSubjectName().equals("生物") ||
                            pkSubject.getSubjectName().equals("历史") ||
                            pkSubject.getSubjectName().equals("政治") ||
                            pkSubject.getSubjectName().equals("地理") ||
                            pkSubject.getSubjectName().equals("日语")){
                            pkCurriculumSet.setCurriculumType(2);
                        }else {
                            pkCurriculumSet.setCurriculumType(1);
                        }
                        pkCurriculumSet.setMustNumber(1);
                        pkCurriculumSet.setGradeId(pkTaskCurriculumSetVo.getGradeId());
                        pkCurriculumSet.setChoiceNumber(1);
                        pkCurriculumSet.setLevel(2);
                        pkCurriculumSet.setTaskId(pkTask.getId());
                        pkCurriculumSet.setCreateTime(new Date());
                        pkCurriculumSetMapper.insert(pkCurriculumSet);
                    }
                    pkTaskMapper.insert(pkTask);
                }
    }


    /**
    * @Description:    查询排课任务
    * @Author:         jiangyong
    * @CreateDate:     2019/8/17 19:23
    * @UpdateUser:     jiangyong
    * @UpdateDate:     2019/8/17 19:23
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Override
    public List<PkTask> selectPktask(Map<String, Object> map) {
            List<PkTask> pkTasks = pkTaskMapper.selectPktask(map);
            if(pkTasks.size()==0){
                throw new CheckedException("获取排课任务为空");
            }
            return pkTasks;
    }


    /**
    * @Description:    根据ID删除排课任务
    * @Author:         jiangyong
    * @CreateDate:     2019/8/18 14:41
    * @UpdateUser:     jiangyong
    * @UpdateDate:     2019/8/18 14:41
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */

    @Transactional
    public void deletePkTask(PkTask pkTask){
        Map<String,Object> map=new HashMap<>();
        map.put("task_id",pkTask.getId());
        if(pkTaskMapper.deleteById(pkTask.getId())<0){
            throw new CheckedException("删除排课任务失败！");
        }
        if(pkBaseLessonMapper.deleteByMap(map)<0){
            throw new CheckedException("删除课时基础表失败！");
        }
       if(pkLessonDetailMapper.deleteByMap(map)<0){
           throw new CheckedException("删除课时详细表");
       }
        pkTaskMapper.deleteById(pkTask.getId());
        pkBaseLessonMapper.deleteByMap(map);
        pkLessonDetailMapper.deleteByMap(map);
    }

}
