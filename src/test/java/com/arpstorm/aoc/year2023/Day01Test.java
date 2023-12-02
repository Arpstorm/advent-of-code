package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void day01() {
        List<String> input = Utils.readInput("/year2023/input.day01.txt");
        assertEquals(55130, Day01.solvePart1(input));
        assertEquals(54985, Day01.solvePart2(input));
    }
}
