package teamprj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Card { // ī�� �ε��� ==> 0 : ����, 1 : ����, 2 : �Ｚ, 3 : ����, 4 : �츮
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

	public void Create_KBDiscount() { // ����ī�� �߰��� ����ī�� �������� ����.
		
		String kb_entire_list = null;
		StringTokenizer kb_list = null;

		File file = new File("ī�庰 ��������");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("����") || list.isEmpty()) {
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

	public void Create_HyundaiDiscount(){ // ����ī�� �߰��� ����ī�� �������� ����.

		String hyundai_entire_list = null;
		StringTokenizer hyundai_list = null;

		File file = new File("ī�庰 ��������");
 
		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("����") || list.isEmpty()) {
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

	public void Create_SamsungDiscount() { // �Ｚī�� �߰��� �Ｚī�� �������� ����.

		String samsung_entire_list = null;
		StringTokenizer samsung_list = null;

		File file = new File("ī�庰 ��������");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("�Ｚ") || list.isEmpty()) {
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

	public void Create_ShinhanDiscount(){ // ����ī�� �߰��� ����ī�� �������� ����.

		String shinhan_entire_list = null;
		StringTokenizer shinhan_list = null;

		File file = new File("ī�庰 ��������");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("����") || list.isEmpty()) {
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

	public void Create_WooriDiscount() { // �츮ī�� �߰��� �츮ī�� �������� ����.

		String woori_entire_list = null;
		StringTokenizer woori_list = null;

		File file = new File("ī�庰 ��������");

		try {
			scan = new Scanner(file);

			while(true) {

				String list = scan.nextLine();
				StringTokenizer list_tokens = new StringTokenizer(list, "/");

				if(list_tokens.nextToken().equals("�츮") || list.isEmpty()) {
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
	
	public void add_Card(int card_index) { // ī�� �߰��ϰ� �߰��� ī���� ���������� ����.
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
			System.out.println("�߸��� ī�� �ε����� �Է��ϼ̽��ϴ�.");
		}
	}
	
	public void delete_Card(int card_index) { // ī�� �߰��Ѱ� ����
		if(card_index >= 0 && card_index <= 5) {
			is_CardAdded[card_index] = false;
		}
		else {
			System.out.println("�߸��� ī�� �ε����� �Է��ϼ̽��ϴ�.");
		}
	}
	
	public boolean getIs_CardAdded(int card_index) { // BestDate���� ī�尡 �߰��Ǿ����� Ȯ���ϱ� ���� �ʿ��� �޼ҵ�.
		if(card_index >= 0 && card_index <= 4) {
			return is_CardAdded[card_index];
		}
		else {
			System.out.println("�߸��� ī���ε����� �Է��ϼ̽��ϴ�.");
			return false;
		}
	}
	
	public int get_DiscountRate(String card, String place) { // card���ٰ� ī��ȸ��, place���ٰ� ������ �˻��� ��� �Է�.
	      if(card.equals("����")) {
	         if(KB_discount.containsKey(place) == true) {
	            return KB_discount.get(place);
	         }
	         else {
	            System.out.println("���� ��Ҹ� �Է��ϼ̽��ϴ�.");
	            return 0;
	         }
	      }
	      else if(card.equals("����")) {
	         if(Hyundai_discount.containsKey(place) == true) {
	            return Hyundai_discount.get(place);
	         }
	         else {
	            System.out.println("���� ��Ҹ� �Է��ϼ̽��ϴ�.");
	            return 0;
	         }
	         
	      }
	      else if(card.equals("�Ｚ")) {
	         if(Samsung_discount.containsKey(place) == true) {
	            return Samsung_discount.get(place);
	         }
	         else {
	            System.out.println("���� ��Ҹ� �Է��ϼ̽��ϴ�.");
	            return 0;
	         }
	      }
	      else if(card.equals("����")) {
	         if(Shinhan_discount.containsKey(place) == true) {
	            return Shinhan_discount.get(place);
	         }
	         else {
	            System.out.println("���� ��Ҹ� �Է��ϼ̽��ϴ�.");
	            return 0;
	         }
	      }
	      else if(card.equals("�츮")) {
	         if(Woori_discount.containsKey(place) == true) {
	            return Woori_discount.get(place);
	         }
	         else {
	            System.out.println("���� ��Ҹ� �Է��ϼ̽��ϴ�.");
	            return 0;
	         }
	      }
	      else {
	         System.out.println("�߸��� ī�� ȸ�縦 �Է��ϼ̽��ϴ�.");
	      }

	      return 0;
	   }
}
