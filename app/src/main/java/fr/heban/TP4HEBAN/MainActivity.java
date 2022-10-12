package fr.heban.TP4HEBAN;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> callGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String numberPhone = result.getData().getStringExtra(CallActivity.RESULT_DATA);

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + numberPhone));

                        startActivity(intent);

                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> smsGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String[] data = result.getData().getStringArrayExtra(SMSActivity.RESULT_DATA);

                        String phone_number = data[0];
                        String msg = data[1];

                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("sms:" + phone_number + "?body=" + msg));

                        startActivity(intent);

                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> urlGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String data = result.getData().getStringExtra(URLActivity.DATA_RESULT);

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(data));

                        startActivity(intent);

                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> geoGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String[] data = result.getData().getStringArrayExtra(GeoActivity.RESULT_DATA);

                        String latitude = data[0];
                        String longitude = data[1];

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:"+latitude+","+longitude));

                        startActivity(intent);

                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClicked(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.button_call:
                Intent intentCall = new Intent(this, CallActivity.class);
                callGetResult.launch(intentCall);
                break;
            case R.id.button_sms:
            case R.id.button_mms:
                Intent intentSMS = new Intent(this, SMSActivity.class);
                smsGetResult.launch(intentSMS);
                break;
            case R.id.button_web:
                Intent intentURI = new Intent(this, URLActivity.class);
                urlGetResult.launch(intentURI);
                break;
            case R.id.button_geo:
                Intent intentGeo = new Intent(this, GeoActivity.class);
                geoGetResult.launch(intentGeo);
                break;
            default:
                Button btn = (Button) view;
                Toast.makeText(this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }


    }
}