package com.kantapp.javatpoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParsingAndroidHive extends AppCompatActivity {

    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlparsing);

        tv1=(TextView)findViewById(R.id.textView1);

        try
        {
            InputStream inputStream=getAssets().open("file2.xml");
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(inputStream);
            Element element=document.getDocumentElement();
            element.normalize();

            NodeList nodeList=document.getElementsByTagName("employee");
            for(int i=0;i<nodeList.getLength();i++)
            {
                Node node =nodeList.item(i);
                if(node.getNodeType()== Node.ELEMENT_NODE)
                {
                    Element element2=(Element)node;
                    tv1.setText(tv1.getText()+"\nName : " + getValue("name", element2)+"\n");
                    tv1.setText(tv1.getText()+"Surname : " + getValue("surname", element2)+"\n");
                    tv1.setText(tv1.getText()+"-----------------------");
                }
            }
        }
        catch (IOException | ParserConfigurationException | SAXException e)
        {
            e.printStackTrace();
        }
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}
