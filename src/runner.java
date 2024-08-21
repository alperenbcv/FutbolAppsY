import java.util.*;

public class runner {
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		ArrayList<StringBuilder> firstHalf = new ArrayList<>();
		ArrayList<StringBuilder> secondHalf = new ArrayList<>();
		ArrayList<String> season = new ArrayList<>();
		
		for (int i = 1; i < 21; i++) {
			numbers.add(i);
		}
		for (int i = 0; i < numbers.size() - 1; i++) {
			for (int j = i + 1; j <= numbers.size() - 1; j++) {
				firstHalf.add(new StringBuilder().append(numbers.get(i)).append("-").append(numbers.get(j)));
				secondHalf.add(new StringBuilder().append(numbers.get(j)).append("-").append(numbers.get(i)));
			}
		}
		
		Collections.shuffle(firstHalf);
		Collections.shuffle(secondHalf);
		
		int weeks=numbers.size()-1;
		for (int i = 0; i < weeks; i++) {
			Set<Integer> teamsInThisWeek = new HashSet<>();
			for (int j = 0; j < numbers.size()/2; j++) {
				String firstMatch = firstHalf.get(i*numbers.size()/2 + j).toString();
				String[] teamsFirstHalf = firstMatch.split("-");
				if(!teamsInThisWeek.contains(Integer.parseInt(teamsFirstHalf[0])) &&!teamsInThisWeek.contains(Integer.parseInt(teamsFirstHalf[1]))){
					
					teamsInThisWeek.add(Integer.parseInt(teamsFirstHalf[0]));
					teamsInThisWeek.add(Integer.parseInt(teamsFirstHalf[1]));
                    season.add(firstMatch);
				}
			}
		}
        System.out.println(season);
		
//		Collections.shuffle(firstHalf);
//		Collections.shuffle(secondHalf);
//		System.out.println(firstHalf);
//		System.out.println(secondHalf);
//		System.out.println(firstHalf.size());
//		System.out.println(secondHalf.size());
		
//		season.stream().map(s -> s.substring(0, 1)).forEach(s-> System.out.print(s+" ,"));
//
//		for (int i = 0; i < 19; i++) {
//
//		}
		
	}
}