package ain.tolva.alerter.functions;

public class ManualPing extends Ping {

	public ManualPing(String n, String a) {
		super(n, a);
	}

	@Override
	public void run() {
		writeLog(testPing());
		// TODO: Alert user of PING Status
	}
}

