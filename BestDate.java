package teamprj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;

//import teamprj.InfoTest.MenuInfoListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BestDate extends JFrame {

   // 패널 종류
   public MainPanel mainPanel = null;
   public CardAddPanel cardAddPanel = null;
   public DateCourse dateCourse = null;
   public CategoryPick categoryPick = null;
   public CategoryPick2 categoryPick2 = null;
   public MenuPick menuPick = null;
   public userData ud = null;
   public placeData pd = null;
   public String map1, map2, map3;
   
   /**
    * Create the frame.
    */
   public BestDate() {
      setTitle("main");
      
      // 패널 생성                              // 패널 추가 시 추가할 부분1
      mainPanel = new MainPanel(this);
      cardAddPanel = new CardAddPanel(this);
      dateCourse = new DateCourse(this);
      //categoryPick = new CategoryPick(this);
      ud = new userData();
      pd = new placeData(); //코스에 대한 정보 받아옴 
      // 패널 추가
      this.add(this.mainPanel);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      setBounds(100, 100, 450, 300);
      setBounds(100, 100, 800, 650);

   }

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               BestDate frame = new BestDate();
               
               //frame.mainPanel = new MainPanel(frame);
               
               //frame.add(frame.mainPanel);
               
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   
   // 화면 전환                                 // 패널 추가 시 추가할 부분2
   public void change(String panelName){
      if(panelName.equals("mainPanel")){
         getContentPane().removeAll();
         getContentPane().add(mainPanel);
         revalidate();
         repaint();
      }
      else if(panelName.equals("cardAddPanel")){
         getContentPane().removeAll();
         getContentPane().add(cardAddPanel);
         revalidate();
         repaint();
      }
      else if(panelName.equals("dateCourse")){
         getContentPane().removeAll();
         getContentPane().add(dateCourse);
         revalidate();
         repaint();
      }
      else if(panelName.equals("categoryPick")){
         categoryPick = new CategoryPick(this);
         getContentPane().removeAll();
         getContentPane().add(categoryPick);
         revalidate();
         repaint();
      }
      else if(panelName.equals("categoryPick2")){
         categoryPick2 = new CategoryPick2(this);
         getContentPane().removeAll();
         getContentPane().add(categoryPick2);
         revalidate();
         repaint();
      }
      else if(panelName.equals("menuPick")){
    	  menuPick = new MenuPick(this);
    	  getContentPane().removeAll();
    	  getContentPane().add(menuPick);
    	  revalidate();
    	  repaint();
      }
   }

}

// 1. 메인 패널
class MainPanel extends JPanel{
//   public JTextField txtCardList;
   public BestDate win;
   public JList listCardList;
   public DefaultListModel<String> model;
   
   public MainPanel(BestDate win){
      this.win = win;
      
      this.setBorder(new EmptyBorder(1, 5, 5, 1));
      this.setLayout(new BorderLayout(0, 0));
      
      // 1) 버튼
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(3, 1));
      this.add(panel, BorderLayout.WEST);
      
