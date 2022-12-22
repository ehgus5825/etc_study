package jdbc.ex2;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NoticeConsole {
	private NoticeService notice;
	private int page;
	private String field;
	private String query;
	
	public NoticeConsole() {
		this.notice = new NoticeService();
		this.page = 1;
		this.field = "TITLE";
		this.query = "";
	}

	// �⺻ ��ȸ
	public void printNoticeList() {
		int count = notice.getCount(field, query);
		int idx = 0;
		System.out.println("-------------------------------------");		
		System.out.printf("         <��������> �� %d �Խñ�\n", count);
		System.out.println("-------------------------------------");

		List<Notice> list = notice.getList(page, field, query);
		for(Notice n : list) {
			System.out.printf("[%02d] ����: %s / �ۼ���: %s / �����: %s / ��ȸ��: %d\n",++idx, n.getTitle(), n.getWriteId(), n.getRegdate(), n.getHit());
		}
		System.out.println("-------------------------------------");
		System.out.printf("            %d / %d pages\n\n", page, count%10 != 0 ? count/10 + 1:count/10); 
	}
	
	// �� ��ȸ
	public void detailNotice() {
		Scanner in = new Scanner(System.in);
		int number;
		while(true) {
			System.out.print("��ȸ��ȣ �Է� (1 ~ 10) >> ");
			number = Integer.parseInt(in.nextLine());
			if(number <= 10 && number >= 1)
				break;
		}		
		
		List<Notice> list = notice.getList(page, field, query);
		notice.setHitUp(list.get(number - 1).getId());
		
		list = notice.getList(page, field, query);
		
		Notice temp = list.get(number - 1);
		System.out.println();
		System.out.printf("���� : %s\n", temp.getTitle());
		System.out.printf("�ۼ��� : %s\n", temp.getWriteId());
		System.out.printf("����� : %s\n", temp.getRegdate());
		System.out.printf("��ȸ�� : %d\n", temp.getHit());
		System.out.printf("÷������ : %s\n", temp.getFiles() == null?"":temp.getFiles());
		System.out.printf("���� : %s\n", temp.getContent());
		System.out.println();
		
		while(true) {
			System.out.print("������ : Y/n >> ");
			String exit = in.nextLine();
			System.out.println(exit);
			if(exit.equals("Y") || exit.equals("y"))
				break;
		}
	}
	
	// �۾���
	public void writeNotice() {
		Scanner in = new Scanner(System.in);
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println();
		
		System.out.print("���� : ");
		String title = in.nextLine();
		System.out.print("�ۼ��� : ");
		String writeId = in.nextLine();
		System.out.print("÷������ : ");
		String files = in.nextLine();
		System.out.print("���� : ");
		String content = in.nextLine();
		
		notice.insertData(title, writeId, files, content);
		
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println();
	} 

	// ����
	public void movePrevList() {
		if(page == 1) {
			System.out.println();
			System.out.println("���� �������� �����ϴ�.");
			System.out.println();
			return;
		}
		
		page--;
	}

	// ����
	public void moveNextList() {
		int count = notice.getCount(field, query);
		if(page == (count%10 != 0 ? count/10 + 1:count/10)) {
			System.out.println();
			System.out.println("���� �������� �����ϴ�.");
			System.out.println();
			return;
		}
		
		page++;
	}

	// �˻�
	public void searchNoticeList() {
		Scanner in = new Scanner(System.in);
		int re;
		
		System.out.println("�˻� ���� : 1 ~ 3 �� �ϳ��� ����ּ��� ~ (1. ���� / 2. �ۼ��� / 3. ����)");
		
		Exit:
		while(true) {
			re = Integer.parseInt(in.nextLine());
			switch(re) {
			case 1:		// ����
				this.field = "TITLE";
				System.out.println("������ �����߽��ϴ�.");
				break Exit;
			case 2:		// �ۼ���
				this.field = "WRITER_ID";
				System.out.println("�ۼ��ڸ� �����߽��ϴ�.");
				break Exit;
			case 3:		// ����
				this.field = "CONTENT";
				System.out.println("������ �����߽��ϴ�.");
				break Exit;
			default:
				System.out.println("�� ���� �Է��Դϴ�.");
				break;
			}
		}
			
		System.out.print("�˻��� �Է� >> ");
		this.query = in.nextLine();
	}
	
	// �˻� �ʱ�ȭ
	public void searchReset() {
		this.field = "TITLE";
		this.query = "";
		System.out.println("�˻��� �ʱ�ȭ �մϴ�.");
	}
	
	// �� ����
	public void deleteNotice() {
		Scanner in = new Scanner(System.in);
		int number;
		while(true) {
			System.out.print("������ �� ���� (1 ~ 10) >> ");
			number = Integer.parseInt(in.nextLine());
			if(number <= 10 && number >= 1)
				break;
		}		
		
		List<Notice> list = notice.getList(page, field, query);
		notice.deleteData(list.get(number - 1).getId());
	}
	
	// �Է�
	public int inputNoticeMenu() {
		Scanner in = new Scanner(System.in); 
		System.out.print("1.����ȸ/2.����/3.����/4.�۾���/5.�˻�/6.�˻� �ʱ�ȭ/7.�ۻ���/8.���� >");
		int re = in.nextInt();
		
		return re;
	}
}
