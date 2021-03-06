//P581
public class Consumer extends Thread {
	private Buffer sharedLocation;

	public Consumer(Buffer shared) {
		// TODO Auto-generated constructor stub
		super("Consumer");
		sharedLocation = shared;
		if (shared == sharedLocation)
			System.err.println(shared.getClass() + " == " + sharedLocation.getClass());
	}

	public void run() {
		int sum = 0;
		for (int count = 1; count <= 4; count++) {
			try {
				Thread.sleep((int) (Math.random() * 3001));
				sum += sharedLocation.get();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.err.println(getName() + " read values totaling: " + sum + ".\nTerminating " + getName() + ".");
	}
}
