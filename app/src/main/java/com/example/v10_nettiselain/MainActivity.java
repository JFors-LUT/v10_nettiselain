package com.example.v10_nettiselain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    WebView webbi;
    EditText ww_sivu;
    Button hae;
    Button paivita;
    Button h_world;
    Button m_maa;
    Button edel_siuv;
    Button seuu_sivu;

    ArrayList<String> historia = new ArrayList<>();
    public ListIterator liter = historia.listIterator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webbi = findViewById(R.id.webselain);
        ww_sivu = findViewById(R.id.editTextTextPersonName);
        webbi.setWebViewClient(new WebViewClient());

        webbi.getSettings().setJavaScriptEnabled(true);
        webbi.getSettings().setAllowContentAccess(true);
        webbi.getSettings().setAllowFileAccess(true);

        hae = findViewById(R.id.button);
        paivita = findViewById(R.id.button2);
        h_world = findViewById(R.id.button3);
        m_maa = findViewById(R.id.button4);
        edel_siuv = findViewById(R.id.button5);
        seuu_sivu = findViewById(R.id.button6);



     webbi.loadUrl("file:///android_asset/index.html");
     sivuHistoria("file:///android_asset/index.html");

    }

    public void performHaku(View v) {
        if (ww_sivu.getText().toString().equals("") == false) {
            String kohde = ww_sivu.getText().toString();
            if(kohde.length()>8){
                if ((kohde.substring(0, 7).equals("http://") == true) || (kohde.substring(0, 8).equals("https://") == true)) {
                    webbi.loadUrl(kohde);
                } else {
                    webbi.loadUrl("http://" + kohde);
                }
            } else {
                webbi.loadUrl("http://" + kohde);
            }
            }
            String edellinen = webbi.getUrl();
            sivuHistoria(edellinen);
        }

    public void paivitaSivu(View v){

    String webUrl = webbi.getUrl();
    webbi.loadUrl(webUrl);
}

public void sivuHistoria(String URL) {

        if (liter.hasPrevious()){
            liter.previous();
    }
        while (liter.hasPrevious()){
            liter.previous();
            liter.remove();
        }
        liter.add(URL);
    if (historia.size() > 10) {
        historia.remove(10);
    }
    liter = historia.listIterator();
}

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public void execute_world(View v){
    if (!webbi.getUrl().equals("file:///android_asset/index.html")) {
        webbi.loadUrl("file:///android_asset/index.html");
    }
    webbi.evaluateJavascript("javascript:shoutOut()", null);
}

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public void init_maailma(View v) {
    if (!webbi.getUrl().equals("file:///android_asset/index.html")) {
        webbi.loadUrl("file:///android_asset/index.html");
    }
    webbi.evaluateJavascript("javascript:initialize()", null);
}

public void sivuTaakse(View v){
        String current = "";
    if (liter.hasNext()) {
        liter.next();
        current = (String) liter.next();
        webbi.loadUrl(current);
        liter.previous();
    }
    }

public void sivuEtee (View v){
    String current = "";
    for (int i=0; i<historia.size(); i++)
    {
        System.out.println("alkio: "+i+" ja osoite: "+historia.get(i));
    }
    System.out.println("alkoimäärä: "+historia.size());
        if (liter.hasPrevious()) {
            System.out.println("Indeksi: "+liter.previousIndex());
            if (liter.previousIndex() == historia.size()-1){
                String sivu = (String) liter.previous();
            }
        current = (String) liter.previous();
        webbi.loadUrl(current);

    }
}

}