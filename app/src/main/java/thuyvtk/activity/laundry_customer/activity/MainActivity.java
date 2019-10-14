package thuyvtk.activity.laundry_customer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.fragment.AccountFragment;
import thuyvtk.activity.laundry_customer.fragment.HomeFragment;
import thuyvtk.activity.laundry_customer.fragment.OrderFragment;

public class MainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(this);
        loadFrament( new AccountFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_notification:
//                fragment =  new HomeFragment();
                break;
            case R.id.navigation_oder:
                fragment =  new OrderFragment();
                break;
            case R.id.navigation_account:
               fragment = new AccountFragment();
                break;
        }
        return loadFrament(fragment);
    }

    private boolean loadFrament(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
