package fr.heban.TP4HEBAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

    public static final String RESULT_DATA = "fr.heban.android_tp_4.CallActivity.RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
    }

    public void onBtnValidClicked(View view) {
        EditText phone_number = (EditText) findViewById(R.id.input_phone_number);
        String phone_number_value = phone_number.getText().toString();

        if(!phone_number_value.equals("")) {
            Intent intent = new Intent();
            intent.putExtra(RESULT_DATA, phone_number_value);
            setResult(RESULT_OK, intent);
            this.finish();
        } else {
            Toast.makeText(this, R.string.call_app_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void onBtnCancelClicked(View view) {
        setResult(RESULT_CANCELED);
        this.finish();
    }
}