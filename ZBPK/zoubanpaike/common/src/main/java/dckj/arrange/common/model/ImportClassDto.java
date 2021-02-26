package dckj.arrange.common.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import dckj.arrange.common.entity.PkClass;
import lombok.Data;

/**
 * @Classname ImportClassDto
 * @Description TODO
 * @Date 2019/8/28 10:47
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class ImportClassDto extends PkClass {

    @Excel(name = "班主任", width=15)
    private String teacherName;

    @Excel(name = "年级", width=15)
    private String gradeName;

}
