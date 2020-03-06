package com.ssm.springboot.webServiceAuto;

import java.util.Scanner;

/**
 * @author zhaohf
 * @date 2020/3/6 16:25
 */
public class QQOnlineTest {
    public static void main(String[] args) {
        //也可以使用new WeatherWebService(url)此方法可重新设置请求的地址 URL url=new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl")
        cn.com.webxml.QqOnlineWebService factory = new cn.com.webxml.QqOnlineWebService();
        cn.com.webxml.QqOnlineWebServiceSoap qqOnlineWebServiceSoap = factory.getQqOnlineWebServiceSoap(); //WeatherWebServiceSoap为调用的实现类
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.print("请输入QQ号：");
            String scstr=sc.next();
            String str=qqOnlineWebServiceSoap.qqCheckOnline(scstr);
            if ("Y".equals(str)){
                System.out.println(scstr+"==========在线");
            }else if("N".equals(str)){
                System.out.println(scstr+"==========离线");
            }else {
                System.out.println(scstr+"==========不存在");
            }
        }
    }
}
