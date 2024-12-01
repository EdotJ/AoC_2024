package day1;

import utils.Utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> lines = Utils.readAllLines("day1/input.txt");

        List<List<Integer>> locationIds = List.of(new ArrayList<>(), new ArrayList<>());

        for (String line : lines) {
            String[] splitLine = line.split(" {3}");
            locationIds.getFirst().add(Integer.parseInt(splitLine[0]));
            locationIds.getLast().add(Integer.parseInt(splitLine[1]));
        }

        partOne(locationIds);
        partTwo(locationIds);
    }

    private static void partOne(List<List<Integer>> locationIds) {
        for (List<Integer> ints : locationIds) {
            Collections.sort(ints);
        }

        int sum = 0;
        for (int i = 0; i < locationIds.getFirst().size(); i++) {
            sum += Math.abs(locationIds.getFirst().get(i) - locationIds.getLast().get(i));
        }

        System.out.println("Part One: " + sum);
    }

    private static void partTwo(List<List<Integer>> locationIds) {
        Map<Integer, Integer> frequencies = locationIds.getLast()
                .stream()
                .collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));

        int answer = locationIds.getFirst().stream().reduce(0, (coll, id) -> id * frequencies.getOrDefault(id, 0));

        System.out.println("Part Two: " + answer);
    }

}
