package com.ssm.springboot.DemoTests;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zhaohf
 * @date 2020/3/17 14:57
 */
public class HutoolUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(HutoolUtilsTest.class);

    /**
     * Convert使用：类型转换工具类
     */
    public static void covert(){
        //转换为字符串
        int a = 1;
        String aStr = Convert.toStr(a);
        logger.info("转换为字符串{}",aStr);
        //转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);
        logger.info("转换为指定类型数组{}",Arrays.toString(bArr));
        //转换为日期对象
        String dateStr = "2017-05-06";
        Date date = Convert.toDate(dateStr);
        logger.info("转换为日期对象{}",date);
        //转换为列表
        String[] strArr = {"a", "b", "c", "d"};
        logger.info("转换为列表{}",strArr.toString());//没有重写toString方法，只是对toString方法重载（数组中可以包含基本类型）
        List<String> strList = Convert.toList(String.class, strArr);
        logger.info("转换为列表{}",strList.toString());//重写了toString方法（集合中不能存放基本数据类型）
    }

    /**
     * DateUtil使用：日期时间工具
     */
    public static void dateUtil() {
        //Date、long、Calendar之间的相互转换
        //当前时间
        Date date = DateUtil.date();
        //Calendar(日历类型)转Date
        date = DateUtil.date(Calendar.getInstance());
        logger.info("Calendar(日历类型)转Date",date);
        //时间戳转Date
        date = DateUtil.date(System.currentTimeMillis());
        logger.info("时间戳转Date",date);
        //自动识别(String)格式转换
        String dateStr = "2017-03-01";
        date = DateUtil.parse(dateStr);
        logger.info("自动识别(String)格式转换",date);
        //自定义格式化转换
        date = DateUtil.parse(dateStr, "yyyy-MM-dd");
        logger.info("自定义格式化转换",date);
        //格式化输出日期
        String format = DateUtil.format(date, "yyyy-MM-dd");
        logger.info("格式化输出日期",format);
        //获得年的部分
        int year = DateUtil.year(date);
        logger.info("获得年的部分",year);
        //获得月份，从0开始计数
        int month = DateUtil.month(date);
        logger.info("获得月份，从0开始计数",month);
        //获取某天的开始、结束时间
        Date beginOfDay = DateUtil.beginOfDay(date);
        Date endOfDay = DateUtil.endOfDay(date);
        logger.info("获取某天的开始、结束时间",beginOfDay+"---"+endOfDay);
        //计算偏移后的日期时间
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        logger.info("计算偏移后的日期时间",newDate);
        //计算日期时间之间的偏移量
        long betweenDay = DateUtil.between(date, newDate, DateUnit.DAY);
        logger.info("计算日期时间之间的偏移量",betweenDay);
    }

    /**
     * StrUtil使用：字符串工具
     */
    public static void strUtil() {
        //判断是否为空字符串
        String str = "test";
        StrUtil.isEmpty(str);
        StrUtil.isNotEmpty(str);
        //去除字符串的前后缀
        StrUtil.removeSuffix("a.jpg", ".jpg");
        StrUtil.removePrefix("a.jpg", "a.");
        //格式化字符串
        String template = "这只是个占位符:{}";
        String str2 = StrUtil.format(template, "我是占位符");
        logger.info("strUtil format:{}", str2);
    }

    /**
     * ClassPathResource使用：在classPath下查找文件，
     * 在Tomcat等容器下，classPath一般是WEB-INF/classes
     */
    public static void classPath() throws IOException {
        //获取定义在src/main/resources文件夹中的配置文件
        ClassPathResource resource = new ClassPathResource("application.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        logger.info("classPath:{}", properties);
    }

    /**
     * ReflectUtil使用：Java反射工具类
     * @return
     */
    public static void reflectUtil() {
        //获取某个类的所有方法
        Method[] methods = ReflectUtil.getMethods(PmsBrand.class);
        //获取某个类的指定方法
        Method method = ReflectUtil.getMethod(PmsBrand.class, "getId");
        //使用反射来创建对象
        PmsBrand pmsBrand = ReflectUtil.newInstance(PmsBrand.class);
        //反射执行对象的方法
        ReflectUtil.invoke(pmsBrand, "setId", 1);
    }

    /**
     * NumberUtil使用：数字处理工具类
     */
    public static void numberUtil() {
        double n1 = 1.234;
        double n2 = 1.234;
        double result;
        //对float、double、BigDecimal做加减乘除操作
        result = NumberUtil.add(n1, n2);
        logger.info("两位小数运算:{}", result);
        result = NumberUtil.sub(n1, n2);
        logger.info("两位小数运算:{}", result);
        result = NumberUtil.mul(n1, n2);
        logger.info("两位小数运算:{}", result);
        result = NumberUtil.div(n1, n2);
        logger.info("两位小数运算:{}", result);
        //保留两位小数
        BigDecimal roundNum = NumberUtil.round(n1, 2);
        logger.info("保留两位小数:{}", roundNum);
        String n3 = "1.234";
        //判断是否为数字、整数、浮点数
        NumberUtil.isNumber(n3);
        NumberUtil.isInteger(n3);
        NumberUtil.isDouble(n3);
    }

    /**
     * BeanUtil使用：JavaBean的工具类
     */
    public static void beanUtil() {
        PmsBrand brand = new PmsBrand();
        brand.setId(1L);
        brand.setName("小米");
        brand.setShowStatus(0);
        //Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(brand);
        logger.info("beanUtil bean to map:{}", map);
        //Map转Bean
        PmsBrand mapBrand = BeanUtil.mapToBean(map, PmsBrand.class, false);
        logger.info("beanUtil map to bean:{}", mapBrand);
        //Bean属性拷贝
        PmsBrand copyBrand = new PmsBrand();
        BeanUtil.copyProperties(brand, copyBrand);
        logger.info("beanUtil copy properties:{}", copyBrand);
    }

    /**
     * CollUtil使用：集合工具类
     */
    public static void collUtil() {
        //数组转换为列表
        String[] array = new String[]{"a", "b", "c", "d", "e"};
        List<String> list = CollUtil.newArrayList(array);
        //join：数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        logger.info("collUtil join:{}", joinStr);
        //将以连接符号分隔的字符串再转换为列表
        List<String> splitList = StrUtil.split(joinStr, ',');
        logger.info("collUtil split:{}", splitList);
        //创建新的Map、Set、List
        HashMap<Object, Object> newMap = CollUtil.newHashMap();
        HashSet<Object> newHashSet = CollUtil.newHashSet();
        ArrayList<Object> newList = CollUtil.newArrayList();
        //判断列表是否为空
        CollUtil.isEmpty(list);
        CollUtil.isNotEmpty(list);
    }

    /**
     * MapUtil使用：Map工具类
     */
    public static void mapUtil() {
        //将多个键值对加入到Map中
        Map<Object, Object> map = MapUtil.of(new String[][]{
                {"key1", "value1"},
                {"key2", "value2"},
                {"key3", "value3"}
        });
        //判断Map是否为空
        MapUtil.isEmpty(map);
        MapUtil.isNotEmpty(map);
    }

    /**
     * AnnotationUtil使用：注解工具类
     */
    public static void annotationUtil() {
        //获取指定类、方法、字段、构造器上的注解列表
        Annotation[] annotationList = AnnotationUtil.getAnnotations(HutoolUtilsTest.class, false);
        logger.info("annotationUtil annotations:{}", annotationList);
        //获取指定类型注解
        Api api = AnnotationUtil.getAnnotation(HutoolUtilsTest.class, Api.class);
        logger.info("annotationUtil api value:{}", api.description());
        //获取指定类型注解的值
        Object annotationValue = AnnotationUtil.getAnnotationValue(HutoolUtilsTest.class, RequestMapping.class);
        logger.info("annotationUtil annotationValue:{}", annotationValue);
    }

    /**
     * JSONUtil使用：JSON解析工具类
     */
    public static void jsonUtil() {
        PmsBrand brand = new PmsBrand();
        brand.setId(1L);
        brand.setName("小米");
        brand.setShowStatus(1);
        //对象转化为JSON字符串
        String jsonStr = JSONUtil.parse(brand).toString();
        logger.info("jsonUtil parse:{}", jsonStr);
        //JSON字符串转化为对象
        PmsBrand brandBean = JSONUtil.toBean(jsonStr, PmsBrand.class);
        logger.info("jsonUtil toBean:{}", brandBean);
        List<PmsBrand> brandList = new ArrayList<>();
        brandList.add(brand);
        String jsonListStr = JSONUtil.parse(brandList).toString();
        //JSON字符串转化为列表
        brandList = JSONUtil.toList(new JSONArray(jsonListStr), PmsBrand.class);
        logger.info("jsonUtil toList:{}", brandList);
    }

    /**
     * SecureUtil使用：加密解密工具类
     */
    public static void secureUtil() {
        //MD5加密
        String str = "123456";
        String md5Str = SecureUtil.md5(str);
        logger.info("secureUtil md5:{}", md5Str);
    }

    /**
     * CaptchaUtil使用：图形验证码
     * @param request
     * @param response
     */
    public void captchaUtil(HttpServletRequest request, HttpServletResponse response) {
        //生成验证码图片
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        try {
            request.getSession().setAttribute("CAPTCHA_KEY", lineCaptcha.getCode());
            response.setContentType("image/png");//告诉浏览器输出内容为图片
            response.setHeader("Pragma", "No-cache");//禁止浏览器缓存
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        beanUtil();
    }
}
