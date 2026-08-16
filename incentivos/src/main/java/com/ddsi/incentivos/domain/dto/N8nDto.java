package com.ddsi.incentivos.domain.dto;

public class N8nDto {
    private String user;
    private String badge;
    private String description;

    public N8nDto() {}

    public N8nDto(String user, String badge, String description) {
        this.user = user;
        this.badge = badge;
        this.description = description;
    }

    public String getUser() { return user; }
    public String getBadge() { return badge; }
    public String getDescription() { return description; }
}
