package com.rhy.note.work.controller;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CountObject {
    private Integer total;
    private Integer type;
    private String orgId;
    private String orgName;
}
