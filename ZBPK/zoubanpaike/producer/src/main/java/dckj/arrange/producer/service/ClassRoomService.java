package dckj.arrange.producer.service;

import dckj.arrange.common.model.request.ClassRoomReq;

import java.util.List;

public interface ClassRoomService {
    List<ClassRoomReq> toGetClassRoom(ClassRoomReq classRoomReq);
    void toInsertClassRoom(List<ClassRoomReq> classRoomReqs);
}
