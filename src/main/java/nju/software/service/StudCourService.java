package nju.software.service;

import nju.software.model.StudCour;

import java.util.List;

/**
 * Created by ZhangYF on 2017/6/4.
 */
public interface StudCourService {
    public StudCour getStudCour(int id);

    public List<StudCour> getAllStudCour();

    public boolean addStudCour(StudCour studCour);

    public boolean updateStudCour(StudCour studCour);

    public boolean deleteStudCour(int studentid, int courseid);
}
