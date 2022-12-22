package part2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CharList3 {
	private char[] list;
	private ReentrantReadWriteLock listLock;
	private int index;
	private Object a = new Object();
	private Object b = new Object();


	public CharList3() {
		listLock = new ReentrantReadWriteLock();
		this.list = new char[26];
		this.index = 0;
	}

	public void load() {
		synchronized (b) {
			Thread th = Thread.currentThread();
			// listLock.writeLock().lock();
			for (int i = 0; i < 26; i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list[i] = (char)('A' + i);
			}
			System.out.printf("%s[%d] : loaded\n", th.getName(), th.getId());
			//listLock.writeLock().unlock();
		}
	}

	public void printAll(int count) {
		synchronized (a) {
			Thread th = Thread.currentThread();
			//listLock.readLock().lock();
			for(int i = 0; i < count; i++) {
				StringBuilder builder = new StringBuilder();
				builder.append(list);
				System.out.printf("%s[%d-%d] : %s\n", 
						th.getName(), th.getId(), ++index, builder.toString());
			}
			//listLock.readLock().unlock();
		}
	}	
}
