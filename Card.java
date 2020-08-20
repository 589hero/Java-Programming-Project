package teamprj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Card { // 카드 인덱스 ==> 0 : 국민, 1 : 현대, 2 : 삼성, 3 : 신한, 4 : 우리
	private boolean[] is_CardAdded = new boolean[5];
	private LinkedHashMap<String, Integer> KB_discount = new LinkedHashMap<String, Integer>(); 
	private LinkedHashMap<String, Integer> Hyundai_discount = new LinkedHashMap<String, Integer>(); 
	private LinkedHashMap<String, Integer> Samsung_discount = new LinkedHashMap<String, Integer>(); 
	private LinkedHashMap<String, Integer> Shinhan_discount = new LinkedHashMap<String, Integer>(); 
	private LinkedHashMap<String, Integer> Woori_discount = new LinkedHashMap<String, Integer>(); 
	Scanner scan = new Scanner(System.in);

	public Card() {
		for(int i = 0; i < is_CardAdded.length; i++) {
			is_CardAdded[i] = false;
		}
	}

	public void Create_KBDiscount() { // 국민카드 추가시 국민카드 할인정보 생성.
		
		String kb_entire_list = null;
		StringTokenizer kb_list = null;

		File file = new File("카드별 할인혜택");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("국민") || list.isEmpty()) {
					kb_entire_list = list;
					kb_list = list_tokens;
					break;
				}
			}

			while(kb_list.hasMoreTokens()) {
				KB_discount.put(kb_list.nextToken(), Integer.parseInt(kb_list.nextToken()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		
	}

	public void Create_HyundaiDiscount(){ // 현대카드 추가시 현대카드 할인정보 생성.

		String hyundai_entire_list = null;
		StringTokenizer hyundai_list = null;

		File file = new File("카드별 할인혜택");
 
		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("현대") || list.isEmpty()) {
					hyundai_entire_list = list;
					hyundai_list = list_tokens;
					break;
				}
			}

			while(hyundai_list.hasMoreTokens()) {
				Hyundai_discount.put(hyundai_list.nextToken(), Integer.parseInt(hyundai_list.nextToken()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	

	}

	public void Create_SamsungDiscount() { // 삼성카드 추가시 삼성카드 할인정보 생성.

		String samsung_entire_list = null;
		StringTokenizer samsung_list = null;

		File file = new File("카드별 할인혜택");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("삼성") || list.isEmpty()) {
					samsung_entire_list = list;
					samsung_list = list_tokens;
					break;
				}
			}

			while(samsung_list.hasMoreTokens()) {
				Samsung_discount.put(samsung_list.nextToken(), Integer.parseInt(samsung_list.nextToken()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}

	public void Create_ShinhanDiscount(){ // 신한카드 추가시 신한카드 할인정보 생성.

		String shinhan_entire_list = null;
		StringTokenizer shinhan_list = null;

		File file = new File("카드별 할인혜택");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("신한") || list.isEmpty()) {
					shinhan_entire_list = list;
					shinhan_list = list_tokens;
					break;
				}
			}

			while(shinhan_list.hasMoreTokens()) {
				Shinhan_discount.put(shinhan_list.nextToken(), Integer.parseInt(shinhan_list.nextToken()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	

	}

	public void Create_WooriDiscount() { // 우리카드 추가시 우리카드 할인정보 생성.

		String woori_entire_list = null;
		StringTokenizer woori_list = null;

		File file = new File("카드별 할인혜택");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("우리") || list.isEmpty()) {
					woori_entire_list = list;
					woori_list = list_tokens;
					break;
				}
			}

			while(woori_list.hasMoreTokens()) {
				Woori_discount.put(woori_list.nextToken(), Integer.parseInt(woori_list.nextToken()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	

	}
	
	public void add_Card(int card_index) { // 카드 추가하고 추가된 카드의 할인정보를 담음.
		if(card_index >= 0 && card_index <= 5) {
			is_CardAdded[card_index] = true;
			
			if(card_index == 0) {
				Create_KBDiscount();
			}
			else if(card_index == 1) {
				Create_HyundaiDiscount();
			}
			else if(card_index == 2) {
				Create_SamsungDiscount();
			}
			else if(card_index == 3) {
				Create_ShinhanDiscount();
			}
			else if(card_index == 4) {
				Create_WooriDiscount();
			}
		}
		else {
			System.out.println("잘못된 카드 인덱스를 입력하셨습니다.");
		}
	}
	
	public void delete_Card(int card_index) { // 카드 추가한거 삭제
		if(card_index >= 0 && card_index <= 5) {
			is_CardAdded[card_index] = false;
		}
		else {
			System.out.println("잘못된 카드 인덱스를 입력하셨습니다.");
		}
	}
	
	public boolean getIs_CardAdded(int card_index) { // BestDate에서 카드가 추가되었는지 확인하기 위해 필요한 메소드.
		if(card_index >= 0 && card_index <= 4) {
			return is_CardAdded[card_index];
		}
		else {
			System.out.println("잘못된 카드인덱스를 입력하셨습니다.");
			return false;
		}
	}
	
	public int get_DiscountRate(String card, String place) { // card에다가 카드회사, place에다가 할인율 검색할 장소 입력.
	      if(card.equals("국민")) {
	         if(KB_discount.containsKey(place) == true) {
	            return KB_discount.get(place);
	         }
	         else {
	            System.out.println("없는 장소를 입력하셨습니다.");
	            return 0;
	         }
	      }
	      else if(card.equals("현대")) {
	         if(Hyundai_discount.containsKey(place) == true) {
	            return Hyundai_discount.get(place);
	         }
	         else {
	            System.out.println("없는 장소를 입력하셨습니다.");
	            return 0;
	         }
	         
	      }
	      else if(card.equals("삼성")) {
	         if(Samsung_discount.containsKey(place) == true) {
	            return Samsung_discount.get(place);
	         }
	         else {
	            System.out.println("없는 장소를 입력하셨습니다.");
	            return 0;
	         }
	      }
	      else if(card.equals("신한")) {
	         if(Shinhan_discount.containsKey(place) == true) {
	            return Shinhan_discount.get(place);
	         }
	         else {
	            System.out.println("없는 장소를 입력하셨습니다.");
	            return 0;
	         }
	      }
	      else if(card.equals("우리")) {
	         if(Woori_discount.containsKey(place) == true) {
	            return Woori_discount.get(place);
	         }
	         else {
	            System.out.println("없는 장소를 입력하셨습니다.");
	            return 0;
	         }
	      }
	      else {
	         System.out.println("잘못된 카드 회사를 입력하셨습니다.");
	      }

	      return 0;
	   }
}