         // 카드 등록 버튼
      JButton btnCardAdd = new JButton("카드 등록");
      btnCardAdd.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardAdd);
      
         // 카드 삭제 버튼
      JButton btnCardDelete = new JButton("카드 삭제");
      btnCardDelete.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardDelete);
      
         // 데이트 코스 짜기 버튼
      JButton btnDating = new JButton("데이트 코스 짜기");
      btnDating.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnDating);
      
      // 2) 카드 목록 보이기
      JPanel panelCardList = new JPanel();
      this.add(panelCardList, BorderLayout.CENTER);
      
      JScrollPane scrollPane = new JScrollPane();
      this.add(scrollPane, BorderLayout.CENTER);
      
      JLabel lblListCard = new JLabel("<보유 중인 카드 목록>");
      lblListCard.setFont(new Font("", Font.BOLD, 30));
      scrollPane.setColumnHeaderView(lblListCard);
      
      listCardList = new JList();
      listCardList.setFont(new Font("Ariel", 50, 50));
      model = new DefaultListModel<>();     
      

      listCardList.setModel(model);
      scrollPane.setViewportView(listCardList);
      listCardList.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JList list = (JList)e.getSource();
			if(e.getClickCount() == 2){
				int index = list.locationToIndex(e.getPoint());
				if(model.getElementAt(index) == "국민카드"){
					String explanation = "애슐리 ▶ 샐러드바 10% 할인" + '\n' + "VIPS ▶ 샐러드바 5% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 10% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 10%할인" + '\n' + "고베규카츠 ▶ 전메뉴 10%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 15% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 5% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 15% 할인" + '\n' + "에버랜드 ▶ 자유이용권 25% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 10% 할인" + '\n' + "CGV ▶ 영화예매권 5% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 10% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 10% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 10%  할인" + '\n' + "할리스 ▶ 전메뉴 5% 할인" + '\n' + "스타벅스 ▶ 전메뉴 10% 할인" +
							'\n' + "스트라이크존 ▶ 15% 할인" + '\n' + "위VR ▶ 평일, 주말  10% 할인" + '\n' +
							"한아름볼링 ▶ 15% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 15% 할인"; 
					JOptionPane.showMessageDialog(null, explanation, "국민카드 혜택", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "현대카드"){
					String explanation = "애슐리 ▶ 샐러드바 5% 할인" + '\n' + "VIPS ▶ 샐러드바 5% 할인" + '\n' +
	 						"아웃백 ▶ 전메뉴 10% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 5%할인" + '\n' + "고베규카츠 ▶ 전메뉴 5%할인 " +
	 						'\n' + "짚신매운갈비찜 ▶ 전메뉴 5% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 10% 할인" + '\n' +
	 						"롯데월드 ▶ 자유이용권 20% 할인" + '\n' + "에버랜드 ▶ 자유이용권 20% 할인" + '\n' +
	 						"어린이대공원 ▶ 자유이용권 5% 할인" + '\n' + "CGV ▶ 영화예매권 10% 할인" + '\n' +
	 						"메가박스 ▶ 영화예매권 5% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 10% 할인" + '\n' +
	 						"엔젤리너스 ▶ 전메뉴 5%  할인" + '\n' + "할리스 ▶ 전메뉴 10% 할인" + '\n' + "스타벅스 ▶ 전메뉴 15% 할인" +
	 						'\n' + "스트라이크존 ▶ 15% 할인" + '\n' + "위VR ▶ 평일, 주말  5% 할인" + '\n' +
	 						"한아름볼링 ▶ 15% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 10% 할인";
					JOptionPane.showMessageDialog(null, explanation, "현대카드 혜택", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "삼성카드"){
					String explanation = "애슐리 ▶ 샐러드바 10% 할인" + '\n' + "VIPS ▶ 샐러드바 10% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 5% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 10%할인" + '\n' + "고베규카츠 ▶ 전메뉴 5%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 10% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 5% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 25% 할인" + '\n' + "에버랜드 ▶ 자유이용권 15% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 10% 할인" + '\n' + "CGV ▶ 영화예매권 10% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 5% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 5% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 10%  할인" + '\n' + "할리스 ▶ 전메뉴 5% 할인" + '\n' + "스타벅스 ▶ 전메뉴 15% 할인" +
	    	 				'\n' + "스트라이크존 ▶ 10% 할인" + '\n' + "위VR ▶ 평일, 주말  5% 할인" + '\n' +
	    	 				"한아름볼링 ▶ 10% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 20% 할인";
					JOptionPane.showMessageDialog(null, explanation, "삼성카드 혜택", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "신한카드"){
					String explanation = "애슐리 ▶ 샐러드바 10% 할인" + '\n' + "VIPS ▶ 샐러드바 10% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 5% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 10%할인" + '\n' + "고베규카츠 ▶ 전메뉴 10%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 10% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 5% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 20% 할인" + '\n' + "에버랜드 ▶ 자유이용권 20% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 5% 할인" + '\n' + "CGV ▶ 영화예매권 10% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 5% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 5% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 10%  할인" + '\n' + "할리스 ▶ 전메뉴 5% 할인" + '\n' + "스타벅스 ▶ 전메뉴 10% 할인" +
	    	 				'\n' + "스트라이크존 ▶ 10% 할인" + '\n' + "위VR ▶ 평일, 주말  5% 할인" + '\n' +
	    	 				"한아름볼링 ▶ 10% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 20% 할인";
					JOptionPane.showMessageDialog(null, explanation, "신한카드 혜택", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "우리카드"){
					String explanation = "애슐리 ▶ 샐러드바 5% 할인" + '\n' + "VIPS ▶ 샐러드바 10% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 5% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 5%할인" + '\n' + "고베규카츠 ▶ 전메뉴 10%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 5% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 10% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 15% 할인" + '\n' + "에버랜드 ▶ 자유이용권 25% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 10% 할인" + '\n' + "CGV ▶ 영화예매권 5% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 10% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 5% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 5%  할인" + '\n' + "할리스 ▶ 전메뉴 10% 할인" + '\n' + "스타벅스 ▶ 전메뉴 10% 할인" +
	    	 						'\n' + "스트라이크존 ▶ 20% 할인" + '\n' + "위VR ▶ 평일, 주말  10% 할인" + '\n' +
	    	 						"한아름볼링 ▶ 10% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 15% 할인";
					JOptionPane.showMessageDialog(null, explanation, "우리카드 혜택", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
    	  
	});
      
      // 버튼 리스너 추가
      btnCardAdd.addActionListener(new ButtonListener());
      btnCardDelete.addActionListener(new ButtonListener());
      btnDating.addActionListener(new ButtonListener());

   }
   
   public void showList(){
//     System.out.println(win.ud.card.getIs_CardAdded(0));
	   model.clear();
     
     if(win.ud.card.getIs_CardAdded(0)){
   	  model.addElement("국민카드");      	
     }
     if(win.ud.card.getIs_CardAdded(1)){
   	  model.addElement("현대카드");      	
     }
     if(win.ud.card.getIs_CardAdded(2)){
   	  model.addElement("삼성카드");      	
     }
     if(win.ud.card.getIs_CardAdded(3)){
   	  model.addElement("신한카드");      	
     }
     if(win.ud.card.getIs_CardAdded(4)){
   	  model.addElement("우리카드");      	
     }
  }
   
   // 버튼 리스너
   class ButtonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton)e.getSource();
         if(b.getText().equals("카드 등록")){
            win.change("cardAddPanel");
         }
         else if(b.getText().equals("카드 삭제")){
            if(!listCardList.isSelectionEmpty()){   // 카드 목록에서 카드 하나가 선택되면 동작
               int result = JOptionPane.showConfirmDialog(null, "정말 카드를 삭제하시겠습니까?", "카드 삭제", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
               if(result == 0){
                  ///////카드 삭제 작업
            	   int index = -1;
//            	   System.out.println(listCardList.getSelectedValue());
            	   if(listCardList.getSelectedValue() == "국민카드"){
            		   index = 0;
            	   }
            	   else if(listCardList.getSelectedValue() == "현대카드"){
            		   index = 1;
            	   }
            	   else if(listCardList.getSelectedValue() == "삼성카드"){
            		   index = 2;
            	   }
            	   else if(listCardList.getSelectedValue() == "신한카드"){
            		   index = 3;
            	   }
            	   else if(listCardList.getSelectedValue() == "우리카드"){
            		   index = 4;
            	   }
            	   win.ud.card.delete_Card(index);
                  JOptionPane.showMessageDialog(null, "삭제되었습니다!");
                  showList();
               }else{
                  return;
               }
            }
         }
         else if(b.getText().equals("데이트 코스 짜기")){
            win.change("dateCourse");
         }
      }
      
   }

}


// 1.1 카드 등록 패널
class CardAddPanel extends JPanel{
   public MainPanel parent;
   public BestDate win;
   
   public CardAddPanel(BestDate win){
      this.win = win;
      parent = win.mainPanel;
      
      this.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setLayout(new BorderLayout(0, 0));
      
      // 제목
      JLabel label = new JLabel("<카드 등록>");
      label.setFont(new Font("", Font.BOLD, 30));
      label.setHorizontalAlignment(SwingConstants.CENTER);;
      this.add(label, BorderLayout.NORTH);
      
      // 버튼 추가
      JPanel panel = new JPanel();
      this.add(panel, BorderLayout.CENTER);
      panel.setLayout(new GridLayout(3, 3));
      
      JButton btnCardGuk = new JButton("KB국민카드");
      btnCardGuk.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardGuk);
      btnCardGuk.setFocusable(false);
      
      JButton btnCardHyun = new JButton("현대카드");
      btnCardHyun.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardHyun);
      btnCardHyun.setFocusable(false);
      
      JButton btnCardSam = new JButton("삼성카드");
      btnCardSam.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardSam);
      btnCardSam.setFocusable(false);
      
      JButton btnCardShin = new JButton("신한카드");
      btnCardShin.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardShin);
      btnCardShin.setFocusable(false);
      
      JButton btnCardOo = new JButton("우리카드");
      btnCardOo.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardOo);
      btnCardOo.setFocusable(false);
      
      // UI만 추가
      JButton btnCardHa = new JButton("하나카드");
      btnCardHa.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardHa);
      btnCardOo.setFocusable(false);
		
      JButton btnCardBc = new JButton("BC카드");
      btnCardBc.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardBc);
      btnCardOo.setFocusable(false);
		
      JButton btnCardLo = new JButton("롯데카드");
      btnCardLo.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardLo);
      btnCardOo.setFocusable(false);
		
      JButton btnCardNo = new JButton("농협카드");
      btnCardNo.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardNo);
      btnCardOo.setFocusable(false);
      
      // 버튼 리스너 추가
      btnCardGuk.addActionListener(new RegButtonListener());
      btnCardHyun.addActionListener(new RegButtonListener());
      btnCardSam.addActionListener(new RegButtonListener());
      btnCardShin.addActionListener(new RegButtonListener());
      btnCardOo.addActionListener(new RegButtonListener());

      // 돌아가기 버튼
      JPanel returnPanel = new JPanel();
      this.add(returnPanel, BorderLayout.SOUTH);
      
      JButton btnReturn = new JButton("돌아가기");
      btnReturn.setFont(new Font("", Font.BOLD, 30));
      returnPanel.add(btnReturn);
      btnReturn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 parent.showList();
            win.change("mainPanel");
         }
      });

   }
   
   // 카드 버튼 리스너
   class RegButtonListener implements ActionListener{
	      @Override
	      public void actionPerformed(ActionEvent e) {
	         JButton b = (JButton)e.getSource();
	         if(b.getText().equals("KB국민카드")){
	        	 String explanation = "애슐리 ▶ 샐러드바 10% 할인" + '\n' + "VIPS ▶ 샐러드바 5% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 10% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 10%할인" + '\n' + "고베규카츠 ▶ 전메뉴 10%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 15% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 5% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 15% 할인" + '\n' + "에버랜드 ▶ 자유이용권 25% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 10% 할인" + '\n' + "CGV ▶ 영화예매권 5% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 10% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 10% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 10%  할인" + '\n' + "할리스 ▶ 전메뉴 5% 할인" + '\n' + "스타벅스 ▶ 전메뉴 10% 할인" +
							'\n' + "스트라이크존 ▶ 15% 할인" + '\n' + "위VR ▶ 평일, 주말  10% 할인" + '\n' +
							"한아름볼링 ▶ 15% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 15% 할인"; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "KB국민카드 등록", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////국민카드 추가
	            	win.ud.card.add_Card(0);
	               JOptionPane.showMessageDialog(null, "추가되었습니다!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("현대카드")){
	        	
	        	 String explanation = "애슐리 ▶ 샐러드바 5% 할인" + '\n' + "VIPS ▶ 샐러드바 5% 할인" + '\n' +
	        	 						"아웃백 ▶ 전메뉴 10% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 5%할인" + '\n' + "고베규카츠 ▶ 전메뉴 5%할인 " +
	        	 						'\n' + "짚신매운갈비찜 ▶ 전메뉴 5% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 10% 할인" + '\n' +
	        	 						"롯데월드 ▶ 자유이용권 20% 할인" + '\n' + "에버랜드 ▶ 자유이용권 20% 할인" + '\n' +
	        	 						"어린이대공원 ▶ 자유이용권 5% 할인" + '\n' + "CGV ▶ 영화예매권 10% 할인" + '\n' +
	        	 						"메가박스 ▶ 영화예매권 5% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 10% 할인" + '\n' +
	        	 						"엔젤리너스 ▶ 전메뉴 5%  할인" + '\n' + "할리스 ▶ 전메뉴 10% 할인" + '\n' + "스타벅스 ▶ 전메뉴 15% 할인" +
	        	 						'\n' + "스트라이크존 ▶ 15% 할인" + '\n' + "위VR ▶ 평일, 주말  5% 할인" + '\n' +
	        	 						"한아름볼링 ▶ 15% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 10% 할인"; ; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "현대국민 카드 등록", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////현대카드 추가
	            	win.ud.card.add_Card(1);
	               JOptionPane.showMessageDialog(null, "추가되었습니다!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("삼성카드")){
	        	 String explanation = "애슐리 ▶ 샐러드바 10% 할인" + '\n' + "VIPS ▶ 샐러드바 10% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 5% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 10%할인" + '\n' + "고베규카츠 ▶ 전메뉴 5%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 10% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 5% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 25% 할인" + '\n' + "에버랜드 ▶ 자유이용권 15% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 10% 할인" + '\n' + "CGV ▶ 영화예매권 10% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 5% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 5% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 10%  할인" + '\n' + "할리스 ▶ 전메뉴 5% 할인" + '\n' + "스타벅스 ▶ 전메뉴 15% 할인" +
	    	 				'\n' + "스트라이크존 ▶ 10% 할인" + '\n' + "위VR ▶ 평일, 주말  5% 할인" + '\n' +
	    	 				"한아름볼링 ▶ 10% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 20% 할인"; ;  
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "삼성카드 등록", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////삼성카드 추가
	            	win.ud.card.add_Card(2);
	               JOptionPane.showMessageDialog(null, "추가되었습니다!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("신한카드")){
	        	 String explanation = "애슐리 ▶ 샐러드바 10% 할인" + '\n' + "VIPS ▶ 샐러드바 10% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 5% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 10%할인" + '\n' + "고베규카츠 ▶ 전메뉴 10%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 10% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 5% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 20% 할인" + '\n' + "에버랜드 ▶ 자유이용권 20% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 5% 할인" + '\n' + "CGV ▶ 영화예매권 10% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 5% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 5% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 10%  할인" + '\n' + "할리스 ▶ 전메뉴 5% 할인" + '\n' + "스타벅스 ▶ 전메뉴 10% 할인" +
	    	 				'\n' + "스트라이크존 ▶ 10% 할인" + '\n' + "위VR ▶ 평일, 주말  5% 할인" + '\n' +
	    	 				"한아름볼링 ▶ 10% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 20% 할인"; ; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "신한카드 등록", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////신한카드 추가
	            	win.ud.card.add_Card(3);
	               JOptionPane.showMessageDialog(null, "추가되었습니다!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("우리카드")){
	        	 String explanation = "애슐리 ▶ 샐러드바 5% 할인" + '\n' + "VIPS ▶ 샐러드바 10% 할인" + '\n' +
							"아웃백 ▶ 전메뉴 5% 할인" + '\n' + "Tokyo420  ▶ 전메뉴 5%할인" + '\n' + "고베규카츠 ▶ 전메뉴 10%할인 " +
							'\n' + "짚신매운갈비찜 ▶ 전메뉴 5% 할인" + '\n' + "서래갈매기 ▶ 전메뉴 10% 할인" + '\n' +
							"롯데월드 ▶ 자유이용권 15% 할인" + '\n' + "에버랜드 ▶ 자유이용권 25% 할인" + '\n' +
							"어린이대공원 ▶ 자유이용권 10% 할인" + '\n' + "CGV ▶ 영화예매권 5% 할인" + '\n' +
							"메가박스 ▶ 영화예매권 10% 할인" + '\n' + "롯데시네마 ▶ 영화예매권 5% 할인" + '\n' +
							"엔젤리너스 ▶ 전메뉴 5%  할인" + '\n' + "할리스 ▶ 전메뉴 10% 할인" + '\n' + "스타벅스 ▶ 전메뉴 10% 할인" +
	    	 						'\n' + "스트라이크존 ▶ 20% 할인" + '\n' + "위VR ▶ 평일, 주말  10% 할인" + '\n' +
	    	 						"한아름볼링 ▶ 10% 할인" + '\n' + "레전드야구존 ▶ 일반, 파티룸 15% 할인"; ; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "우리카드 등록", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////우리카드 추가
	            	win.ud.card.add_Card(4);
	               JOptionPane.showMessageDialog(null, "추가되었습니다!");
	            }else{
	               return;
	            }
	         }
	      }
   }

}

class userData{
	   
	   
	public Card card = new Card();
   
   public boolean card1; //카드정보. 
   public boolean card2;
   public boolean card3;
   public boolean card4;
   public boolean card5;
   
   
   public int temp_course; //첫번째 선택, 여기서는 큰 카테고리를 잡음(ex) 놀이공원, 식당 등.)
   public int temp_course2; //두번째 선택, 여기서는 작은 카테고리를 잡음. (ex) 스벅, 탐탐 등.)
   public int choice_course[][];
   boolean check_course1;
   boolean check_course2;
   boolean check_course3;
   public String course_name[] = {"놀이공원", "식당", "카페", "영화관", "스포츠"};
   userData(){
	   check_course1 = false;
	   check_course2 = false;
	   check_course3 = false;
	   choice_course = new int[3][3]; //1,2,3 코스 순서대로 큰 카테고리 -> 작은 카테고리 -> 가격 총합의 정보가 들어감 
   }


}


// 1.2 데이트 코스 짜는 패널
class DateCourse extends JPanel{
   public MainPanel parent;
   public BestDate win;
   public JLabel lblCourse1;
   public JLabel lblCourse2;
   public JLabel lblCourse3;
   public int total_price = 0;
   
   public DateCourse(BestDate win){
      this.win = win;
      
      this.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setLayout(new BorderLayout(0, 0));
      
      // 상단(제목)
      JLabel lblNewLabel = new JLabel("데이트 코스 짜기");
      lblNewLabel.setFont(new Font("", Font.BOLD, 30));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(lblNewLabel, BorderLayout.NORTH);
      
      // 중단
      JPanel panelCourse = new JPanel();
      this.add(panelCourse, BorderLayout.CENTER);
      panelCourse.setLayout(new GridLayout(1, 3));
      
         // 코스1
      JPanel panelCourse1 = new JPanel();
      panelCourse.add(panelCourse1);
      panelCourse1.setLayout(new BorderLayout(0, 0));
      
      JButton btnCourse1 = new JButton("코스1 선택");
      btnCourse1.setFont(new Font("", Font.BOLD, 20));
      panelCourse1.add(btnCourse1, BorderLayout.NORTH);
      
      lblCourse1 = new JLabel("코스1 정보");
      lblCourse1.setBackground(Color.YELLOW);
      lblCourse1.setHorizontalAlignment(SwingConstants.CENTER);   
      lblCourse1.setOpaque(true);
      lblCourse1.setFont(new Font("", Font.CENTER_BASELINE, 20));
      panelCourse1.add(lblCourse1, BorderLayout.CENTER);

         // 코스2
      JPanel panelCourse2 = new JPanel();
      panelCourse.add(panelCourse2);
      panelCourse2.setLayout(new BorderLayout(0, 0));

      JButton btnCourse2 = new JButton("코스2 선택");
      btnCourse2.setFont(new Font("", Font.BOLD, 20));
      panelCourse2.add(btnCourse2, BorderLayout.NORTH);
      
      lblCourse2 = new JLabel("코스2 정보");
      lblCourse2.setBackground(Color.CYAN);
      lblCourse2.setHorizontalAlignment(SwingConstants.CENTER);   
      lblCourse2.setOpaque(true);
      lblCourse2.setFont(new Font("", Font.CENTER_BASELINE, 20));
      panelCourse2.add(lblCourse2, BorderLayout.CENTER);
      
         // 코스3
      JPanel panelCourse3 = new JPanel();
      panelCourse.add(panelCourse3);
      panelCourse3.setLayout(new BorderLayout(0, 0));
      
      JButton btnCourse3 = new JButton("코스3 선택");
      btnCourse3.setFont(new Font("", Font.BOLD, 20));
      panelCourse3.add(btnCourse3, BorderLayout.NORTH);
      
      lblCourse3 = new JLabel("코스3 정보");
      lblCourse3.setBackground(Color.PINK);
      lblCourse3.setHorizontalAlignment(SwingConstants.CENTER);   
      lblCourse3.setOpaque(true);
      lblCourse3.setFont(new Font("", Font.CENTER_BASELINE, 20));
      panelCourse3.add(lblCourse3, BorderLayout.CENTER);
      
         // 버튼 리스너 등록
      btnCourse1.addActionListener(new CourseButtonListener());
      btnCourse2.addActionListener(new CourseButtonListener());
      btnCourse3.addActionListener(new CourseButtonListener());
      
      // 하단
      JPanel panelTotal = new JPanel();
      this.add(panelTotal, BorderLayout.SOUTH);
 	  

         // 총 가격
      JButton btnTotalPrice = new JButton("총 가격 보기");
      btnTotalPrice.setFont(new Font("", Font.BOLD, 30));
      panelTotal.add(btnTotalPrice);
      btnTotalPrice.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //// 총 가격(원래 가격, 할인된 가격 표시, 사용한 카드 등) 넣기
        	 total_price = 0;
        	 for(int i = 0; i < 3; i++) {
        		 total_price += win.ud.choice_course[i][2];
        	 }
    		 JOptionPane.showMessageDialog(null, "총 가격 : " + total_price + " 원");            
         }
      });
      
         // 맵
      JButton btnMap = new JButton("지도 보기");
      btnMap.setFont(new Font("", Font.BOLD, 30));
      panelTotal.add(btnMap);
      btnMap.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //// 맵 넣기 (코스 장소에 따라 위치 찍게 해야됨)
            GoogleMap google = new GoogleMap();
            google.setMap(win.map1 + "&" + win.map2 + "&" + win.map3);
            //System.out.println(win.map1 + win.map2 + win.map3);
         }
      });
      
         // 돌아가기 버튼
      JButton btnReturn = new JButton("돌아가기");
      btnReturn.setFont(new Font("", Font.BOLD, 30));
      panelTotal.add(btnReturn);
      btnReturn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            win.change("mainPanel");
         }
      });
   }
   
   class CourseButtonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton)e.getSource();
         if(b.getText().equals("코스1 선택")){
        	 win.change("categoryPick");
        	 win.ud.check_course1 = true; //true로 전환해서 데이터 넣어줄 계획. 
         }
         else if(b.getText().equals("코스2 선택")){
        	 win.change("categoryPick");
        	 win.ud.check_course2 = true; //true로 전환해서 데이터 넣어줄 계획. 

         }else if(b.getText().equals("코스3 선택")){
        	 win.change("categoryPick");
        	 win.ud.check_course3 = true; //true로 전환해서 데이터 넣어줄 계획. 

         }
            // 카테고리 선택 작업
      }
   }

}

