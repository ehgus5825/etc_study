package jdbc.ex2;

public class Program {
	public static void main(String[] args) {
			NoticeConsole console = new NoticeConsole(); 
			
			Exit:
			while(true) {
				console.printNoticeList();
				int menu = console.inputNoticeMenu();
				switch(menu) {
				case 1:		// ����ȸ
					console.detailNotice();
					break;
				case 2:		// ����
					console.movePrevList();
					break;
				case 3:		// ����
					console.moveNextList();
					break;
				case 4:		// �۾���
					console.writeNotice();
					break;
				case 5:		// �˻�
					console.searchNoticeList();
					break;
				case 6:		// �˻� �ʱ�ȭ
					console.searchReset();
					break;
				case 7:		// �ۻ���
					console.deleteNotice();
					break;
				case 8:		// ����
					System.out.println();
					System.out.println("Bye~~");
					System.out.println();
					break Exit;
				default:
					System.out.println();
					System.out.println("�� ���� �Է��Դϴ�.");
					System.out.println();
					break;
			}
		}
	}
}
