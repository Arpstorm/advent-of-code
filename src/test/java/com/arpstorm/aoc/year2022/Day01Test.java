package com.arpstorm.aoc.year2022;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void day01() {
        List<String> input = Utils.readInput("/year2022/input.day01.txt");
        assertEquals(66616, Day01.solvePart1(input));
        assertEquals(199172, Day01.solvePart2(input));
    }
}
