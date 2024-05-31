
public class Mother implements Runnable {
	
	private Plate plate;
	
	

	public Mother(Plate plate) {
		super();
		this.plate = plate;
	}



	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			String food = "Food at "+i+"th serve";
			plate.produce(food);
			//System.out.println(Thread.currentThread().getName()+" produced "+food);
		}

	}

}
