package com.hexad.lmsproj.datamodels.dto;

public class DashboardDto {

    private Long userId;
    private String searchString;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
