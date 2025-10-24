package com.demo.quiz.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Question {
    private final int id;
    private final String text;
    private final HashMap<Character, String> options;
    private final char correctOption; // e.g. 'A', 'B', 'C', 'D'

    public Question(int id, String text, HashMap<Character, String> options, char correctOption) {
        this.id = id;
        this.text = text;
        this.options = new HashMap<>(options);
        this.correctOption = Character.toUpperCase(correctOption);
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public HashMap<Character, String> getOptions() { return new HashMap<>(options); }
    public char getCorrectOption() { return correctOption; }

    public boolean isCorrect(char answer) {
        return Character.toUpperCase(answer) == correctOption;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(". ").append(text).append("\n");
        for (Map.Entry<Character, String> e : options.entrySet()) {
            sb.append("  ").append(e.getKey()).append(") ").append(e.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}