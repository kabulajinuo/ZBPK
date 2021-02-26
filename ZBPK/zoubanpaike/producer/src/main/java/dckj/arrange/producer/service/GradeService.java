package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.model.request.GradeVo;

public interface GradeService {
    int toInsertGrade(GradeVo gradeVo);
    PageInfo<GradeVo> toGetGrade(GradeVo gradeVo);
    int toDeleteGrade(GradeVo gradeVo);
    int toUpdateGrade(GradeVo gradeVo);
    String toGetGradeId(String gradeName, String schoolCode);
}
