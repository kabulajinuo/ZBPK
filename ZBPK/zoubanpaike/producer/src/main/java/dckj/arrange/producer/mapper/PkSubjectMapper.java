package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkSubject;
import dckj.arrange.common.model.request.SubjectVo;

import java.util.List;
import java.util.Map;

public interface PkSubjectMapper extends BaseMapper<PkSubject> {
    List<PkSubject> selectListBySchoolCode(SubjectVo subjectVo);

    SubjectVo selectSubjectIdsByName(Map<String, Object> map);
}