package dckj.arrange.producer.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import dckj.arrange.common.entity.*;
import dckj.arrange.common.enums.SubjectEnum;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.BaseImportDto;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.*;
import dckj.arrange.producer.service.BaseImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @Classname BaseImportServiceImpl
 * @Description TODO
 * @Date 2019/8/27 15:26
 * @Created by JinPeng
 * @Version 1.0
 */
@Service
@Slf4j
public class BaseImportServiceImpl implements BaseImportService {

    @Autowired
    private PkSiteMapper pkSiteMapper;

    @Autowired
    private PkRoomInfoMapper pkRoomInfoMapper;

    @Autowired
    private PkClassMapper pkClassMapper;

    @Autowired
    private TeacherInfoServiceImpl teacherInfoServiceImpl;

    @Autowired
    private PkClassRoomMapper pkClassRoomMapper;

    @Autowired
    private PkClassServiceImpl pkClassServiceImpl;

    @Autowired
    private PkSubjectMapper pkSubjectMapper;

    @Autowired
    private PkBaseDetailMapper pkBaseDetailMapper;


    @Transactional
    @Override
    public StringBuffer importData(MultipartFile file, String taskId, String schoolCode) {
        pkBaseDetailMapper.delete(new EntityWrapper<PkBaseDetail>().eq("task_id", taskId));
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        // 需要验证
        importParams.setNeedVerfiy(false);
        StringBuffer sb = new StringBuffer();

        Map<String, Object> map = new HashMap<>();
        try {
            ExcelImportResult<BaseImportDto> result = ExcelImportUtil.importExcelMore(file.getInputStream(), BaseImportDto.class,
                    importParams);
            List<BaseImportDto> bidList = result.getList();

            sb.append("导入异常说明：");


            //科目列表
            List<PkSubject> psList = pkSubjectMapper.selectList(new EntityWrapper<PkSubject>().eq("school_code", schoolCode));

            //学科键值对
            Map<String, Integer> psMap = new HashMap<>();

            if (EmptyUtil.isEmpty(psList)) {
                throw new CheckedException("该学校还没导入学科！");
            }
            for (PkSubject ps: psList
            ) {
                psMap.put(ps.getSubjectName(), ps.getSubjectId());
            }

            //教师列表
            List<PkTeacherInfo> ptiList = teacherInfoServiceImpl.selectList(new EntityWrapper<PkTeacherInfo>().eq("school_code", schoolCode));

            //教师键值对
            Map<String, String> ptiMap = new HashMap<>();

            if (EmptyUtil.isEmpty(ptiList)) {
                throw new CheckedException("该学校还没导入教师！");
            }
            for (PkTeacherInfo pti: ptiList
            ) {
                ptiMap.put(pti.getTeacherName(), pti.getUserId());
            }

            for (BaseImportDto bid : bidList) {

                Boolean verify = true;

                //校验输入项有效性
                map = verifyParam(schoolCode, sb, bid, map, verify);

                if (!Boolean.parseBoolean(map.get("verify").toString())) {
                    continue;
                }

                //保存教室设置
                updateClassRoom(bid, map, taskId);

                //保存课程设置
                saveCurriculumSet(bid, taskId, map, psMap, ptiMap);




                PkClassRoom pcr = new PkClassRoom();
                pcr.setId(IDGenerate.buildID());
                // System.out.println(User);
                log.info("从Excel导入数据到数据库的详细为 ：{}", JSONObject.toJSONString(bid));
                //TODO 将导入的数据做保存数据库操作
            }
            log.info("从Excel导入数据一共 {} 行 ", bidList.size());
        } catch (IOException e) {
            log.error("导入失败：{}", e.getMessage());
        } catch (Exception e1) {
            log.error("导入失败：{}", e1.getMessage());
        }
        return new StringBuffer(map.get("sb").toString());

    }


