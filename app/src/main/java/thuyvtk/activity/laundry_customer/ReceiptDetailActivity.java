package thuyvtk.activity.laundry_customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import thuyvtk.activity.laundry_customer.activity.StoreDetailActivity;
import thuyvtk.activity.laundry_customer.adapter.ServiceAdapter;
import thuyvtk.activity.laundry_customer.library.CartDTO;
import thuyvtk.activity.laundry_customer.library.LocationLibrary;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderServiceDTO;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;
import thuyvtk.activity.laundry_customer.services.OrderService;

public class ReceiptDetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView timeDelivery, dateDelivery, txtTotalReceipt, txtCustomerReceipt, txtAddress;
    LinearLayout ln_timeDelivery, ln_dateDelivery;
    ListView lvReceipt;
    SharePreferenceLib sharePreferenceLib;
    ArrayList<ServiceDTO> listService;
    CartDTO cartDTO;
    String PENDING = "pending";

    private void defineView() {
        timeDelivery = findViewById(R.id.timeDelivery);
        dateDelivery = findViewById(R.id.dateDelivery);
        ln_timeDelivery = findViewById(R.id.ln_timeDelivery);
        ln_dateDelivery = findViewById(R.id.ln_dateDelivery);
        lvReceipt = findViewById(R.id.lvReceipt);
        txtTotalReceipt = findViewById(R.id.txtTotalReceipt);
        txtCustomerReceipt = findViewById(R.id.txtCustomerReceipt);
        txtAddress = findViewById(R.id.txtAddress);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_detail);
        defineView();
        setTime();
        sharePreferenceLib = new SharePreferenceLib(this);
        cartDTO = sharePreferenceLib.getShoppingCart();
        if (cartDTO != null) {
            listService = new ArrayList<>(cartDTO.getListStore().values());
            ServiceAdapter serviceAdapter = new ServiceAdapter(this, listService, 1);
            lvReceipt.setAdapter(serviceAdapter);
        }
        txtCustomerReceipt.setText(cartDTO.getCustomer().getCustomerName());
        txtTotalReceipt.setText(cartDTO.getTotalPrice() + "");
        setAddressTextBox();

    }

    private void setAddressTextBox() {
        LocationLibrary locationLibrary = new LocationLibrary(getApplicationContext(), this);
        List<Address> address = locationLibrary.getCurrentAddress();
        String addressLine = address.get(0).getAddressLine(0);
        if (addressLine.length() > 35) {
            String temp = addressLine.replace(addressLine.substring(35), "...");
            addressLine = temp;
        }
        txtAddress.setText(addressLine);
    }

    public void clickToOrder(View view) {
        ArrayList<OrderServiceDTO> listOrder = new ArrayList<>();
        for (ServiceDTO service: listService) {
            OrderServiceDTO dto = new OrderServiceDTO(service.getQuantity(), (float) service.getPrice(),service.getId());
            listOrder.add(dto);
        }
        Date takeTime =
        OrderDTO orderDTO = new OrderDTO(cartDTO.getTotalPrice(),PENDING,cartDTO.getCustomer().getCustomerId(),)
    }

    private void setTime() {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, 2019, 10, 12);
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        timeDelivery.setText(timeFormat.format(date));
        Date nextDate = date;
        nextDate.setDate(date.getDay() + 1);
        dateDelivery.setText(dateFormat.format(nextDate));
        ln_timeDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ReceiptDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        timeDelivery.setText(time);
                    }
                }, 0, 0, true);
                timePickerDialog.setTitle("Select Delivery Time");
                timePickerDialog.show();
            }
        });
        ln_dateDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + 1 + "/" + year;
        dateDelivery.setText(date);
    }
}
