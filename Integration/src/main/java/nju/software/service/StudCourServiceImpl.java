package nju.software.service;

import nju.software.dao.StudCourDao;
import nju.software.model.StudCour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Service(value = "studCourService")
public class StudCourServiceImpl implements StudCourService{

    @Autowired
    private StudCourDao studCourDao;

    @Override
    public StudCour getStudCour(int id) {
        return studCourDao.findOne(id);
    }

    @Override
    public List<StudCour> getAllStudCour() {
        return studCourDao.findAll();
    }

    @Override
    public boolean addStudCour(StudCour studCour) {
        studCourDao.save(studCour);
        return true;
    }

    @Override
    public boolean updateStudCour(StudCour studCour) {
        studCourDao.save(studCour);
        return true;
    }

    @Override
    public boolean deleteStudCour(int studentid, int courseid) {
        studCourDao.deleteStudCour(studentid,courseid);
        return true;
    }

}
