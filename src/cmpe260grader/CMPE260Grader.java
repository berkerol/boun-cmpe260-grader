package cmpe260grader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CMPE260Grader {

    private static TreeMap<Integer, Double> m = new TreeMap<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        readQuiz();
        readProlog();
        readScheme();
        readSmalltalk();
        readMidterm();
        readFinal();
        Map<Integer, Double> p = sortByValue(m);
        double t = p.size(), sum = 0, c = 0;
        for (Map.Entry<Integer, Double> e : p.entrySet()) {
            System.out.printf("%.0f \t %d \t %.2f \n", ++c, e.getKey(), e.getValue());
            sum += e.getValue();
        }
        System.out.printf("Total: %.0f \t Sum: %.2f \t Average: %.2f \n", t, sum, sum * 1.0 / t);
    }

    private static void readQuiz() throws FileNotFoundException {
        double t = 0, sum = 0;
        Scanner s = new Scanner(new File("quiz"));
        while (s.hasNextLine()) {
            Integer i = s.nextInt(), j = s.nextInt();
            m.put(i, j / 17.0 * 20);
            s.nextLine();
            t++;
            sum += j / 17.0 * 20;
        }
        System.out.printf("Quiz Total: %.0f \t\t Sum: %.2f \t Average: %.2f \n", t, sum, sum * 1.0 / t);
    }

    private static void readProlog() throws FileNotFoundException {
        double t = 0, sum = 0;
        Scanner keys = new Scanner(new File("prolog keys"));
        Scanner values = new Scanner(new File("prolog values"));
        while (keys.hasNextLine()) {
            Integer i = Integer.parseInt(keys.nextLine());
            Double j = Double.parseDouble(values.nextLine());
            if (m.containsKey(i)) {
                m.put(i, m.get(i) + j);
            }
            else {
                m.put(i, j);
            }
            t++;
            sum += j;
        }
        System.out.printf("Prolog Total: %.0f \t Sum: %.2f \t Average: %.2f \n", t, sum, sum * 1.0 / t);
    }

    private static void readScheme() throws FileNotFoundException {
        double t = 0, sum = 0;
        Scanner s = new Scanner(new File("scheme"));
        while (s.hasNextLine()) {
            Integer i = Integer.parseInt(s.nextLine());
            Double j = Double.parseDouble(s.nextLine());
            if (m.containsKey(i)) {
                m.put(i, m.get(i) + j * 0.1);
            }
            else {
                m.put(i, j * 0.1);
            }
            t++;
            sum += j * 0.1;
        }
        System.out.printf("Scheme Total: %.0f \t Sum: %.2f \t Average: %.2f \n", t, sum, sum * 1.0 / t);
    }

    private static void readSmalltalk() throws FileNotFoundException {
        double t = 0, sum = 0;
        Scanner keys = new Scanner(new File("smalltalk keys"));
        Scanner values = new Scanner(new File("smalltalk values"));
        while (keys.hasNextLine()) {
            Integer i = Integer.parseInt(keys.nextLine()), j = Integer.parseInt(values.nextLine());
            if (m.containsKey(i)) {
                m.put(i, m.get(i) + j * 0.1);
            }
            else {
                m.put(i, j * 0.1);
            }
            t++;
            sum += j * 0.1;
        }
        System.out.printf("Smalltalk Total: %.0f \t Sum: %.2f \t Average: %.2f \n", t, sum, sum * 1.0 / t);
    }

    private static void readMidterm() throws FileNotFoundException, IOException {
        ArrayList<String> list = (ArrayList<String>) Files.readAllLines(Paths.get("midterm"));
        double t = list.size(), sum = 0;
        for (String s : list) {
            Scanner sc = new Scanner(s);
            Integer i = sc.nextInt(), j = 0;
            if (sc.hasNextInt()) {
                j = sc.nextInt();
            }
            if (m.containsKey(i)) {
                m.put(i, m.get(i) + j * 0.2);
            }
            else {
                m.put(i, j * 0.2);
            }
            sum += j * 0.2;
        }
        System.out.printf("Midterm Total: %.0f \t Sum: %.2f \t Average: %.2f \n", t, sum, sum * 1.0 / t);
    }

    private static void readFinal() throws FileNotFoundException {
        double t = 0, sum = 0;
        Scanner s = new Scanner(new File("final"));
        while (s.hasNextLine()) {
            Integer i = s.nextInt(), j = 0;
            if (s.hasNextInt()) {
                j = s.nextInt();
            }
            if (m.containsKey(i)) {
                m.put(i, m.get(i) + j * 0.3);
            }
            else {
                m.put(i, j * 0.3);
            }
            s.nextLine();
            t++;
            sum += j * 0.3;
        }
        System.out.printf("Final Total: %.0f \t Sum: %.2f \t Average: %.2f \n", t, sum, sum * 1.0 / t);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
