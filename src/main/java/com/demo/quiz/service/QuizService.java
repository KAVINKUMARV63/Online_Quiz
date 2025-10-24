package com.demo.quiz.service;



import com.demo.quiz.model.Question;
import com.demo.quiz.repository.QuizRepository;

import java.util.List;

public class QuizService {
    private final QuizRepository repository;
    private final int pointsPerQuestion = 1;

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public List<Question> getQuestions() {
        return repository.getAllQuestions();
    }

    
    public int gradeAndSave(String username, char[] answers) {
        List<Question> qs = repository.getAllQuestions();
        int score = 0;
        for (int i = 0; i < qs.size() && i < answers.length; i++) {
            if (qs.get(i).isCorrect(answers[i])) {
                score += pointsPerQuestion;
            }
        }
        repository.saveScore(username, score);
        return score;
    }

    public List<String> getLeaderboardLines() {
        StringBuilder sb = new StringBuilder();
        var tree = repository.getScoresDescending();
        // Compose readable lines
        List<String> lines = new java.util.ArrayList<>();
        for (var entry : tree.entrySet()) {
            int score = entry.getKey();
            for (String user : entry.getValue()) {
                lines.add(user + " : " + score);
            }
        }
        return lines;
    }
}
