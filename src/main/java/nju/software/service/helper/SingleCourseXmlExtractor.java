package nju.software.service.helper;

import nju.software.model.standard.StandardCourseInfo;

/**
 * Created by Anson Shaw on 2017/6/13.
 */
public interface SingleCourseXmlExtractor {

    StandardCourseInfo extract(int courseId);
}
