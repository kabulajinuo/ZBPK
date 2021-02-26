package dckj.arrange.common.model.response;

import dckj.arrange.common.model.ClassDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname AllClassScheduleRes
 * @Description TODO
 * @Date 2019/8/21 3:47
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class AllClassScheduleRes {

    List<ClassDto> cdList;
}
