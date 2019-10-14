package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.adapter.StoreAdapter;
import thuyvtk.activity.laundry_customer.model.StoreDTO;
import thuyvtk.activity.laundry_customer.presenter.StorePresenter;
import thuyvtk.activity.laundry_customer.view.StoreView;

public class SearchStoreResultActivity extends AppCompatActivity implements StoreView {
    StorePresenter presenter;
    LinearLayout ln_searchResult_waiting;
    ListView lvResult;
    StoreAdapter storeAdapter;
    EditText edtSearchStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_store_result);
        defineView();
        presenter  = new StorePresenter(this);
        getStoreName();
    }

    private void defineView() {
        ln_searchResult_waiting = findViewById(R.id.ln_searchResult_waiting);
        lvResult = findViewById(R.id.lvResult);
        edtSearchStore = findViewById(R.id.edtSearchStore);
        edtSearchStore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                storeAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getStoreName() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        presenter.searchStoreByName(name);
        ln_searchResult_waiting.setVisibility(View.VISIBLE);
    }

    @Override
    public void returnAllStore(ArrayList<StoreDTO> listStore) {

    }

    @Override
    public void loadStoreFail(String message) {

    }

    @Override
    public void searchStoreByName(ArrayList<StoreDTO> listStore) {
        storeAdapter = new StoreAdapter(this,listStore);
        lvResult.setAdapter(storeAdapter);
        ln_searchResult_waiting.setVisibility(View.GONE);
    }

    @Override
    public void searchStoreByNameFail(String message) {

    }

    @Override
    public void returnStoreById(StoreDTO storeDTO) {

    }
}
