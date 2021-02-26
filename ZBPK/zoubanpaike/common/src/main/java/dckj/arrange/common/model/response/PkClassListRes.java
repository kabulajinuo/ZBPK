package dckj.arrange.common.model.response;

import dckj.arrange.common.entity.PkClass;
import lombok.Data;

/**
 * @Classname PkClassListRes
 * @Description TODO
 * @Date 2019/8/17 10:16
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class PkClassListRes extends PkClass {

    private String gradeName;
    private String adviseName;

}
