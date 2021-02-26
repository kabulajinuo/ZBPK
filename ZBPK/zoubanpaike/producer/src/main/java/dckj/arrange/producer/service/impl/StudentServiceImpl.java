package dckj.arrange.producer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.BaseUser;
import dckj.arrange.common.entity.PkStudent;
import dckj.arrange.common.enums.GraduateEnum;
import dckj.arrange.common.enums.ReadEnum;
import dckj.arrange.common.enums.SexEnum;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.StudentVo;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.common.util.MD5Utils;
import dckj.arrange.common.util.TrimUtil;
import dckj.arrange.producer.mapper.BaseUserMapper;
import dckj.arrange.producer.mapper.PkStudentMapper;
import dckj.arrange.producer.service.GradeService;
import dckj.arrange.producer.service.IPkClassService;
import dckj.arrange.producer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private PkStudentMapper pkStudentMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private IPkClassService iPkClassService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toInsertBatchStudent(List<StudentVo> studentVoList) {
        List userList = new ArrayList();
        String id = null;
        Map<String, Object> paramMap = new HashMap<>();
        //检出用户
        for (StudentVo studentVo : studentVoList) {
            paramMap.put("stu_no", studentVo.getStuNo());
            paramMap.put("school_code", studentVo.getSchoolCode());
            List<PkStudent> pkStudents = pkStudentMapper.selectByMap(paramMap);
            if (pkStudents.size() > 0) {
                throw new CheckedException("该学校已经存在此编号：" + studentVo.getStuNo());
            }
            id = IDGenerate.buildID();
            BaseUser user = new BaseUser();
            //统一id
            user.setId(id);
            studentVo.setUserId(id);
            user.setAccount(studentVo.getSchoolCode() + studentVo.getStuNo());
            user.setNickname(studentVo.getStuName());
            user.setPassword(MD5Utils.encodeMemberPassword("123456"));
            user.setGender(studentVo.getStuSex());
            user.setRegisterType(0);
            //统一时间
            user.setCreateTime(new Date());
            studentVo.setCreateTime(new Date());
            user.setStatus(1);
            userList.add(user);
        }
        //增加用户
        baseUserMapper.insertBatch(userList);
        //增加学生
        pkStudentMapper.insertBtach(studentVoList);
    }

    @Override
    public void toInsertStudent(StudentVo studentVo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("stu_no", studentVo.getStuNo());
        paramMap.put("school_code", studentVo.getSchoolCode());
        List<PkStudent> pkStudents = pkStudentMapper.selectByMap(paramMap);
        if (pkStudents.size() > 0) {
            throw new CheckedException("该学校已经存在此编号：" + studentVo.getStuNo());
        }
        String id = null;
        id = IDGenerate.buildID();
        BaseUser user = new BaseUser();
        //统一id
        user.setId(id);
        studentVo.setUserId(id);
        user.setAccount(studentVo.getSchoolCode() + studentVo.getStuNo());
        user.setNickname(studentVo.getStuName());
        user.setPassword(MD5Utils.encodeMemberPassword(("123456")));
        user.setGender(studentVo.getStuSex());
        user.setRegisterType(0);
        //统一时间
        user.setCreateTime(new Date());
        studentVo.setCreateTime(new Date());
        user.setStatus(1);
        //增加用户
        baseUserMapper.insert(user);
        //增加学生
        pkStudentMapper.insert(studentVo);
    }

    @Override
    public PageInfo<StudentVo> toGetStudent(StudentVo studentVo) {
        PageHelper.startPage(studentVo.getPageFilterVo().getPageNumber(), studentVo.getPageFilterVo().getPageSize());
        List<StudentVo> studentVos = pkStudentMapper.selectByCondition(studentVo);
        PageInfo<StudentVo> studentVoPageInfo = new PageInfo<>(studentVos);
        return studentVoPageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toDeleteStudent(StudentVo studentVo) {
        List paramList = new ArrayList();
        paramList.add(studentVo.getUserId());
        baseUserMapper.updateByUserId(paramList);
        studentVo.setUpdateTime(new Date());
        pkStudentMapper.updateById(studentVo);
    }

    @Override
    public void toDeleteBatchStudent(List<StudentVo> students) {
        List paramList = new ArrayList();
        for (StudentVo student : students) {
            paramList.add(student.getUserId());
        }
        baseUserMapper.updateByUserId(paramList);
        pkStudentMapper.updateBatchByUserId(paramList);
    }

    @Override
    public void toUpdateStudent(StudentVo studentVo) {
        List<PkStudent> pkStudents = pkStudentMapper.selectByCodeAndStuNo(studentVo);
        if (pkStudents.size() > 0) {
            throw new CheckedException("该学校已经存在此学号：" + studentVo.getStuNo());
        }
        //绑定的用户
        BaseUser user = new BaseUser();
        user.setId(studentVo.getUserId());
        user.setAccount(studentVo.getSchoolCode() + studentVo.getStuNo());
        user.setNickname(studentVo.getStuName());
        user.setPassword(MD5Utils.encodeMemberPassword("123456"));
        user.setGender(studentVo.getStuSex());
        user.setRegisterType(0);
        user.setUpdateTime(new Date());
        //教师
        studentVo.setUpdateTime(new Date());
        baseUserMapper.updateById(user);
        pkStudentMapper.updateById(studentVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toImportStudent(List<StudentVo> list) {
        //转换 性别 就读方式 是否毕业 年级 班级
        list.stream().forEach(studentVo -> {
            TrimUtil.beanAttributeValueTrim(studentVo);
            studentVo.setStuSex(studentVo.getStuSex() == SexEnum.MAN.getMessage() ? "0" : "1");
            studentVo.setReadingWay(studentVo.getReadingWay() == ReadEnum.BOARDING.getMessage() ? "0" : "1");
            studentVo.setIsGraduate(studentVo.getIsGraduate() == GraduateEnum.NO.getMessage() ? "0" : "1");
            studentVo.setGradeId(gradeService.toGetGradeId(studentVo.getGradeId(), studentVo.getSchoolCode()));
            studentVo.setClassId(iPkClassService.toGetClassId(studentVo.getClassId(),studentVo.getSchoolCode()));
        });
        toInsertBatchStudent(list);
    }
}
