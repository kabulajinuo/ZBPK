package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkSubject;
import lombok.Data;

@Data
public class SubjectVo extends PkSubject {
    private PageFilterVo pageFilterVo;
    private String subjectIds;
}
