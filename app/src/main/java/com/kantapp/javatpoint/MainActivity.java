package com.kantapp.javatpoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    ArrayList tutorialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);


        try
        {
            SAXParserFactory factory=SAXParserFactory.newInstance();

            SAXParser parser=factory.newSAXParser();

            DefaultHandler handler=new DefaultHandler(){
                Boolean name=false;
                Boolean salary=false;

                public void startElement(String uri, String localName, String qName, Attributes attributes)
                {
                    if(qName.equalsIgnoreCase("name"))
                    {
                        name=true;
                    }
                    else if(qName.equalsIgnoreCase("salary"))
                    {
                        salary=true;

                    }
                }

                public void endElement(String uri,String localName,String qName) throws SAXException
                {

                }

                public void characters(char ch[],int start,int length) throws SAXException
                {
                    if(name)
                    {
                        tv.setText(tv.getText()+"\n\n Name : " + new String(ch, start, length));
                        name = false;
                    }
                    if(salary)
                    {
                        tv.setText(tv.getText()+"\n Salary : " + new String(ch, start, length));
                        salary = false;
                    }

                }
            };

            InputStream inputStream=getAssets().open("file.xml");
            parser.parse(inputStream,handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        recyclerView();
    }

    public void recyclerView()
    {
        tutorialList=new ArrayList();
        tutorialList.add("XMLParsingAndroidHive");
        tutorialList.add("XMLPullParsing");
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        RecyclerView.Adapter adapter=new RecyclerViewAdapter(tutorialList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //Adding Click listener on List Recyclerview
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector=new GestureDetector(getApplicationContext(),new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View view=rv.findChildViewUnder(e.getX(),e.getY());
                if(view!=null && gestureDetector.onTouchEvent(e))
                {
                    int position=rv.getChildAdapterPosition(view);
                    //Toast.makeText(MainActivity.this, ""+tutorialList.get(position) ,Toast.LENGTH_SHORT).show();
                    if(tutorialList.get(position)=="XMLParsingAndroidHive")
                    {
                        startActivity(XMLParsingAndroidHive.class);
                    }
                    else if(tutorialList.get(position)=="XMLPullParsing")
                    {
                        startActivity(XMLPullParsing.class);
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    public void startActivity(Class aClass)
    {
        startActivity(new Intent(getApplicationContext(),aClass));
    }
}
