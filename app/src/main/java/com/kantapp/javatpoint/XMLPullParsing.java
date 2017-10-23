package com.kantapp.javatpoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kantapp.javatpoint.androidpala.HttpHandler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.StringReader;
public class XMLPullParsing extends AppCompatActivity {

    private String LOG_TAG = "XML";
    private int UpdateFlag = 0;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_pull_parsing);
        textView2= (TextView) findViewById(R.id.textView2);

//        new GetXMLFromServer().execute();

        ParseXML2();
    }


    public void ParseXML2()
    {
        String xmlstringText="<PrintLetterBarcodeData name=\"Arvind Kartik Kant\" co=\"S/O: Kartik Kant\" dist=\"Bilaspur\" dob=\"1992-11-13\"\n" +
                "    gender=\"M\" house=\"ward no.4 house no.660\" lm=\"dilip lahare house\" loc=\"adarsh nagar sirgitti\"\n" +
                "    pc=\"495001\" po=\"Bilaspur\" state=\"Chhattisgarh\" street=\"gali no.8\" subdist=\"Bilaspur\"\n" +
                "    uid=\"560907835998\" vtc=\"Sirgitti\" yob=\"1992\" />";
        try
        {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(new StringReader(xmlstringText));
            int eventType=parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if(eventType== XmlPullParser.START_TAG)
                {

                    String name = parser.getName();
                    if(name.equals("PrintLetterBarcodeData"))
                    {
                        String name1=parser.getAttributeValue(null,"name");
                        textView2.append("Aadhar Card "+name1);
                    }


                }
                eventType = parser.next();


            }



        }
        catch (XmlPullParserException | IOException e)
        {
            e.printStackTrace();
        }
    }
    public void ParseXML(String xmlString){

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){

                if(eventType== XmlPullParser.START_TAG){

                    String name = parser.getName();
                    if(name.equals("UpdateFlag")){

                        String ref = parser.getAttributeValue(null,"ref");
                        Log.i(LOG_TAG,"ref:" + ref);
                        textView2.append(LOG_TAG+"ref:" + ref);

                        if(parser.next() == XmlPullParser.TEXT) {
                            String UpdateFlag = parser.getText();
                            Log.i(LOG_TAG,"UpdateFlag:" + UpdateFlag);
                            textView2.append(LOG_TAG+"UpdateFlag:" + UpdateFlag);
                        }


                    }else if(name.equals("Name")) {

                        if(parser.next() == XmlPullParser.TEXT) {
                            String Name = parser.getText();
                            Log.i(LOG_TAG,"Name:" + Name);
                            textView2.append(LOG_TAG+"Name:" + Name);
                        }
                    }else if(name.equals("Range")) {

                        if(parser.next() == XmlPullParser.TEXT) {
                            String Range = parser.getText();
                            Log.i(LOG_TAG,"Range:" + Range);
                            textView2.append(LOG_TAG+"Range:" + Range);
                        }
                    }


                }else if(eventType== XmlPullParser.END_TAG){


                }
                eventType = parser.next();

            }


        }catch (Exception e){
            Log.i(LOG_TAG,"Error in ParseXML()",e);
            textView2.append(LOG_TAG+"Error in ParseXML()"+e);
        }

    }

    private class GetXMLFromServer extends AsyncTask<String,Void,String> {

        HttpHandler nh;

        @Override
        protected String doInBackground(String... strings) {

            String URL = "http://androidpala.com/tutorial/horoscope.xml";
            String res = "";
            nh =  new HttpHandler();
            InputStream is = nh.CallServer(URL);
            if(is!=null){

                res = nh.StreamToString(is);

            }else{
                res = "NotConnected";
            }

            return res;
        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result.equals("NotConnected")){

                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();

            }else {
//                ParseXML2();
            }
        }



    }
}
