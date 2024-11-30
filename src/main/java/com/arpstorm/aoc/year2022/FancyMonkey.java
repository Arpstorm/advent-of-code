package com.arpstorm.aoc.year2022;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class FancyMonkey {
    private List<List<Integer>> items;
    private String operationOperator;
    private String operationOperand;
    private int divisor;
    private int trueTarget;
    private int falseTarget;
    private long inspectCount;

}