package com.nextera.dev.memo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Memo {
	
	public static final String MEMO_DIR = "/temp/memo";
	//종료커맨드를 상수로
	public static final String EXIT = "/exit";
	public void showCommand() {	//1. 명령어 출력함수
		System.out.println("명령어 입력  ? ");
		System.out.println("1. 쓰기 2. 읽기 3. 수정 4.삭제 0.프로그램종료");
	}
	//메모를 저장하려고 파일경로설정 없으면 만든다
	public Memo() {
		File dir = new File(MEMO_DIR);
		if(!dir.exists()) {
			dir.mkdirs();
		}
	}
	
   //쓰기
	public void write(Scanner scanner) {
		
		System.out.println("---쓰기모드----");
		//글씨 저장변수
		StringBuilder content = new StringBuilder();
		
		//키보드 입력을 받는다.
		while(true) {
			String line = scanner.nextLine();
			if(line.equals(EXIT)) {
				break;
			}else {
					content.append(line + "\r\n");
				}
		}
		
		//빈파일이 아니면 파일명으로 저장한다
		if(!content.toString().equals("")) {
			// 현재시간 가져와서 파일명으로
			long now = System.currentTimeMillis();
			// 날짜 YYMMDD_HHMMSS 포맷
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String filename =  sdf.format(now) + ".txt";
			//내용을 저장할 파일경로셋팅
			Path path = Paths.get(MEMO_DIR, filename);
			try {
				Files.write(path, content.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("메모를 등록하였습니다");
		}
	}
	//메모 리스트 읽어내서 펼치기 - 읽기
	public void list() {
		File file = new File(MEMO_DIR);
		String fileList[] = file.list();
		
		for(String filename : fileList) {
			System.out.println(filename);
			System.out.println("파일크기  " +  file.length());
		}
	}
	
	//읽을수 있는지 확인
	static boolean checkRead(File f) {
		if(f.exists()) {
			if(f.isFile() && f.canRead()) {
				return true;
			}
		}
		return false;
	}
	//파일 읽기 함수
	 static void readFile() {
	  Scanner sc = new Scanner(System.in);
	  String datas[] = null;
	  String filename = "";

	  //파일명 입력
	  System.out.print("읽을 파일 명 = ");
	  filename = sc.next();
	  //파일 선언
	  File readFile = new File(MEMO_DIR + filename + ".txt");


	  //파일이 존재하는지, 읽을 수 있는지 확인
	  boolean b = checkRead(readFile); //return값이 true면 파일이 존재, 읽을 수 있음
	  if(!b) {
	   System.out.println("파일 존재하지 않거나 읽을 수 없습니다");
	   return;
	  }
	  //b = true면 밑에 문장 처리
	  try {
	   BufferedReader br = new BufferedReader(new FileReader(readFile));

	   // 데이터의 갯수 세기
	   int count = 0;
	   String str; 
	   while((str = br.readLine()) != null) {
		   //파일에 있는 문장이 null값이 아닐때까지 반복(즉 파일내 마지막 문장까지 반복)
	    count++;   }
	   br.close();
	   // 배열의 동적할당
	   System.out.println("데이터의 갯수 = " + count);
	   datas = new String[count];

	   // 데이터를 읽어 들임
	   int w = 0;
	   //파일내의 커서가 위의 반복문으로 인해 맨 마지막에 있기 때문에 다시 초기화시켜줌

	   br = new BufferedReader(new FileReader(readFile)); 

	   while((str = br.readLine()) != null) {
	    datas[w] = str;
	    w++;
	   }
	   br.close();
	  } catch (FileNotFoundException e) {
	   e.printStackTrace();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  for (int i = 0; i < datas.length; i++) {
	   System.out.println("datas[" + i + "] = " + datas[i]);
	  }
	 }
	
	public void modify(Scanner scanner) {
		File f.exists() = new File(MEMO_DIR);
		readFile();
			while(true) {
				String line = scanner.nextLine();
				if(line.equals("") && line.equals(EXIT)) {
					break; //1. 아무것도 입력하지 않는다면, 기존글자를 그대로 사용
				}else {
					content.append("");
					content.append(line + "\r\n"); //2. 입력한다면, 기존 내용을 삭제하고 입력한 글자를 사용.
				}
			}
	}
	public void delete() {
		
		Scanner sc = new Scanner(System.in);
		String filename="";
		
		File delfile = new File(MEMO_DIR + filename + ".txt");
		
		System.out.println("삭제할 파일이름?");
		filename = sc.next();
		
		if (!delfile.exists()) {
			System.out.println("삭제할 파일이 존재하지않습니다");
			return;
		}
		
		if(delfile.delete()) {
			System.out.println(MEMO_DIR  + filename + "파일이 삭제되었습니다");
		}else {
		System.out.println(MEMO_DIR + filename + "파일이 삭제되지 않았습니다");		
			}
		}
		
}
