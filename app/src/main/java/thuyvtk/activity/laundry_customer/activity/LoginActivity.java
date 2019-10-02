package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;

public class LoginActivity extends Activity {
    Button btnLogin_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkLocalUser();
        btnLogin_phone = findViewById(R.id.btnLogin_phone);
        btnLogin_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginByPhoneActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLocalUser() {
        SharePreferenceLib sharePreferenceLib =  new SharePreferenceLib(this);
        CustomerDTO dto = sharePreferenceLib.getUser();
        if(dto != null){
            // if there is a local account, do not need to login
            Intent intent =  new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            // if there is no local account then login
        }
    }

}