// 1.2.1 카테고리 선택하는 패널
class CategoryPick extends JPanel{
   public BestDate win;
   
   public CategoryPick(BestDate win){
      this.win = win;
      
      this.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setLayout(new BorderLayout(0, 0));
      
      // 상단 (제목)
      JLabel lblCategory = new JLabel("카테고리 선택");
      lblCategory.setFont(new Font("", Font.BOLD, 30));
      lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(lblCategory, BorderLayout.NORTH);
      
      // 중단 (카테고리 버튼)
      JPanel panelCategory = new JPanel();
      this.add(panelCategory, BorderLayout.CENTER);
      panelCategory.setLayout(new GridLayout(3, 3));
      
      JButton button1 = new JButton("놀이공원");
      button1.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button1);
      button1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            win.ud.temp_course = 1; //일시적으로 저장해서 다음으로 정보 옮겨줌. 
            win.change("categoryPick2");
         }
         
      });
      
      JButton button2 = new JButton("식당");
      button2.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button2);
      button2.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            win.ud.temp_course = 2;
            win.change("categoryPick2");
         }
         
      });
      
      JButton button3 = new JButton("카페");
      button3.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button3);
      button3.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            win.ud.temp_course = 3;
            win.change("categoryPick2");
         }
         
      });
      
      JButton button4 = new JButton("영화관");
      button4.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button4);
      button4.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
             // TODO Auto-generated method stub
             win.ud.temp_course = 4;
             win.change("categoryPick2");
          }
          
       });
      
      JButton button5 = new JButton("스포츠");
      button5.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button5);
      button5.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
             // TODO Auto-generated method stub
             win.ud.temp_course = 5;
             win.change("categoryPick2");
          }
       });
      
      JButton button6 = new JButton("미술관"); //6~9번은 사용하지 않음 
      button6.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button6);
      
      JButton button7 = new JButton("공연");
      button7.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button7);
      
      JButton button8 = new JButton("뮤지컬");
      button8.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button8);
      
      JButton button9 = new JButton("드라이브");
      button9.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button9);
      
      // 하단
      JPanel panelReturn = new JPanel();
      this.add(panelReturn, BorderLayout.SOUTH);
      
         // 돌아가기 버튼
      JButton btnReturn = new JButton("돌아가기");
      btnReturn.setFont(new Font("", Font.BOLD, 30));
      panelReturn.add(btnReturn);
      btnReturn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	if(win.ud.check_course1 == true) {
        		win.ud.check_course1 = false; //돌아갈 경우 이렇게 다시 false로 초기화 시켜줌 
        	}
        	else if(win.ud.check_course2 == true) {
        		win.ud.check_course2 = false; //돌아갈 경우 이렇게 다시 false로 초기화 시켜줌 
        	}
        	else if(win.ud.check_course3 == true) {
        		win.ud.check_course3 = false; //돌아갈 경우 이렇게 다시 false로 초기화 시켜줌 
        	}
            win.change("dateCourse");
         }
      });
   }
   
}

