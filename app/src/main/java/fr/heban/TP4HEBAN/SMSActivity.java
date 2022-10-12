package fr.heban.TP4HEBAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    public static final String RESULT_DATA = "fr.heban.android_TP_4.SMSActivity.RESULT_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsactivity);
    }

    public void onBtnValidClicked(View view) {
        EditText input_phone_number = (EditText) findViewById(R.id.sms_input_phone_number);
        EditText input_msg = (EditText) findViewById(R.id.sms_input_msg);

        String phone_number = input_phone_number.getText().toString();
        String msg = input_msg.getText().toString();

        if(!msg.equals("") && !phone_number.equals("")) {
            Intent intent = new Intent();
            intent.putExtra(RESULT_DATA, new String[]{phone_number, msg});

            setResult(RESULT_OK, intent);
            this.finish();
        } else {
            Toast.makeText(this, R.string.app_fields_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void onBtnCancelClicked(View view) {
        setResult(RESULT_CANCELED);
        this.finish();
    }
}