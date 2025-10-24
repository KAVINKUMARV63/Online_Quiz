package com.demo.quiz;
import com.demo.quiz.controller.QuizController;
import com.demo.quiz.repository.QuizRepository;
import com.demo.quiz.service.QuizService;

	public class Main {
	    public static void main(String[] args) {
	        QuizRepository repo = new QuizRepository();
	        QuizService service = new QuizService(repo);
	        QuizController controller = new QuizController(service);
	        controller.start();
	    }
	}