// 1.2.2 카테고리에서 하나 누르면 넘어어는 패널(하위 카테고리 선택) 
class CategoryPick2 extends JPanel{
   public BestDate win;
   public String[] str_arr;
   public int[] int_arr;
   
   public CategoryPick2(BestDate win){
      this.win = win;
      
      this.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setLayout(new BorderLayout(0, 0));
      int_arr = new int[6]; //줄 번호가 저장되어 있음. 1일땐  1,2,3,4
      str_arr = new String[6];
      for(int i = 0; i < 6; i++){
    	  str_arr[i] = "X";  //가게 이름. 
      }
      // 상단 (제목)
      JLabel lblCategory = new JLabel("하위 카테고리 선택");
      lblCategory.setFont(new Font("", Font.BOLD, 30));
      lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(lblCategory, BorderLayout.NORTH);
      
      // 중단 (카테고리 버튼)
      JPanel panelCategory = new JPanel();
      this.add(panelCategory, BorderLayout.CENTER);
      panelCategory.setLayout(new GridLayout(3, 3));
      int j = 0;
      switch(win.ud.temp_course) { 			//코스에 따른 선택 가능한 버튼 다르게 제시. 
      case 1:
         //JOptionPane.showMessageDialog(null, win.pd.arr.length); 테스트용
         for(int i = 0; i < win.pd.arr.length ; i++ ) {
            if(win.pd.arr[i][1].equals("1")) {
               int_arr[j] = i;
               j++;
               if(j == 3) //3개 다 받아오면 탈출 하도록 함. 
                  break;
            }
         }
         for(int k = 0; k < int_arr.length; k++) {
        	 
            str_arr[k] = win.pd.arr[int_arr[k]][0];
            if(k > 2) {
            	str_arr[k] = "X"; //0으로 초기화 되어 있기 때문에 없을 경우 x로 선언
            }
         }
         j = 0; //다시 0으로 선언 
            
         break;
      case 2:
         for(int i = 0; i < win.pd.arr.length ; i++ ) {
            if(win.pd.arr[i][1].equals("2")) {
               int_arr[j] = i;
               j++;
               if(j == 6) //6개 다 받아오면 탈출 하도록 함. 
                  break;
            }
         }
         for(int k = 0; k < int_arr.length; k++) {
            str_arr[k] = win.pd.arr[int_arr[k]][0];
            
         }
         j = 0; //다시 0으로 선언 
         break;
      case 3:
         for(int i = 0; i < win.pd.arr.length ; i++ ) {
            if(win.pd.arr[i][1].equals("3")) {
               int_arr[j] = i;
               j++;
               if(j == 3) //3개 다 받아오면 탈출 하도록 함. 
                  break;
            }
         }
         for(int k = 0; k < int_arr.length; k++) {
            str_arr[k] = win.pd.arr[int_arr[k]][0];
            if(k > 2) {
            	str_arr[k] = "X"; //0으로 초기화 되어 있기 때문에 없을 경우 x로 선언
            }
            
         }
         j = 0; //다시 0으로 선언 
         break;
      case 4:
          for(int i = 0; i < win.pd.arr.length ; i++ ) {
             if(win.pd.arr[i][1].equals("4")) {
                int_arr[j] = i;
                j++;
                if(j == 3) //3개 다 받아오면 탈출 하도록 함. 
                   break;
             }
          }
          for(int k = 0; k < int_arr.length; k++) {
             str_arr[k] = win.pd.arr[int_arr[k]][0];
             if(k > 2) {
             	str_arr[k] = "X"; //0으로 초기화 되어 있기 때문에 없을 경우 x로 선언
             }
             
          }
          j = 0; //다시 0으로 선언 
          break;
      case 5:
          for(int i = 0; i < win.pd.arr.length ; i++ ) {
             if(win.pd.arr[i][1].equals("5")) {
                int_arr[j] = i;
                j++;
                if(j == 4) //4개 다 받아오면 탈출 하도록 함. 
                   break;
             }
          }
          for(int k = 0; k < int_arr.length; k++) {
             str_arr[k] = win.pd.arr[int_arr[k]][0];
             if(k > 3) {
             	str_arr[k] = "X"; //0으로 초기화 되어 있기 때문에 없을 경우 x로 선언
             }
             
          }
          j = 0; //다시 0으로 선언 
          break;
      }
      JButton button1 = new JButton(str_arr[0]);
      button1.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button1);
      button1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            //버튼 누르면 해당 카테코리가 뜨도록 설정. 
            //해당 정보를 ud에 저장해서 사용할 계획. 
        	 win.ud.temp_course2 = 1;
        	 win.change("menuPick");
         }
         
      });
      JButton button2 = new JButton(str_arr[1]);
      button2.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button2);
      button2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 win.ud.temp_course2 = 2;
        	 win.change("menuPick");
         }
      });
      
      JButton button3 = new JButton(str_arr[2]);
      button3.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button3);
      button3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 win.ud.temp_course2 = 3;
        	 win.change("menuPick");
         }
         
      });
      
      JButton button4 = new JButton(str_arr[3]);
      button4.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button4);
      button4.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 win.ud.temp_course2 = 4;
        	 win.change("menuPick");
         }
      });
      JButton button5 = new JButton(str_arr[4]);
      button5.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button5);
      button5.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
         	 win.ud.temp_course2 = 5;
         	 win.change("menuPick");
          }
       });
      
      JButton button6 = new JButton(str_arr[5]); //최대 여섯개 까지 사용
      button6.setFont(new Font("", Font.BOLD, 25));
      //button6.setEnabled(false);
      panelCategory.add(button6);
      button6.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
         	 win.ud.temp_course2 = 6;
         	 win.change("menuPick");
          }
       });
      
      JButton button7 = new JButton("X");
      button7.setEnabled(false);
      panelCategory.add(button7);
      
      JButton button8 = new JButton("X");
      button8.setEnabled(false);
      panelCategory.add(button8);
      
      JButton button9 = new JButton("X");
      button9.setEnabled(false);
      panelCategory.add(button9);
      
      // 하단
      JPanel panelReturn = new JPanel();
      this.add(panelReturn, BorderLayout.SOUTH);
      
         // 돌아가기 버튼
      JButton btnReturn = new JButton("돌아가기");
      btnReturn.setFont(new Font("", Font.BOLD, 30));
      panelReturn.add(btnReturn);
      btnReturn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            win.change("categoryPick"); //이전 화면으로 돌아가도록 수정 
         }
      });
   }
   
}

