import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Please input your age: ");
                int age = scanner.nextInt();
                BirthYearCalculator calc = new BirthYearCalculator(age);
                int birthYear = calc.getBirthYear();
                System.out.println("Your birth year is " + birthYear);
                break;
            } catch (InvalidAgeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer for age.");
                scanner.next(); // clear the invalid input
            }
        }

        // Example usage of IntTools
        IntTools intTools = new IntTools(12345);
        System.out.println("Digit sum: " + intTools.digitSum());
        System.out.println("Last digit: " + intTools.lastDigit());
        try {
            System.out.println("Digit at position 2: " + intTools.digitAt(2));
        } catch (InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}

class BirthYearCalculator {
    private int age;

    public BirthYearCalculator(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Age " + age + " is invalid.");
        }
        this.age = age;
    }

    public int getBirthYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.age;
    }
}

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class IntTools {
    private int number;

    public IntTools(int number) {
        this.number = number;
    }

    public int digitSum() {
        int sum = 0;
        int num = number;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public int lastDigit() {
        return Math.abs(number % 10);
    }

    public int digitAt(int position) throws InvalidPositionException {
        String numStr = Integer.toString(number);
        if (position < 0 || position >= numStr.length()) {
            throw new InvalidPositionException("Position " + position + " is invalid.");
        }
        return Character.getNumericValue(numStr.charAt(position));
    }
}

class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}
