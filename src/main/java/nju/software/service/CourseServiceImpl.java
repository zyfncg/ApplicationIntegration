package nju.software.service;

import nju.software.dao.SelectionDao;
import nju.software.model.Selection;
import nju.software.model.standard.StandardCourseInfo;
import nju.software.service.helper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Service(value = "courseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    private SelectionDao selectionDao;

    @Override
    public String getAllCourse(int institutionId) {
        Map<Integer, AllCourseXmlGenerator> handlerMap = AllCourseHelper.getHandlerMap();
        if (handlerMap.containsKey(institutionId)) {
            return handlerMap.get(institutionId).generate();
        }
        return "没有这个院系ID";
    }

    @Override
    public String getCourseNameById(int institutionId, int courseId) {
        Map<Integer, SingleCourseXmlExtractor> handlerMap = SingleCourseHelper.getHandlerMap();
        if (handlerMap.containsKey(institutionId)) {
            StandardCourseInfo info = handlerMap.get(institutionId).extract(courseId);
            return info.getName();
        }
        return "未知";
    }

    @Override
    public String getStudyCourse(int institutionId, int studentId) {
        List<Selection> selectionList = selectionDao.findAllByStudentidAndStudentInstitution(studentId,institutionId);
        Map<Integer, SelectXmlGenerator> handlerMap = SelectHelper.getHandlerMap();
        if (handlerMap.containsKey(institutionId)) {
            return handlerMap.get(institutionId).generate(selectionList);
        }

        return "此院系ID不存在";
    }

    @Override
    public boolean addCourse(int institutionId, int studentId, int courseInstitution, int courseId) {

        if(selectionDao.findByStudentidAndStudentInstitutionAndCourseidAndCourseInstitution(
                studentId, institutionId,courseId,courseInstitution)==null){
            Selection selection = new Selection(studentId,institutionId,courseId,courseInstitution);
            selectionDao.save(selection);
//            System.out.println("Item not existed");
        }
        System.out.println(studentId);
        return true;
    }

    @Override
    public boolean deleteCourse(int institutionId, int studentId, int courseInstitution, int courseId) {
        Selection selection = selectionDao.findByStudentidAndStudentInstitutionAndCourseidAndCourseInstitution
                (studentId,institutionId,courseId,courseInstitution);
        if(selection != null){
            selectionDao.delete(selection);
        }
        return true;
    }

    @Override
    public int studyCourseNum(int studentid, int institution) {
        return selectionDao.findAllByStudentidAndStudentInstitution(studentid,institution).size();
    }

    @Override
    public int getStudentNum(int courseid, int institution) {
        return selectionDao.findAllByCourseidAndCourseInstitution(courseid, institution).size();
    }

    @Override
    public Map<Integer, Integer> statisticCourses(int intitution) {
        List<Selection> list = selectionDao.findAllByCourseInstitution(intitution);
        Map<Integer, Integer> map = new HashMap<>();
        for(Selection selection:list){
            if(!map.containsKey(selection.getCourseid())){
                map.put(selection.getCourseid(), getStudentNum(selection.getCourseid(),selection.getCourseInstitution()));
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> statisticStudents(int intitution) {
        List<Selection> list = selectionDao.findAllByStudentInstitution(intitution);
        Map<Integer, Integer> map = new HashMap<>();
        for(Selection selection:list){
            if(!map.containsKey(selection.getStudentid())){
                map.put(selection.getStudentid(),studyCourseNum(selection.getStudentid(),selection.getStudentInstitution()));
            }
        }
        return map;
    }
}
