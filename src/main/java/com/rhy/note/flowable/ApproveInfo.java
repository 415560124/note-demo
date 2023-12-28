package com.rhy.note.flowable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApproveInfo {
    private String description;
    private Integer nrOfHolidays;
    private String employee;
}
