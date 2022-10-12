package fr.heban.TP4HEBAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class URLActivity extends AppCompatActivity {

    public final static String DATA_RESULT = "fr.heban.android_tp_4.URLActivity.DATA_RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlactivity);
    }

    public void onBtnValidClicked(View view)
    {
        EditText url_input = (EditText) findViewById(R.id.input_url);

        String url = url_input.getText().toString();
        if(!url.equals("")) {
            Intent intent = new Intent();
            intent.putExtra(DATA_RESULT, url);

            setResult(RESULT_OK, intent);
            this.finish();
        } else {
            Toast.makeText(this, R.string.call_app_error, Toast.LENGTH_SHORT).show();
        }

    }

    public void onBtnCancelClicked(View view)
    {
        setResult(RESULT_CANCELED);
        this.finish();
    }
}