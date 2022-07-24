package Rajagiri.playfaircipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEncode(View V)
    {
        Intent i = new Intent(this, EncodeActivity.class);
        startActivity(i);
    }

    public void onDecode(View v)
    {
        Intent i1 = new Intent(this, DecodeActivity.class);
        startActivity(i1);
    }
}