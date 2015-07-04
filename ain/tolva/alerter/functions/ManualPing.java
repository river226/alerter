package ain.tolva.alerter.functions;

public class ManualPing extends Ping {

	public ManualPing(String[][] addr) {
		super(addr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		writeLog(testPing(id[0][0], id[0][1], 0));
	}
}

