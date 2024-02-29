package com.zyk.newaimdataapi.entity.dto;

/******************************************************************
 * <p>
 *      活动推荐数据
 * </p>
 * @author oak
 * @version 1.0
 * @date 2020/10/23 15:33
 ******************************************************************/
public class RecommendData {
    private String category;
    private Integer rank;

    public RecommendData(String category, Integer rank) {
        this.category = category;
        this.rank = rank;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
