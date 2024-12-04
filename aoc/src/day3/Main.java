package day3;

import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern enabledPattern = Pattern.compile("do\\(\\)(.+)");
    private static final Pattern functionPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

    public static void main(String[] args) throws Exception {
        List<String> lines = Utils.readAllLines("day3/input.txt");

        String str = lines.getFirst();

        System.out.println("Part one: " + executeFunctions(str));
        System.out.println("Part two: " + executeDisableableFunctions(str));
    }

    private static int executeFunctions(String str) {
        Matcher matcher = functionPattern.matcher(str);
        int sum = 0;
        while (matcher.find()) {
            int digitOne = Integer.parseInt(matcher.group(1));
            int digitTwo = Integer.parseInt(matcher.group(2));

            sum += digitOne * digitTwo;
        }

        return sum;
    }

    private static int executeDisableableFunctions(String str) {
        String enabledStr = "do()" + str;
        int sum = 0;
        for (String disabledFunctionString: enabledStr.split("don't")) {
            Matcher matcher = enabledPattern.matcher(disabledFunctionString);
            while (matcher.find()) {
                String enabledSequence = matcher.group();
                sum += executeFunctions(enabledSequence);
            }
        }

        return sum;
    }

}
