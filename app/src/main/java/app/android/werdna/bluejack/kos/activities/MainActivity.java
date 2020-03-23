package app.android.werdna.bluejack.kos.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.pojos.User;

public class MainActivity extends AppCompatActivity {

    private static final int REGISTER_USER_CODE = 1;

    private ArrayList<User> _users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _users = new ArrayList<>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REGISTER_USER_CODE) {
                if (null != data) {
                    _users = data.getParcelableArrayListExtra("users");
                }
            }
        }
    }

    public void onClickRegister(View v) {
        Intent intent = RegisterActivity.createIntent(MainActivity.this, _users);
        startActivityForResult(intent, REGISTER_USER_CODE);
    }
}
