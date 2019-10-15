package thuyvtk.activity.laundry_customer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.presenter.CustomerPresenter;
import thuyvtk.activity.laundry_customer.view.CustomerView;

public class VerifyCodeOTPActivity extends Activity implements CustomerView {
    EditText txtCodeOTP;
    FirebaseAuth mAuth;
    String codeSent;
    String phone;
    Button btnContinue;
    CustomerPresenter presenter;
    LinearLayout ln_login_waiting;

    private void definedView() {
        txtCodeOTP = findViewById(R.id.txtCodeOTP);
        btnContinue = findViewById(R.id.btnContinue);
        ln_login_waiting =  findViewById(R.id.ln_login_waiting);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code_otp);
        definedView();
        phone = getIntent().getStringExtra("phone");
        sendRequest();
        presenter = new CustomerPresenter(this);
        mAuth = FirebaseAuth.getInstance();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = txtCodeOTP.getText().toString();
                if(!code.equals("")){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
                    signInWithPhoneAuthCredential(credential);
                    ln_login_waiting.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(VerifyCodeOTPActivity.this, "Please enter sent code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendRequest() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//            Toast.makeText(, "auto fill", Toast.LENGTH_SHORT).show();
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        try{
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = task.getResult().getUser();
                                Toast.makeText(VerifyCodeOTPActivity.this, user.getUid(), Toast.LENGTH_SHORT).show();
                                String id = user.getUid();
                                presenter.getCustomerByFirebaseId(id);
                            } else {
                                // Sign in failed, display a message and update the UI
//                            Toast.makeText(MainActivity.this, "signing fail", Toast.LENGTH_SHORT).show();
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                Toast.makeText(MainActivity.this, "invalid code", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }catch (Exception e){
            Toast.makeText(this, "Wrong login code", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void returnCustomer(CustomerDTO customerDTO) {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(this);
        sharePreferenceLib.saveUser(customerDTO);
        ln_login_waiting.setVisibility(View.GONE);
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
