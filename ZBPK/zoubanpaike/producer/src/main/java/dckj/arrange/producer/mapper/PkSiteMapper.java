package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkSite;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.model.request.SiteVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-14
 */
public interface PkSiteMapper extends BaseMapper<PkSite> {
    List<PkSite> selectListBySchoolCode(SiteVo siteVo);
    List<PkSite> selectSiteWithOutPage(SiteVo siteVo);
    PkSite selectSiteIdByName(Map map);
}
