package com.rhy.note.work.controller;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class CountResult {
    private String orgName;

    private int used;
    private int unused;
    private int total;
    private BigDecimal usedRate = new BigDecimal(0);


    private int cityUsed;
    private int cityUnused;
    private int cityTotal;
    private BigDecimal cityUsedRate = new BigDecimal(0);


    private int villageUsed;
    private int villageUnused;
    private int villageTotal;
    private BigDecimal villageUsedRate = new BigDecimal(0);
}
