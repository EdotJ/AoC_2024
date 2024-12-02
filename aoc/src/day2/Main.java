package day2;

import utils.Utils;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> lines = Utils.readAllLines("day2/input.txt");

        List<List<Integer>> reports = lines.stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Integer::parseInt).toList())
                .toList();

        System.out.println("P1");
        solve(reports, false);
        System.out.println("P2");
        solve(reports, true);
    }

    public static void solve(List<List<Integer>> reports, boolean partTwo) {
        List<List<Integer>> safeReports = new ArrayList<>();
        for (List<Integer> report : reports) {
            if (isSafeReport(report)) {
                safeReports.add(report);
            } else if (partTwo) {
                for (int i = 0; i < report.size(); i++) {
                    List<Integer> newReport = getExcludedReport(report, i);
                    if (isSafeReport(newReport)) {
                        safeReports.add(report);
                        break;
                    }
                }
            }
        }

        System.out.println("Safe reports: " + safeReports.size());
    }

    public static boolean isSafeReport(List<Integer> report) {
        boolean ascending = report.get(0) < report.get(1) && report.get(1) < report.get(2);

        for (int levelIndex = 0; levelIndex < report.size(); levelIndex++) {
            if (isUnsafeLevel(report, levelIndex, ascending)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isUnsafeLevel(List<Integer> report, int levelIndex, boolean ascending) {
        if (levelIndex == 0) {
            return isUnsafe(report.get(levelIndex), report.get(levelIndex + 1), ascending);
        } else if (levelIndex == report.size() - 1) {
            return isUnsafe(report.get(levelIndex - 1), report.get(levelIndex), ascending);
        } else {
            return isUnsafe(report.get(levelIndex), report.get(levelIndex + 1), ascending)
                    || isUnsafe(report.get(levelIndex - 1), report.get(levelIndex), ascending);
        }
    }

    private static List<Integer> getExcludedReport(List<Integer> report, int i) {
        List<Integer> newReport = new ArrayList<>();
        newReport.addAll(report.subList(0, i));
        newReport.addAll(report.subList(i + 1, report.size()));
        return newReport;
    }

    public static boolean isUnsafe(int num1, int num2, boolean ascending) {
        int diff = Math.abs(num1 - num2);
        boolean incorrectDifference = diff == 0 || diff > 3;
        boolean incorrectOrder = ascending ? num1 > num2 : num1 < num2;

        return incorrectOrder || incorrectDifference;
    }
}
