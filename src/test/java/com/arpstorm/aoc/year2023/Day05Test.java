package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day05.txt");
        assertEquals(403695602, Day05.solvePart1(input));
        assertEquals(219529182, Day05.solvePart2(input));
    }
}
