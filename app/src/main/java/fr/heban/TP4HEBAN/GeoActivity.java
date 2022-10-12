package fr.heban.TP4HEBAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GeoActivity extends AppCompatActivity {

    public static final String RESULT_DATA = "fr.heban.android_tp_4.GeoActivity.RESULT_DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
    }

    public void onBtnValidClicked(View view) {

        EditText latitude_input = (EditText) findViewById(R.id.input_latitude);
        EditText longitude_input = (EditText) findViewById(R.id.input_longitude);

        String latitude = latitude_input.getText().toString();
        String longitude = longitude_input.getText().toString();

        if(!latitude.equals("") && !longitude.equals("")) {

            Intent intent = new Intent();
            intent.putExtra(RESULT_DATA, new String[]{latitude, longitude});

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