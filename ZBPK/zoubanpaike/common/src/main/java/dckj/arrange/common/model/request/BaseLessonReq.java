package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkBaseLesson;
import dckj.arrange.common.entity.PkLessonDetail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class BaseLessonReq extends PkBaseLesson {
    private List<PkLessonDetail> pkLessonDetailList;
    @NotEmpty(message = "年级ID不能为空")
    private String gradeId;
}
