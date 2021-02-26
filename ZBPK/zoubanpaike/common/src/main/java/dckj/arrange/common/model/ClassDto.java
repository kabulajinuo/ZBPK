package dckj.arrange.common.model;

import lombok.Data;

import java.util.List;

/**
 * @Classname ClassDto
 * @Description TODO
 * @Date 2019/8/21 3:47
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class ClassDto {

    private String classId;
    private String className;

    //星期
    List<DayDto> ddList;

}
