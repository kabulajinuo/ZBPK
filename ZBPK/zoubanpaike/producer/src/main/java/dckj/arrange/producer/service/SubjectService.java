package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkSubject;
import dckj.arrange.common.model.request.PageFilterVo;
import dckj.arrange.common.model.request.SubjectVo;

public interface SubjectService {
    int toInsertSubject(SubjectVo subjectVo);
    PageInfo<PkSubject> toGetSubject(SubjectVo subjectVo,PageFilterVo pageFilterVo);
    int toDeleteSubject(SubjectVo subjectVo);
    int toUpdateSubject(SubjectVo subjectVo);
    String toGetSubjectIds(String subjectNames,String schoolCode);
}
