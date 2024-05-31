
public class Main {

	public static void main(String[] args) {
		Account obj = new Account("Acc001",0);
		Thread cmw = new Thread(new CareerMindedWife(obj), "Wife");
		Thread hbh = new Thread(new HouseBasedHusband(obj), "Husband");
		cmw.start();
		hbh.start();
	}

}
