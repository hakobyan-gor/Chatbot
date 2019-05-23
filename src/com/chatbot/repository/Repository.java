package com.chatbot.repository;

import java.io.*;

public class Repository {

    private static final String KEY_VALUE_DIVIDER = " @#001!100#@ ";
    private static final String LINE_DIVIDER = "\n";
    private String questionsAnswersDataFilePath = "src\\com\\chatbot\\questionsAndAnswers\\questionsAndAnswersData.txt";

    public void setQuestionsAndAnswers(String questions, String answer){

        try {
            FileWriter fileWriter = new FileWriter(questionsAnswersDataFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(questions + KEY_VALUE_DIVIDER + answer + LINE_DIVIDER);
            bufferedWriter.flush();
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getAnswer(String questions) {

        try{
            FileReader fileReader = new FileReader(questionsAnswersDataFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                questions = questions.toLowerCase();
                questions = questions.replaceAll("[^a-zA-Z0-9]", "");
                if (line.split(KEY_VALUE_DIVIDER)[0].replace(" ","").equals(questions)) {
                    return line.split(KEY_VALUE_DIVIDER)[1];
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        if (questions.toLowerCase().equals("exit")){
            return "Bye Bye!";
        }

        return "I can not answer your question!\nPlease, ask another question.";
    }

}
