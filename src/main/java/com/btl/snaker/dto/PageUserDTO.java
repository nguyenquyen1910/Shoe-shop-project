package com.btl.snaker.dto;

import java.util.List;

public class PageUserDTO {
    private List<UserDTO> userDTOS;
    private int totalPage;
    private long totalElements;
    public PageUserDTO(List<UserDTO> userDTOS, int totalPage, long totalElements) {
        this.userDTOS = userDTOS;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
    }

    public List<UserDTO> getUserDTOS() {
        return userDTOS;
    }

    public void setUserDTOS(List<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
