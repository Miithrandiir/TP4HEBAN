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

    /**
     * Méthode permettant de traiter le retour de l'activité d'appel
     */
    private ActivityResultLauncher<Intent> callGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //On vérifie que le retour est bon ET qu'il y a des résultats
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        //On récupère le numéro de téléphone
                        String numberPhone = result.getData().getStringExtra(CallActivity.RESULT_DATA);

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + numberPhone));

                        startActivity(intent);

                    }
                }
            }
    );

    /**
     * Méthode permettant de traiter le retour de l'activité de SMS / MMS
     */
    private ActivityResultLauncher<Intent> smsGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //On vérifie que le retour est bon ET qu'il y a des résultats
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        //On récupère sous forme de tableau de String les données
                        //[0] => Numéro de téléphone
                        //[1] => Le message
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
    /**
     * Méthode permettant de traiter le retour de l'activité d'URL
     */
    private ActivityResultLauncher<Intent> urlGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //On vérifie que le retour est bon ET qu'il y a des résultats
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        //On récupère l'url demandé
                        String data = result.getData().getStringExtra(URLActivity.DATA_RESULT);

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://" + data));

                        startActivity(intent);

                    }
                }
            }
    );

    /**
     * Méthode permettant de traiter le retour de l'activité de latitude & longitude
     */
    private ActivityResultLauncher<Intent> geoGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //On vérifie que le retour est bon ET qu'il y a des résultats
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        //On récupère sous forme de tableau de String les coordonnées latitude & longitude
                        //[0] => Latitude
                        //[1] => Longitude
                        String[] data = result.getData().getStringArrayExtra(GeoActivity.RESULT_DATA);

                        String latitude = data[0];
                        String longitude = data[1];

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:" + latitude + "," + longitude));

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

    /**
     * Méthode appelé quand un bouton est appuyé
     *
     * @param view View
     */
    public void onButtonClicked(View view) {

        switch (view.getId()) {
            case R.id.button_call:
                //Bouton appel appuyé
                Intent intentCall = new Intent(this, CallActivity.class);
                callGetResult.launch(intentCall);
                break;
            case R.id.button_sms:
            case R.id.button_mms:
                //L'action concernant le sms et le mms est la même
                Intent intentSMS = new Intent(this, SMSActivity.class);
                smsGetResult.launch(intentSMS);
                break;
            case R.id.button_web:
                //Bouton WEB appuyé
                Intent intentURI = new Intent(this, URLActivity.class);
                urlGetResult.launch(intentURI);
                break;
            case R.id.button_geo:
                //Bouton GEO appuyé
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