// 장소 정보 클래스
class placeData{
   public BestDate win;
   public String arr[][];
   public placeData(){
   arr = new String[100][10]; //데이트 할 수 있는 장소에 대한 정보가 들어가는 곳 
   for(int i = 0; i< 100; i++) {
	   for (int j = 0; j < 10; j++) {
		   arr[i][j] = "0";
	   }
   }
    try { 
        File myFile = new File("식당명단.txt"); //처음은 장소 이름, 그다음은 종류를 의미. 그 다음 부턴 메뉴-가격 순 
        FileReader fileReader = new FileReader(myFile); 
        BufferedReader reader = new BufferedReader(fileReader); 
        String line = null; // line 에다가 한 줄씩 읽어 옴 
     int i=0; 
     while ((line = reader.readLine()) != null) { 
           String[] splits = line.split("/"); // 읽은 행을 '/' 간격으로 스플릿(조각)낸다. 
       for(int j=0;j<splits.length;j++) 
       { 
          //j에서 두번째 인덱스는 종류를 가리킴 1 -> 놀이공원 2 -> 식당 3 -> 영화관 등등. 
         arr[i][j]=splits[j]; 
      } 
      i++; 
      } 
      reader.close(); // 버퍼리더 닫기.          
     } catch (Exception ex) { 
      ex.printStackTrace(); 
     } 
   }
}

