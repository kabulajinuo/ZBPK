package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkSite;
import dckj.arrange.common.model.request.SiteVo;

import java.util.List;

public interface SiteService {
    int toInsertSite(SiteVo siteVo);
    PageInfo<PkSite> toGetSite(SiteVo siteVo);
    int toDeleteSite(SiteVo siteVo);
    int toUpdateSite(SiteVo siteVo);
    List<PkSite> toGetSiteWithOutPage(SiteVo siteVo);
    String toGetSiteId(String siteName,String schoolCode);
}
