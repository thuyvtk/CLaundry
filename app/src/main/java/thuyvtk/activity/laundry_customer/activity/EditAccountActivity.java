package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
    CustomerDTO dto;
    CustomerPresenter customerPresenter;

    public EditAccountActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        txtUsername = findViewById(R.id.txtUsername);
        btnSave = findViewById(R.id.btnSave);
        customerPresenter = new CustomerPresenter(this);
        imgBackActivity = findViewById(R.id.imgBackActivity);

        loadCustomerInfor();

        imgBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditAccountActivity.this.finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dto != null) {
                    String name = txtUsername.getText().toString();
                    updateCustomer(dto.getCustomerId(), name);
                    //update dto to save local
                    dto.setCustomerName(name);
                    EditAccountActivity.this.finish();
                } else {
                    Toast.makeText(EditAccountActivity.this, "EditAccount -null User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateCustomer(String customerId, String customerName) {
        customerPresenter.updateCustomer(customerId, customerName);
    }

    public void loadCustomerInfor() {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(this);
        dto = sharePreferenceLib.getUser();
        txtUsername.setText(dto.getCustomerName());
    }

    @Override
    public void returnCustomer(CustomerDTO customerDTO) {
        // do nothing
    }

    @Override
    public void updateSuccess() {
        // update user in SharePreferent
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(this);
        sharePreferenceLib.saveUser(dto);
    }
}
