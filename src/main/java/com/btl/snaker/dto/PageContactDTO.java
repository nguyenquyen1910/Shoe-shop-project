package com.btl.snaker.dto;

import java.util.List;

public class PageContactDTO {
    private List<ContactDTO> contactDTOS;
    private int totalPage;
    private long totalElements;
    public PageContactDTO(List<ContactDTO> contactDTOS, int totalPage, long totalElements) {
        this.contactDTOS = contactDTOS;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
    }

    public List<ContactDTO> getContactDTOS() {
        return contactDTOS;
    }

    public void setContactDTOS(List<ContactDTO> contactDTOS) {
        this.contactDTOS = contactDTOS;
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
