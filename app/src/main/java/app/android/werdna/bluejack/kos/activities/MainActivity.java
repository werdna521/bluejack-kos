package app.android.werdna.bluejack.kos.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.db.UserDb;
import app.android.werdna.bluejack.kos.pojos.BookingTransaction;
import app.android.werdna.bluejack.kos.pojos.User;

public class MainActivity extends AppCompatActivity {

    private ArrayList<BookingTransaction> _bookingTransactions;
    private EditText _usernameEditText;
    private EditText _passwordEditText;
    private TextView _usernameError;
    private TextView _passwordError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _usernameEditText = findViewById(R.id.edit_text_username);
        _passwordEditText = findViewById(R.id.edit_text_password);
        _usernameError = findViewById(R.id.username_error);
        _passwordError = findViewById(R.id.password_error);
    }

    private boolean validateCredentials(String username, String password) {
        String registeredPassword = "";
        for (User u: UserDb.getDb().getAll()) {
            if (u.getUsername().equals(username)) {
                registeredPassword = u.getPassword();
                break;
            }
        }
        return password.length() == 0 || !registeredPassword.equals(password);
    }

    private User getLoggedInUser(String username) {
        User user = null;
        for (User u: UserDb.getDb().getAll()) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }
        return user;
    }

    private boolean validateInputs() {
        boolean validated = true;
        String username = _usernameEditText.getText().toString();
        String password = _passwordEditText.getText().toString();

        if (username.length() == 0) {
            validated = false;
            _usernameError.setText(R.string.username_must_filled);
            _usernameError.setVisibility(View.VISIBLE);
        } else if (validateCredentials(username, password)) {
            validated = false;
            _usernameError.setText(R.string.wrong_credentials);
            _usernameError.setVisibility(View.VISIBLE);
        } else {
            _usernameError.setVisibility(View.GONE);
        }

        if (password.length() == 0) {
            validated = false;
            _passwordError.setText(R.string.password_must_filled);
            _passwordError.setVisibility(View.VISIBLE);
        } else if (validateCredentials(username, password)) {
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
                    getLoggedInUser(_usernameEditText.getText().toString()));
            _usernameEditText.setText("");
            _passwordEditText.setText("");
            startActivity(intent);
        }
    }
}
