package dckj.arrange.common.model.response;

import lombok.Data;

import java.util.List;

/**
 * @Classname CurriculumSetRes
 * @Description TODO
 * @Date 2019/8/22 0:22
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class CurriculumSetRes {

    private List<PkCurriculumSetListRes> pcsrList;

    private Integer countNum;

    private Integer isSelectNum;

}
