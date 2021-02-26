package dckj.arrange.modules.feign;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.*;
import dckj.arrange.modules.feign.fallback.FeginServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "zbpk-producer-service", fallback = FeginServiceImpl.class)
public interface FeginService {
    /**
     * 班级所属教室
     */
    @PostMapping("class/room/toGetClassRoom")
    ApiResult toGetClassRoom(ClassRoomReq classRoomReq);

    @PostMapping("class/room/toInsertClassRoom")
    ApiResult toInsertClassRoom(List<ClassRoomReq> classRoomReqs);

    /**
     * 课时基础
     */
    @PostMapping("lesson/base/toGetBaseLesson")
    ApiResult toGetLesson(BaseLessonReq baseLessonReq);

    @PostMapping("lesson/base/toInsertBaseLesson")
    ApiResult toInsertLesson(BaseLessonReq baseLessonReq);

    @PostMapping("lesson/base/toUpdateBaseLesson")
    ApiResult toUpdateLesson(BaseLessonReq baseLessonReq);

    /**
     * 学生信息
     */
    @PostMapping("student/toInsertBatchStudent")
    ApiResult toInsertBatchStudent(List<StudentVo> studentVoList);

    @PostMapping("student/toInsertStudent")
    ApiResult toInsertStudent(StudentVo studentVo);

    @PostMapping("student/toGetStudent")
    ApiResult toGetStudent(StudentVo studentVo);

    @PostMapping("student/toDeleteStudent")
    ApiResult toDeleteStudent(StudentVo studentVo);

    @PostMapping("student/toDeleteBatchStudent")
    ApiResult toDeleteBatchStudent(List<StudentVo> students);

    @PostMapping("student/toUpdateStudent")
    ApiResult toUpdateStudent(StudentVo studentVo);

    /**
     * 教师信息
     */
    @PostMapping("teacher/info/toInsertBatchTeacherInfo")
    ApiResult toInsertBatchTeacherInfo(List<TeacherInfoVo> teacherInfoVoList);

    @PostMapping("teacher/info/toInsertTeacherInfo")
    ApiResult toInsertTeacherInfo(TeacherInfoVo teacherInfoVo);

    @PostMapping("teacher/info/toGetTeacherInfo")
    ApiResult toGetTeacherInfo(TeacherInfoVo teacherInfoVo);

    @PostMapping("teacher/info/toDeleteTeacherInfo")
    ApiResult toDeleteTeacherInfo(TeacherInfoVo teacherInfoVo);

    @PostMapping("teacher/info/toUpdateTeacherInfo")
    ApiResult toUpdateTeacherInfo(TeacherInfoVo teacherInfoVo);
    @PostMapping("teacher/info/searchTeacher")
    ApiResult searchTeacher(@RequestBody BaseReq param);


    /**
     * 年级
     */
    @PostMapping("grade/toInsertGrade")
    ApiResult toInsertGrade(GradeVo gradeVo);

    @PostMapping("grade/toGetGrade")
    ApiResult toGetGrade(GradeVo gradeVo);

    @PostMapping("grade/toDeleteGrade")
    ApiResult toDeleteGrade(GradeVo gradeVo);

    @PostMapping("grade/toUpdateGrade")
    ApiResult toUpdateGrade(GradeVo gradeVo);

    /**
     * 教师职务
     *
     * @param teacherPositionVo
     * @return
     */
    @PostMapping("teacher/position/toInsertTeacherPosition")
    ApiResult toInsertTeacherPosition(TeacherPositionVo teacherPositionVo);

    @PostMapping("teacher/position/toGetTeacherPosition")
    ApiResult toGetTeacherPosition(TeacherPositionVo teacherPositionVo);

    @PostMapping("teacher/position/toDeleteTeacherPosition")
    ApiResult toDeleteTeacherPosition(TeacherPositionVo teacherPositionVo);

    @PostMapping("teacher/position/toUpdateTeacherPosition")
    ApiResult toUpdateTeacherPosition(TeacherPositionVo teacherPositionVo);

    /**
     * 学科
     *
     * @param subjectVo
     * @return
     */
    @PostMapping("subject/toInsertSubject")
    ApiResult toInsertSubject(SubjectVo subjectVo);

    @PostMapping("subject/toGetSubject")
    ApiResult toGetSubject(SubjectVo subjectVo);

    @PostMapping("subject/toDeleteSubject")
    ApiResult toDeleteSubject(SubjectVo subjectVo);

    @PostMapping("subject/toUpdateSubject")
    ApiResult toUpdateSubject(SubjectVo subjectVo);

    /**
     * 教室类型
     *
     * @param roomTypeVo
     * @return
     */
    @PostMapping("room/type/toInsertRoomType")
    ApiResult toInsertRoomType(RoomTypeVo roomTypeVo);

    @PostMapping("room/type/toGetRoomType")
    ApiResult toGetRoomType(RoomTypeVo roomTypeVo);

    @PostMapping("room/type/toDeleteRoomType")
    ApiResult toDeleteRoomType(RoomTypeVo roomTypeVo);

    @PostMapping("room/type/toUpdateRoomType")
    ApiResult toUpdateRoomType(RoomTypeVo roomTypeVo);

    /**
     * 教学场地
     */
    @PostMapping("room/site/toInsertSite")
    ApiResult toInsertSite(SiteVo siteVo);

    @PostMapping("room/site/toGetSite")
    ApiResult toGetSite(SiteVo siteVo);

    @PostMapping("room/site/toDeleteSite")
    ApiResult toDeleteSite(SiteVo siteVo);

    @PostMapping("room/site/toUpdateSite")
    ApiResult toUpdateSite(SiteVo siteVo);

    @PostMapping("room/site/toGetSiteWithOutPage")
    ApiResult toGetSiteWithOutPage(SiteVo siteVo);

    /**
     * 教室信息
     */
    @PostMapping("room/info/toInsertRoomInfo")
    ApiResult toInsertRoomInfo(RoomInfoVo roomInfoVo);

    @PostMapping("room/info/toGetRoomInfo")
    ApiResult toGetRoomInfo(RoomInfoVo roomInfoVo);

    @PostMapping("room/info/toDeleteRoomInfo")
    ApiResult toDeleteRoomInfo(RoomInfoVo roomInfoVo);

    @PostMapping("room/info/toUpdateRoomInfo")
    ApiResult toUpdateRoomInfo(RoomInfoVo roomInfoVo);

    @PostMapping("room/info/toGetRoomInfoWithOutPage")
    ApiResult toGetRoomInfoWithOutPage(RoomInfoVo roomInfoVo);
}
