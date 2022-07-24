package Rajagiri.playfaircipher;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EncodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);
    }

    public void onEncodePlaintext(View V)
    {
        EditText t1=(EditText)findViewById(R.id.et1);
        EditText t2=(EditText)findViewById(R.id.et2);
        char keysquar[][] = new char[5][5];
        String skey = t1.getText().toString();
        skey= skey.toUpperCase();
        char ckey[] = skey.toCharArray();
        //checking for repetition of characters in string if so removing
        int len = ckey.length;
        for(int i=0; i<len-1; i++)
        {
            for(int j=i+1; j<len; j++)
            {
                if(ckey[i]==ckey[j])
                {
                    int z=j;
                    while(z < len-1)
                    {
                        ckey[z]=ckey[z+1];
                        z++;
                    }
                    len--;
                    j--;
                    ckey[z]='\0';
                }
            }
        }
        char a='A';
        skey="";
        for(int q=0; q<len; q++)
            skey=skey+ckey[q];
        // 'Z' ignored as the occurrence of 'Z' in dict is 1/1111
        for(int q=len; q<25; q++)
        {
            while(skey.contains(a+""))
                a++;
            skey=skey+a;
        }
        //making key square
        int x=0;
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                keysquar[i][j]=skey.charAt(x++);
            }
        }
        String plaintxt = t2.getText().toString();
        plaintxt = plaintxt.toUpperCase();
        String stxt = "";
        int count=0;
        for(int i=0; i< plaintxt.length();i++)
        {
            count++;
            if(count == 2)
            {
                if(plaintxt.charAt(i) == 'Z')
                    stxt += "Q";
                else if(stxt.charAt(i-1) == plaintxt.charAt(i))
                    stxt +=  "Q"+plaintxt.charAt(i);
                else
                    stxt += plaintxt.charAt(i);
                count = 0;
            }
            else
            {
                if(plaintxt.charAt(i) == 'Z')
                    stxt += "Q";
                else
                    stxt += plaintxt.charAt(i)+"";
            }
        }
        if(stxt.length()%2 == 1)
            stxt += "Q";
        //System.out.println(stxt);
        char[] ctxt = stxt.toCharArray();
        String ciphertxt = "";
        for(int i=0; i< ctxt.length; i+=2)
        {
            int x1=0,x2=0,y1=0,y2=0;
            for(int i1=0; i1<5; i1++)
            {
                for(int j=0; j<5; j++)
                {
                    if(keysquar[i1][j]==ctxt[i])
                    {
                        x1=i1;
                        y1=j;
                    }
                    if(keysquar[i1][j]==ctxt[i+1])
                    {
                        x2=i1;
                        y2=j;
                    }
                }
            }
            if(x1 == x2)
            {
                ciphertxt += keysquar[x1][(y1+1)%5]+""+keysquar[x1][(y2+1)%5];
            }
            else if(y2 == y1)
            {
                ciphertxt += keysquar[(x1+1)%5][y1]+""+keysquar[(x2+1)%5][y2];
            }
            else
            {
                ciphertxt += keysquar[x1%5][y2%5]+""+keysquar[x2%5][y1%5];
            }
        }
        EditText et3 = (EditText)findViewById(R.id.et3);
        et3.setText(ciphertxt);
    }
}