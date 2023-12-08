package com.arpstorm.aoc.year2023;

public class Day06 {

    public static void main(String[] args) {

        long part1 = solvePart1();
        System.out.println("Part 1: " + part1);

        long part2 = solvePart2();
        System.out.println("Part 2: " + part2);

    }

    public static int solvePart1() {
        long[] times = {56,97,78,75};
        long[] distance = {546,1927,1131,1139};
        int[] myWins = new int[4];
        return getRaceResult(times, distance, myWins);
    }

    public static long solvePart2() {
        long[] times = {56977875};
        long[] distance = {546192711311139L};
        int[] myWins = new int[1];
        return getRaceResult(times, distance, myWins);
    }

    private static int getRaceResult(long[] times, long[] distance, int[] myWins) {
        for (int race=0; race < times.length; race++) {
            for (int chargeT=1; chargeT <= times[race]; chargeT++) {
                long d = chargeT * (times[race] - chargeT);
                if (d > distance[race]) {
                    myWins[race]++;
                }
            }
        }
        int result = 1;
        for(int win : myWins) {
            result *= win;
        }
        return result;
    }
}
