package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkSite;
import lombok.Data;

@Data
public class SiteVo extends PkSite {
    private PageFilterVo pageFilterVo;
}
