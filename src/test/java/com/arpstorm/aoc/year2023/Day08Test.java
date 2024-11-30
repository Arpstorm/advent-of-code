
package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day08.txt");
        assertEquals(18727, Day08.solvePart1(input));
        assertEquals(18024643846273L, Day08.solvePart2(input));
    }
}
