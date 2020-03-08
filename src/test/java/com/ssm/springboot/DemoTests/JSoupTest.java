package com.ssm.springboot.DemoTests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 去除String中的HTML代码
 * @author zhaohf
 * @date 2020/3/8 21:12
 */
public class JSoupTest {
    public static void main(String[] args) {
        String html = "<html><head><title>Website title</title></head><body><p>Sample paragraph number 1 </p><p>Sample paragraph number 2</p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.title());
        Elements paragraphs = doc.getElementsByTag("p");
        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }
    }
}
