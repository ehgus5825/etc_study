package jdbc.ex2;

public class Program {
	public static void main(String[] args) {
			NoticeConsole console = new NoticeConsole(); 
			
			Exit:
			while(true) {
				console.printNoticeList();
				int menu = console.inputNoticeMenu();
				switch(menu) {
				case 1:		// 상세조회
					console.detailNotice();
					break;
				case 2:		// 이전
					console.movePrevList();
					break;
				case 3:		// 다음
					console.moveNextList();
					break;
				case 4:		// 글쓰기
					console.writeNotice();
					break;
				case 5:		// 검색
					console.searchNoticeList();
					break;
				case 6:		// 검색 초기화
					console.searchReset();
					break;
				case 7:		// 글삭제
					console.deleteNotice();
					break;
				case 8:		// 종료
					System.out.println();
					System.out.println("Bye~~");
					System.out.println();
					break Exit;
				default:
					System.out.println();
					System.out.println("잘 못된 입력입니다.");
					System.out.println();
					break;
			}
		}
	}
}
