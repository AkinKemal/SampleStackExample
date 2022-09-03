import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] data = null;
        int[] result = null;

        Scanner scanner = new Scanner(System.in);

        System.out.println("This program sorts an array of ınteger values.");

        // Create an empty array of ıntegers
        data = createArray(0, 1, 1);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with one ıntegers
        data = createArray(1, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with two ıntegers
        data = createArray(2, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 10 ıntegers
        data = createArray(10, 0, 9999);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 20 ıntegers
        data = createArray(20, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        System.out.println("Please enter the number of values to sort");
        int size = getInt("It should be an ınteger value greater than or equal to 1.");
        // Create an array of the given size

        data = createArray(size, 0, 99);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();
    }

    private static int[] doStackSort(int[] data) {

        if (data.length == 2) {
            return func(data);
        }

        if (data.length < 2) {
            return data;
        }

        int[] result = new int[data.length];

        // ADD CODE HERE TO SORT THE ARRAY USING TWO STACKS
        StackInterface<Integer> bigStack = new VectorStack<>();
        StackInterface<Integer> lowStack = new VectorStack<>();

        if (data[0] < data[1]) {
            lowStack.push(data[0]);
            bigStack.push(data[1]);
        } else {
            lowStack.push(data[1]);
            bigStack.push(data[0]);
        }

        for (int i = 2; i < data.length; i++) {

            if (Math.abs(data[i] - lowStack.peek()) <= Math.abs(data[i] - bigStack.peek())) {
                if (data[i] > lowStack.peek()) {
                    lowStack.push(data[i]);
                } else {
                    boolean control = true;

                    while (control) {

                        if (data[i] < lowStack.peek()) {
                            bigStack.push(lowStack.pop());
                        } else {
                            control = false;
                            lowStack.push(data[i]);
                        }

                        if (lowStack.peek() == null) {
                            control = false;
                            lowStack.push(data[i]);
                        }
                    }
                }
            } else {

                if (data[i] < bigStack.peek()) {
                    bigStack.push(data[i]);
                } else {

                    boolean control = true;

                    while (control) {

                        if (data[i] > bigStack.peek()) {
                            lowStack.push(bigStack.pop());
                        } else {
                            control = false;
                            bigStack.push(data[i]);
                        }

                        if (bigStack.peek() == null) {
                            control = false;
                            bigStack.push(data[i]);
                        }
                    }
                }
            }
        }

        int sizeLow = lowStack.getSize();
        for (int i = 0; i < sizeLow; i++) {

            bigStack.push(lowStack.pop());
        }

        int sizeTotal = bigStack.getSize() + lowStack.getSize();
        for (int k = 0; k < sizeTotal; k++) {

            result[k] = bigStack.pop();
        }
        return result;
    }

    private static int[] func(int[] data) {
        int[] newData = new int[2];

        if (data[0] < data[1]) {
            newData[0] = data[0];
            newData[1] = data[1];
        } else {
            newData[0] = data[1];
            newData[1] = data[0];
        }
        return newData;
    }

    private static int[] createArray(int size, int min, int max) {

        Random generator = new Random();

        if (size < 0) {
            size = 1;
        }

        if (max <= min) {
            max = min + 1;
        }

        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = min + generator.nextInt(max - min);
        }

        return data;
    }

    private static String representationOfArray(int[] data) {
        String result = "< ";
        for (int i = 0; i < data.length; i++) {
            result += data[i] + " ";
        }
        result += ">";

        return result;
    }

    private static int getInt(String rangePrompt) {
        Scanner scanner;
        int result = 10;
        try {
            scanner = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = scanner.nextInt();

        } catch (NumberFormatException e) {
            System.out.println("Could not convert scanner to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
    }
}