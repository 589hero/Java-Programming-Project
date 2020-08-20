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

   // �г� ����
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
      
      // �г� ����                              // �г� �߰� �� �߰��� �κ�1
      mainPanel = new MainPanel(this);
      cardAddPanel = new CardAddPanel(this);
      dateCourse = new DateCourse(this);
      //categoryPick = new CategoryPick(this);
      ud = new userData();
      pd = new placeData(); //�ڽ��� ���� ���� �޾ƿ� 
      // �г� �߰�
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
   
   // ȭ�� ��ȯ                                 // �г� �߰� �� �߰��� �κ�2
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

// 1. ���� �г�
class MainPanel extends JPanel{
//   public JTextField txtCardList;
   public BestDate win;
   public JList listCardList;
   public DefaultListModel<String> model;
   
   public MainPanel(BestDate win){
      this.win = win;
      
      this.setBorder(new EmptyBorder(1, 5, 5, 1));
      this.setLayout(new BorderLayout(0, 0));
      
      // 1) ��ư
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(3, 1));
      this.add(panel, BorderLayout.WEST);
      
         // ī�� ��� ��ư
      JButton btnCardAdd = new JButton("ī�� ���");
      btnCardAdd.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardAdd);
      
         // ī�� ���� ��ư
      JButton btnCardDelete = new JButton("ī�� ����");
      btnCardDelete.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardDelete);
      
         // ����Ʈ �ڽ� ¥�� ��ư
      JButton btnDating = new JButton("����Ʈ �ڽ� ¥��");
      btnDating.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnDating);
      
      // 2) ī�� ��� ���̱�
      JPanel panelCardList = new JPanel();
      this.add(panelCardList, BorderLayout.CENTER);
      
      JScrollPane scrollPane = new JScrollPane();
      this.add(scrollPane, BorderLayout.CENTER);
      
      JLabel lblListCard = new JLabel("<���� ���� ī�� ���>");
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
				if(model.getElementAt(index) == "����ī��"){
					String explanation = "�ֽ��� �� ������� 10% ����" + '\n' + "VIPS �� ������� 5% ����" + '\n' +
							"�ƿ��� �� ���޴� 10% ����" + '\n' + "Tokyo420  �� ���޴� 10%����" + '\n' + "����ī�� �� ���޴� 10%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 15% ����" + '\n' + "�������ű� �� ���޴� 5% ����" + '\n' +
							"�Ե����� �� �����̿�� 15% ����" + '\n' + "�������� �� �����̿�� 25% ����" + '\n' +
							"��̴���� �� �����̿�� 10% ����" + '\n' + "CGV �� ��ȭ���ű� 5% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 10% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 10% ����" + '\n' +
							"�������ʽ� �� ���޴� 10%  ����" + '\n' + "�Ҹ��� �� ���޴� 5% ����" + '\n' + "��Ÿ���� �� ���޴� 10% ����" +
							'\n' + "��Ʈ����ũ�� �� 15% ����" + '\n' + "��VR �� ����, �ָ�  10% ����" + '\n' +
							"�ѾƸ����� �� 15% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 15% ����"; 
					JOptionPane.showMessageDialog(null, explanation, "����ī�� ����", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "����ī��"){
					String explanation = "�ֽ��� �� ������� 5% ����" + '\n' + "VIPS �� ������� 5% ����" + '\n' +
	 						"�ƿ��� �� ���޴� 10% ����" + '\n' + "Tokyo420  �� ���޴� 5%����" + '\n' + "����ī�� �� ���޴� 5%���� " +
	 						'\n' + "¤�Ÿſ���� �� ���޴� 5% ����" + '\n' + "�������ű� �� ���޴� 10% ����" + '\n' +
	 						"�Ե����� �� �����̿�� 20% ����" + '\n' + "�������� �� �����̿�� 20% ����" + '\n' +
	 						"��̴���� �� �����̿�� 5% ����" + '\n' + "CGV �� ��ȭ���ű� 10% ����" + '\n' +
	 						"�ް��ڽ� �� ��ȭ���ű� 5% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 10% ����" + '\n' +
	 						"�������ʽ� �� ���޴� 5%  ����" + '\n' + "�Ҹ��� �� ���޴� 10% ����" + '\n' + "��Ÿ���� �� ���޴� 15% ����" +
	 						'\n' + "��Ʈ����ũ�� �� 15% ����" + '\n' + "��VR �� ����, �ָ�  5% ����" + '\n' +
	 						"�ѾƸ����� �� 15% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 10% ����";
					JOptionPane.showMessageDialog(null, explanation, "����ī�� ����", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "�Ｚī��"){
					String explanation = "�ֽ��� �� ������� 10% ����" + '\n' + "VIPS �� ������� 10% ����" + '\n' +
							"�ƿ��� �� ���޴� 5% ����" + '\n' + "Tokyo420  �� ���޴� 10%����" + '\n' + "����ī�� �� ���޴� 5%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 10% ����" + '\n' + "�������ű� �� ���޴� 5% ����" + '\n' +
							"�Ե����� �� �����̿�� 25% ����" + '\n' + "�������� �� �����̿�� 15% ����" + '\n' +
							"��̴���� �� �����̿�� 10% ����" + '\n' + "CGV �� ��ȭ���ű� 10% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 5% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 5% ����" + '\n' +
							"�������ʽ� �� ���޴� 10%  ����" + '\n' + "�Ҹ��� �� ���޴� 5% ����" + '\n' + "��Ÿ���� �� ���޴� 15% ����" +
	    	 				'\n' + "��Ʈ����ũ�� �� 10% ����" + '\n' + "��VR �� ����, �ָ�  5% ����" + '\n' +
	    	 				"�ѾƸ����� �� 10% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 20% ����";
					JOptionPane.showMessageDialog(null, explanation, "�Ｚī�� ����", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "����ī��"){
					String explanation = "�ֽ��� �� ������� 10% ����" + '\n' + "VIPS �� ������� 10% ����" + '\n' +
							"�ƿ��� �� ���޴� 5% ����" + '\n' + "Tokyo420  �� ���޴� 10%����" + '\n' + "����ī�� �� ���޴� 10%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 10% ����" + '\n' + "�������ű� �� ���޴� 5% ����" + '\n' +
							"�Ե����� �� �����̿�� 20% ����" + '\n' + "�������� �� �����̿�� 20% ����" + '\n' +
							"��̴���� �� �����̿�� 5% ����" + '\n' + "CGV �� ��ȭ���ű� 10% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 5% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 5% ����" + '\n' +
							"�������ʽ� �� ���޴� 10%  ����" + '\n' + "�Ҹ��� �� ���޴� 5% ����" + '\n' + "��Ÿ���� �� ���޴� 10% ����" +
	    	 				'\n' + "��Ʈ����ũ�� �� 10% ����" + '\n' + "��VR �� ����, �ָ�  5% ����" + '\n' +
	    	 				"�ѾƸ����� �� 10% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 20% ����";
					JOptionPane.showMessageDialog(null, explanation, "����ī�� ����", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(model.getElementAt(index) == "�츮ī��"){
					String explanation = "�ֽ��� �� ������� 5% ����" + '\n' + "VIPS �� ������� 10% ����" + '\n' +
							"�ƿ��� �� ���޴� 5% ����" + '\n' + "Tokyo420  �� ���޴� 5%����" + '\n' + "����ī�� �� ���޴� 10%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 5% ����" + '\n' + "�������ű� �� ���޴� 10% ����" + '\n' +
							"�Ե����� �� �����̿�� 15% ����" + '\n' + "�������� �� �����̿�� 25% ����" + '\n' +
							"��̴���� �� �����̿�� 10% ����" + '\n' + "CGV �� ��ȭ���ű� 5% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 10% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 5% ����" + '\n' +
							"�������ʽ� �� ���޴� 5%  ����" + '\n' + "�Ҹ��� �� ���޴� 10% ����" + '\n' + "��Ÿ���� �� ���޴� 10% ����" +
	    	 						'\n' + "��Ʈ����ũ�� �� 20% ����" + '\n' + "��VR �� ����, �ָ�  10% ����" + '\n' +
	    	 						"�ѾƸ����� �� 10% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 15% ����";
					JOptionPane.showMessageDialog(null, explanation, "�츮ī�� ����", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
    	  
	});
      
      // ��ư ������ �߰�
      btnCardAdd.addActionListener(new ButtonListener());
      btnCardDelete.addActionListener(new ButtonListener());
      btnDating.addActionListener(new ButtonListener());

   }
   
   public void showList(){
//     System.out.println(win.ud.card.getIs_CardAdded(0));
	   model.clear();
     
     if(win.ud.card.getIs_CardAdded(0)){
   	  model.addElement("����ī��");      	
     }
     if(win.ud.card.getIs_CardAdded(1)){
   	  model.addElement("����ī��");      	
     }
     if(win.ud.card.getIs_CardAdded(2)){
   	  model.addElement("�Ｚī��");      	
     }
     if(win.ud.card.getIs_CardAdded(3)){
   	  model.addElement("����ī��");      	
     }
     if(win.ud.card.getIs_CardAdded(4)){
   	  model.addElement("�츮ī��");      	
     }
  }
   
   // ��ư ������
   class ButtonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton)e.getSource();
         if(b.getText().equals("ī�� ���")){
            win.change("cardAddPanel");
         }
         else if(b.getText().equals("ī�� ����")){
            if(!listCardList.isSelectionEmpty()){   // ī�� ��Ͽ��� ī�� �ϳ��� ���õǸ� ����
               int result = JOptionPane.showConfirmDialog(null, "���� ī�带 �����Ͻðڽ��ϱ�?", "ī�� ����", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
               if(result == 0){
                  ///////ī�� ���� �۾�
            	   int index = -1;
//            	   System.out.println(listCardList.getSelectedValue());
            	   if(listCardList.getSelectedValue() == "����ī��"){
            		   index = 0;
            	   }
            	   else if(listCardList.getSelectedValue() == "����ī��"){
            		   index = 1;
            	   }
            	   else if(listCardList.getSelectedValue() == "�Ｚī��"){
            		   index = 2;
            	   }
            	   else if(listCardList.getSelectedValue() == "����ī��"){
            		   index = 3;
            	   }
            	   else if(listCardList.getSelectedValue() == "�츮ī��"){
            		   index = 4;
            	   }
            	   win.ud.card.delete_Card(index);
                  JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!");
                  showList();
               }else{
                  return;
               }
            }
         }
         else if(b.getText().equals("����Ʈ �ڽ� ¥��")){
            win.change("dateCourse");
         }
      }
      
   }

}


// 1.1 ī�� ��� �г�
class CardAddPanel extends JPanel{
   public MainPanel parent;
   public BestDate win;
   
   public CardAddPanel(BestDate win){
      this.win = win;
      parent = win.mainPanel;
      
      this.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setLayout(new BorderLayout(0, 0));
      
      // ����
      JLabel label = new JLabel("<ī�� ���>");
      label.setFont(new Font("", Font.BOLD, 30));
      label.setHorizontalAlignment(SwingConstants.CENTER);;
      this.add(label, BorderLayout.NORTH);
      
      // ��ư �߰�
      JPanel panel = new JPanel();
      this.add(panel, BorderLayout.CENTER);
      panel.setLayout(new GridLayout(3, 3));
      
      JButton btnCardGuk = new JButton("KB����ī��");
      btnCardGuk.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardGuk);
      btnCardGuk.setFocusable(false);
      
      JButton btnCardHyun = new JButton("����ī��");
      btnCardHyun.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardHyun);
      btnCardHyun.setFocusable(false);
      
      JButton btnCardSam = new JButton("�Ｚī��");
      btnCardSam.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardSam);
      btnCardSam.setFocusable(false);
      
      JButton btnCardShin = new JButton("����ī��");
      btnCardShin.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardShin);
      btnCardShin.setFocusable(false);
      
      JButton btnCardOo = new JButton("�츮ī��");
      btnCardOo.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardOo);
      btnCardOo.setFocusable(false);
      
      // UI�� �߰�
      JButton btnCardHa = new JButton("�ϳ�ī��");
      btnCardHa.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardHa);
      btnCardOo.setFocusable(false);
		
      JButton btnCardBc = new JButton("BCī��");
      btnCardBc.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardBc);
      btnCardOo.setFocusable(false);
		
      JButton btnCardLo = new JButton("�Ե�ī��");
      btnCardLo.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardLo);
      btnCardOo.setFocusable(false);
		
      JButton btnCardNo = new JButton("����ī��");
      btnCardNo.setFont(new Font("", Font.BOLD, 30));
      panel.add(btnCardNo);
      btnCardOo.setFocusable(false);
      
      // ��ư ������ �߰�
      btnCardGuk.addActionListener(new RegButtonListener());
      btnCardHyun.addActionListener(new RegButtonListener());
      btnCardSam.addActionListener(new RegButtonListener());
      btnCardShin.addActionListener(new RegButtonListener());
      btnCardOo.addActionListener(new RegButtonListener());

      // ���ư��� ��ư
      JPanel returnPanel = new JPanel();
      this.add(returnPanel, BorderLayout.SOUTH);
      
      JButton btnReturn = new JButton("���ư���");
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
   
   // ī�� ��ư ������
   class RegButtonListener implements ActionListener{
	      @Override
	      public void actionPerformed(ActionEvent e) {
	         JButton b = (JButton)e.getSource();
	         if(b.getText().equals("KB����ī��")){
	        	 String explanation = "�ֽ��� �� ������� 10% ����" + '\n' + "VIPS �� ������� 5% ����" + '\n' +
							"�ƿ��� �� ���޴� 10% ����" + '\n' + "Tokyo420  �� ���޴� 10%����" + '\n' + "����ī�� �� ���޴� 10%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 15% ����" + '\n' + "�������ű� �� ���޴� 5% ����" + '\n' +
							"�Ե����� �� �����̿�� 15% ����" + '\n' + "�������� �� �����̿�� 25% ����" + '\n' +
							"��̴���� �� �����̿�� 10% ����" + '\n' + "CGV �� ��ȭ���ű� 5% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 10% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 10% ����" + '\n' +
							"�������ʽ� �� ���޴� 10%  ����" + '\n' + "�Ҹ��� �� ���޴� 5% ����" + '\n' + "��Ÿ���� �� ���޴� 10% ����" +
							'\n' + "��Ʈ����ũ�� �� 15% ����" + '\n' + "��VR �� ����, �ָ�  10% ����" + '\n' +
							"�ѾƸ����� �� 15% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 15% ����"; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "KB����ī�� ���", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////����ī�� �߰�
	            	win.ud.card.add_Card(0);
	               JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("����ī��")){
	        	
	        	 String explanation = "�ֽ��� �� ������� 5% ����" + '\n' + "VIPS �� ������� 5% ����" + '\n' +
	        	 						"�ƿ��� �� ���޴� 10% ����" + '\n' + "Tokyo420  �� ���޴� 5%����" + '\n' + "����ī�� �� ���޴� 5%���� " +
	        	 						'\n' + "¤�Ÿſ���� �� ���޴� 5% ����" + '\n' + "�������ű� �� ���޴� 10% ����" + '\n' +
	        	 						"�Ե����� �� �����̿�� 20% ����" + '\n' + "�������� �� �����̿�� 20% ����" + '\n' +
	        	 						"��̴���� �� �����̿�� 5% ����" + '\n' + "CGV �� ��ȭ���ű� 10% ����" + '\n' +
	        	 						"�ް��ڽ� �� ��ȭ���ű� 5% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 10% ����" + '\n' +
	        	 						"�������ʽ� �� ���޴� 5%  ����" + '\n' + "�Ҹ��� �� ���޴� 10% ����" + '\n' + "��Ÿ���� �� ���޴� 15% ����" +
	        	 						'\n' + "��Ʈ����ũ�� �� 15% ����" + '\n' + "��VR �� ����, �ָ�  5% ����" + '\n' +
	        	 						"�ѾƸ����� �� 15% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 10% ����"; ; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "���뱹�� ī�� ���", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////����ī�� �߰�
	            	win.ud.card.add_Card(1);
	               JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("�Ｚī��")){
	        	 String explanation = "�ֽ��� �� ������� 10% ����" + '\n' + "VIPS �� ������� 10% ����" + '\n' +
							"�ƿ��� �� ���޴� 5% ����" + '\n' + "Tokyo420  �� ���޴� 10%����" + '\n' + "����ī�� �� ���޴� 5%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 10% ����" + '\n' + "�������ű� �� ���޴� 5% ����" + '\n' +
							"�Ե����� �� �����̿�� 25% ����" + '\n' + "�������� �� �����̿�� 15% ����" + '\n' +
							"��̴���� �� �����̿�� 10% ����" + '\n' + "CGV �� ��ȭ���ű� 10% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 5% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 5% ����" + '\n' +
							"�������ʽ� �� ���޴� 10%  ����" + '\n' + "�Ҹ��� �� ���޴� 5% ����" + '\n' + "��Ÿ���� �� ���޴� 15% ����" +
	    	 				'\n' + "��Ʈ����ũ�� �� 10% ����" + '\n' + "��VR �� ����, �ָ�  5% ����" + '\n' +
	    	 				"�ѾƸ����� �� 10% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 20% ����"; ;  
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "�Ｚī�� ���", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////�Ｚī�� �߰�
	            	win.ud.card.add_Card(2);
	               JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("����ī��")){
	        	 String explanation = "�ֽ��� �� ������� 10% ����" + '\n' + "VIPS �� ������� 10% ����" + '\n' +
							"�ƿ��� �� ���޴� 5% ����" + '\n' + "Tokyo420  �� ���޴� 10%����" + '\n' + "����ī�� �� ���޴� 10%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 10% ����" + '\n' + "�������ű� �� ���޴� 5% ����" + '\n' +
							"�Ե����� �� �����̿�� 20% ����" + '\n' + "�������� �� �����̿�� 20% ����" + '\n' +
							"��̴���� �� �����̿�� 5% ����" + '\n' + "CGV �� ��ȭ���ű� 10% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 5% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 5% ����" + '\n' +
							"�������ʽ� �� ���޴� 10%  ����" + '\n' + "�Ҹ��� �� ���޴� 5% ����" + '\n' + "��Ÿ���� �� ���޴� 10% ����" +
	    	 				'\n' + "��Ʈ����ũ�� �� 10% ����" + '\n' + "��VR �� ����, �ָ�  5% ����" + '\n' +
	    	 				"�ѾƸ����� �� 10% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 20% ����"; ; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "����ī�� ���", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////����ī�� �߰�
	            	win.ud.card.add_Card(3);
	               JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�!");
	            }else{
	               return;
	            }
	         }
	         else if(b.getText().equals("�츮ī��")){
	        	 String explanation = "�ֽ��� �� ������� 5% ����" + '\n' + "VIPS �� ������� 10% ����" + '\n' +
							"�ƿ��� �� ���޴� 5% ����" + '\n' + "Tokyo420  �� ���޴� 5%����" + '\n' + "����ī�� �� ���޴� 10%���� " +
							'\n' + "¤�Ÿſ���� �� ���޴� 5% ����" + '\n' + "�������ű� �� ���޴� 10% ����" + '\n' +
							"�Ե����� �� �����̿�� 15% ����" + '\n' + "�������� �� �����̿�� 25% ����" + '\n' +
							"��̴���� �� �����̿�� 10% ����" + '\n' + "CGV �� ��ȭ���ű� 5% ����" + '\n' +
							"�ް��ڽ� �� ��ȭ���ű� 10% ����" + '\n' + "�Ե��ó׸� �� ��ȭ���ű� 5% ����" + '\n' +
							"�������ʽ� �� ���޴� 5%  ����" + '\n' + "�Ҹ��� �� ���޴� 10% ����" + '\n' + "��Ÿ���� �� ���޴� 10% ����" +
	    	 						'\n' + "��Ʈ����ũ�� �� 20% ����" + '\n' + "��VR �� ����, �ָ�  10% ����" + '\n' +
	    	 						"�ѾƸ����� �� 10% ����" + '\n' + "������߱��� �� �Ϲ�, ��Ƽ�� 15% ����"; ; 
	        	 
	        	 ////
	            int result = JOptionPane.showConfirmDialog(null, explanation, "�츮ī�� ���", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
	            if(result == 0){
	               ///////�츮ī�� �߰�
	            	win.ud.card.add_Card(4);
	               JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�!");
	            }else{
	               return;
	            }
	         }
	      }
   }

}

