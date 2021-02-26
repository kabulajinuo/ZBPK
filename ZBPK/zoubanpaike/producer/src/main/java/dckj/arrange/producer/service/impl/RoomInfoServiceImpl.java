package dckj.arrange.producer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkRoomInfo;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.RoomInfoVo;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.common.util.TrimUtil;
import dckj.arrange.producer.mapper.PkRoomInfoMapper;
import dckj.arrange.producer.service.RoomInfoService;
import dckj.arrange.producer.service.RoomTypeService;
import dckj.arrange.producer.service.SiteService;
import dckj.arrange.producer.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {
    @Autowired
    private PkRoomInfoMapper pkRoomInfoMapper;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SubjectService subjectService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toInsertRoomInfo(RoomInfoVo roomInfoVo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("room_name", roomInfoVo.getRoomName());
        paramMap.put("school_code", roomInfoVo.getSchoolCode());
        //教室名唯一性
        List<PkRoomInfo> pkRoomInfos = pkRoomInfoMapper.selectByMap(paramMap);
        if (pkRoomInfos.size() > 0) {
            throw new CheckedException("该教室名已经存在");
        }
        //时间与主键
        roomInfoVo.setRoomId(IDGenerate.buildID());
        roomInfoVo.setCreateTime(new Date());
        return pkRoomInfoMapper.insert(roomInfoVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toInsertBatchTeacherInfo(List<RoomInfoVo> roomInfoVos) {
        Map<String, Object> paramMap = new HashMap<>();
        for (RoomInfoVo roomInfoVo : roomInfoVos) {
            paramMap.put("room_name", roomInfoVo.getRoomName());
            paramMap.put("school_code", roomInfoVo.getSchoolCode());
            //教室名唯一性
            List<PkRoomInfo> pkRoomInfos = pkRoomInfoMapper.selectByMap(paramMap);
            if (pkRoomInfos.size() > 0) {
                throw new CheckedException("该教室名已经存在");
            }
            //时间与主键
            roomInfoVo.setRoomId(IDGenerate.buildID());
            roomInfoVo.setCreateTime(new Date());
        }
        pkRoomInfoMapper.insertBatch(roomInfoVos);
    }


    @Override
    public PageInfo<RoomInfoVo> toGetRoomInfo(RoomInfoVo roomInfoVo) {
        PageHelper.startPage(roomInfoVo.getPageFilterVo().getPageNumber(), roomInfoVo.getPageFilterVo().getPageSize());
        List<RoomInfoVo> roomInfoVos = pkRoomInfoMapper.selectBySchooleCode(roomInfoVo);
        PageInfo<RoomInfoVo> roomInfoVoPageInfo = new PageInfo<>(roomInfoVos);
        return roomInfoVoPageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toDeleteRoomInfo(RoomInfoVo roomInfoVo) {
        return pkRoomInfoMapper.deleteById(roomInfoVo.getRoomId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toUpdateRoomInfo(RoomInfoVo roomInfoVo) {
        return pkRoomInfoMapper.updateById(roomInfoVo);
    }

    @Override
    public List<PkRoomInfo> toGetRoomInfoWithOutPage(RoomInfoVo roomInfoVo) {
        return pkRoomInfoMapper.selectWithOutPage(roomInfoVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toImportRoomInfo(List<RoomInfoVo> list) {
        //转换 科目 教室类型 教学场地
        list.stream().forEach(roomInfoVo -> {
            TrimUtil.beanAttributeValueTrim(roomInfoVo);
            roomInfoVo.setRoomType(roomTypeService.toGetTypeIds(roomInfoVo.getTypeName(),roomInfoVo.getSchoolCode()));
            roomInfoVo.setSiteId(siteService.toGetSiteId(roomInfoVo.getSiteName(),roomInfoVo.getSchoolCode()));
            roomInfoVo.setSubjectIds(subjectService.toGetSubjectIds(roomInfoVo.getSubjectIds(),roomInfoVo.getSchoolCode()));
        });
        toInsertBatchTeacherInfo(list);
    }
}
