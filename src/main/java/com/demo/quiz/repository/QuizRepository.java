package com.demo.quiz.repository;

import com.demo.quiz.model.Question;

import java.util.*;

public class QuizRepository {
    // Questions stored in ArrayList
    private final List<Question> questions = new ArrayList<>();

    
  
    private final TreeMap<Integer, List<String>> scores = new TreeMap<>();

    public QuizRepository() {
        // seed sample questions
        seedQuestions();
    }

    public List<Question> getAllQuestions() {
        // return a copy to avoid external modification
        return new ArrayList<>(questions);
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public Optional<Question> findById(int id) {
        return questions.stream().filter(q -> q.getId() == id).findFirst();
    }

    public void saveScore(String username, int score) {
        List<String> users = scores.get(score);
        if (users == null) {
            users = new ArrayList<>();
            scores.put(score, users);
        }
        users.add(username);
    }

    public NavigableMap<Integer, List<String>> getScoresDescending() {
        return scores.descendingMap();
    }

    private void seedQuestions() {
        // Add at least 5 sample questions. This method can be modified to load from file later.
        HashMap<Character, String> opts1 = new HashMap<>();
        opts1.put('A', "Java Virtual Machine");
        opts1.put('B', "Just Virtual Machine");
        opts1.put('C', "Java Very Much");
        opts1.put('D', "Joint Virtual Memory");
        addQuestion(new Question(1, "What does JVM stand for?", opts1, 'A'));

        HashMap<Character, String> opts2 = new HashMap<>();
        opts2.put('A', "int");
        opts2.put('B', "String");
        opts2.put('C', "double");
        opts2.put('D', "boolean");
        addQuestion(new Question(2, "Which data type is used to store true/false?", opts2, 'D'));

        HashMap<Character, String> opts3 = new HashMap<>();
        opts3.put('A', "extends");
        opts3.put('B', "implements");
        opts3.put('C', "inherits");
        opts3.put('D', "includes");
        addQuestion(new Question(3, "Which keyword indicates a class implements an interface?", opts3, 'B'));

        HashMap<Character, String> opts4 = new HashMap<>();
        opts4.put('A', "java.util");
        opts4.put('B', "java.lang");
        opts4.put('C', "java.io");
        opts4.put('D', "java.net");
        addQuestion(new Question(4, "Which package contains ArrayList?", opts4, 'A'));

        HashMap<Character, String> opts5 = new HashMap<>();
        opts5.put('A', "JRE");
        opts5.put('B', "JDK");
        opts5.put('C', "JVM");
        opts5.put('D', "JCL");
        addQuestion(new Question(5, "Which contains the Java compiler (javac)?", opts5, 'B'));
    }
}

