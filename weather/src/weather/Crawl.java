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

public class Crawl {
	public static void main(String[] args) throws IOException {
		new UI();
	}
}
