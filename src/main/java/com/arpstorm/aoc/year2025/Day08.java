package com.arpstorm.aoc.year2025;

import com.arpstorm.aoc.Utils;
import lombok.Getter;

import java.util.*;

public class Day08 {

    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2025/input.day08.txt");

        long part1 = solvePart1(input);
        long part2 = solvePart2(input);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }

    public static long solvePart1(List<String> input) {
        long sum = 0;
        List<Link> links = initLinks(input);
        links.sort(Comparator.comparing(Link::getDistance));
        List<List<Junction>> circuits = initCircuits(input);
        int connections = 0;
        for (Link link : links) {
            // link ist sortiert von der kleinsten zur größten Distanz
            List<Junction> circuitA = getCircuitOfJunction(link.junctionA, circuits);
            List<Junction> circuitB = getCircuitOfJunction(link.junctionB, circuits);
            if (circuitA != circuitB) {
                circuits.remove(circuitB);
                circuitA.addAll(circuitB);
            }
            connections++;
            if (connections == 1000) {
                break;
            }
        }
        sum = multiplyThreeLargestCircuits(circuits);
        return sum;
    }

    public static long solvePart2(List<String> input) {
        long sum = 0;
        List<Link> links = initLinks(input);
        links.sort(Comparator.comparing(Link::getDistance));
        List<List<Junction>> circuits = initCircuits(input);
        for (Link link : links) {
            // link ist sortiert von der kleinsten zur größten Distanz
            List<Junction> circuitA = getCircuitOfJunction(link.junctionA, circuits);
            List<Junction> circuitB = getCircuitOfJunction(link.junctionB, circuits);
            if (circuitA != circuitB) {
                circuits.remove(circuitB);
                circuitA.addAll(circuitB);
            }
            if (circuits.size() == 1) {
                return link.junctionA.x * link.junctionB.x;
            }
        }
        return sum;
    }

    private static int multiplyThreeLargestCircuits(List<List<Junction>> circuits) {
        List<Integer> sizes = new ArrayList<>();
        for (List<Junction> circuit : circuits) {
            sizes.add(circuit.size());
        }
        int first = 0;
        int second = 0;
        int third = 0;
        for (int size : sizes) {
            if (size > first) {
                third = second;
                second = first;
                first = size;
            } else if (size > second) {
                third = second;
                second = size;
            } else if (size > third) {
                third = size;
            }
        }
        return first * second * third;
    }

    private static List<Junction> getCircuitOfJunction(Junction junctionA, List<List<Junction>> circuits) {
        for (List<Junction> circuit : circuits) {
            for (Junction junction : circuit) {
                if (junctionA.x == junction.x && junctionA.y == junction.y && junctionA.z == junction.z) {
                    return circuit;
                }
            }
        }
        return null;
    }

    private static List<List<Junction>> initCircuits(List<String> input) {
        List<List<Junction>> circuits = new ArrayList<>();
        for (String line : input) {
            String[] coos = line.split(",");
            Junction junction = new Junction(Integer.parseInt(coos[0]), Integer.parseInt(coos[1]), Integer.parseInt(coos[2]));
            List<Junction> circuit = new ArrayList<>();
            circuit.add(junction);
            circuits.add(circuit);
        }
        return circuits;
    }

    private static List<Link> initLinks(List<String> input) {
        List<Link> links = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                String[] coos1 = input.get(i).split(",");
                String[] coos2 = input.get(j).split(",");
                long deltaX = Long.parseLong(coos1[0]) - Long.parseLong(coos2[0]);
                long deltaY = Long.parseLong(coos1[1]) - Long.parseLong(coos2[1]);
                long deltaZ = Long.parseLong(coos1[2]) - Long.parseLong(coos2[2]);
                Link link = new Link(
                        deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ,
                        new Junction(Integer.parseInt(coos1[0]), Integer.parseInt(coos1[1]), Integer.parseInt(coos1[2])),
                        new Junction(Integer.parseInt(coos2[0]), Integer.parseInt(coos2[1]), Integer.parseInt(coos2[2]))
                        );
                links.add(link);
            }
        }
        return links;
    }

    static class Link {
        @Getter
        long distance;
        Junction junctionA;
        Junction junctionB;
        List<Junction> junctions = new ArrayList<>();
        public Link(long distance, Junction junctionA, Junction junctionB) {
            this.distance = distance;
            this.junctionA = junctionA;
            this.junctionB = junctionB;
        }
    }

    static class Junction {
        int x;
        int y;
        int z;
        public Junction(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;

        }
    }

    private static <T> T[][] deepCopy(T[][] matrix) {
        return Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }
}
