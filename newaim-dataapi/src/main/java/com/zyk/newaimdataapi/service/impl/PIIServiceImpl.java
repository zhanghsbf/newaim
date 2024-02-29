package com.zyk.newaimdataapi.service.impl;

import com.zyk.newaimdataapi.entity.dto.Contactor;
import com.zyk.newaimdataapi.entity.dto.RecommendData;
import com.zyk.newaimdataapi.service.PIIService;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @date: 2024/2/29 10:33
 * @author: zyk
 */
@Service
public class PIIServiceImpl implements PIIService {

    @Override
    public Contactor getContactor(String username) {
        // todo 改造mybatis

        // PostgreSQL数据库连接信息
//        String url = "jdbc:postgresql://43.139.219.123:5432/dbt";
        String url = "jdbc:postgresql://localhost:5432/dbt";
        String user = "dbt";
        String password = "dbt";
        List<RecommendData> rsList = new ArrayList<>();
        Contactor contactor = null;
        try {
            // 连接数据库
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "select\n" +
                    "    id,\n" +
                    "    username,\n" +
                    "    name,\n" +
                    "    gender,\n" +
                    "    '***' as address,\n" +
                    "    '***' as mail,\n" +
                    "    birthdate\n" +
                    "from contacts\n" +
                    "where username = ?\n" +
                    "limit 1";
            // 创建查询语句
            PreparedStatement  preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            // 遍历查询结果
            while (rs.next()) {
                contactor = new Contactor(rs.getString(1)
                        ,rs.getString(2)
                        ,rs.getString(3)
                        ,rs.getString(4)
                        ,rs.getString(5)
                        ,rs.getString(6)
                        ,rs.getString(7)
                );
                System.out.println(contactor);
            }

            // 关闭连接
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return contactor;
    }

}
