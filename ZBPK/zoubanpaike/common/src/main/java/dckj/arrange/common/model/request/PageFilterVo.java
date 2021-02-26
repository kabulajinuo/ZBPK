package dckj.arrange.common.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Xiangjing
 * TODO 用于分页
 */
@Data
public class PageFilterVo implements Serializable {
    /**
     * 序列化保存对象唯一
     */
    private static final long serialVersionUID = 1L;
    /**
     * // 当前页
     */
    @ApiModelProperty(value="当前页")
    @NotNull(message = "当前页不能为空")
    private Integer pageNumber;
    /**
     * // 每页显示记录数
     */
    @ApiModelProperty(value="每页显示记录数")
    @NotNull(message = "每页显示记录数不能为空")
    private Integer pageSize;
    /**
     * // 排序字段
     */
    @ApiModelProperty(value="排序字段")
    private String sort;
    /**
     * // asc/desc
     */
    @ApiModelProperty(value="asc/desc")
    private String order;
}