class userData{
	   
	   
	public Card card = new Card();
   
   public boolean card1; //ī������. 
   public boolean card2;
   public boolean card3;
   public boolean card4;
   public boolean card5;
   
   
   public int temp_course; //ù��° ����, ���⼭�� ū ī�װ��� ����(ex) ���̰���, �Ĵ� ��.)
   public int temp_course2; //�ι�° ����, ���⼭�� ���� ī�װ��� ����. (ex) ����, ŽŽ ��.)
   public int choice_course[][];
   boolean check_course1;
   boolean check_course2;
   boolean check_course3;
   public String course_name[] = {"���̰���", "�Ĵ�", "ī��", "��ȭ��", "������"};
   userData(){
	   check_course1 = false;
	   check_course2 = false;
	   check_course3 = false;
	   choice_course = new int[3][3]; //1,2,3 �ڽ� ������� ū ī�װ� -> ���� ī�װ� -> ���� ������ ������ �� 
   }


}


// 1.2 ����Ʈ �ڽ� ¥�� �г�
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
      
      // ���(����)
      JLabel lblNewLabel = new JLabel("����Ʈ �ڽ� ¥��");
      lblNewLabel.setFont(new Font("", Font.BOLD, 30));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(lblNewLabel, BorderLayout.NORTH);
      
      // �ߴ�
      JPanel panelCourse = new JPanel();
      this.add(panelCourse, BorderLayout.CENTER);
      panelCourse.setLayout(new GridLayout(1, 3));
      
         // �ڽ�1
      JPanel panelCourse1 = new JPanel();
      panelCourse.add(panelCourse1);
      panelCourse1.setLayout(new BorderLayout(0, 0));
      
      JButton btnCourse1 = new JButton("�ڽ�1 ����");
      btnCourse1.setFont(new Font("", Font.BOLD, 20));
      panelCourse1.add(btnCourse1, BorderLayout.NORTH);
      
      lblCourse1 = new JLabel("�ڽ�1 ����");
      lblCourse1.setBackground(Color.YELLOW);
      lblCourse1.setHorizontalAlignment(SwingConstants.CENTER);   
      lblCourse1.setOpaque(true);
      lblCourse1.setFont(new Font("", Font.CENTER_BASELINE, 20));
      panelCourse1.add(lblCourse1, BorderLayout.CENTER);

         // �ڽ�2
      JPanel panelCourse2 = new JPanel();
      panelCourse.add(panelCourse2);
      panelCourse2.setLayout(new BorderLayout(0, 0));

      JButton btnCourse2 = new JButton("�ڽ�2 ����");
      btnCourse2.setFont(new Font("", Font.BOLD, 20));
      panelCourse2.add(btnCourse2, BorderLayout.NORTH);
      
      lblCourse2 = new JLabel("�ڽ�2 ����");
      lblCourse2.setBackground(Color.CYAN);
      lblCourse2.setHorizontalAlignment(SwingConstants.CENTER);   
      lblCourse2.setOpaque(true);
      lblCourse2.setFont(new Font("", Font.CENTER_BASELINE, 20));
      panelCourse2.add(lblCourse2, BorderLayout.CENTER);
      
         // �ڽ�3
      JPanel panelCourse3 = new JPanel();
      panelCourse.add(panelCourse3);
      panelCourse3.setLayout(new BorderLayout(0, 0));
      
      JButton btnCourse3 = new JButton("�ڽ�3 ����");
      btnCourse3.setFont(new Font("", Font.BOLD, 20));
      panelCourse3.add(btnCourse3, BorderLayout.NORTH);
      
      lblCourse3 = new JLabel("�ڽ�3 ����");
      lblCourse3.setBackground(Color.PINK);
      lblCourse3.setHorizontalAlignment(SwingConstants.CENTER);   
      lblCourse3.setOpaque(true);
      lblCourse3.setFont(new Font("", Font.CENTER_BASELINE, 20));
      panelCourse3.add(lblCourse3, BorderLayout.CENTER);
      
         // ��ư ������ ���
      btnCourse1.addActionListener(new CourseButtonListener());
      btnCourse2.addActionListener(new CourseButtonListener());
      btnCourse3.addActionListener(new CourseButtonListener());
      
      // �ϴ�
      JPanel panelTotal = new JPanel();
      this.add(panelTotal, BorderLayout.SOUTH);
 	  

         // �� ����
      JButton btnTotalPrice = new JButton("�� ���� ����");
      btnTotalPrice.setFont(new Font("", Font.BOLD, 30));
      panelTotal.add(btnTotalPrice);
      btnTotalPrice.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //// �� ����(���� ����, ���ε� ���� ǥ��, ����� ī�� ��) �ֱ�
        	 total_price = 0;
        	 for(int i = 0; i < 3; i++) {
        		 total_price += win.ud.choice_course[i][2];
        	 }
    		 JOptionPane.showMessageDialog(null, "�� ���� : " + total_price + " ��");            
         }
      });
      
         // ��
      JButton btnMap = new JButton("���� ����");
      btnMap.setFont(new Font("", Font.BOLD, 30));
      panelTotal.add(btnMap);
      btnMap.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //// �� �ֱ� (�ڽ� ��ҿ� ���� ��ġ ��� �ؾߵ�)
            GoogleMap google = new GoogleMap();
            google.setMap(win.map1 + "&" + win.map2 + "&" + win.map3);
            //System.out.println(win.map1 + win.map2 + win.map3);
         }
      });
      
         // ���ư��� ��ư
      JButton btnReturn = new JButton("���ư���");
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
         if(b.getText().equals("�ڽ�1 ����")){
        	 win.change("categoryPick");
        	 win.ud.check_course1 = true; //true�� ��ȯ�ؼ� ������ �־��� ��ȹ. 
         }
         else if(b.getText().equals("�ڽ�2 ����")){
        	 win.change("categoryPick");
        	 win.ud.check_course2 = true; //true�� ��ȯ�ؼ� ������ �־��� ��ȹ. 

         }else if(b.getText().equals("�ڽ�3 ����")){
        	 win.change("categoryPick");
        	 win.ud.check_course3 = true; //true�� ��ȯ�ؼ� ������ �־��� ��ȹ. 

         }
            // ī�װ� ���� �۾�
      }
   }

}

