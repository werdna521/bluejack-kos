package app.android.werdna.bluejack.kos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.db.UserRepository;

public class MainActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 1;

    private EditText _usernameEditText;
    private EditText _passwordEditText;
    private TextView _usernameError;
    private TextView _passwordError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.SEND_SMS }, SMS_PERMISSION_CODE);

        _usernameEditText = findViewById(R.id.edit_text_username);
        _passwordEditText = findViewById(R.id.edit_text_password);
        _usernameError = findViewById(R.id.username_error);
        _passwordError = findViewById(R.id.password_error);
    }

    private boolean validateInputs() {
        boolean validated = true;
        String username = _usernameEditText.getText().toString();
        String password = _passwordEditText.getText().toString();

        if (username.length() == 0) {
            validated = false;
            _usernameError.setText(R.string.username_must_filled);
            _usernameError.setVisibility(View.VISIBLE);
        } else if (!UserRepository.with(this).isRegistered(username)) {
            validated = false;
            _usernameError.setText(R.string.username_not_registered);
            _usernameError.setVisibility(View.VISIBLE);
        } else {
            _usernameError.setVisibility(View.GONE);
        }

        if (password.length() == 0) {
            validated = false;
            _passwordError.setText(R.string.password_must_filled);
            _passwordError.setVisibility(View.VISIBLE);
        } else if (!UserRepository.with(this).authenticate(username, password)) {
            validated = false;
            _passwordError.setText(R.string.wrong_credentials);
            _passwordError.setVisibility(View.VISIBLE);
        } else {
            _passwordError.setVisibility(View.GONE);
        }

        return validated;
    }

    public void onClickRegister(View v) {
        _usernameEditText.setText("");
        _passwordEditText.setText("");
        Intent intent = RegisterActivity.createIntent(MainActivity.this);
        startActivity(intent);
    }

    public void onClickLogin(View v) {
        if (validateInputs()) {
            Intent intent = KosListActivity.createIntent(MainActivity.this,
                    UserRepository.with(this).getUser(_usernameEditText.getText().toString()));
            _usernameEditText.setText("");
            _passwordEditText.setText("");
            startActivity(intent);
        }
    }
}
