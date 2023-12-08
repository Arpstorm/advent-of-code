package com.arpstorm.aoc.year2023;

import com.arpstorm.aoc.Utils;
import lombok.Data;

import java.util.*;

public class Day07 {
    enum Type {
        FIVES(6), FOURS(5), FULL_HOUSE(4), THREES(3), TWO_PAIR(2), PAIR(1), HIGH_CARD(0);
        private final int value;
        Type(final int newValue) {
            value = newValue;
        }
    }


    public static void main(String[] args) {
        List<String> input = Utils.readInput("/year2023/input.day07.txt");

        long part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        long part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    public static int solvePart1(List<String> input) {
        String CARD_ORDER = "23456789TJQKA";
        List<Game> games = new ArrayList<>();
        for (String line : input) {
            List<Integer> hand = new ArrayList<>();
            for (char card : line.split(" ")[0].toCharArray()) {
                hand.add(CARD_ORDER.indexOf(card));
            }
            List<Integer> sortedHand = sortHand(new ArrayList<>(hand));
            int strength = getStrength(new ArrayList<>(sortedHand));
            games.add(new Game(strength, hand, Integer.parseInt(line.split(" ")[1])));
        }
        games.sort(new GameComparator());
        int result = 0;
        for (int i = 0; i < games.size(); i++) {
            result = result + (i + 1) * games.get(i).getBid();
        }
        return result;
    }

    public static long solvePart2(List<String> input) {
        String CARD_ORDER = "J23456789TQKA";
        List<Game> games = new ArrayList<>();
        for (String line : input) {
            List<Integer> hand = new ArrayList<>();
            for (char card : line.split(" ")[0].toCharArray()) {
                hand.add(CARD_ORDER.indexOf(card));
            }
            List<Integer> sortedHand = sortHand(new ArrayList<>(hand));
            int strength = getJokerStrength(new ArrayList<>(sortedHand));
            games.add(new Game(strength, hand, Integer.parseInt(line.split(" ")[1])));
        }
        games.sort(new GameComparator());
        int result = 0;
        for (int i = 0; i < games.size(); i++) {
            result = result + (i + 1) * games.get(i).getBid();
        }
        return result;
    }

    private static List<Integer> sortHand(List<Integer> input) {
        List<Integer> output = new ArrayList<>();
        while (input.size() > 0) {
            Integer minimum = 14;
            for (Integer card : input) {
                if (card < minimum) minimum = card;
            }
            if (input.contains(minimum)) {
                input.remove(minimum);
                output.add(minimum);
            }
        }
        return output;
    }

    private static int getJokerStrength(List<Integer> hand) {
        int jokers = (int) hand.stream().filter(c -> c == 0).count();
        int strength = getStrength(hand);
        if (strength == Type.FOURS.value && jokers == 1) {
            return Type.FIVES.value;
        }
        if (strength == Type.FOURS.value && jokers == 4) {
            return Type.FIVES.value;
        }
        if (strength == Type.FULL_HOUSE.value && jokers == 2) {
            return Type.FIVES.value;
        }
        if (strength == Type.FULL_HOUSE.value && jokers == 3) {
            return Type.FIVES.value;
        }
        if (strength == Type.THREES.value && jokers == 1) {
            return Type.FOURS.value;
        }
        if (strength == Type.THREES.value && jokers == 3) {
            return Type.FOURS.value;
        }
        if (strength == Type.TWO_PAIR.value && jokers == 1) {
            return Type.FULL_HOUSE.value;
        }
        if (strength == Type.TWO_PAIR.value && jokers == 2) {
            return Type.FOURS.value;
        }
        if (strength == Type.PAIR.value && (jokers == 1 || jokers == 2)) {
            return Type.THREES.value;
        }
        if (strength == Type.HIGH_CARD.value && jokers == 1) {
            return Type.PAIR.value;
        }
        return strength;
    }

    private static int getStrength(List<Integer> hand) {
        hand.removeAll(Collections.singleton(hand.get(0)));
        if (hand.size() == 0) {
            return Type.FIVES.value;
        }
        if (hand.size() == 1) {
            return Type.FOURS.value;
        }
        if (hand.size() == 2) {
            hand.removeAll(Collections.singleton(hand.get(0)));
            if (hand.size() == 0) {
                return Type.FULL_HOUSE.value;
            } else {
                return Type.THREES.value;
            }
        }
        if (hand.size() == 3) {
            hand.removeAll(Collections.singleton(hand.get(0)));
            if (hand.size() == 0) {
                return Type.FULL_HOUSE.value;
            } else if (hand.size() == 1) {
                return Type.TWO_PAIR.value;
            } else {
                hand.removeAll(Collections.singleton(hand.get(0)));
                if (hand.size() == 0) {
                    return Type.TWO_PAIR.value;
                } else {
                    return Type.PAIR.value;
                }
            }
        }
        if (hand.size() == 4) {
            hand.removeAll(Collections.singleton(hand.get(0)));
            if (hand.size() == 0) {
                return Type.FOURS.value;
            }
            if (hand.size() == 1) {
                return Type.THREES.value;
            }
            if (hand.size() == 2) {
                hand.removeAll(Collections.singleton(hand.get(0)));
                if (hand.size() == 0) {
                    return Type.TWO_PAIR.value;
                } else {
                    return Type.PAIR.value;
                }
            }
            if (hand.size() == 3) {
                hand.removeAll(Collections.singleton(hand.get(0)));
                if (hand.size() == 0) {
                    return Type.THREES.value;
                } else if (hand.size() == 1) {
                    return Type.PAIR.value;
                } else {
                    hand.removeAll(Collections.singleton(hand.get(0)));
                    if (hand.size() == 1) {
                        return Type.HIGH_CARD.value;
                    } else {
                        return Type.PAIR.value;
                    }
                }
            }
        }
        return Type.HIGH_CARD.value;
    }

    @Data
    static class Game {
        private final Integer strength;
        private final List<Integer> hand;
        private final Integer bid;
    }

    static class GameComparator implements Comparator<Game> {
        @Override
        public int compare(Game a, Game b) {
            if (a.getStrength() > b.getStrength()) {
                return 1;
            } else if (a.getStrength() < b.getStrength()) {
                return -1;
            } else {
                return compareSameStrength(a, b, 0);
            }
        }
        private int compareSameStrength(Game a, Game b, int index) {
            Integer cardA = a.getHand().get(index);
            Integer cardB = b.getHand().get(index);
            if (cardA > cardB) {
                return 1;
            } else if (cardA < cardB) {
                return -1;
            } else {
                return compareSameStrength(a, b, index + 1);
            }
        }
    }
}