// 1.2.1 ī�װ� �����ϴ� �г�
class CategoryPick extends JPanel{
   public BestDate win;
   
   public CategoryPick(BestDate win){
      this.win = win;
      
      this.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setLayout(new BorderLayout(0, 0));
      
      // ��� (����)
      JLabel lblCategory = new JLabel("ī�װ� ����");
      lblCategory.setFont(new Font("", Font.BOLD, 30));
      lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(lblCategory, BorderLayout.NORTH);
      
      // �ߴ� (ī�װ� ��ư)
      JPanel panelCategory = new JPanel();
      this.add(panelCategory, BorderLayout.CENTER);
      panelCategory.setLayout(new GridLayout(3, 3));
      
      JButton button1 = new JButton("���̰���");
      button1.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button1);
      button1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            win.ud.temp_course = 1; //�Ͻ������� �����ؼ� �������� ���� �Ű���. 
            win.change("categoryPick2");
         }
         
      });
      
      JButton button2 = new JButton("�Ĵ�");
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
      
      JButton button3 = new JButton("ī��");
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
      
      JButton button4 = new JButton("��ȭ��");
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
      
      JButton button5 = new JButton("������");
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
      
      JButton button6 = new JButton("�̼���"); //6~9���� ������� ���� 
      button6.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button6);
      
      JButton button7 = new JButton("����");
      button7.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button7);
      
      JButton button8 = new JButton("������");
      button8.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button8);
      
      JButton button9 = new JButton("����̺�");
      button9.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button9);
      
      // �ϴ�
      JPanel panelReturn = new JPanel();
      this.add(panelReturn, BorderLayout.SOUTH);
      
         // ���ư��� ��ư
      JButton btnReturn = new JButton("���ư���");
      btnReturn.setFont(new Font("", Font.BOLD, 30));
      panelReturn.add(btnReturn);
      btnReturn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	if(win.ud.check_course1 == true) {
        		win.ud.check_course1 = false; //���ư� ��� �̷��� �ٽ� false�� �ʱ�ȭ ������ 
        	}
        	else if(win.ud.check_course2 == true) {
        		win.ud.check_course2 = false; //���ư� ��� �̷��� �ٽ� false�� �ʱ�ȭ ������ 
        	}
        	else if(win.ud.check_course3 == true) {
        		win.ud.check_course3 = false; //���ư� ��� �̷��� �ٽ� false�� �ʱ�ȭ ������ 
        	}
            win.change("dateCourse");
         }
      });
   }
   
}

