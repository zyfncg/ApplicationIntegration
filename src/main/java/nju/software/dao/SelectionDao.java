package nju.software.dao;

import nju.software.model.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ZhangYF on 2017/6/11.
 */
@Repository
public interface SelectionDao extends JpaRepository<Selection,Integer>{

}
