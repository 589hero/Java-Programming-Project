package teamprj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

public class GoogleMap extends JFrame{
	private JPanel panel = new JPanel();
	
	private GoogleAPI googleAPI = new GoogleAPI();
	private JLabel googleMap = new JLabel();

	
	public GoogleMap() {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screenSize = tool.getScreenSize();
		int width = screenSize.width;  // ������� ���α��� ����.
		int height = screenSize.height; // ������� ���α��� ����.
		setLocation(width/3, height/4);
		
		//setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Google Maps");
		setVisible(true);
		
		pack();
		//setMap("cgv");
	}
	
	public void setMap(String location) { // frame�� �Ʒ��ʿ� ������ ��Ÿ���� �޼ҵ�
		googleAPI.downloadMap(location);
		googleMap.setIcon(googleAPI.getMap(location));
		googleAPI.fileDelete(location);
		add(BorderLayout.SOUTH, googleMap);
		pack();
	}
	
	public class SearchMap extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e) {
			////
//			setMap(textField.getText());
		}
		
	}
}

	class GoogleAPI {
	public void downloadMap(String location) {
		try {
			StringTokenizer st = new StringTokenizer(location, "&");
			String imageURL = null;
			////
			if(st.countTokens() == 0) { // �ƹ��͵� �Է¾��ϸ� �Ǳ����б� �Է��ϵ��� ��.
				imageURL = "https://maps.googleapis.com/maps/api/staticmap?size=512x512&scale=2&"
						+ "maptype=roadmap\\&markers=size:mid|color:red"
						+ "%7C" + URLEncoder.encode("������", "UTF-8")
					    + "&key=AIzaSyBRgljRApZ960iJqcM5IInfA0fZJaYCVKo";
			}
			
			if(st.countTokens() == 1) { 
				imageURL = "https://maps.googleapis.com/maps/api/staticmap?size=512x512&scale=2&"
						+ "maptype=roadmap\\&markers=size:mid|color:red"
						+ "%7C" + URLEncoder.encode(st.nextToken() + "�Ǵ�", "UTF-8")
					    + "&key=AIzaSyBRgljRApZ960iJqcM5IInfA0fZJaYCVKo";
			}
			
			else if(st.countTokens() == 2) {
				imageURL = "https://maps.googleapis.com/maps/api/staticmap?size=512x512&scale=2&"
						+ "maptype=roadmap\\&markers=size:mid|color:red"
						+ "%7C" + URLEncoder.encode(st.nextToken() + "�Ǵ�", "UTF-8")
					    + "%7C" + URLEncoder.encode(st.nextToken() + "�Ǵ�", "UTF-8")
					    + "&key=AIzaSyBRgljRApZ960iJqcM5IInfA0fZJaYCVKo";
			}
			
			else if(st.countTokens() == 3) {
				imageURL = "https://maps.googleapis.com/maps/api/staticmap?size=512x512&scale=2&"
						+ "maptype=roadmap\\&markers=size:mid|color:red"
						+ "%7C" + URLEncoder.encode(st.nextToken() + "�Ǵ�", "UTF-8")
					    + "%7C" + URLEncoder.encode(st.nextToken() + "�Ǵ�", "UTF-8")
					    + "%7C" + URLEncoder.encode(st.nextToken() + "�Ǵ�", "UTF-8")
					    + "&key=AIzaSyBRgljRApZ960iJqcM5IInfA0fZJaYCVKo";
			}
			////
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location);
			byte[] b = new byte[2048];
			int length;
			while((length = is.read(b)) != -1) { // EOF�� �ɶ����� ����.
				os.write(b,  0,  length);
			}
			is.close();
			os.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getMap(String location) {
		return new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(612, 612, java.awt.Image.SCALE_SMOOTH));
	}
	
	public void fileDelete(String fileName) {
		File f = new File(fileName);
		f.delete();
	}
}

