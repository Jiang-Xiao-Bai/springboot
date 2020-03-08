package com.ssm.springboot.DemoTests;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 通过Document解析xml
 * @author zhaohf
 * @date 2020/3/8 21:35
 */
public class DocumentTest {
    public static void main(String[] args) throws Exception {
        //创建一个文档解析器工厂
        DocumentBuilderFactory fac= DocumentBuilderFactory.newInstance();

        //用上面的工厂创建一个文档解析器
        DocumentBuilder builder=fac.newDocumentBuilder();

        //用上面的文档解析器解析一个文件放到document对象里
        Document doc=builder.parse("src/main/resources/logback.xml");

        //获取文档中节点名称为Listener的所有节点，并返回一个节点集合
        NodeList listenerList=doc.getElementsByTagName("appender");
        System.out.println("一共有"+listenerList.getLength()+"个节点");

        //遍历整个集合(把所有标签名为Listener的节点一个一个拿出来
        for (int i=0;i<listenerList.getLength();i++){
            System.out.println("===========这是第"+(i+1)+"个listener节点的开始:===========");
            //把集合里的每一个listener节点分别拿出来
            Node node=listenerList.item(i);
            //再把上一个节点中的所有属性拿出来
            NamedNodeMap nodeMap= node.getAttributes();
            System.out.println("第"+(i+1)+"个节点一共有"+nodeMap.getLength()+"个属性");

            //遍历所有属性
            for(int j=0;j<nodeMap.getLength();j++){
                Node node1=nodeMap.item(j);
                System.out.println("第"+(j+1)+"个属性的名称是"+node1.getNodeName());
                System.out.println("第"+(j+1)+"个属性的值是"+node1.getNodeValue());
            }
            //获取节点的所有子节点，注意会把所有换行符也解析为子节点
            NodeList childNode=node.getChildNodes();

            //遍历所有子节点
            for(int k=0;k<childNode.getLength();k++){
                if(childNode.item(k).getNodeType()==Node.ELEMENT_NODE){
                    //这个过滤条件是只将标签节点保留（换行符的节点就删去）
                }

                System.out.println("===========这是第"+(i+1)+"个节点的结束:===========");
            }
        }
    }
}
