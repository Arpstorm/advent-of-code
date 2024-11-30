
package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day09.txt");
        assertEquals(1798691765, Day09.solvePart1(input));
        assertEquals(1104, Day09.solvePart2(input));
    }
}
