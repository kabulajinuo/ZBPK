package dckj.arrange.producer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkSite;
import dckj.arrange.common.model.request.SiteVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.PkSiteMapper;
import dckj.arrange.producer.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private PkSiteMapper pkSiteMapper;

    @Override
    public int toInsertSite(SiteVo siteVo) {
        siteVo.setCreateTime(new Date());
        siteVo.setSiteId(IDGenerate.buildID());
        return pkSiteMapper.insert(siteVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<PkSite> toGetSite(SiteVo siteVo) {
        PageHelper.startPage(siteVo.getPageFilterVo().getPageNumber(), siteVo.getPageFilterVo().getPageSize());
        List<PkSite> pkSites = pkSiteMapper.selectListBySchoolCode(siteVo);
        PageInfo<PkSite> pkSitePageInfo = new PageInfo<>(pkSites);
        return pkSitePageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toDeleteSite(SiteVo siteVo) {
        return pkSiteMapper.deleteById(siteVo.getSiteId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toUpdateSite(SiteVo siteVo) {
        return pkSiteMapper.updateById(siteVo);
    }

    @Override
    public List<PkSite> toGetSiteWithOutPage(SiteVo siteVo) {
        return pkSiteMapper.selectSiteWithOutPage(siteVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String toGetSiteId(String siteName, String schoolCode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolCode", schoolCode);
        paramMap.put("siteName", siteName);

        PkSite pkSite = pkSiteMapper.selectSiteIdByName(paramMap);

        if (EmptyUtil.isEmpty(pkSite)) {
            PkSite pkSite1 = new PkSite();
            pkSite1.setSiteName(siteName);
            pkSite1.setSchoolCode(schoolCode);
            pkSite1.setSiteId(IDGenerate.buildID());
            pkSite1.setCreateTime(new Date());
            pkSiteMapper.insert(pkSite1);
        }

        PkSite pkSite2 = pkSiteMapper.selectSiteIdByName(paramMap);
        return pkSite2.getSiteId();
    }
}
