package dckj.arrange.producer.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import dckj.arrange.common.entity.*;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkTeachingTaskReq;
import dckj.arrange.common.model.request.TeachingTaskListReq;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.PkTeacherClassMapper;
import dckj.arrange.producer.mapper.PkTeacherInfoMapper;
import dckj.arrange.producer.mapper.PkTeachingTaskMapper;
import dckj.arrange.producer.service.IPkTeachingTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 教学任务表 服务实现类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@Service
public class PkTeachingTaskServiceImpl extends ServiceImpl<PkTeachingTaskMapper, PkTeachingTask> implements IPkTeachingTaskService {

    @Autowired
    private PkTeacherClassMapper pkTeacherClassMapper;

    @Autowired
    private PkTeacherInfoMapper pkTeacherInfoMapper;

    @Autowired
    private dckj.arrange.producer.mapper.PkTeacherClassCopyMapper pkTeacherClassCopyMapper;

    @Override
    public TeachingTaskListReq getTeachingTaskList(BaseReq param) {
        TeachingTaskListReq ttr = new TeachingTaskListReq();
        List<PkTeachingTaskReq> pttrList = new ArrayList<>();

        //根据科目id获取教师任教列表
        List<PkTeachingTask> pttList = baseMapper.selectList(new EntityWrapper<PkTeachingTask>().eq("subject_id",param.getSubjectId()).eq("task_id",param.getTaskId()));
        for (PkTeachingTask ptt: pttList
             ) {
            PkTeachingTaskReq pttr = new PkTeachingTaskReq();

            //根据教师id获取教师任教班级
            List<PkTeacherClass> ptcList = pkTeacherClassMapper.selectList(new EntityWrapper<PkTeacherClass>().eq("teacher_id",ptt.getTeacherId()).eq("task_id",ptt.getTaskId()));

            List<PkClass> pcList = new ArrayList<>();

            if (!EmptyUtil.isEmpty(ptcList)) {
                for (PkTeacherClass ptc:ptcList
                 ) {
                    PkClass pc = new PkClass();
                    pc.setId(ptc.getClassId());
                    pc.setClassName(ptc.getClassName());
                    pcList.add(pc);
                }
            }
            pttr.setClassIds(pcList);
            BeanUtils.copyProperties(ptt, pttr);

            pttrList.add(pttr);

        }
        List<PkTeacherInfo> ptiList = pkTeacherInfoMapper.selectList(new EntityWrapper<PkTeacherInfo>().eq("subject_ids",param.getSubjectId()));

        ttr.setPttrList(pttrList);
        if (!EmptyUtil.isEmpty(ptiList)) {
            ttr.setPtiList(ptiList);
        }

        return ttr;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTeachingTask(PkTeachingTaskReq param) {
        param.setId(IDGenerate.buildID());
        param.setCreateTime(new Date());
        if (baseMapper.insert(param)<= 0) {
            throw new CheckedException("添加教学任务失败！");
        }

        for (PkClass pc: param.getClassIds()
             ) {
            PkTeacherClass ptc = new PkTeacherClass();
            ptc.setId(IDGenerate.buildID());
            ptc.setClassId(pc.getId());
            ptc.setClassName(pc.getClassName());
            ptc.setCreateTime(new Date());
            ptc.setTaskId(param.getTaskId());
            ptc.setTeacherId(param.getTeacherId());
            ptc.setTeacherName(param.getTeacherName());
            if (pkTeacherClassMapper.insert(ptc) <= 0) {
                throw new CheckedException("插入任教班级失败！");
            }

            Integer count = param.getMustNumber() + param.getChoiceNumber();

            for (int i = 0; i < count; i++) {
                PkTeacherClassCopy ptcc = new PkTeacherClassCopy();
                ptcc.setId(IDGenerate.buildID());
                ptcc.setClassId(pc.getId());
                ptcc.setClassName(pc.getClassName());
                ptcc.setCreateTime(new Date());
                ptcc.setTaskId(param.getTaskId());
                ptcc.setTeacherId(param.getTeacherId());
                ptcc.setTeacherName(param.getTeacherName());
                if (pkTeacherClassCopyMapper.insert(ptcc) <= 0) {
                    throw new CheckedException("插入任教班级失败！");
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTeachingTask(List<PkTeachingTaskReq> param) {
        for (PkTeachingTaskReq pttr:param
             ) {

            if (baseMapper.updateById(pttr) <= 0) {
                throw new CheckedException("修改教学任务失败！");
            }

            if (!EmptyUtil.isEmpty(pttr.getClassIds())) {
                //先删除后覆盖
                pkTeacherClassMapper.delete(new EntityWrapper<PkTeacherClass>().eq("teacher_id",pttr.getTeacherId()));

                for (PkClass pc: pttr.getClassIds()
                ) {
                    PkTeacherClass ptc = new PkTeacherClass();
                    ptc.setId(IDGenerate.buildID());
                    ptc.setClassId(pc.getId());
                    ptc.setClassName(pc.getClassName());
                    ptc.setCreateTime(new Date());
                    ptc.setTaskId(pttr.getTaskId());
                    ptc.setTeacherId(pttr.getTeacherId());
                    ptc.setTeacherName(pttr.getTeacherName());
                    if (pkTeacherClassMapper.insert(ptc) <= 0) {
                        throw new CheckedException("插入任教班级失败！");
                    }
                }

            }

        }

    }

    @Override
    public void delTeachingTask(PkTeachingTask param) {
        if (baseMapper.deleteById(param.getId()) <= 0) {
            throw new CheckedException("删除教学任务失败！");
        }
    }
}
