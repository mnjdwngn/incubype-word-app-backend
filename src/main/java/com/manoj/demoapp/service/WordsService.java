package com.manoj.demoapp.service;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Query;
import com.manoj.demoapp.dto.Word;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WordsService {
    @Autowired
	private SessionFactory sessionFactory;

    @Transactional
    public Map<String, Boolean> deleteWord(Long id) {
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        List<Object> list = new ArrayList<Object>();
        Session sessionObj = sessionFactory.getCurrentSession();
		List<Object[]> resultList;
        int result;
		String sqlQuery = "delete from words where id = :id";
		Query query = sessionObj.createNativeQuery(sqlQuery);
        query.setParameter("id", id);
		result = query.executeUpdate();
        if(result != 0) {
            response.put("isSuccess", true);
        } else {
            response.put("isSuccess", false);
        }
        return response;
    }

    public List<Object> getAllWords() {
        List<Object> list = new ArrayList<Object>();
        Session sessionObj = sessionFactory.getCurrentSession();
		List<Object[]> resultList;
		String sqlQuery = "select id, word from words";
		Query query = sessionObj.createNativeQuery(sqlQuery);
		resultList = query.getResultList();
		for(Object[] result: resultList) {
            Word wordDTO = new Word();
            wordDTO.setId(Integer.parseInt(result[0] != null ? result[0].toString() : "0"));
            wordDTO.setWord(result[1].toString());
            list.add(wordDTO);
		}
        return list;
    }

    @Transactional
    public Map<String, Boolean> saveWord(Word word) {
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        int result;
        Session sessionObj = sessionFactory.getCurrentSession();
        String sqlQuery = "insert into words (word) values (:word)";
        Query query = sessionObj.createNativeQuery(sqlQuery);
        query.setParameter("word", word.getWord());
        result = query.executeUpdate();
        if(result != 0) {
            response.put("isSuccess", true);
        } else {
            response.put("isSuccess", false);
        }
        return response;
    }

    @Transactional
    public Map<String, Boolean> updateWord(Word word) {
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        int result;
        Session sessionObj = sessionFactory.getCurrentSession();
        String sqlQuery = "update words set word = :word where id = :id";
        Query query = sessionObj.createNativeQuery(sqlQuery);
        query.setParameter("word", word.getWord());
        query.setParameter("id", word.getId());
        result = query.executeUpdate();
        if(result != 0) {
            response.put("isSuccess", true);
            System.out.println("updated successfully");
        } else {
            response.put("isSuccess", false);
            System.out.println("Not updated");
        }
        return response;
    }
}
