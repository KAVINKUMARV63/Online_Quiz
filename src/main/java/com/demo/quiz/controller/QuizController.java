package com.demo.quiz.controller;

import com.demo.quiz.model.Question;
import com.demo.quiz.service.QuizService;

import java.util.List;
import java.util.Scanner;

public class QuizController {
    private final QuizService service;
    private final Scanner scanner = new Scanner(System.in);

    public QuizController(QuizService service) {
        this.service = service;
    }

    public void start() {
        System.out.println("=== Welcome to the Online Quiz ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        List<Question> questions = service.getQuestions();
        char[] answers = new char[questions.size()];

        System.out.println("\nInstructions: Type the option letter (A/B/C/D) and press Enter.\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println(q.toString());
            char ans;
            while (true) {
                System.out.print("Your answer: ");
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("Please enter A/B/C/D.");
                    continue;
                }
                ans = Character.toUpperCase(line.charAt(0));
                if (!q.getOptions().containsKey(ans)) {
                    System.out.println("Invalid option. Please enter one of " + q.getOptions().keySet());
                } else break;
            }
            answers[i] = ans;
            System.out.println();
        }

        int score = service.gradeAndSave(name, answers);
        System.out.println("Quiz finished. " + name + ", your score: " + score + "/" + questions.size());

        System.out.println("\n=== Leaderboard ===");
        var lines = service.getLeaderboardLines();
        if (lines.isEmpty()) {
            System.out.println("No scores yet.");
        } else {
            int rank = 1;
            for (String line : lines) {
                System.out.println(rank++ + ". " + line);
            }
        }

        System.out.println("\nThank you for playing!");
    }
}
