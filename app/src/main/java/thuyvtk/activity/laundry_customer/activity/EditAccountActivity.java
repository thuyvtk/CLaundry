package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.fragment.AccountFragment;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.presenter.CustomerPresenter;
import thuyvtk.activity.laundry_customer.view.CustomerView;

public class EditAccountActivity extends Activity implements CustomerView {

    ImageButton imgBackActivity;
    EditText txtUsername;
    EditText txtPhone;
    Button btnSave;

    public EditAccountActivity() {
    }

    CustomerPresenter customerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        customerPresenter = new CustomerPresenter(this);
        loadCustomerInfor();
        imgBackActivity = findViewById(R.id.imgBackActivity);
        imgBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditAccountActivity.this.finish();
            }
        });
        txtUsername = findViewById(R.id.txtUsername);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCustomer("d8961124-f5dd-4124-8453-08d74555b235", txtUsername.getText().toString());
                EditAccountActivity.this.finish();
            }
        });
    }

    private void updateCustomer(String customerId, String customerName) {
        customerPresenter.updateCustomer(customerId, customerName);
    }

    public void loadCustomerInfor() {
        SharePreferenceLib sharePreferenceLib =  new SharePreferenceLib();
        CustomerDTO dto = sharePreferenceLib.getUser(this);
        txtUsername.setText(dto.getCustomerName());
    }

    @Override
    public void returnCustomer(CustomerDTO customerDTO) {
        // do nothing
    }
}
