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

	// 기본 조회
	public void printNoticeList() {
		int count = notice.getCount(field, query);
		int idx = 0;
		System.out.println("-------------------------------------");		
		System.out.printf("         <공지사항> 총 %d 게시글\n", count);
		System.out.println("-------------------------------------");

		List<Notice> list = notice.getList(page, field, query);
		for(Notice n : list) {
			System.out.printf("[%02d] 제목: %s / 작성자: %s / 등록일: %s / 조회수: %d\n",++idx, n.getTitle(), n.getWriteId(), n.getRegdate(), n.getHit());
		}
		System.out.println("-------------------------------------");
		System.out.printf("            %d / %d pages\n\n", page, count%10 != 0 ? count/10 + 1:count/10); 
	}
	
	// 상세 조회
	public void detailNotice() {
		Scanner in = new Scanner(System.in);
		int number;
		while(true) {
			System.out.print("조회번호 입력 (1 ~ 10) >> ");
			number = Integer.parseInt(in.nextLine());
			if(number <= 10 && number >= 1)
				break;
		}		
		
		List<Notice> list = notice.getList(page, field, query);
		notice.setHitUp(list.get(number - 1).getId());
		
		list = notice.getList(page, field, query);
		
		Notice temp = list.get(number - 1);
		System.out.println();
		System.out.printf("제목 : %s\n", temp.getTitle());
		System.out.printf("작성자 : %s\n", temp.getWriteId());
		System.out.printf("등록일 : %s\n", temp.getRegdate());
		System.out.printf("조회수 : %d\n", temp.getHit());
		System.out.printf("첨부파일 : %s\n", temp.getFiles() == null?"":temp.getFiles());
		System.out.printf("내용 : %s\n", temp.getContent());
		System.out.println();
		
		while(true) {
			System.out.print("나가기 : Y/n >> ");
			String exit = in.nextLine();
			System.out.println(exit);
			if(exit.equals("Y") || exit.equals("y"))
				break;
		}
	}
	
	// 글쓰기
	public void writeNotice() {
		Scanner in = new Scanner(System.in);
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println();
		
		System.out.print("제목 : ");
		String title = in.nextLine();
		System.out.print("작성자 : ");
		String writeId = in.nextLine();
		System.out.print("첨부파일 : ");
		String files = in.nextLine();
		System.out.print("내용 : ");
		String content = in.nextLine();
		
		notice.insertData(title, writeId, files, content);
		
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println();
	} 

	// 이전
	public void movePrevList() {
		if(page == 1) {
			System.out.println();
			System.out.println("이전 페이지가 없습니다.");
			System.out.println();
			return;
		}
		
		page--;
	}

	// 다음
	public void moveNextList() {
		int count = notice.getCount(field, query);
		if(page == (count%10 != 0 ? count/10 + 1:count/10)) {
			System.out.println();
			System.out.println("다음 페이지가 없습니다.");
			System.out.println();
			return;
		}
		
		page++;
	}

	// 검색
	public void searchNoticeList() {
		Scanner in = new Scanner(System.in);
		int re;
		
		System.out.println("검색 구분 : 1 ~ 3 중 하나를 골라주세요 ~ (1. 제목 / 2. 작성자 / 3. 내용)");
		
		Exit:
		while(true) {
			re = Integer.parseInt(in.nextLine());
			switch(re) {
			case 1:		// 제목
				this.field = "TITLE";
				System.out.println("제목을 선택했습니다.");
				break Exit;
			case 2:		// 작성자
				this.field = "WRITER_ID";
				System.out.println("작성자를 선택했습니다.");
				break Exit;
			case 3:		// 내용
				this.field = "CONTENT";
				System.out.println("내용을 선택했습니다.");
				break Exit;
			default:
				System.out.println("잘 못된 입력입니다.");
				break;
			}
		}
			
		System.out.print("검색어 입력 >> ");
		this.query = in.nextLine();
	}
	
	// 검색 초기화
	public void searchReset() {
		this.field = "TITLE";
		this.query = "";
		System.out.println("검색을 초기화 합니다.");
	}
	
	// 글 삭제
	public void deleteNotice() {
		Scanner in = new Scanner(System.in);
		int number;
		while(true) {
			System.out.print("삭제할 글 선택 (1 ~ 10) >> ");
			number = Integer.parseInt(in.nextLine());
			if(number <= 10 && number >= 1)
				break;
		}		
		
		List<Notice> list = notice.getList(page, field, query);
		notice.deleteData(list.get(number - 1).getId());
	}
	
	// 입력
	public int inputNoticeMenu() {
		Scanner in = new Scanner(System.in); 
		System.out.print("1.상세조회/2.이전/3.다음/4.글쓰기/5.검색/6.검색 초기화/7.글삭제/8.종료 >");
		int re = in.nextInt();
		
		return re;
	}
}
