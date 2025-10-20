class BillHandler{
	private int bill;
	public BillHandler(int billAmount) {
		if (billAmount > 0) {
			bill = billAmount;
		}
	}
	public int execute(int withdrawal) {
		if (withdrawal >= bill) {
			withdrawal = withdrawal - bill;
			System.out.println("$" + bill + " Handler hands out $" + bill);
			System.out.println("$" + bill + " Handler passes to next BillHandler");
			return withdrawal;
		}
		else {
			System.out.println("$" + bill + " Handler passes to next BillHandler");
			return withdrawal;
		}
	}
}
public class ChainDemo {
	public static void main(String[] args) {
		BillHandler billHandler100 = new BillHandler(100);
		BillHandler billHandler50 = new BillHandler(50);
		BillHandler billHandler20 = new BillHandler(20);
		BillHandler billHandler10 = new BillHandler(10);
		BillHandler billHandler5 = new BillHandler(5);
		BillHandler billHandler1 = new BillHandler(1);
		
		int clientWithdrawal = 147;
		while(clientWithdrawal > 0) {
			clientWithdrawal = billHandler100.execute(clientWithdrawal);
			clientWithdrawal = billHandler50.execute(clientWithdrawal);
			clientWithdrawal = billHandler20.execute(clientWithdrawal);
			clientWithdrawal = billHandler10.execute(clientWithdrawal);
			clientWithdrawal = billHandler5.execute(clientWithdrawal);
			clientWithdrawal = billHandler1.execute(clientWithdrawal);

		}
	}
}
