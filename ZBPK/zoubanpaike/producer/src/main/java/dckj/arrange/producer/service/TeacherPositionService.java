package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkTeacherPosition;
import dckj.arrange.common.model.request.PageFilterVo;
import dckj.arrange.common.model.request.TeacherPositionVo;

public interface TeacherPositionService {
    int toInsertTeacherPosition(TeacherPositionVo teacherPositionVo);
    PageInfo<PkTeacherPosition> toGetTeacherPosition(PageFilterVo pageFilterVo, TeacherPositionVo teacherPositionVo);
    int toDeleteTeacherPosition(TeacherPositionVo teacherPositionVo);
    int toUpdateTeacherPosition(TeacherPositionVo teacherPositionVo);
    String toGetPostIds(String postNames, String schoolCode);
}
