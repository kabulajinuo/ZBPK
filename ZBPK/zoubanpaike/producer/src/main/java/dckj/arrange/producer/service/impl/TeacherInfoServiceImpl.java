package dckj.arrange.producer.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.BaseUser;
import dckj.arrange.common.entity.PkTeacherInfo;
import dckj.arrange.common.enums.SexEnum;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.TeacherInfoVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.common.util.MD5Utils;
import dckj.arrange.common.util.TrimUtil;
import dckj.arrange.producer.mapper.BaseUserMapper;
import dckj.arrange.producer.mapper.PkTeacherInfoMapper;
import dckj.arrange.producer.service.SubjectService;
import dckj.arrange.producer.service.TeacherInfoService;
import dckj.arrange.producer.service.TeacherPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TeacherInfoServiceImpl extends ServiceImpl<PkTeacherInfoMapper, PkTeacherInfo> implements TeacherInfoService {
    @Autowired
    private PkTeacherInfoMapper pkTeacherInfoMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private TeacherPositionService teacherPositionService;
    @Autowired
    private SubjectService subjectService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toInsertBatchTeacherInfo(List<TeacherInfoVo> teacherInfoVoList) {
        List userList = new ArrayList();
        String id = null;
        Map<String, Object> paramMap = new HashMap<>();
        //检出用户
        for (TeacherInfoVo teacherInfoVo : teacherInfoVoList) {
            paramMap.put("job_no", teacherInfoVo.getJobNo());
            paramMap.put("school_code", teacherInfoVo.getSchoolCode());
            List<PkTeacherInfo> pkTeacherInfos = pkTeacherInfoMapper.selectByMap(paramMap);
            if (pkTeacherInfos.size() > 0) {
                throw new CheckedException("该学校已经存在此编号：" + teacherInfoVo.getJobNo());
            }
            id = IDGenerate.buildID();
            BaseUser user = new BaseUser();
            //统一id
            user.setId(id);
            teacherInfoVo.setUserId(id);
            user.setAccount(teacherInfoVo.getPhone());
            user.setPhone(teacherInfoVo.getPhone());
            user.setNickname(teacherInfoVo.getTeacherName());
            if (EmptyUtil.isEmpty(teacherInfoVo.getPassword())) {
                user.setPassword(MD5Utils.encodeMemberPassword("123456"));
            } else {
                user.setPassword(MD5Utils.encodeMemberPassword(teacherInfoVo.getPassword()));
            }
            user.setGender(teacherInfoVo.getGender());
            user.setDutyId(Integer.parseInt(teacherInfoVo.getPostIds()));
            user.setRegisterType(1);
            //统一时间
            user.setCreateTime(new Date());
            teacherInfoVo.setCreateTime(new Date());
            user.setStatus(1);
            userList.add(user);
        }
        //增加用户
        baseUserMapper.insertBatch(userList);
        //增加教师
        pkTeacherInfoMapper.insertBatch(teacherInfoVoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toInsertTeacherInfo(TeacherInfoVo teacherInfoVo) {
        String id = null;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("job_no", teacherInfoVo.getJobNo());
        paramMap.put("school_code", teacherInfoVo.getSchoolCode());
        List<PkTeacherInfo> pkTeacherInfos = pkTeacherInfoMapper.selectByMap(paramMap);
        if (pkTeacherInfos.size() > 0) {
            throw new CheckedException("该学校已经存在此编号：" + teacherInfoVo.getJobNo());
        }
        id = IDGenerate.buildID();
        BaseUser user = new BaseUser();
        //统一id
        user.setId(id);
        teacherInfoVo.setUserId(id);
        user.setAccount(teacherInfoVo.getPhone());
        user.setPhone(teacherInfoVo.getPhone());
        user.setNickname(teacherInfoVo.getTeacherName());
        if (EmptyUtil.isEmpty(teacherInfoVo.getPassword())) {
            user.setPassword(MD5Utils.encodeMemberPassword("123456"));
        } else {
            user.setPassword(MD5Utils.encodeMemberPassword(teacherInfoVo.getPassword()));
        }
        user.setGender(teacherInfoVo.getGender());
        user.setDutyId(Integer.parseInt(teacherInfoVo.getPostIds()));
        user.setRegisterType(1);
        //统一时间
        user.setCreateTime(new Date());
        teacherInfoVo.setCreateTime(new Date());
        user.setStatus(1);
        //增加用户
        baseUserMapper.insert(user);
        //增加教师
        pkTeacherInfoMapper.insert(teacherInfoVo);
    }

    @Override
    public PageInfo<TeacherInfoVo> toGetTeacherInfo(TeacherInfoVo teacherInfoVo) {
        PageHelper.startPage(teacherInfoVo.getPageFilterVo().getPageNumber(), teacherInfoVo.getPageFilterVo().getPageSize());
        List<TeacherInfoVo> teacherInfoVos = pkTeacherInfoMapper.selectByCodeAndUserId(teacherInfoVo);
        PageInfo<TeacherInfoVo> teacherInfoVoPageInfo = new PageInfo<>(teacherInfoVos);
        return teacherInfoVoPageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toDeleteTeacherInfo(TeacherInfoVo teacherInfoVo) {
        List<String> paramList = new ArrayList();
        paramList.add(teacherInfoVo.getUserId());
        baseUserMapper.updateByUserId(paramList);
        teacherInfoVo.setUpdateTime(new Date());
        pkTeacherInfoMapper.updateById(teacherInfoVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toUpdateTeacherInfo(TeacherInfoVo teacherInfoVo) {
        List<PkTeacherInfo> pkTeacherInfos = pkTeacherInfoMapper.selectByCondition(teacherInfoVo);
        if (pkTeacherInfos.size() > 0) {
            throw new CheckedException("该学校已经存在此教师编号：" + teacherInfoVo.getJobNo());
        }
        //绑定的用户
        BaseUser baseUser = new BaseUser();
        baseUser.setId(teacherInfoVo.getUserId());
        baseUser.setAccount(teacherInfoVo.getPhone());
        baseUser.setPhone(teacherInfoVo.getPhone());
        baseUser.setNickname(teacherInfoVo.getTeacherName());
        if (EmptyUtil.isEmpty(teacherInfoVo.getPassword())) {
            baseUser.setPassword(MD5Utils.encodeMemberPassword("123456"));
        } else {
            baseUser.setPassword(MD5Utils.encodeMemberPassword(teacherInfoVo.getPassword()));
        }
        baseUser.setGender(teacherInfoVo.getGender());
        baseUser.setDutyId(Integer.parseInt(teacherInfoVo.getPostIds()));
        baseUser.setUpdateTime(new Date());
        //教师
        teacherInfoVo.setUpdateTime(new Date());
        baseUserMapper.updateById(baseUser);
        pkTeacherInfoMapper.updateById(teacherInfoVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toImportTeacherInfo(List<TeacherInfoVo> list) {
        list.stream().forEach(teacherInfoVo -> {
            //转换 --> 性别 职务 科目
            TrimUtil.beanAttributeValueTrim(teacherInfoVo);
            teacherInfoVo.setGender(teacherInfoVo.getGender() == SexEnum.MAN.getMessage() ? "0" : "1");
            teacherInfoVo.setPostIds(teacherPositionService.toGetPostIds(teacherInfoVo.getPostIds(), teacherInfoVo.getSchoolCode()));
            teacherInfoVo.setSubjectIds(subjectService.toGetSubjectIds(teacherInfoVo.getSubjectIds(), teacherInfoVo.getSchoolCode()));
        });
        toInsertBatchTeacherInfo(list);
    }

    @Override
    public List<PkTeacherInfo> searchTeacher(BaseReq param) {
        return pkTeacherInfoMapper.selectList(new EntityWrapper<PkTeacherInfo>().like("teacher_name", param.getName()));
    }

    /**
     * 职务
     *
     * @param postNames
     * @param schoolCode
     * @return
     */
   /* public String toGetPostIds(String postNames, String schoolCode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolCode", schoolCode);
        paramMap.put("postNames", postNames);
        PkTeacherPosition pkTeacherPosition = pkTeacherPositionMapper.selectPostIdsByName(paramMap);
        //如果职务不存在 创建
        if (EmptyUtil.isEmpty(pkTeacherPosition)) {
            PkTeacherPosition pkTeacherPosition1 = new PkTeacherPosition();
            pkTeacherPosition1.setPostName(postNames);
            pkTeacherPosition1.setSchoolCode(schoolCode);
            pkTeacherPositionMapper.insert(pkTeacherPosition1);
        }
        TeacherPositionVo teacherPositionVo = pkTeacherPositionMapper.selectPostIdsByName(paramMap);
        return teacherPositionVo.getPostIds();
    }
*/
    /**
     * 科目
     */
   /* public String toGetSubjectIds(String subjectNames,String schoolCode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolCode", schoolCode);
        paramMap.put("subjectNames", subjectNames);
         SubjectVo subjectVo = pkSubjectMapper.selectSubjectIdsByName(paramMap);
        //如果职务不存在 创建
        if (EmptyUtil.isEmpty(subjectVo)) {
            List<String> subjectName = Arrays.asList(subjectNames.split(","));
            for (String s : subjectName) {
                PkSubject pkSubject = new PkSubject();
                pkSubject.setSubjectName(s);
                pkSubject.setSchoolCode(schoolCode);
                pkSubject.setCreateTime(new Date());
            }
        }
        SubjectVo subjectVo1 = pkSubjectMapper.selectSubjectIdsByName(paramMap);
        return subjectVo1.getSubjectIds();
    }*/

}
