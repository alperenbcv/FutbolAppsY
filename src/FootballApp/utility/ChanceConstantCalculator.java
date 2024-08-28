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
	
	public static double oneOnOneOutChance(double averageAbility) {
		int rand=random.nextInt(1,11);
		if(averageAbility>=16)
			if(rand>=2)
				return 0; //0 ise kaleye
			else if(rand==1)
				return 1; //1 ise aut
			else if(averageAbility>=13 && averageAbility<16)
				if(rand>=3)
					return 0;
				else if(rand==1 ||rand==2)
					return 1;
				else
				if(rand>=5)
					return 0;
				else if(rand<5)
					return 1;
		return 1;
	}
	
	public static double distantShootOutChance(double averageAbility) {
		int rand=random.nextInt(1,11);
		if(averageAbility>=16)
			if(rand>=3)
				return 0; //0 ise kaleye
			else if(rand==1 || rand==2)
				return 1; //1 ise aut
			else if(averageAbility>=13 && averageAbility<16)
				if(rand>=4)
					return 0;
				else if(rand==1 ||rand==2 || rand==3)
					return 1;
				else
				if(rand>=6)
					return 0;
				else if(rand<4)
					return 1;
		return 1;
	}
}