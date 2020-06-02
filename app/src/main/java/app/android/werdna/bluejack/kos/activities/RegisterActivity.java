package app.android.werdna.bluejack.kos.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.db.UserRepository;
import app.android.werdna.bluejack.kos.pojos.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText _usernameEditText;
    private EditText _passwordEditText;
    private EditText _confirmPasswordEditText;
    private EditText _phoneNumberEditText;
    private CheckBox _termsCheckBox;
    private EditText _birthdayEditText;
    private TextView _usernameError;
    private TextView _passwordError;
    private TextView _confirmPasswordError;
    private TextView _phoneNumberError;
    private TextView _termsError;
    private TextView _genderError;
    private TextView _birthdayError;
    private RadioGroup _radioGroup;

    public static Intent createIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _usernameEditText = findViewById(R.id.username_edit_text);
        _passwordEditText = findViewById(R.id.password_edit_text);
        _confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        _phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        _termsCheckBox = findViewById(R.id.terms_checkbox);
        _birthdayEditText = findViewById(R.id.birthday_edit_text);
        _usernameError = findViewById(R.id.error_username);
        _passwordError = findViewById(R.id.error_password);
        _confirmPasswordError = findViewById(R.id.error_confirm_password);
        _phoneNumberError = findViewById(R.id.error_phone_number);
        _termsError = findViewById(R.id.error_terms);
        _birthdayError = findViewById(R.id.error_birthday);
        _genderError = findViewById(R.id.error_gender);
        _radioGroup = findViewById(R.id.radio_group);
    }

    private boolean validateInputs() {
        boolean validated = true;
        String username = _usernameEditText.getText().toString();
        String password = _passwordEditText.getText().toString();
        String confirmPassword = _confirmPasswordEditText.getText().toString();
        String phoneNumber = _phoneNumberEditText.getText().toString();
        String dateOfBirth = _birthdayEditText.getText().toString();

        if (username.length() < 3 || username.length() > 25) {
            validated = false;
            _usernameError.setText(R.string.username_length_error);
            _usernameError.setVisibility(View.VISIBLE);
        } else if (!Pattern.compile("[A-Za-z0-9]*[0-9][a-zA-Z][A-Za-z0-9]*|[A-Za-z0-9]*[a-zA-Z][0-9][A-Za-z0-9]*").matcher(username).matches()) {
            validated = false;
            _usernameError.setText(R.string.username_alphanumeric_error);
            _usernameError.setVisibility(View.VISIBLE);
        } else if (UserRepository.with(this).isRegistered(username)) {
            validated = false;
            _usernameError.setText(R.string.username_taken_error);
            _usernameError.setVisibility(View.VISIBLE);
        } else {
            _usernameError.setVisibility(View.GONE);
        }

        if (password.length() <= 6) {
            validated = false;
            _passwordError.setText(R.string.password_length_error);
            _passwordError.setVisibility(View.VISIBLE);
        } else if (!Pattern.compile(".*[0-9].*[a-z].*[A-Z].*|.*[0-9].*[A-Z].*[a-z].*|.*[A-Z].*[0-9].*[a-z].*|.*[A-Z].*[a-z].*[0-9].*|.*[a-z].*[A-Z].*[0-9].*|.*[a-z].*[0-9].*[A-Z].*").matcher(password).matches()) {
            validated = false;
            _passwordError.setText(R.string.password_regex_error);
            _passwordError.setVisibility(View.VISIBLE);
        } else {
            _passwordError.setVisibility(View.GONE);
        }

        if (!confirmPassword.equals(password)) {
            validated = false;
            _confirmPasswordError.setText(R.string.confirm_password_error);
            _confirmPasswordError.setVisibility(View.VISIBLE);
        } else {
            _confirmPasswordError.setVisibility(View.GONE);
        }

        if (phoneNumber.length() < 10 || phoneNumber.length() > 12) {
            validated = false;
            _phoneNumberError.setText(R.string.phone_number_length_error);
            _phoneNumberError.setVisibility(View.VISIBLE);
        } else if (!Pattern.compile("[0-9]+").matcher(phoneNumber).matches()) {
            validated = false;
            _phoneNumberError.setText(R.string.phone_number_regex_error);
            _phoneNumberError.setVisibility(View.VISIBLE);
        } else {
            _phoneNumberError.setVisibility(View.GONE);
        }

        if (!(dateOfBirth.length() > 0)) {
            validated = false;
            _birthdayError.setText(R.string.birthday_error);
            _birthdayError.setVisibility(View.VISIBLE);
        } else {
            _birthdayError.setVisibility(View.GONE);
        }

        if (_radioGroup.getCheckedRadioButtonId() == -1) {
            validated = false;
            _genderError.setText(R.string.gender_error);
            _genderError.setVisibility(View.VISIBLE);
        } else {
            _genderError.setVisibility(View.GONE);
        }

        if (!_termsCheckBox.isChecked()) {
            validated = false;
            _termsError.setText(R.string.terms_error);
            _termsError.setVisibility(View.VISIBLE);
        } else {
            _termsError.setVisibility(View.GONE);
        }

        return validated;
    }

    public void onClickRegister(View v) {
        if (validateInputs()) {
            String userId = "US" + String.format(Locale.US, "%03d", UserRepository.with(this).getLastId()+1);
            String username = _usernameEditText.getText().toString();
            String password = _passwordEditText.getText().toString();
            String phoneNumber = _phoneNumberEditText.getText().toString();
            String dateOfBirth = _birthdayEditText.getText().toString();
            String gender;

            if (_radioGroup.getCheckedRadioButtonId() == R.id.radio_button_male) {
                gender = "Male";
            } else {
                gender = "Female";
            }

            UserRepository.with(this).register(new User(userId, username,
                    password, phoneNumber, gender, dateOfBirth));
            finish();
        }
    }

    public void onClickDate(View v) {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        _birthdayEditText.setText(getResources().getString(R.string.date_format,
                                dayOfMonth, monthOfYear + 1, year));
                    }
                }, y, m, d);
        datePickerDialog.show();
    }
}
