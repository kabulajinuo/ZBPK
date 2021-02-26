package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.BaseUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-13
 */
public interface BaseUserMapper extends BaseMapper<BaseUser> {
    int insertBatch(List<BaseUser> users);
    int updateByUserId(List<String> userIds);
}
