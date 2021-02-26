package dckj.arrange.common.model.response;

import dckj.arrange.common.model.ClassDayDto;
import lombok.Data;

import java.util.List;

/**
 * @Classname ClassScheduleRes
 * @Description TODO
 * @Date 2019/8/21 6:31
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class ClassScheduleRes {

    private Integer sectionNum;

    private String section;

    List<ClassDayDto> cddList;
}
