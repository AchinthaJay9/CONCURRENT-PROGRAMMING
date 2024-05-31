
public class App {

	public static void main(String[] args) {
		BankAccount account = new BankAccount(0, "Acc001");
		HouseBasedHusband husband = new HouseBasedHusband("Jack", account);
		CareerMindedWife wife = new CareerMindedWife("Malki", account);
		
		Thread hbh = new Thread(husband, husband.getName());
		Thread cmw = new Thread(wife, wife.getName());
		
		cmw.start();
		hbh.start();
	}

}