    /**
     * 保存课程设置
     * @param bid
     * @param taskId
     * @param map
     * @param psMap
     * @param ptiMap
     */
    private void saveCurriculumSet(BaseImportDto bid, String taskId, Map<String, Object> map,
                                     Map<String, Integer> psMap, Map<String, String> ptiMap) {
        PkBaseDetail pbd = new PkBaseDetail();
        pbd.setClassId(map.get("classId").toString());
        pbd.setCreateTime(new Date());
        pbd.setTaskId(taskId);

        Integer singleNum = 0;
        Integer continuouNum = 0;
        //美术
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.ART.getValue()));
        pbd.setSubjectName(SubjectEnum.ART.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getArtTeacher()));
        pbd.setTeacherName(bid.getArtTeacher());
        if (!EmptyUtil.isEmpty(bid.getArtNum()) && bid.getArtNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getArtNum().substring(0, bid.getArtNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getArtNum().substring(bid.getArtNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getArtNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //生物
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.BIOLOGY.getValue()));
        pbd.setSubjectName(SubjectEnum.BIOLOGY.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getBiologyTeacher()));
        pbd.setTeacherName(bid.getBiologyTeacher());
        if (!EmptyUtil.isEmpty(bid.getBiologyNum()) && bid.getBiologyNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getBiologyNum().substring(0, bid.getBiologyNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getBiologyNum().substring(bid.getBiologyNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getBiologyNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //化学
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.CHEMISTRY.getValue()));
        pbd.setSubjectName(SubjectEnum.CHEMISTRY.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getChemistryTeacher()));
        pbd.setTeacherName(bid.getChemistryTeacher());
        if (!EmptyUtil.isEmpty(bid.getChemistryNum()) && bid.getChemistryNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getChemistryNum().substring(0, bid.getChemistryNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getChemistryNum().substring(bid.getChemistryNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getChemistryNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //语文
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.CHINESE.getValue()));
        pbd.setSubjectName(SubjectEnum.CHINESE.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getChineseTeacher()));
        pbd.setTeacherName(bid.getChineseTeacher());
        if (!EmptyUtil.isEmpty(bid.getChineseNum()) && bid.getChineseNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getChineseNum().substring(0, bid.getChineseNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getChineseNum().substring(bid.getChineseNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getChineseNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //外语
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.FOREIGN.getValue()));
        pbd.setSubjectName(SubjectEnum.FOREIGN.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getForeignTeacher()));
        pbd.setTeacherName(bid.getForeignTeacher());
        if (!EmptyUtil.isEmpty(bid.getForeignNum()) && bid.getForeignNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getForeignNum().substring(0, bid.getForeignNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getForeignNum().substring(bid.getForeignNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getForeignNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //通用技术
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.GENERAL.getValue()));
        pbd.setSubjectName(SubjectEnum.GENERAL.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getGeneralTeacher()));
        pbd.setTeacherName(bid.getGeneralTeacher());
        if (!EmptyUtil.isEmpty(bid.getGeneralNum()) && bid.getGeneralNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getGeneralNum().substring(0, bid.getGeneralNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getGeneralNum().substring(bid.getGeneralNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getGeneralNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //地理
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.GEOGRAPHY.getValue()));
        pbd.setSubjectName(SubjectEnum.GEOGRAPHY.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getGeographyTeacher()));
        pbd.setTeacherName(bid.getGeographyTeacher());
        if (!EmptyUtil.isEmpty(bid.getGeographyNum()) && bid.getGeographyNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getGeographyNum().substring(0, bid.getGeographyNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getGeographyNum().substring(bid.getGeographyNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getGeographyNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //历史
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.HISTORY.getValue()));
        pbd.setSubjectName(SubjectEnum.HISTORY.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getHistoryTeacher()));
        pbd.setTeacherName(bid.getHistoryTeacher());
        if (!EmptyUtil.isEmpty(bid.getHistoryNum()) && bid.getHistoryNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getHistoryNum().substring(0, bid.getHistoryNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getHistoryNum().substring(bid.getHistoryNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getHistoryNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //信息技术
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.INFORMATION.getValue()));
        pbd.setSubjectName(SubjectEnum.INFORMATION.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getInformationTeacher()));
        pbd.setTeacherName(bid.getInformationTeacher());
        if (!EmptyUtil.isEmpty(bid.getInformationNum()) && bid.getInformationNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getInformationNum().substring(0, bid.getInformationNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getInformationNum().substring(bid.getInformationNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getInformationNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //数学
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.MATH.getValue()));
        pbd.setSubjectName(SubjectEnum.MATH.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getMathTeacher()));
        pbd.setTeacherName(bid.getMathTeacher());
        if (!EmptyUtil.isEmpty(bid.getMathNum()) && bid.getMathNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getMathNum().substring(0, bid.getMathNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getMathNum().substring(bid.getMathNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getMathNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //音乐
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.MUSIC.getValue()));
        pbd.setSubjectName(SubjectEnum.MUSIC.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getMusicTeacher()));
        pbd.setTeacherName(bid.getMusicTeacher());
        if (!EmptyUtil.isEmpty(bid.getMusicNum()) && bid.getMusicNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getMusicNum().substring(0, bid.getMusicNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getMusicNum().substring(bid.getMusicNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getMusicNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //体育
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.PE.getValue()));
        pbd.setSubjectName(SubjectEnum.PE.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getPeTeacher()));
        pbd.setTeacherName(bid.getPeTeacher());
        if (!EmptyUtil.isEmpty(bid.getPeNum()) && bid.getPeNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getPeNum().substring(0, bid.getPeNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getPeNum().substring(bid.getPeNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getPeNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //物理
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.PHYSICS.getValue()));
        pbd.setSubjectName(SubjectEnum.PHYSICS.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getPhysicsTeacher()));
        pbd.setTeacherName(bid.getPhysicsTeacher());
        if (!EmptyUtil.isEmpty(bid.getPhysicsNum()) && bid.getPhysicsNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getPhysicsNum().substring(0, bid.getPhysicsNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getPhysicsNum().substring(bid.getPhysicsNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getPhysicsNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

        //政治
        pbd.setId(IDGenerate.buildID());
        pbd.setSubjectId(psMap.get(SubjectEnum.POLITICS.getValue()));
        pbd.setSubjectName(SubjectEnum.POLITICS.getValue());
        pbd.setTeacherId(ptiMap.get(bid.getPoliticsTeacher()));
        pbd.setTeacherName(bid.getPoliticsTeacher());
        if (!EmptyUtil.isEmpty(bid.getPoliticsNum()) && bid.getPoliticsNum().contains("+")) {
            singleNum = Integer.parseInt(bid.getPoliticsNum().substring(0, bid.getPoliticsNum().indexOf("+")));
            continuouNum = Integer.parseInt(bid.getPoliticsNum().substring(bid.getPoliticsNum().indexOf("+") + 1));
        }else {
            singleNum = Integer.parseInt(bid.getPoliticsNum());
            continuouNum = 0;
        }
        pbd.setContinuouNum(continuouNum);
        pbd.setSingleNum(singleNum);
        if (pkBaseDetailMapper.insert(pbd) <= 0) {
            throw new CheckedException("插入失败!");
        }

    }

    public static void main(String[] args) {
        String s = "333+111";

        System.out.println(Integer.parseInt(s.substring(0, s.indexOf("+"))));
        System.out.println(Integer.parseInt(s.substring(s.indexOf("+") + 1)));

    }

    /**
     * 保存教室设置
     * @param bid
     * @param map
     * @param taskId
     */
    private void updateClassRoom(BaseImportDto bid, Map<String, Object> map, String taskId) {

        PkClassRoom pcr = new PkClassRoom();
        pcr.setRoomName(bid.getRoomName());
        pcr.setSiteName(bid.getSiteName());
        pcr.setUpdateTime(new Date());

        pkClassRoomMapper.update(pcr, new EntityWrapper<PkClassRoom>().eq("task_id", taskId).eq("class_id", map.get("classId"))
                .eq("class_id", map.get("classId")));
    }


    /**
     * 校验输入项有效性
     * @param schoolCode
     * @param sb
     * @param bid
     * @param map
     * @param verify
     */
    private Map<String, Object> verifyParam(String schoolCode, StringBuffer sb, BaseImportDto bid, Map<String, Object> map, Boolean verify) {

        PkSite ps = new PkSite();
        ps.setSchoolCode(schoolCode);
        ps.setSiteName(bid.getSiteName());
        ps = pkSiteMapper.selectOne(ps);
        if (EmptyUtil.isEmpty(ps.getSiteId())) {
            sb.append("教学楼：" + bid.getSiteName() + "不存在！\n");
            verify = false;
        }

        PkRoomInfo pri = new PkRoomInfo();
        pri.setSchoolCode(schoolCode);
        pri.setSiteId(ps.getSiteId());
        pri.setRoomName(bid.getRoomName());
        pri = pkRoomInfoMapper.selectOne(pri);
        if (EmptyUtil.isEmpty(pri.getRoomId())) {
            sb.append("教室：" + bid.getRoomName() + "不存在！\n");
            verify = false;
        }

        PkClass pc = new PkClass();
        pc.setSchoolCode(schoolCode);
        pc.setClassName(bid.getClassName());
        pc = pkClassMapper.selectOne(pc);
        if (EmptyUtil.isEmpty(pc.getId())) {
            sb.append("班级：" + bid.getClassName() + "不存在！\n");
            verify = false;
        }

        PkTeacherInfo pti = teacherInfoServiceImpl.selectOne(new EntityWrapper<PkTeacherInfo>().eq("school_code", schoolCode)
                .eq("teacher_name", bid.getChineseTeacher()).or().eq("teacher_name", bid.getArtTeacher())
                .or().eq("teacher_name", bid.getBiologyTeacher()).or().eq("teacher_name", bid.getChemistryTeacher())
                .or().eq("teacher_name", bid.getForeignTeacher()).or().eq("teacher_name", bid.getGeneralTeacher())
                .or().eq("teacher_name", bid.getGeographyTeacher()).or().eq("teacher_name", bid.getHistoryTeacher())
                .or().eq("teacher_name", bid.getInformationTeacher()).or().eq("teacher_name", bid.getMathTeacher())
                .or().eq("teacher_name", bid.getMusicTeacher()).or().eq("teacher_name", bid.getPeTeacher())
                .or().eq("teacher_name", bid.getPhysicsTeacher()).or().eq("teacher_name", bid.getPoliticsTeacher()));
        if (EmptyUtil.isEmpty(pti)) {
            sb.append("教师不存在;\n");
        }


        map.put("roomId", pri.getRoomId());
        map.put("siteId", ps.getSiteId());
        map.put("classId", pc.getId());
        map.put("sb", sb);
        map.put("verify", verify);

        return map;

    }

}
