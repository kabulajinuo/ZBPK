package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.model.request.StudentVo;

import java.util.List;

public interface StudentService {
    void toInsertBatchStudent(List<StudentVo> studentVoList);
    void toInsertStudent(StudentVo studentVo);
    PageInfo<StudentVo> toGetStudent(StudentVo studentVo);
    void toDeleteStudent(StudentVo studentVo);
    void toDeleteBatchStudent(List<StudentVo> students);
    void toUpdateStudent(StudentVo studentVo);
    void toImportStudent(List<StudentVo> list);
}
