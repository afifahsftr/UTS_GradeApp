package com.afifahsafitri.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultNIM = findViewById(R.id.result_nim);
        TextView resultNama = findViewById(R.id.result_nama);
        TextView resultMatkul = findViewById(R.id.result_matkul);
        TextView resultSemester = findViewById(R.id.result_semester);
        TextView resultNilaiAkhir = findViewById(R.id.result_nilai_akhir);
        TextView resultGrade = findViewById(R.id.result_grade);
        Button btnKembali = findViewById(R.id.btn_kembali);

        // Ambil data dari intent
        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        String nama = intent.getStringExtra("nama");
        String semester = intent.getStringExtra("semester");
        int nilaiAkhir = intent.getIntExtra("nilaiAkhir", 0);
        String grade = intent.getStringExtra("grade");

        resultNIM.setText("NIM: " + nim);
        resultNama.setText("Nama: " + nama);
        resultMatkul.setText("Matkul: Mobile Programming");
        resultSemester.setText("Semester: " + semester);
        resultNilaiAkhir.setText("Nilai Akhir: " + nilaiAkhir);
        resultGrade.setText("Grade: " + grade);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
