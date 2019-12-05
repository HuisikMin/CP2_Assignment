package weather;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import javax.swing.*;

public class scrap  {
	public HashMap scraping() throws IOException {
		HashMap <String, String> map = new HashMap<String, String>(); //해시맵 선언
		
		String URL = "https://weather.naver.com/rgn/cityWetrMain.nhn"; //크롤링 할 주소 삽입
		Document doc =Jsoup.connect(URL).get();
		for(int j=1; j<=12;j++) {
	    	String fac = ".tbl_weather tbody>tr:nth-child("+(j)+")"; //각 지역 정보별로 얻어오기
	    	Elements elem = doc.select(fac);  
	    	String[] str = elem.text().split(" ");
	    	String weather="";
	    	String location="";
	    	
	    	if(j!=12) location=str[0]+str[1]; //나머지 지역
	    	else location=str[0];       //제주 예외처리
	    	
	        for(int i=0; i<str.length;i++) {
	        	if(str[i].contains("℃")) weather += str[i]+"/";
	        	if(str[i].contains("%")) weather += str[i]+"/";

	        }
	        map.put(location, weather); //지역과 날씨정보 입력
	        //System.out.println(location);
	        //System.out.println(weather);
	
	    }
		return map;  //UI클래스에 맵 형태로 반환
	}
}

	


