package ain.tolva.alerter.functions;

public class AutoPing extends Ping {

	public AutoPing(String[][] addr) {
		super(addr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(true) {
			String result = "";

			for(int i = 0; i < id[0].length; i++) {
				result += testPing(id[i][0], id[i][1], i);
			}
			
			writeLog(result);
			
			try { // Put program to sleep for 15 minutes
				Thread.sleep(900000);
			} catch (InterruptedException e) {
				writeLog(sdf.format(cal.getTime()) + e.toString());
				break;
			}
		}
	}


}