// 1.2.2 ī�װ����� �ϳ� ������ �Ѿ��� �г�(���� ī�װ� ����) 
class CategoryPick2 extends JPanel{
   public BestDate win;
   public String[] str_arr;
   public int[] int_arr;
   
   public CategoryPick2(BestDate win){
      this.win = win;
      
      this.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setLayout(new BorderLayout(0, 0));
      int_arr = new int[6]; //�� ��ȣ�� ����Ǿ� ����. 1�϶�  1,2,3,4
      str_arr = new String[6];
      for(int i = 0; i < 6; i++){
    	  str_arr[i] = "X";  //���� �̸�. 
      }
      // ��� (����)
      JLabel lblCategory = new JLabel("���� ī�װ� ����");
      lblCategory.setFont(new Font("", Font.BOLD, 30));
      lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(lblCategory, BorderLayout.NORTH);
      
      // �ߴ� (ī�װ� ��ư)
      JPanel panelCategory = new JPanel();
      this.add(panelCategory, BorderLayout.CENTER);
      panelCategory.setLayout(new GridLayout(3, 3));
      int j = 0;
      switch(win.ud.temp_course) { 			//�ڽ��� ���� ���� ������ ��ư �ٸ��� ����. 
      case 1:
         //JOptionPane.showMessageDialog(null, win.pd.arr.length); �׽�Ʈ��
         for(int i = 0; i < win.pd.arr.length ; i++ ) {
            if(win.pd.arr[i][1].equals("1")) {
               int_arr[j] = i;
               j++;
               if(j == 3) //3�� �� �޾ƿ��� Ż�� �ϵ��� ��. 
                  break;
            }
         }
         for(int k = 0; k < int_arr.length; k++) {
        	 
            str_arr[k] = win.pd.arr[int_arr[k]][0];
            if(k > 2) {
            	str_arr[k] = "X"; //0���� �ʱ�ȭ �Ǿ� �ֱ� ������ ���� ��� x�� ����
            }
         }
         j = 0; //�ٽ� 0���� ���� 
            
         break;
      case 2:
         for(int i = 0; i < win.pd.arr.length ; i++ ) {
            if(win.pd.arr[i][1].equals("2")) {
               int_arr[j] = i;
               j++;
               if(j == 6) //6�� �� �޾ƿ��� Ż�� �ϵ��� ��. 
                  break;
            }
         }
         for(int k = 0; k < int_arr.length; k++) {
            str_arr[k] = win.pd.arr[int_arr[k]][0];
            
         }
         j = 0; //�ٽ� 0���� ���� 
         break;
      case 3:
         for(int i = 0; i < win.pd.arr.length ; i++ ) {
            if(win.pd.arr[i][1].equals("3")) {
               int_arr[j] = i;
               j++;
               if(j == 3) //3�� �� �޾ƿ��� Ż�� �ϵ��� ��. 
                  break;
            }
         }
         for(int k = 0; k < int_arr.length; k++) {
            str_arr[k] = win.pd.arr[int_arr[k]][0];
            if(k > 2) {
            	str_arr[k] = "X"; //0���� �ʱ�ȭ �Ǿ� �ֱ� ������ ���� ��� x�� ����
            }
            
         }
         j = 0; //�ٽ� 0���� ���� 
         break;
      case 4:
          for(int i = 0; i < win.pd.arr.length ; i++ ) {
             if(win.pd.arr[i][1].equals("4")) {
                int_arr[j] = i;
                j++;
                if(j == 3) //3�� �� �޾ƿ��� Ż�� �ϵ��� ��. 
                   break;
             }
          }
          for(int k = 0; k < int_arr.length; k++) {
             str_arr[k] = win.pd.arr[int_arr[k]][0];
             if(k > 2) {
             	str_arr[k] = "X"; //0���� �ʱ�ȭ �Ǿ� �ֱ� ������ ���� ��� x�� ����
             }
             
          }
          j = 0; //�ٽ� 0���� ���� 
          break;
      case 5:
          for(int i = 0; i < win.pd.arr.length ; i++ ) {
             if(win.pd.arr[i][1].equals("5")) {
                int_arr[j] = i;
                j++;
                if(j == 4) //4�� �� �޾ƿ��� Ż�� �ϵ��� ��. 
                   break;
             }
          }
          for(int k = 0; k < int_arr.length; k++) {
             str_arr[k] = win.pd.arr[int_arr[k]][0];
             if(k > 3) {
             	str_arr[k] = "X"; //0���� �ʱ�ȭ �Ǿ� �ֱ� ������ ���� ��� x�� ����
             }
             
          }
          j = 0; //�ٽ� 0���� ���� 
          break;
      }
      JButton button1 = new JButton(str_arr[0]);
      button1.setFont(new Font("", Font.BOLD, 25));
      panelCategory.add(button1);
      button1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            //��ư ������ �ش� ī���ڸ��� �ߵ��� ����. 
            //�ش� ������ ud�� �����ؼ� ����� ��ȹ. 
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
      
      JButton button6 = new JButton(str_arr[5]); //�ִ� ������ ���� ���
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
      
      // �ϴ�
      JPanel panelReturn = new JPanel();
      this.add(panelReturn, BorderLayout.SOUTH);
      
         // ���ư��� ��ư
      JButton btnReturn = new JButton("���ư���");
      btnReturn.setFont(new Font("", Font.BOLD, 30));
      panelReturn.add(btnReturn);
      btnReturn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            win.change("categoryPick"); //���� ȭ������ ���ư����� ���� 
         }
      });
   }
   
}

