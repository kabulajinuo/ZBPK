package dckj.arrange.common.model;

import lombok.Data;

import java.util.List;

/**
 * @Classname DayDto
 * @Description TODO
 * @Date 2019/8/21 3:50
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class DayDto {

    private Integer dayNum;
    private String day;

    List<SectionDto> sdList;
}
