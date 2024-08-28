package FootballApp.utility;

import java.util.Random;

public class ChanceConstantCalculator {
	static Random random = new Random();
	public static double chanceConstant(double averageAbility) {
		int rand=random.nextInt(1,11);
		if(averageAbility>=16)
			if(rand>=2)
			return 2;
			else if(rand==1)
			return 0.5;
        else if(averageAbility>=13 && averageAbility<16)
				if(rand>=3)
					return 2;
				else if(rand==1 ||rand==2)
					return 0.5;
		else
			if(rand>=5)
				return 2;
			else if(rand<5)
				return 0.5;
        return 1;
	}
	
	
}