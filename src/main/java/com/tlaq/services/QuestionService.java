/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlaq.services;

import com.mysql.cj.jdbc.JdbcConnection;
import com.tlaq.pojo.Choice;
import com.tlaq.pojo.Question;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionService {
    public List<Question> getQuestions(String kw, int num) throws SQLException {
        List<Question> questions= new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()){
            CallableStatement stm= conn.prepareCall("{call get_question(?, ?) }");
            stm.setString(1, kw);
            stm.setInt(2, num);
            
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                Question q;
                q = new Question(rs.getString("id"), rs.getString("content"), rs.getInt("category_id"));
                questions.add(q);
            }
        }
        return questions;
    }
    
    public List<Choice> getChoices(String questionId) throws SQLException{
        List<Choice> choice= new ArrayList<>();
        try(Connection conn= JdbcUtils.getConn()){
            CallableStatement stm= conn.prepareCall("{ call get_choice(?);}");
            stm.setString(1, questionId);
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                Choice c= new Choice(rs.getString("id"), rs.getString("content"), rs.getBoolean("isCorrect"), rs.getString("question_id"));
            }
        }
        return choice;
        
        
    }
}
