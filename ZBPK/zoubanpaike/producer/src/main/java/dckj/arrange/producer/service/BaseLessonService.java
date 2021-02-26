package dckj.arrange.producer.service;

import dckj.arrange.common.model.request.BaseLessonReq;

public interface BaseLessonService {
    BaseLessonReq toGetLesson(BaseLessonReq baseLessonReq);
    void toInsertLesson(BaseLessonReq baseLessonReq);
    void toUpdateLesson(BaseLessonReq baseLessonReq);
}
