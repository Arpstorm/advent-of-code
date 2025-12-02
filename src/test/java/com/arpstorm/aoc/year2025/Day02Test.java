package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2025/input.day02.txt");
        assertEquals(38310256125L, Day02.solvePart1(input));
        assertEquals(58961152806L, Day02.solvePart2(input));
    }
}
