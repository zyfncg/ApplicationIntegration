package nju.software.service;

import nju.software.dao.SelectionDao;
import nju.software.model.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
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
        Map<Integer, CourseXmlExtractor> handlerMap = CourseHelper.getHandlerMap();
        if (handlerMap.containsKey(institutionId)) {
            return handlerMap.get(institutionId).extract();
        }
        return "没有这个院系ID";
    }

    @Override
    public String getStudyCourse(int institutionId, int studentId) {
        List<Selection> selectionList = selectionDao.findAllByStudentidAndStudentInstitution(studentId,institutionId);
        Map<Integer, SelectXmlExtractor> handlerMap = SelectHelper.getHandlerMap();
        if (handlerMap.containsKey(institutionId)) {
            return handlerMap.get(institutionId).extract(selectionList);
        }

        return "此院系ID不存在";
    }

    @Override
    public boolean addCourse(int institutionId, int studentId, int courseInstitution, int courseId) {
        Selection selection = new Selection(studentId,institutionId,courseId,courseInstitution);
        if(selectionDao.findByStudentidAndStudentInstitutionAndCourseidAndCourseInstitution(
                studentId, institutionId,courseId,courseInstitution)==null){
            selectionDao.save(selection);
        }
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
