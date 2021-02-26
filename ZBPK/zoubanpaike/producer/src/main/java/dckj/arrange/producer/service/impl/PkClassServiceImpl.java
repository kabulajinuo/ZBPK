package dckj.arrange.producer.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.entity.PkGrade;
import dckj.arrange.common.entity.PkTeacherInfo;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.ImportClassDto;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.common.model.response.PkClassListRes;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.PkClassMapper;
import dckj.arrange.producer.service.IPkClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 班级表 服务实现类
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-14
 */
@Service
@Slf4j
public class PkClassServiceImpl extends ServiceImpl<PkClassMapper, PkClass> implements IPkClassService {

    @Autowired
    private TeacherInfoServiceImpl teacherInfoServiceImpl;

    @Autowired
    private GradeServiceImpl gradeServiceImpl;

    @Override
    @Transactional
    public void saveClass(PkClass param) {
        param.setId(IDGenerate.buildID());
        param.setCreateTime(new Date());
        //插入班级表
        if (!this.insert(param)) {
            throw new CheckedException("保存班级失败！");
        }
    }

    @Override
    public List<PkClassListRes> getClassList(PkClassListReq param) {
        List<PkClassListRes> pcrList = baseMapper.getClassList(param);
        if (EmptyUtil.isEmpty(pcrList)) {
            throw new CheckedException("班级列表为空！");
        }
        return pcrList;
    }

    @Transactional
    @Override
    public void updateClass(PkClass param) {
        if (!this.updateById(param)) {
            throw new CheckedException("修改班级信息失败！");
        }
    }

    @Transactional
    @Override
    public void delClass(PkClass param) {
        if (!this.deleteById(param.getId())) {
            throw new CheckedException("删除班级信息失败！");
        }
    }

    @Override
    public String toGetClassId(String className, String schoolCode) {
        PkClass pkClass = new PkClass();
        pkClass.setClassName(className);
        pkClass.setSchoolCode(schoolCode);

        PkClass pkClass1 = baseMapper.selectOne(pkClass);
        //如果不存在 创建
        if (EmptyUtil.isEmpty(pkClass1)) {
            pkClass.setId(IDGenerate.buildID());
            pkClass.setCreateTime(new Date());
            baseMapper.insert(pkClass);
        }

        PkClass pkClass2 = baseMapper.selectOne(pkClass);
        return pkClass2.getId();
    }

    @Override
    public StringBuffer importClass(MultipartFile file, String schoolCode) {
        StringBuffer sb = new StringBuffer();
        try {
            ImportParams importParams = new ImportParams();
            // 数据处理
            importParams.setHeadRows(1);
            importParams.setTitleRows(1);
            // 需要验证
            importParams.setNeedVerfiy(false);
            ExcelImportResult<ImportClassDto> result = ExcelImportUtil.importExcelMore(file.getInputStream(), ImportClassDto.class,
                    importParams);
            List<ImportClassDto> pcList = result.getList();

            sb.append("导入异常说明：");


            for (ImportClassDto icd : pcList) {
                PkClass pc = new PkClass();
                PkTeacherInfo pti = teacherInfoServiceImpl.selectOne(new EntityWrapper<PkTeacherInfo>().eq("school_code", schoolCode)
                        .eq("teacher_name", icd.getTeacherName()).like("post_ids", "4"));
                if (EmptyUtil.isEmpty(pti)) {
                    sb.append(icd.getTeacherName() + "不是班主任;\n");
                    continue;
                }
                PkGrade pg = gradeServiceImpl.selectOne(new EntityWrapper<PkGrade>().eq("school_code", schoolCode)
                        .eq("grade_name", icd.getGradeName()));
                if (EmptyUtil.isEmpty(pg)) {
                    sb.append(icd.getGradeName() + "该年级不存在;\n");
                    continue;
                }
                PkClass pcs = this.selectOne(new EntityWrapper<PkClass>().eq("school_code", schoolCode)
                                .eq("class_name", icd.getClassName()));
                if (!EmptyUtil.isEmpty(pcs)) {
                    sb.append(icd.getClassName() + "该班级已存在;\n");
                    continue;
                }
                pc.setId(IDGenerate.buildID());
                pc.setAdviserId(pti.getUserId());
                pc.setGradeId(pg.getGradeId());
                pc.setSchoolCode(schoolCode);
                pc.setClassName(icd.getClassName());
                pc.setClassNum(icd.getClassNum());
                pc.setCreateTime(new Date());

                if (!this.insert(pc)){
                    throw new CheckedException("插入班级失败");
                }

                log.info("从Excel导入数据到数据库的详细为 ：{}", JSONObject.toJSONString(pc));
                //TODO 将导入的数据做保存数据库操作
            }
            log.info("从Excel导入数据一共 {} 行 ", pcList.size());
        } catch (IOException e) {
            log.error("导入失败：{}", e.getMessage());
        } catch (Exception e1) {
            log.error("导入失败：{}", e1.getMessage());
        }
        return sb;
    }
}
