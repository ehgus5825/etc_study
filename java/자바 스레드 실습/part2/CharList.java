package part2;

public class CharList {
	private char[] list;
	private int index;
	private Object indexLock;
	
	public CharList() {
		indexLock = new Object();
		this.list = new char[240];
		for(int i = 0; i < 240; i++)
			list[i] = (char)i;
		
		this.index = 0;
	}

	public void printNext() {
		Thread th = Thread.currentThread();
		char ch = list[index];
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			return;
		}
		synchronized (indexLock) {
			System.out.printf("%s[%d] : index:%d, char:%c\n",  
					th.getName(), th.getId(), index, list[index]);
			index++;
		}
	}
	
	
	
}