// 1.2.3 메뉴 선택하는 패널
class MenuPick extends JPanel{
	public BestDate win;
	public String menu_arr[];
	public int totalprice = 0;		// 한 코스에서의 총 가격
	public JLabel lblPrice = new JLabel("총 가격 : " + totalprice + " 원");		// 한 코스에서의 총 가격 출력
	public int card_check[];
	public String card_name[] = {"국민", "현대", "삼성", "신한", "우리"};
	public int max_discount;
	public String max_discount_name;
	public int before_price[];
	public int discount_price[];
//	public String map1, map2, map3;
	
	public MenuPick(BestDate win){
		this.win = win;
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
		menu_arr = new String[8];
		card_check = new int[5];
		before_price = new int[4];
		discount_price = new int [4];
		max_discount_name = "카드없음";
		max_discount = 0;
		for(int i = 0; i < menu_arr.length; i++) {
			menu_arr[i] = "X"; // X로 초기화
		}
		// 상단 (이름)
		JLabel lblName = new JLabel(win.categoryPick2.str_arr[win.ud.temp_course2-1]); //// 선택한 장소로 이름 변경해야함
		lblName.setFont(new Font("", Font.BOLD, 30));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblName, BorderLayout.NORTH);
		
		// 중단
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1));		// 4개까지 들어갈 수 있도록 고정함
		
		int j = 0;
	      switch(win.ud.temp_course2) { //코스에 따른 선택 가능한 버튼 다르게 제시. 
	      case 1:
	         //JOptionPane.showMessageDialog(null, win.pd.arr.length); 테스트용
	    	 if(win.ud.temp_course == 1) {
	         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	        	menu_arr[j] = win.pd.arr[0][i];
	        	j++;
	         	}
	         }
	    	 else if(win.ud.temp_course == 2) {
		         for(int i = 2; i < 10 ; i++ ) { 
			        	menu_arr[j] = win.pd.arr[3][i];
			        	j++;
			     }
			 }
	    	 else if(win.ud.temp_course == 3) {
		         for(int i = 2; i < 10 ; i++ ) { 
			        	menu_arr[j] = win.pd.arr[9][i];
			        	j++;
			     }
			 }
	    	 else if(win.ud.temp_course == 4) {
		         for(int i = 2; i < 10 ; i++ ) { 
			        	menu_arr[j] = win.pd.arr[12][i];
			        	j++;
			     }
			 }
	    	 else if(win.ud.temp_course == 5) {
		         for(int i = 2; i < 10 ; i++ ) { 
			        	menu_arr[j] = win.pd.arr[15][i];
			        	j++;
			     }
			 }
	    	 j = 0; //다시 쓰려고 초기화 시켜줌
	    	 break;
	      case 2:
	      		if(win.ud.temp_course == 1) {
	   	         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   	        	menu_arr[j] = win.pd.arr[1][i];
	   	        	j++;
	   	         	}
	   	         }
	   	    	 else if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   			        	menu_arr[j] = win.pd.arr[4][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	 else if(win.ud.temp_course == 3) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   			        	menu_arr[j] = win.pd.arr[10][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	else if(win.ud.temp_course == 4) {
			         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
				        	menu_arr[j] = win.pd.arr[13][i];
				        	j++;
				     }
				 }
	   	    	else if(win.ud.temp_course == 5) {
			         for(int i = 2; i < 10 ; i++ ) { 
				        	menu_arr[j] = win.pd.arr[16][i];
				        	j++;
				     }
				 }
	   	    	 j = 0; //다시 쓰려고 초기화 시켜줌
	   	    	 break;
	      	case 3:
	   	    	if(win.ud.temp_course == 1) {
	   	         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   	        	menu_arr[j] = win.pd.arr[2][i];
	   	        	j++;
	   	         	}
	   	         }
	   	    	 else if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   			        	menu_arr[j] = win.pd.arr[5][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	 else if(win.ud.temp_course == 3) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   			        	menu_arr[j] = win.pd.arr[11][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	else if(win.ud.temp_course == 4) {
			         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
				        	menu_arr[j] = win.pd.arr[14][i];
				        	j++;
				     }
				 }
	   	    	else if(win.ud.temp_course == 5) {
			         for(int i = 2; i < 10 ; i++ ) { 
				        	menu_arr[j] = win.pd.arr[17][i];
				        	j++;
				     }
				 }
	   	    	j = 0; //다시 쓰려고 초기화 시켜줌
	   	    	break;
	      	case 4:
	      		/*if(win.ud.temp_course == 1) {
		   	         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
		   	        	menu_arr[j] = win.pd.arr[3][i];
		   	        	j++;
		   	         	}
		   	         }*/
		   	   	if(win.ud.temp_course == 2) {
		   		         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
		   			        menu_arr[j] = win.pd.arr[6][i];
		   			        	j++;
		   			     }
		   			 }
		   	   /*else if(win.ud.temp_course == 3) {
		   		    for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
		   			        menu_arr[j] = win.pd.arr[12][i];
		   			        j++;
		   			     }
		   			 }*/
		   	 else if(win.ud.temp_course == 5) {
		         for(int i = 2; i < 10 ; i++ ) { 
			        	menu_arr[j] = win.pd.arr[18][i];
			        	j++;
			     }
			 }
	   	    	 j = 0; //다시 쓰려고 초기화 시켜줌
	   	    	 break;
	      	case 5:
	      		if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   			        menu_arr[j] = win.pd.arr[7][i];
	   			        	j++;
	   		      }
	   			}
	      		 j = 0; //다시 쓰려고 초기화 시켜줌
	   	    	 break;
	      	case 6:
	      		if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2번 인덱스부터 집어넣을꺼임.
	   			        menu_arr[j] = win.pd.arr[8][i];
	   			        	j++;
	   		      }
	   			}
	      		 j = 0; //다시 쓰려고 초기화 시켜줌
	   	    	 break;
	      		
	         
	      }
		//카드 할인율 받아오기
	      for(int i = 0; i < 5; i ++) {
	          boolean check_card;
	         check_card = win.ud.card.getIs_CardAdded(i);
	         if(check_card) { //카드가 있을 경우 인덱스 채워줄 것
	            card_check[i] = win.ud.card.get_DiscountRate(card_name[i],win.categoryPick2.str_arr[win.ud.temp_course2-1] ); //있을 경우 1로 채워 줄 것. 국민 현대 삼성 신한 우리 순.

	            if(i == 0) {
	                max_discount = card_check[i];
	                max_discount_name = card_name[i];
	             }
	             else if(i!= 0) {
	                if(card_check[i] > max_discount) {
	                   max_discount = card_check[i]; //최고 할인율
	                   max_discount_name = card_name[i]; //카드를 저장해줌.
	                }
	             }
	         }
	         else
	            card_check[i] = 0 ; //카드가 있을 경우 할인율을 넣어주고, 없을 경우 0을 넣어줌 

	       }
	   
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		panel.add(p1);
		JCheckBox cbox1 = new JCheckBox(menu_arr[0]);		//이름 들어가는 곳 
		cbox1.setFont(new Font("", Font.BOLD, 30));
		cbox1.setName("cb1");								// 어떤 체크박스를 선택했는지 구분하기 위해 지정
		cbox1.addItemListener(new CheckBoxListener());
		p1.add(cbox1, BorderLayout.WEST);
		JButton b1 = new JButton(">");
		b1.setFont(new Font("", Font.BOLD, 30));
		b1.setName("b1");									// 어떤 버튼을 선택했는지 구분하기 위해 지정
		p1.add(b1, BorderLayout.EAST);
		b1.addActionListener(new MenuInfoListener());
		JLabel j1 = new JLabel("원 가격 : " + menu_arr[1] + "원");
		j1.setFont(new Font("", Font.BOLD, 30));
		j1.setHorizontalAlignment(SwingConstants.RIGHT);
		p1.add(j1, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		panel.add(p2);
		JCheckBox cbox2 = new JCheckBox(menu_arr[2]);
		cbox2.setFont(new Font("", Font.BOLD, 30));
		cbox2.setName("cb2");
		cbox2.addItemListener(new CheckBoxListener());
		p2.add(cbox2, BorderLayout.WEST);
		JButton b2 = new JButton(">");
		b2.setFont(new Font("", Font.BOLD, 30));
		b2.setName("b2");
		p2.add(b2, BorderLayout.EAST);
		b2.addActionListener(new MenuInfoListener());
		JLabel j2 = new JLabel("원 가격 : " + menu_arr[3] + "원");
		j2.setFont(new Font("", Font.BOLD, 30));
		j2.setHorizontalAlignment(SwingConstants.RIGHT);
		p2.add(j2, BorderLayout.CENTER);

		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		panel.add(p3);
		JCheckBox cbox3 = new JCheckBox(menu_arr[4]);
		cbox3.setFont(new Font("", Font.BOLD, 30));
		cbox3.setName("cb3");
		cbox3.addItemListener(new CheckBoxListener());
		p3.add(cbox3, BorderLayout.WEST);
		JButton b3 = new JButton(">");
		b3.setFont(new Font("", Font.BOLD, 30));
		b3.setName("b3");
		p3.add(b3, BorderLayout.EAST);
		b3.addActionListener(new MenuInfoListener());
		JLabel j3 = new JLabel("원 가격 : " + menu_arr[5] + "원");
		j3.setFont(new Font("", Font.BOLD, 30));
		j3.setHorizontalAlignment(SwingConstants.RIGHT);
		p3.add(j3, BorderLayout.CENTER);

		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		panel.add(p4);
		JCheckBox cbox4 = new JCheckBox(menu_arr[6]);
		cbox4.setFont(new Font("", Font.BOLD, 30));
		cbox4.setName("cb4");
		cbox4.addItemListener(new CheckBoxListener() {
			
		});
		p4.add(cbox4, BorderLayout.WEST);
		JButton b4 = new JButton(">");
		b4.setFont(new Font("", Font.BOLD, 30));
		b4.setName("b4");
		p4.add(b4, BorderLayout.EAST);
		b4.addActionListener(new MenuInfoListener());
		JLabel j4 = new JLabel("원 가격 : " + menu_arr[7] + "원");
		j4.setFont(new Font("", Font.BOLD, 30));
		j4.setHorizontalAlignment(SwingConstants.RIGHT);
		p4.add(j4, BorderLayout.CENTER);
		
		// 하단
		JPanel panelBottom = new JPanel();
		this.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
			// 총 가격
		lblPrice.setFont(new Font("", Font.BOLD, 30));
		lblPrice.setForeground(Color.RED);
		panelBottom.add(lblPrice, BorderLayout.WEST);
		
			// 버튼
		JPanel btnArea = new JPanel();
		btnArea.setLayout(new GridLayout(1, 2));
		panelBottom.add(btnArea, BorderLayout.EAST);		
		
				// 저장 버튼
		JButton btnSave = new JButton("저장");
		btnSave.setFont(new Font("", Font.BOLD, 30));
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//// 정보 저장해야함 + 저장된 정보를 dateCourse화면에 갱신해야함
				if(win.ud.check_course1 == true) {
					win.ud.choice_course[0][0] = win.ud.temp_course;
					win.ud.choice_course[0][1] = win.ud.temp_course2;
					win.ud.choice_course[0][2] = totalprice;
					win.map1 = win.categoryPick2.str_arr[win.ud.temp_course2-1];
					win.dateCourse.lblCourse1.setText("<html>" + win.categoryPick2.str_arr[win.ud.temp_course2-1]+"<br><br>" + max_discount_name + "카드 사용 시 <br>" + "총 가격 : " + totalprice+"원<br></html>"); //선택한 장소에 대한 정보를 받아온 이후 값을 변경시켜줌.
					win.ud.check_course1 = false; // 다시 false로 변환시켜줌. 
				}
				else if(win.ud.check_course2 == true) {
					win.ud.choice_course[1][0] = win.ud.temp_course;
					win.ud.choice_course[1][1] = win.ud.temp_course2;
					win.ud.choice_course[1][2] = totalprice;
					win.map2 = win.categoryPick2.str_arr[win.ud.temp_course2-1];
					win.dateCourse.lblCourse2.setText("<html>" + win.categoryPick2.str_arr[win.ud.temp_course2-1]+"<br><br>" + max_discount_name + "카드 사용 시 <br>" + "총 가격 : " + totalprice+"원<br></html>"); //선택한 장소에 대한 정보를 받아온 이후 값을 변경시켜줌.
					win.ud.check_course2 = false; // 다시 false로 변환시켜줌. 
				}
				else if(win.ud.check_course3 == true) {
					win.ud.choice_course[2][0] = win.ud.temp_course;
					win.ud.choice_course[2][1] = win.ud.temp_course2;
					win.ud.choice_course[2][2] = totalprice;
					win.map3 = win.categoryPick2.str_arr[win.ud.temp_course2-1];
					win.dateCourse.lblCourse3.setText("<html>" + win.categoryPick2.str_arr[win.ud.temp_course2-1]+"<br><br>" + max_discount_name + "카드 사용 시 <br>" + "총 가격 : " + totalprice+"원<br></html>"); //선택한 장소에 대한 정보를 받아온 이후 값을 변경시켜줌.
					win.ud.check_course3 = false; // 다시 false로 변환시켜줌. 
				}
				JOptionPane.showMessageDialog(null, "저장되었습니다!");
				win.change("dateCourse");
			}
		});
				// 돌아가기 버튼(저장 안함)
		JButton btnReturn = new JButton("돌아가기");
		btnReturn.setFont(new Font("", Font.BOLD, 30));
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				win.change("categoryPick2");
			}
		});
		btnArea.add(btnSave);
		btnArea.add(btnReturn);
	}
	
	// 정보 버튼 리스너
	class MenuInfoListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			//// 상품 정보와 카드 정보 받아와야 한다
			
			if(b.getName() == "b1"){
				String message;
				message = "<최적할인정보>-" + max_discount_name + " " + max_discount +"%할인\n원 가격 :" + before_price[0] +"원\n할인된 가격 :" + discount_price[0] +"원";				//// 정보들 요기다 출력하면 됨
				JOptionPane.showMessageDialog(null, message);
			}else if(b.getName() == "b2"){ 
				String message;
				message = "<최적할인정보>-" + max_discount_name + " " + max_discount +"%할인\n원 가격 :" + before_price[1] +"원\n할인된 가격 :" + discount_price[1] +"원";				//// 정보들 요기다 출력하면 됨
				JOptionPane.showMessageDialog(null, message);
			}else if(b.getName() == "b3"){
				String message;
				message = "<최적할인정보>-" + max_discount_name + " " + max_discount +"%할인\n원 가격 :" + before_price[2] +"원\n할인된 가격 :" + discount_price[2] +"원";				//// 정보들 요기다 출력하면 됨
				JOptionPane.showMessageDialog(null, message);
			}else if(b.getName() == "b4"){
				String message;
				message = "<최적할인정보>-" + max_discount_name + " " + max_discount +"%할인\n원 가격 :" + before_price[3] +"원\n할인된 가격 :" + discount_price[3] +"원";				//// 정보들 요기다 출력하면 됨
				JOptionPane.showMessageDialog(null, message);
			}
		}
	}
	
	// 체크박스 선택 리스너
	class CheckBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cb = (JCheckBox)e.getSource();
			//// totalprice 정보 받아와야 한다
			
			if(cb.getName() == "cb1"){
				int intValue = Integer.parseInt(menu_arr[1]); //string을 int로 형 변환
				before_price[0] = intValue;
				discount_price[0] = intValue*(100 - max_discount) / 100;
				if(e.getStateChange() == 1){		// 체크 표시될 때
					totalprice += intValue*(100 - max_discount) / 100;								//// +가격 정보 입력하면 됨
				}else{								// 체크 표시 해제될 때
					totalprice -= intValue*(100 - max_discount) / 100;								//// -가격 정보 입력하면 됨
				}
			}
			else if(cb.getName() == "cb2"){
				int intValue2 = Integer.parseInt(menu_arr[3]);
				before_price[1] = intValue2;
				discount_price[1] = intValue2*(100 - max_discount) / 100;

				if(e.getStateChange() == 1){
					totalprice += intValue2*(100 - max_discount) / 100;;
				}else{
					totalprice -= intValue2*(100 - max_discount) / 100;;
				}
			}
			else if(cb.getName() == "cb3"){
				int intValue3 = Integer.parseInt(menu_arr[5]);
				before_price[2] = intValue3;
				discount_price[2] = intValue3*(100 - max_discount) / 100;

				if(e.getStateChange() == 1){
					totalprice += intValue3*(100 - max_discount) / 100;;
				}else{
					totalprice -= intValue3*(100 - max_discount) / 100;;
				}
			}
			else if(cb.getName() == "cb4"){
				int intValue4 = Integer.parseInt(menu_arr[7]);
				before_price[3] = intValue4;
				discount_price[3] = intValue4*(100 - max_discount) / 100;

				if(e.getStateChange() == 1){
					totalprice += intValue4*(100 - max_discount) / 100;;
				}else{
					totalprice -= intValue4*(100 - max_discount) / 100;;
				}
			}
			lblPrice.setText("총 가격 : " + totalprice + " 원");	// 가격 갱신
		}
		
	}
}
