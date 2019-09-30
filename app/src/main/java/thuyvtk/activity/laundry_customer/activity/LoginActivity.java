package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import thuyvtk.activity.laundry_customer.R;

public class LoginActivity extends Activity {
    Button btnLogin_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin_phone = findViewById(R.id.btnLogin_phone);
        btnLogin_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginByPhoneActivity.class);
                startActivity(intent);
            }
        });
    }

}
