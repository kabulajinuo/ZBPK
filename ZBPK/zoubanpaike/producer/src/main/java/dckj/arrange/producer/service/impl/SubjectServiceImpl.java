package dckj.arrange.producer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkSubject;
import dckj.arrange.common.model.request.PageFilterVo;
import dckj.arrange.common.model.request.SubjectVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.mapper.PkSubjectMapper;
import dckj.arrange.producer.service.SubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private PkSubjectMapper pkSubjectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toInsertSubject(SubjectVo subjectVo) {
        subjectVo.setCreateTime(new Date());
        return pkSubjectMapper.insert(subjectVo);
    }

    @Override
    public PageInfo<PkSubject> toGetSubject(SubjectVo subjectVo, PageFilterVo pageFilterVo) {
        PageHelper.startPage(pageFilterVo.getPageNumber(), pageFilterVo.getPageSize());
        List<PkSubject> pkSubjects = pkSubjectMapper.selectListBySchoolCode(subjectVo);
        PageInfo<PkSubject> pageInfo = new PageInfo<>(pkSubjects);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toDeleteSubject(SubjectVo subjectVo) {
        return pkSubjectMapper.deleteById(subjectVo.getSubjectId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toUpdateSubject(SubjectVo subjectVo) {
        return pkSubjectMapper.updateById(subjectVo);
    }

    @Override
    public String toGetSubjectIds(String subjectNames, String schoolCode) {
        List<String> nameList = Arrays.asList(subjectNames.split("@"));

        String subjectName = StringUtils.join(nameList.toArray(), ",");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolCode", schoolCode);
        paramMap.put("subjectNames", subjectName);

        SubjectVo subjectVo = pkSubjectMapper.selectSubjectIdsByName(paramMap);
        //如果学科 不存在 创建
        if (EmptyUtil.isEmpty(subjectVo)) {
            for (String s : nameList) {
                PkSubject pkSubject = new PkSubject();
                pkSubject.setSubjectName(s);
                pkSubject.setSchoolCode(schoolCode);
                pkSubject.setCreateTime(new Date());
            }
        }
        SubjectVo subjectVo1 = pkSubjectMapper.selectSubjectIdsByName(paramMap);
        return subjectVo1.getSubjectIds();
    }

    public static void main(String[] args) {
        // 将List转换为逗号分隔的字符串
        String name = "数学@语文@英语";
        List<String> nameList = Arrays.asList(name.split("@"));
       /* List<String> list = new ArrayList<String>();
        list.add("数学");
        list.add("英语");
        list.add("语文");*/
        // 1.使用Apache Commons的StringUtils
        String str1 = StringUtils.join(nameList.toArray(), ",");
        System.out.println(str1);
    }
}
