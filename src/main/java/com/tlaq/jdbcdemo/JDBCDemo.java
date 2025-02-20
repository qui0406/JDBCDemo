/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tlaq.jdbcdemo;

import com.tlaq.pojo.Choice;
import com.tlaq.pojo.Question;
import com.tlaq.services.QuestionService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class JDBCDemo {

    public static void main(String[] args) throws SQLException {
        QuestionService s= new QuestionService();
        
        Scanner sc= new Scanner(System.in);
        List<Question> questions= s.getQuestions("", 2);
        
        for(Question ques: questions){
            System.out.println(ques);
            
            try{
                List<Choice> choices= s.getChoices(ques.getId());
                
                for (int i=0; i<choices.size(); i++){
                    System.out.printf("%d. %s\n", i+1, choices.get(i));
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
