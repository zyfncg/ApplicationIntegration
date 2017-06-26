package nju.software.service.webservice;

import nju.software.service.helper.AllCourseXmlGenerator;

import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/26.
 *
 * 从Web Service获得课程信息
 */
public class CourseWebService {

    public static String getAllCourses(int institutionId) {
        Map<Integer, AllCourseXmlGenerator> handlerMap = CourseWSHelper.getHandlerMap();
        if (handlerMap.containsKey(institutionId)) {
            return handlerMap.get(institutionId).generate();
        }
        return "没有这个院系ID";
    }

}
