package thuyvtk.activity.laundry_customer.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import thuyvtk.activity.laundry_customer.R;

public class LoginByPhoneActivity extends Activity {
    Button btnSendCode;
    EditText txtPhone;
    String phone;
    final String PHONE_PREFIX_VN = "+84";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_phone);
        btnSendCode = findViewById(R.id.btnSendCode);
        txtPhone = findViewById(R.id.txtPhone);
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VerifyCodeOTPActivity.class);
                phone = PHONE_PREFIX_VN;
                phone += txtPhone.getText().toString();
                intent.putExtra("phone",phone );
                startActivity(intent);
            }
        });

    }


}
