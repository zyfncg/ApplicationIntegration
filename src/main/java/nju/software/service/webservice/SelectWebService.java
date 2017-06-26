package nju.software.service.webservice;

import nju.software.dao.SelectionDao;
import nju.software.model.Selection;
import nju.software.service.helper.SelectXmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/26.
 *
 * 从其它服务器根据ID获得课程信息
 */
@Service(value = "selectWebService")
public class SelectWebService {

    private final SelectionDao selectionDao;

    @Autowired
    public SelectWebService(SelectionDao selectionDao) {
        this.selectionDao = selectionDao;
    }

    public String getStudyCourses(int institutionId, int studentId) {
        Map<Integer, SelectXmlGenerator> handlerMap = SelectWSHelper.getHandlerMap();
        if (handlerMap.containsKey(institutionId)) {
            List<Selection> selections = selectionDao.findAllByStudentidAndStudentInstitution(
                    studentId, institutionId
            );
            return handlerMap.get(institutionId).generate(selections);
        }
        return "没有此院系ID";
    }
}
