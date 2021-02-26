package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkClassSchedule;
import dckj.arrange.common.entity.PkCurriculumPreset;
import lombok.Data;

import java.util.List;

/**
 * @Description: 课程预设 保存请求入参
 * @Author: jiangyong
 * @CreateDate: 2019/8/20 11:01
 * @Version: 1.0
 */
@Data
public class PkClassSchedulereq extends PkClassSchedule {

    List<PkCurriculumPreset>  pkList;

}
