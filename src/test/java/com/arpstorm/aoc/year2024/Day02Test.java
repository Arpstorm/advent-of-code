package com.arpstorm.aoc.year2024;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2024/input.day02.txt");
        List<List<Integer>> reports = Day02.initReports(input);
        assertEquals(218, Day02.solvePart1(reports));
        assertEquals(290, Day02.solvePart2(reports));
    }
}
