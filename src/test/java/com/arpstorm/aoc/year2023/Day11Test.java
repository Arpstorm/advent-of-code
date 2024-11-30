
package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    @Test
    void verifyResults() {
        List<String> input = Utils.readInput("/year2023/input.day11.txt");
        assertEquals(9274989, Day11.solvePart(input, 2));
        assertEquals(357134560737L, Day11.solvePart(input, 1000000));
    }
}
