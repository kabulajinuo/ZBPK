package dckj.arrange.producer.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkGrade;
import dckj.arrange.common.model.request.GradeVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.PkGradeMapper;
import dckj.arrange.producer.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GradeServiceImpl extends ServiceImpl<PkGradeMapper, PkGrade> implements GradeService {
    @Autowired
    private PkGradeMapper pkGradeMapper;

    @Override
    public int toInsertGrade(GradeVo gradeVo) {
        gradeVo.setCreateTime(new Date());
        gradeVo.setGradeId(IDGenerate.buildID());
        return pkGradeMapper.insert(gradeVo);
    }

    @Override
    public PageInfo<GradeVo> toGetGrade(GradeVo gradeVo) {
        PageHelper.startPage(gradeVo.getPageFilterVo().getPageNumber(), gradeVo.getPageFilterVo().getPageSize());
        List<GradeVo> gradeVos = pkGradeMapper.selectListByCode(gradeVo);
        PageInfo<GradeVo> gradeVoPageInfo = new PageInfo<>(gradeVos);
        return gradeVoPageInfo;
    }

    @Override
    public int toDeleteGrade(GradeVo gradeVo) {
        return pkGradeMapper.deleteById(gradeVo.getGradeId());
    }

    @Override
    public int toUpdateGrade(GradeVo gradeVo) {
        gradeVo.setUpdateTime(new Date());
        return pkGradeMapper.updateById(gradeVo);
    }

    @Override
    public String toGetGradeId(String gradeName, String schoolCode) {
        PkGrade pkGrade = new PkGrade();
        pkGrade.setGradeName(gradeName);
        pkGrade.setSchoolCode(schoolCode);
        PkGrade pkGrade1 = pkGradeMapper.selectOne(pkGrade);

        //如果不存在 创建
        if (EmptyUtil.isEmpty(pkGrade1)) {
                pkGrade.setGradeId(IDGenerate.buildID());
                pkGrade.setCreateTime(new Date());
                pkGradeMapper.insert(pkGrade);
        }

        PkGrade pkGrade2 = pkGradeMapper.selectOne(pkGrade);

        return pkGrade2.getGradeId();
    }
}
