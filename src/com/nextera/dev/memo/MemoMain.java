package com.nextera.dev.memo;

import java.util.Scanner;

public class MemoMain {
	
	public static void main(String[] args) {
		//프로그램 시작하면 명령어번호 보이기 -  1. 쓰기 2. 읽기 3. 수정 4. 삭제
		Memo memo = new Memo();
		//명령번호를 받는다
		Scanner scanner = new Scanner(System.in);
		
		boolean runFlag = true;
		
		while(runFlag) {
			memo.showCommand();
			String cmd = scanner.nextLine();
			switch(cmd) {
			case "1" : // 쓰기모드
				memo.write(scanner);
				break;
				
			case "2":
				memo.list();
				break;
				
			case "3":
				memo.list();
				memo.modify();
				break;
				
			case "4":
				memo.list();
				memo.delete();
				break;
			
			case "0":
				runFlag = false;
				//푸로그램종료
				break;
				
			default :
					System.out.println("명령어를 잘못입력");
					
			}
		}
		
		System.out.println("프로그램이 종료되었습니다--");
	}

}
