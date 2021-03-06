public class testGarden{
	public static void main (String[] args){
		normalTest();
		multipleDigs();
		concurrent();
		
	}
	public static void normalTest(){
		Garden g = new Garden();
		g.startDigging();
		//digging is happening
		g.doneDigging();
		
		g.startSeeding();
		//seeding is happening
		g.doneSeeding();
		
		g.startFilling();
		//filling is happening
		g.doneFilling();
		if((g.totalHolesDugByNewton() == 1) && (g.totalHolesSeededByBenjamin() == 1) || (g.totalHolesFilledByMary() == 1))
			System.out.println("TEST ONE PASSED");
	}
	public static void multipleDigs(){
		Garden a = new Garden();
		a.startDigging();
		//digging is happening
		a.doneDigging();
		
		a.startDigging();
		//digging is happening
		a.doneDigging();
		
		a.startDigging();
		//digging is happening
		a.doneDigging();
		
		a.startSeeding();
		//seeding is happening
		a.doneSeeding();
		
		a.startFilling();
		//filling is happening
		a.doneFilling();
		if((a.totalHolesDugByNewton() == 2) && (a.totalHolesSeededByBenjamin() == 1) || (a.totalHolesFilledByMary() == 1))
			System.out.println("TEST TWO PASSED");
	}
	public static void concurrent(){
		Garden b = new Garden();
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startDigging();
		//digging is happening
		b.doneDigging();
		
		b.startSeeding();
		//seeding is happening
		b.doneSeeding();
		
		b.startFilling();
		//filling is happening
		b.doneFilling();
		
		b.startFilling();
		//filling is happening
		b.doneFilling();
		
		b.startFilling();
		//filling is happening
		b.doneFilling();
		
		if((b.totalHolesDugByNewton() == 9) && (b.totalHolesSeededByBenjamin() == 8) || (b.totalHolesFilledByMary() == 3))
			System.out.println("TEST THREE PASSED YOU");
	}
}