// ��� ���� Ŭ����
class placeData{
   public BestDate win;
   public String arr[][];
   public placeData(){
   arr = new String[100][10]; //����Ʈ �� �� �ִ� ��ҿ� ���� ������ ���� �� 
   for(int i = 0; i< 100; i++) {
	   for (int j = 0; j < 10; j++) {
		   arr[i][j] = "0";
	   }
   }
    try { 
        File myFile = new File("�Ĵ���.txt"); //ó���� ��� �̸�, �״����� ������ �ǹ�. �� ���� ���� �޴�-���� �� 
        FileReader fileReader = new FileReader(myFile); 
        BufferedReader reader = new BufferedReader(fileReader); 
        String line = null; // line ���ٰ� �� �پ� �о� �� 
     int i=0; 
     while ((line = reader.readLine()) != null) { 
           String[] splits = line.split("/"); // ���� ���� '/' �������� ���ø�(����)����. 
       for(int j=0;j<splits.length;j++) 
       { 
          //j���� �ι�° �ε����� ������ ����Ŵ 1 -> ���̰��� 2 -> �Ĵ� 3 -> ��ȭ�� ���. 
         arr[i][j]=splits[j]; 
      } 
      i++; 
      } 
      reader.close(); // ���۸��� �ݱ�.          
     } catch (Exception ex) { 
      ex.printStackTrace(); 
     } 
   }
}

