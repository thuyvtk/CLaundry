package thuyvtk.activity.laundry_customer.activity;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.adapter.ServiceAdapter;
import thuyvtk.activity.laundry_customer.library.CartDTO;
import thuyvtk.activity.laundry_customer.library.LocationLibrary;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderServiceDTO;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;
import thuyvtk.activity.laundry_customer.presenter.OrderPresenter;
import thuyvtk.activity.laundry_customer.view.OrderView;

public class ReceiptDetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, OrderView {
    TextView timeDelivery, dateDelivery, txtTotalReceipt, txtCustomerReceipt, txtAddress, timeTake, dateTake;
    LinearLayout ln_timeDelivery, ln_dateDelivery, ln_timeTake, ln_dateTake;
    ListView lvReceipt;
    SharePreferenceLib sharePreferenceLib;
    ArrayList<ServiceDTO> listService;
    CartDTO cartDTO;
    String PENDING = "pending";
    Boolean isTake = false;
    OrderPresenter presenter;

    private void defineView() {
        timeDelivery = findViewById(R.id.timeDelivery);
        dateDelivery = findViewById(R.id.dateDelivery);
        ln_timeDelivery = findViewById(R.id.ln_timeDelivery);
        ln_dateDelivery = findViewById(R.id.ln_dateDelivery);
        timeTake = findViewById(R.id.timeTake);
        dateTake = findViewById(R.id.dateTake);
        ln_timeTake = findViewById(R.id.ln_timeTake);
        ln_dateTake = findViewById(R.id.ln_dateTake);
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
        presenter = new OrderPresenter(this);
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
        for (ServiceDTO service : listService) {
            OrderServiceDTO dto = new OrderServiceDTO(service.getQuantity(), (float) service.getPrice(), service.getId());
            listOrder.add(dto);
        }
        String deliveryTime = dateDelivery.getText().toString() + "T" + timeDelivery.getText().toString();
        String takeTime = dateTake.getText().toString() + "T" + timeTake.getText().toString();
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyyTHH:mm:ss");
        try {
            OrderDTO orderDTO = new OrderDTO((float) cartDTO.getTotalPrice(), PENDING, cartDTO.getCustomer().getCustomerId(),sdf.parse(takeTime),sdf.parse(deliveryTime),listOrder);
            //todo create order
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        // time take
        timeTake.setText(timeFormat.format(date));
        dateTake.setText(dateFormat.format(nextDate));
        ln_timeTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ReceiptDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        timeTake.setText(time);
                    }
                }, 0, 0, true);
                timePickerDialog.setTitle("Select Take Time");
                timePickerDialog.show();
            }
        });
        ln_dateTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTake = true;
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + 1 + "/" + year;
        if(isTake){
            dateTake.setText(date);
            isTake = false;
        }else{
            dateDelivery.setText(date);
        }

    }

    @Override
    public void loadOrderHistory(List<OrderDTO> orderList) {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void createOrderSuccess() {

    }
}
