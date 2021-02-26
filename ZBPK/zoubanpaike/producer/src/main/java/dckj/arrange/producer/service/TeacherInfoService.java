package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkTeacherInfo;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.TeacherInfoVo;

import java.util.List;

public interface TeacherInfoService {
    void toInsertBatchTeacherInfo(List<TeacherInfoVo> teacherInfoVoList);

    void toInsertTeacherInfo(TeacherInfoVo teacherInfoVo);

    PageInfo<TeacherInfoVo> toGetTeacherInfo(TeacherInfoVo teacherInfoVo);

    void toDeleteTeacherInfo(TeacherInfoVo teacherInfoVo);

    void toUpdateTeacherInfo(TeacherInfoVo teacherInfoVo);

    void toImportTeacherInfo(List<TeacherInfoVo> list);

    List<PkTeacherInfo> searchTeacher(BaseReq param);
}