// 1.2.3 �޴� �����ϴ� �г�
class MenuPick extends JPanel{
	public BestDate win;
	public String menu_arr[];
	public int totalprice = 0;		// �� �ڽ������� �� ����
	public JLabel lblPrice = new JLabel("�� ���� : " + totalprice + " ��");		// �� �ڽ������� �� ���� ���
	public int card_check[];
	public String card_name[] = {"����", "����", "�Ｚ", "����", "�츮"};
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
		max_discount_name = "ī�����";
		max_discount = 0;
		for(int i = 0; i < menu_arr.length; i++) {
			menu_arr[i] = "X"; // X�� �ʱ�ȭ
		}
		// ��� (�̸�)
		JLabel lblName = new JLabel(win.categoryPick2.str_arr[win.ud.temp_course2-1]); //// ������ ��ҷ� �̸� �����ؾ���
		lblName.setFont(new Font("", Font.BOLD, 30));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblName, BorderLayout.NORTH);
		
		// �ߴ�
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1));		// 4������ �� �� �ֵ��� ������
		
		int j = 0;
	      switch(win.ud.temp_course2) { //�ڽ��� ���� ���� ������ ��ư �ٸ��� ����. 
	      case 1:
	         //JOptionPane.showMessageDialog(null, win.pd.arr.length); �׽�Ʈ��
	    	 if(win.ud.temp_course == 1) {
	         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
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
	    	 j = 0; //�ٽ� ������ �ʱ�ȭ ������
	    	 break;
	      case 2:
	      		if(win.ud.temp_course == 1) {
	   	         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   	        	menu_arr[j] = win.pd.arr[1][i];
	   	        	j++;
	   	         	}
	   	         }
	   	    	 else if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   			        	menu_arr[j] = win.pd.arr[4][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	 else if(win.ud.temp_course == 3) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   			        	menu_arr[j] = win.pd.arr[10][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	else if(win.ud.temp_course == 4) {
			         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
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
	   	    	 j = 0; //�ٽ� ������ �ʱ�ȭ ������
	   	    	 break;
	      	case 3:
	   	    	if(win.ud.temp_course == 1) {
	   	         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   	        	menu_arr[j] = win.pd.arr[2][i];
	   	        	j++;
	   	         	}
	   	         }
	   	    	 else if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   			        	menu_arr[j] = win.pd.arr[5][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	 else if(win.ud.temp_course == 3) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   			        	menu_arr[j] = win.pd.arr[11][i];
	   			        	j++;
	   			     }
	   			 }
	   	    	else if(win.ud.temp_course == 4) {
			         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
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
	   	    	j = 0; //�ٽ� ������ �ʱ�ȭ ������
	   	    	break;
	      	case 4:
	      		/*if(win.ud.temp_course == 1) {
		   	         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
		   	        	menu_arr[j] = win.pd.arr[3][i];
		   	        	j++;
		   	         	}
		   	         }*/
		   	   	if(win.ud.temp_course == 2) {
		   		         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
		   			        menu_arr[j] = win.pd.arr[6][i];
		   			        	j++;
		   			     }
		   			 }
		   	   /*else if(win.ud.temp_course == 3) {
		   		    for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
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
	   	    	 j = 0; //�ٽ� ������ �ʱ�ȭ ������
	   	    	 break;
	      	case 5:
	      		if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   			        menu_arr[j] = win.pd.arr[7][i];
	   			        	j++;
	   		      }
	   			}
	      		 j = 0; //�ٽ� ������ �ʱ�ȭ ������
	   	    	 break;
	      	case 6:
	      		if(win.ud.temp_course == 2) {
	   		         for(int i = 2; i < 10 ; i++ ) { //2�� �ε������� �����������.
	   			        menu_arr[j] = win.pd.arr[8][i];
	   			        	j++;
	   		      }
	   			}
	      		 j = 0; //�ٽ� ������ �ʱ�ȭ ������
	   	    	 break;
	      		
	         
	      }
		//ī�� ������ �޾ƿ���
	      for(int i = 0; i < 5; i ++) {
	          boolean check_card;
	         check_card = win.ud.card.getIs_CardAdded(i);
	         if(check_card) { //ī�尡 ���� ��� �ε��� ä���� ��
	            card_check[i] = win.ud.card.get_DiscountRate(card_name[i],win.categoryPick2.str_arr[win.ud.temp_course2-1] ); //���� ��� 1�� ä�� �� ��. ���� ���� �Ｚ ���� �츮 ��.

	            if(i == 0) {
	                max_discount = card_check[i];
	                max_discount_name = card_name[i];
	             }
	             else if(i!= 0) {
	                if(card_check[i] > max_discount) {
	                   max_discount = card_check[i]; //�ְ� ������
	                   max_discount_name = card_name[i]; //ī�带 ��������.
	                }
	             }
	         }
	         else
	            card_check[i] = 0 ; //ī�尡 ���� ��� �������� �־��ְ�, ���� ��� 0�� �־��� 

	       }
	   
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		panel.add(p1);
		JCheckBox cbox1 = new JCheckBox(menu_arr[0]);		//�̸� ���� �� 
		cbox1.setFont(new Font("", Font.BOLD, 30));
		cbox1.setName("cb1");								// � üũ�ڽ��� �����ߴ��� �����ϱ� ���� ����
		cbox1.addItemListener(new CheckBoxListener());
		p1.add(cbox1, BorderLayout.WEST);
		JButton b1 = new JButton(">");
		b1.setFont(new Font("", Font.BOLD, 30));
		b1.setName("b1");									// � ��ư�� �����ߴ��� �����ϱ� ���� ����
		p1.add(b1, BorderLayout.EAST);
		b1.addActionListener(new MenuInfoListener());
		JLabel j1 = new JLabel("�� ���� : " + menu_arr[1] + "��");
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
		JLabel j2 = new JLabel("�� ���� : " + menu_arr[3] + "��");
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
		JLabel j3 = new JLabel("�� ���� : " + menu_arr[5] + "��");
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
		JLabel j4 = new JLabel("�� ���� : " + menu_arr[7] + "��");
		j4.setFont(new Font("", Font.BOLD, 30));
		j4.setHorizontalAlignment(SwingConstants.RIGHT);
		p4.add(j4, BorderLayout.CENTER);
		
		// �ϴ�
		JPanel panelBottom = new JPanel();
		this.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
			// �� ����
		lblPrice.setFont(new Font("", Font.BOLD, 30));
		lblPrice.setForeground(Color.RED);
		panelBottom.add(lblPrice, BorderLayout.WEST);
		
			// ��ư
		JPanel btnArea = new JPanel();
		btnArea.setLayout(new GridLayout(1, 2));
		panelBottom.add(btnArea, BorderLayout.EAST);		
		
				// ���� ��ư
		JButton btnSave = new JButton("����");
		btnSave.setFont(new Font("", Font.BOLD, 30));
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//// ���� �����ؾ��� + ����� ������ dateCourseȭ�鿡 �����ؾ���
				if(win.ud.check_course1 == true) {
					win.ud.choice_course[0][0] = win.ud.temp_course;
					win.ud.choice_course[0][1] = win.ud.temp_course2;
					win.ud.choice_course[0][2] = totalprice;
					win.map1 = win.categoryPick2.str_arr[win.ud.temp_course2-1];
					win.dateCourse.lblCourse1.setText("<html>" + win.categoryPick2.str_arr[win.ud.temp_course2-1]+"<br><br>" + max_discount_name + "ī�� ��� �� <br>" + "�� ���� : " + totalprice+"��<br></html>"); //������ ��ҿ� ���� ������ �޾ƿ� ���� ���� ���������.
					win.ud.check_course1 = false; // �ٽ� false�� ��ȯ������. 
				}
				else if(win.ud.check_course2 == true) {
					win.ud.choice_course[1][0] = win.ud.temp_course;
					win.ud.choice_course[1][1] = win.ud.temp_course2;
					win.ud.choice_course[1][2] = totalprice;
					win.map2 = win.categoryPick2.str_arr[win.ud.temp_course2-1];
					win.dateCourse.lblCourse2.setText("<html>" + win.categoryPick2.str_arr[win.ud.temp_course2-1]+"<br><br>" + max_discount_name + "ī�� ��� �� <br>" + "�� ���� : " + totalprice+"��<br></html>"); //������ ��ҿ� ���� ������ �޾ƿ� ���� ���� ���������.
					win.ud.check_course2 = false; // �ٽ� false�� ��ȯ������. 
				}
				else if(win.ud.check_course3 == true) {
					win.ud.choice_course[2][0] = win.ud.temp_course;
					win.ud.choice_course[2][1] = win.ud.temp_course2;
					win.ud.choice_course[2][2] = totalprice;
					win.map3 = win.categoryPick2.str_arr[win.ud.temp_course2-1];
					win.dateCourse.lblCourse3.setText("<html>" + win.categoryPick2.str_arr[win.ud.temp_course2-1]+"<br><br>" + max_discount_name + "ī�� ��� �� <br>" + "�� ���� : " + totalprice+"��<br></html>"); //������ ��ҿ� ���� ������ �޾ƿ� ���� ���� ���������.
					win.ud.check_course3 = false; // �ٽ� false�� ��ȯ������. 
				}
				JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�!");
				win.change("dateCourse");
			}
		});
				// ���ư��� ��ư(���� ����)
		JButton btnReturn = new JButton("���ư���");
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
	
	// ���� ��ư ������
	class MenuInfoListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			//// ��ǰ ������ ī�� ���� �޾ƿ;� �Ѵ�
			
			if(b.getName() == "b1"){
				String message;
				message = "<������������>-" + max_discount_name + " " + max_discount +"%����\n�� ���� :" + before_price[0] +"��\n���ε� ���� :" + discount_price[0] +"��";				//// ������ ���� ����ϸ� ��
				JOptionPane.showMessageDialog(null, message);
			}else if(b.getName() == "b2"){ 
				String message;
				message = "<������������>-" + max_discount_name + " " + max_discount +"%����\n�� ���� :" + before_price[1] +"��\n���ε� ���� :" + discount_price[1] +"��";				//// ������ ���� ����ϸ� ��
				JOptionPane.showMessageDialog(null, message);
			}else if(b.getName() == "b3"){
				String message;
				message = "<������������>-" + max_discount_name + " " + max_discount +"%����\n�� ���� :" + before_price[2] +"��\n���ε� ���� :" + discount_price[2] +"��";				//// ������ ���� ����ϸ� ��
				JOptionPane.showMessageDialog(null, message);
			}else if(b.getName() == "b4"){
				String message;
				message = "<������������>-" + max_discount_name + " " + max_discount +"%����\n�� ���� :" + before_price[3] +"��\n���ε� ���� :" + discount_price[3] +"��";				//// ������ ���� ����ϸ� ��
				JOptionPane.showMessageDialog(null, message);
			}
		}
	}
	
	// üũ�ڽ� ���� ������
	class CheckBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cb = (JCheckBox)e.getSource();
			//// totalprice ���� �޾ƿ;� �Ѵ�
			
			if(cb.getName() == "cb1"){
				int intValue = Integer.parseInt(menu_arr[1]); //string�� int�� �� ��ȯ
				before_price[0] = intValue;
				discount_price[0] = intValue*(100 - max_discount) / 100;
				if(e.getStateChange() == 1){		// üũ ǥ�õ� ��
					totalprice += intValue*(100 - max_discount) / 100;								//// +���� ���� �Է��ϸ� ��
				}else{								// üũ ǥ�� ������ ��
					totalprice -= intValue*(100 - max_discount) / 100;								//// -���� ���� �Է��ϸ� ��
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
			lblPrice.setText("�� ���� : " + totalprice + " ��");	// ���� ����
		}
		
	}
}
