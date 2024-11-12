package com.afifahsafitri.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputNIM, inputNama, inputPresensi, inputTugas, inputUTS, inputUAS;
    private RadioGroup radioGroupSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNIM = findViewById(R.id.input_nim);
        inputNama = findViewById(R.id.input_nama);
        inputPresensi = findViewById(R.id.input_presensi);
        inputTugas = findViewById(R.id.input_tugas);
        inputUTS = findViewById(R.id.input_uts);
        inputUAS = findViewById(R.id.input_uas);
        radioGroupSemester = findViewById(R.id.radioGroupSemester);
        Button btnHitung = findViewById(R.id.btn_hitung);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    calculateGrade();
                }
            }
        });
    }

    private boolean validateInputs() {
        if (inputNIM.getText().toString().isEmpty() || inputNama.getText().toString().isEmpty() ||
                inputPresensi.getText().toString().isEmpty() || inputTugas.getText().toString().isEmpty() ||
                inputUTS.getText().toString().isEmpty() || inputUAS.getText().toString().isEmpty() ||
                radioGroupSemester.getCheckedRadioButtonId() == -1) {

            Toast.makeText(this, "Seluruh data wajib diisi", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValueInRange(inputPresensi) || !isValueInRange(inputTugas) ||
                !isValueInRange(inputUTS) || !isValueInRange(inputUAS)) {

            Toast.makeText(this, "Nilai tidak boleh lebih kecil dari 10 dan tidak boleh lebih besar dari 100", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValueInRange(EditText editText) {
        int value = Integer.parseInt(editText.getText().toString());
        return value >= 10 && value <= 100;
    }

    private void calculateGrade() {
        int presensi = Integer.parseInt(inputPresensi.getText().toString());
        int tugas = Integer.parseInt(inputTugas.getText().toString());
        int uts = Integer.parseInt(inputUTS.getText().toString());
        int uas = Integer.parseInt(inputUAS.getText().toString());

        double nilaiAkhir = (0.1 * presensi) + (0.2 * tugas) + (0.3 * uts) + (0.4 * uas);
        int nilaiAkhirInt = (int) Math.ceil(nilaiAkhir);

        String grade;
        if (nilaiAkhirInt >= 85) grade = "A";
        else if (nilaiAkhirInt >= 70) grade = "B";
        else if (nilaiAkhirInt >= 50) grade = "C";
        else grade = "D";

        RadioButton selectedSemester = findViewById(radioGroupSemester.getCheckedRadioButtonId());
        String semester = selectedSemester.getText().toString();

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("nim", inputNIM.getText().toString());
        intent.putExtra("nama", inputNama.getText().toString());
        intent.putExtra("semester", semester);
        intent.putExtra("nilaiAkhir", nilaiAkhirInt);
        intent.putExtra("grade", grade);
        startActivity(intent);
    }
}
