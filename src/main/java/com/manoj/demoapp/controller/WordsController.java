package com.manoj.demoapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import org.hibernate.SessionFactory;
import com.manoj.demoapp.dto.Word;
import com.manoj.demoapp.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@RestController
public class WordsController {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private WordsService wordsService;

	//@GetMapping(value = "/words")
	@RequestMapping(value = "/words", method={RequestMethod.GET})
	public ResponseEntity getAllWords() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		List<Object> list = new ArrayList<Object>();
		list = wordsService.getAllWords();
		return ResponseEntity.ok().headers(headers).body(list);
	}

	@GetMapping(value = "/words/{id}")
	public ResponseEntity deleteWord(@PathVariable(value = "id", required = true) Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		Map<String, Boolean> response = wordsService.deleteWord(id);
		return ResponseEntity.ok().headers(headers).body(response);
	}

	@CrossOrigin(origins = "*")
	@PutMapping(value = "/word", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveWord(@RequestBody Word word) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response = wordsService.updateWord(word);
		return ResponseEntity.ok().headers(headers).body(response);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/word", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateWord(@RequestBody Word word) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response = wordsService.saveWord(word);
		return ResponseEntity.ok().headers(headers).body(response);
	}


}
