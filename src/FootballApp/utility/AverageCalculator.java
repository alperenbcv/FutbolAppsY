package FootballApp.utility;

public class AverageCalculator {
	
	public static double calculateAverage(int[] numbers) {
		int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.length;
	}
}