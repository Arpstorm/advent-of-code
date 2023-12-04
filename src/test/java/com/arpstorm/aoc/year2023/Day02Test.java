package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day02.txt");
        assertEquals(2156, Day02.solvePart1(input));
        assertEquals(66909, Day02.solvePart2(input));
    }
}
