
package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day12.txt");
        assertEquals(7007, Day12.solvePart1(input));
        assertEquals(357134560737L, Day12.solvePart2(input));
    }
}
