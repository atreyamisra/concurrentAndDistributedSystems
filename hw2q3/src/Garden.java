//am73676_sr39533
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Garden {
	final Lock lock = new ReentrantLock();
	final Condition diggingStarted = lock.newCondition();
	final Condition maryHasShovel = lock.newCondition();
	final Condition newtonHasShovel = lock.newCondition();
	boolean shovel = true;
	int holesDug = 0;
    int seedsFilled = 0;
    int holesFilled = 0;
	public Garden() {
	}; 
	public void startDigging() {
		lock.lock();
		try{
			if(!shovel){
				try {
					newtonHasShovel.await();
					shovel = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while((holesDug == seedsFilled + 4) || (holesDug == holesFilled + 8)){
				try {
					newtonHasShovel.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		finally{
			lock.unlock();
		}		
	}; 
	public void doneDigging() {
		lock.lock();
		try{
			holesDug++;
			//System.out.println("Dug: " + holesDug);
			maryHasShovel.signal();
			diggingStarted.signal();
		}
		finally{
			lock.unlock();
		}
	}; 
	public void startSeeding() {
		lock.lock();
		try{
			while(seedsFilled == holesDug){
				try {
					diggingStarted.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		finally{
			lock.unlock();
		}		
	};
	public void doneSeeding() {
		lock.lock();
		try{
			seedsFilled++;
			//System.out.println("Seeded: " + seedsFilled);
		}
		finally{
			lock.unlock();
		}
	}; 
	public void startFilling() {
		lock.lock();
		try{
			shovel = false;
			while(seedsFilled == holesFilled){
				try {
					maryHasShovel.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		finally{
			lock.unlock();
		}
	}; 
	public void doneFilling() {
		lock.lock();
		try{
			holesFilled++;
			//System.out.println("Filled: " + holesFilled);
			newtonHasShovel.signal();
		}
		finally{
			lock.unlock();
		}
	}; 
 
    /*
    * The following methods return the total number of holes dug, seeded or 
    * filled by Newton, Benjamin or Mary at the time the methods' are 
    * invoked on the garden class. */
   public int totalHolesDugByNewton() {return holesDug;}; 
   public int totalHolesSeededByBenjamin() {return seedsFilled;}; 
   public int totalHolesFilledByMary() {return holesFilled;}; 
}
