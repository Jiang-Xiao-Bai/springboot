package com.ssm.springboot.DemoTests;


import com.ssm.springboot.utils.StringUtils;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class SpringbootApplicationTests {

    public static void sanMu(){//三目运算
        Integer a=1;
        Integer b=1;
        String str= a>b ? "a大于b":(a<b ? "a小于b":"a等于b");
        System.out.println(str);
    }
    public static void caiShuZi(){//猜数字
        Random r=new Random();
        int random=r.nextInt(100)+1;
        Scanner sc=new Scanner(System.in);
        int i=0;
            while (true) {
                System.out.println("亲输入您所猜的数字：");
                int num = sc.nextInt();
                i++;
                if (random == num) {
                    System.out.println("恭喜你猜对了！共猜了" + i + "次");
                    break;
                }
                if (random < num) {
                    System.out.println("您猜大了！请继续");
                }
                if (random > num) {
                    System.out.println("您猜小了！请继续");
                }
            }
    }
    public static void maoPao(){//冒泡
        Integer[] num={15,20,36,84,1,45};
        for (int i=0 ; i<num.length-1 ;i++){
            for (int j=0;j<num.length-i-1;j++){
                if(num[j]<num[j+1]){
                    int box=num[j];
                    num[j]=num[j+1];
                    num[j+1]=box;
                }
            }
        }
        for (Integer integer : num) {
            System.out.println(integer);
        }
    }
    public static int getSum(int... num) {//自定义参数个数传值
        int sum = 0;
        for (int ints : num) {
            sum += ints;
        }
        return sum;
    }
    public static void copy(){//文件复制
       long startTime=System.currentTimeMillis();
       BufferedInputStream bir=null;
       BufferedOutputStream bow=null;
       try {
           File fi=new File("C:\\Users\\F7090\\Desktop\\in.txt");
           File fo=new File("C:\\Users\\F7090\\Desktop\\文件夹\\out.txt");
           bir=new BufferedInputStream(new FileInputStream(fi));
           bow=new BufferedOutputStream(new FileOutputStream(fo));
           int len;
           byte[] chars=new byte[1024];
           while ((len=bir.read(chars))!=-1){
               bow.write(chars,0,len);
           }
           long endTime=System.currentTimeMillis();
           System.out.println("共用了"+(endTime-startTime)+"毫秒");
           bir.close();
       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           try {
               if (bir!=null){
                   bir.close();
               }if(bow!=null){
                   bow.close();
               }
           } catch (IOException e) {
               e.getMessage();
               e.printStackTrace();
           }
       }
   }
    public static void textPaiXu() {//文件文本排序
        Map<String,String> lineMap=new HashMap<>();
        BufferedReader br=null;
        BufferedWriter bw=null;
        try {
            br=new BufferedReader(new FileReader("a.txt"));
            bw=new BufferedWriter(new FileWriter("b.txt"));
            String len;
            while ((len=br.readLine())!=null){
                String[] spit=len.split("\\.");
                lineMap.put(spit[0],spit[1]);
                System.out.println(spit[0]+" "+spit[1]);
            }
            br.close();
            for (int i=1;i<=lineMap.size();i++){
                String key=String.valueOf(i);
                String value=lineMap.get(key);
                bw.write(key+"."+value);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void dayYear() {//计算天数及岁数
        while (true){
            System.out.println("请输入生日：");
            String birthday=new Scanner(System.in).next();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
            if (birthday.equals("end")){
                break;
            }
            Date starTime = null;
            try {
                starTime = simpleDateFormat.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date=new Date();
            long day=(date.getTime()-starTime.getTime())/86400000L;
            System.out.println(day+"天");
            System.out.println((day/365)+"岁");
        }
    }
    public static void arrayCopy(){//将一个数组中的值复制到另一个数组
        Integer[] src={1,2,3,5,4};
        Integer[] dest={6,5,4,3,1};
        System.arraycopy(src,0,dest,2,3);
        System.out.println(Arrays.asList(src));
        System.out.println("======================");
        System.out.println(Arrays.asList(dest));
    }
    public static void random(){//生成四个随机数且不重复
        Random random=new Random();
        Set<Integer> setInteger=new HashSet<>();
        while (setInteger.size()!=4){
            int num=random.nextInt(10)+1;
            setInteger.add(num);
        }
        System.out.println(setInteger);
    }
    public static void secendMax(){//一万个数中第二大的数
        List<Integer> list=new ArrayList<>(10000);
        for (int i=0;i<10000;i++){
            int num=(int)(Math.random()*(10001));
            list.add(num);
        }
        list.sort(Integer::compareTo);
        System.out.println(list.indexOf(9999));

    }
    public static void calender(){//日历类
        Calendar ca=Calendar.getInstance();
        String sd=new SimpleDateFormat("YYYY-MM-dd").format(ca.getTime());
        System.out.println(sd);
        DateTimeFormatter[] formatters=new DateTimeFormatter[]{
        DateTimeFormatter.ISO_DATE,
        DateTimeFormatter.ISO_TIME,
        DateTimeFormatter.ISO_DATE_TIME,
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,FormatStyle.MEDIUM),
        DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG),
        DateTimeFormatter.ofPattern("Gyyyy-MM-dd HH:mm:ss")
        };
        LocalDateTime date=LocalDateTime.now();
        for (int i=0;i<formatters.length;i++){
        System.out.println(date.format(formatters[i]));
            System.out.println(formatters[i].format(date));
        }
    }
    public static void demo(){
        Random random=new Random();
        List<Integer> list=new ArrayList<>();
        LinkedHashSet<Integer> set=new LinkedHashSet<>();
        for (int i=0;i<10;i++){
            Integer num=random.nextInt(10)+1;
            set.add(num);
        }

    }
    public static void stringUtils(){
        String str="办公时间：周一至周四(09:00-12:00,13:00-17:00)周五(09:00-12:00,13:00-15:00)介绍：广州南沙政务服务中心挂牌成立于2007年12月27日，是南沙管委会、区委、区政府设立的集中政务服务，整合各部门资源的政务服务综合平台。以“便民、高效、廉洁、规范”为宗旨，对外提供“一站式”办公。推行“一条龙”政务服务。大楼办公面积11700平方米，共五层，已进驻了所有具有行政审批业务的职能部门以及海关、检验检疫等垂直单位共36个，业务受理窗口68个。共设一个主中心（区政务服务中心，也是自贸区综合服务大厅），9个分中心（9个镇（街）政务服务中心）。中心还设立监察室及电子监控室，对行政审批全流程进行监控，受理及处理群众关于行政审批效能方面投诉。我区现设一个主中心，10个分中心（含9个镇街政务中心及区人力资源和社会保障局分中心），29个便民服务站。中心设立监察室及电子监控室";
        System.out.println(StringUtils.substringBefore(str,"介"));
    }

    public static void main(String[] args) {
        stringUtils();
    }
}
