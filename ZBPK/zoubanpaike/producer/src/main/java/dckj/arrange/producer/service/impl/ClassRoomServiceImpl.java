package dckj.arrange.producer.service.impl;

import dckj.arrange.common.model.request.ClassRoomReq;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.IDGenerate;
import dckj.arrange.producer.mapper.PkClassRoomMapper;
import dckj.arrange.producer.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    private PkClassRoomMapper pkClassRoomMapper;

    @Override
    public List<ClassRoomReq> toGetClassRoom(ClassRoomReq classRoomReq) {
        return pkClassRoomMapper.selectByGradeId(classRoomReq);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toInsertClassRoom(List<ClassRoomReq> classRoomReqs) {
        boolean flag = false;
        for (ClassRoomReq classRoomReq : classRoomReqs) {
            if (!EmptyUtil.isEmpty(classRoomReq.getId())) {
                classRoomReq.setUpdateTime(new Date());
                pkClassRoomMapper.updateById(classRoomReq);
                flag = true;
            } else {
                classRoomReq.setId(IDGenerate.buildID());
                classRoomReq.setCreateTime(new Date());
            }
        }
        if(!flag){
            pkClassRoomMapper.insertBatch(classRoomReqs);
        }
    }
}
