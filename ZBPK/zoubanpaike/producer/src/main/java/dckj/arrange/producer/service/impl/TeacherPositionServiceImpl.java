package dckj.arrange.producer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkTeacherPosition;
import dckj.arrange.common.model.request.PageFilterVo;
import dckj.arrange.common.model.request.TeacherPositionVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.PkTeacherPositionMapper;
import dckj.arrange.producer.service.TeacherPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TeacherPositionServiceImpl implements TeacherPositionService {
    @Autowired
    private PkTeacherPositionMapper pkTeacherPositionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toInsertTeacherPosition(TeacherPositionVo teacherPositionVo) {
        teacherPositionVo.setCreateTime(new Date());
        teacherPositionVo.setPostCode(IDGenerate.generateRandomName());
        return pkTeacherPositionMapper.insert(teacherPositionVo);
    }

   @Override
    public PageInfo<PkTeacherPosition> toGetTeacherPosition(PageFilterVo pageFilterVo, TeacherPositionVo teacherPositionVo) {
        PageHelper.startPage(pageFilterVo.getPageNumber(),pageFilterVo.getPageSize());
        List<PkTeacherPosition> pkTeacherPositions = pkTeacherPositionMapper.selectListBySchoolCode(teacherPositionVo);
        PageInfo<PkTeacherPosition> pageInfo = new PageInfo<>(pkTeacherPositions);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toDeleteTeacherPosition(TeacherPositionVo teacherPositionVo) {
        return pkTeacherPositionMapper.deleteById(teacherPositionVo.getPostId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toUpdateTeacherPosition(TeacherPositionVo teacherPositionVo) {
        return pkTeacherPositionMapper.updateById(teacherPositionVo);
    }

    @Override
    public String toGetPostIds(String postNames, String schoolCode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolCode", schoolCode);
        paramMap.put("postNames", postNames);
        TeacherPositionVo teacherPositionVo1 = pkTeacherPositionMapper.selectPostIdsByName(paramMap);
        //如果职务不存在 创建
        if (EmptyUtil.isEmpty(teacherPositionVo1)) {
            List<String> postName = Arrays.asList(postNames.split(","));
            for (String s : postName) {
                PkTeacherPosition pkTeacherPosition1 = new PkTeacherPosition();
                pkTeacherPosition1.setPostName(postNames);
                pkTeacherPosition1.setSchoolCode(schoolCode);
                pkTeacherPositionMapper.insert(pkTeacherPosition1);
            }
        }
        TeacherPositionVo teacherPositionVo = pkTeacherPositionMapper.selectPostIdsByName(paramMap);
        return teacherPositionVo.getPostIds();
    }

    public static void main(String[] args) {
        String postNames = "乡长@家长";
        List<String> postName = Arrays.asList(postNames.split("@"));
        for (String s : postName) {
            System.out.println(s);
        }
    }
}
