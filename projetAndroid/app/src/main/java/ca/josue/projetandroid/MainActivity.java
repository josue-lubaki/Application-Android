package ca.josue.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ca.josue.projetandroid.navigation.add;
import ca.josue.projetandroid.navigation.home;
import ca.josue.projetandroid.navigation.message;
import ca.josue.projetandroid.navigation.profile;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener {

    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView nav_bar = findViewById(R.id.nav_bar);
        nav_bar.setOnNavigationItemReselectedListener(this);
        showFragment(home.class);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch(item.getItemId()){
            case R.id.optionHome:
                showFragment(home.class);
                break;
            case R.id.optionAdd:
                showFragment(add.class);
                break;
            case R.id.optionMessage:
                showFragment(message.class);
                break;
            case R.id.optionProfile:
                showFragment(profile.class);
                break;

        }
    }

    private void showFragment(Class<? extends Fragment> fragment) {
        try {
            currentFragment = getSupportFragmentManager().findFragmentByTag(fragment.getName());

            if(currentFragment == null)
                currentFragment = fragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, currentFragment, fragment.getName()).commit();
        }catch(InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            Log.i("error","erreur au moment d'instancier fragment");
        }
    }
}