package weather;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class UI extends JFrame implements ActionListener{
	
	public UI() throws IOException {
		scrap sc = new scrap();
		HashMap<String, String> hm = new HashMap<String, String>(); //해시맵 선
		hm=sc.scraping();//scrap 클래스에서 해쉬맵 형태 받아오기
		Set set = hm.keySet(); //지역 검색을 위한 key정보만 따로 정의
			
		setTitle("날씨 정보");
		setSize(640,480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(6,4,5,5)); //그리드 생성

		
		
		int count=0;
		JButton[] button = new JButton[hm.size()]; // hashmap의 사이즈 크기만큼 JButton 객체 배열 생성
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){   //이터레이터를 돌면서 순서대로 버튼 정
			  String key = (String)iterator.next();
			  button[count] = new JButton(key);
			  button[count].addActionListener(this);   //리스너에 호출
			  contentPane.add(button[count]);
			  count+=1;
		}
		setVisible(true);

	}

	@Override
	   public void actionPerformed(ActionEvent e) { 
		scrap scr = new scrap(); //해시맵 선언 및 초기화
		HashMap<String, String> hmap = new HashMap<String, String>();
		try {
			hmap=scr.scraping();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//scrap 클래스에서 해쉬맵 형태 받아오기
		
		setTitle(e.getActionCommand()+"의 날씨"); // 클릭한 버튼의 key값이 setTitle 됨
        JPanel NewWindowContainer = new JPanel(); //새로 띄울 창을 위해 새로운 패널 선언
        setContentPane(NewWindowContainer);
        String pop = hmap.get(e.getActionCommand()); //클릭한 버튼의 key값이 pop에 저장
        
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월 dd일"); 
        String format_time = format.format (System.currentTimeMillis());     //현재 날씨를 불러오는 패키지 사용
        
        String rain=pop.split("/")[1].replace("%", ""); // 비올확률을 구하기 위해 %제거
        int per = Integer.parseInt(rain);
        String caution;
        if(per>=80) {//비올 확률별로 caution 스트링값 조건문에 따라 선언
        	caution="비올 확률이 80% 이상이니 우산 챙기세요";
        }
        else if(per>=50) {
        	caution="비올 확률이 50% 이상이니 주의 하세요";
        }
        else {
        	caution="비올 확률이 낮습니다.";
        }
        
        JLabel NewLabel1 = new JLabel(format_time+"  "+"오전:"+pop.split("/")[0]+"\n 오후:"+pop.split("/")[2]+
        		" 비 올 확률: "+pop.split("/")[1]+"      "+caution); //들어갈 텍스트 삽입
        NewWindowContainer.add(NewLabel1);       
        setSize(600,100);
        setResizable(true);
        setVisible(true);
       
        }
    }		
		
	


