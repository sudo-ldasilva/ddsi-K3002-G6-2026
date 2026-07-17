package com.ddsi.logistica.dto;

import java.time.LocalDateTime;

public class TimeWindowDTO {
    private LocalDateTime start;
    private LocalDateTime end;

    public TimeWindowDTO(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
