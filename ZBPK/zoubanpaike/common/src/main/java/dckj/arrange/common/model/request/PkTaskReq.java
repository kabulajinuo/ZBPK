package dckj.arrange.common.model.request;

import lombok.Data;

/**
 * @Description: 查询排课任务请求参数
 * @Author: jiangyong
 * @CreateDate: 2019/8/17 19:28
 * @Version: 1.0
 */
@Data
public class PkTaskReq {

    private  String schoolCode;

    private String userId;

    private Integer pageIndex;

    private Integer pageSize;

}
