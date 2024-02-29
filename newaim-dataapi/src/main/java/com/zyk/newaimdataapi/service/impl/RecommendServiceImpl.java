package com.zyk.newaimdataapi.service.impl;

import com.zyk.newaimdataapi.entity.dto.RecommendData;
import com.zyk.newaimdataapi.service.RecommendService;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Override
    public List<RecommendData> getHotCategory() {
        // do get hot category from postgres
        return queryPGHotCategory();
    }

    public static List<RecommendData> queryPGHotCategory(){
        // todo 改造mybatis
        // PostgreSQL数据库连接信息
//        String url = "jdbc:postgresql://43.139.219.123:5432/dbt";
        String url = "jdbc:postgresql://localhost:5432/dbt";
        String user = "dbt";
        String password = "dbt";
        List<RecommendData> rsList = new ArrayList<>();

        try {
            // 连接数据库
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // 创建查询语句
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM top3_hot_categorys");

            // 遍历查询结果
            while (resultSet.next()) {
                // 读取每一行数据
                String category = resultSet.getString("category");
                int rank = resultSet.getInt("rank");

                RecommendData recommendData = new RecommendData(category, rank);
                rsList.add(recommendData);
                // 处理每一行数据，这里简单打印出来
//                System.out.println(recommendData);
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rsList;
    }

//    public static void main(String[] args) {
//        queryPGHotCategory();
//    }
}

