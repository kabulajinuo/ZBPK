package dckj.arrange.producer.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import dckj.arrange.common.entity.PkBaseLesson;
import dckj.arrange.common.entity.PkCurriculumSet;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.response.CurriculumSetRes;
import dckj.arrange.common.model.response.PkCurriculumSetListRes;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.mapper.PkBaseLessonMapper;
import dckj.arrange.producer.mapper.PkCurriculumSetMapper;
import dckj.arrange.producer.service.IPkCurriculumSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程设置表 服务实现类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@Service
public class PkCurriculumSetServiceImpl extends ServiceImpl<PkCurriculumSetMapper, PkCurriculumSet> implements IPkCurriculumSetService {

    @Autowired
    private PkBaseLessonMapper pkBaseLessonMapper;

    @Override
    public CurriculumSetRes getCurriculumSetList(BaseReq param) {
        CurriculumSetRes csr = new CurriculumSetRes();

        List<PkCurriculumSetListRes> pcsList = baseMapper.getCurriculumSetList(param);
        if (EmptyUtil.isEmpty(pcsList)) {
            throw new CheckedException("课程还未设置!");
        }
        csr.setPcsrList(pcsList);
        PkBaseLesson pbl = new PkBaseLesson();
        pbl.setTaskId(param.getTaskId());
        pbl = pkBaseLessonMapper.selectOne(pbl);

        //周总课时
        Integer countNum = pbl.getEndDay() * (pbl.getMorning() + pbl.getAfternoon());
        //已排课程数
        Integer isSelectNum = 0;
        for (PkCurriculumSetListRes pcs:pcsList
             ) {
            Integer subjectNum = pcs.getMustNumber() + pcs.getChoiceNumber();

            isSelectNum = isSelectNum + subjectNum;
        }
        csr.setCountNum(countNum);
        csr.setIsSelectNum(isSelectNum);

        return csr;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setCurriculumSetList(List<PkCurriculumSet> param) {
        for (PkCurriculumSet pcs: param
             ) {
            if (baseMapper.updateById(pcs) <= 0) {
                throw new CheckedException("修改异常");
            }
        }
    }
}
