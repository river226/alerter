package ain.tolva.alerter.functions;

public class AutoPing extends Ping {

	public AutoPing(String n, String a) {
		super(n, a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(true) {
			writeLog(testPing());
			// TODO: Alert user if no successful ping
			try { // Put program to sleep for 15 minutes
				Thread.sleep(900000);
			} catch (InterruptedException e) {
				writeLog(sdf.format(cal.getTime()) + e.toString());
				break;
			}
		}
	}


}
