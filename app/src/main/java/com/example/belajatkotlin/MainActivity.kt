package com.example.belajatkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etHargaBarang: EditText
    private lateinit var etJumlahBarang: EditText
    private lateinit var btnHitung: Button
    private lateinit var btnReset: Button
    private lateinit var tvHasil: TextView
    private var isStart  = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hitung)

        etHargaBarang = findViewById(R.id.etHargaBarang)
        etJumlahBarang = findViewById(R.id.etJumlahBarang)
        btnHitung = findViewById(R.id.btnHitung)
        btnReset = findViewById(R.id.btnReset)
        tvHasil = findViewById(R.id.tvHasil)



        setInitialState()

        btnHitung.setOnClickListener {
            val harga = etHargaBarang.text.toString().trim()
            val jumlah = etJumlahBarang.text.toString().trim()

            if (harga.isNotEmpty() && jumlah.isNotEmpty()) {
                val hargaBarang = harga.toDouble()
                val jumlahBarang = jumlah.toInt()
                val totalBayar = hargaBarang * jumlahBarang


                tvHasil.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.toFloat())
                tvHasil.setTextColor(ContextCompat.getColor(this, R.color.warna1))

                tvHasil.text = "Total Bayar: Rp. $totalBayar"

                btnHitung.isEnabled = false
                btnReset.isEnabled=true
                etHargaBarang.isEnabled = false
                etJumlahBarang.isEnabled = false

            } else {
                Toast.makeText(this, "Masukkan harga dan jumlah barang", Toast.LENGTH_SHORT).show()
            }
        }
        btnReset.setOnClickListener {
            if (isStart) {
                // Jika tombol reset dalam mode start, aktifkan semua elemen
                setActiveState()
                btnReset.text = "Reset" // Ubah teks tombol kembali ke "Reset"
                isStart = false
            } else {
                // Jika tombol reset dalam mode reset, kosongkan semua input dan hasil
                etHargaBarang.text.clear()
                etJumlahBarang.text.clear()
                tvHasil.text = "Total Bayar: "

                // Aktifkan kembali tombol hitung dan EditText
                btnHitung.isEnabled = true
                btnReset.isEnabled=false
                etHargaBarang.isEnabled = true
                etJumlahBarang.isEnabled = true
            }

        }

    }

    private fun setActiveState() {
        btnHitung.isEnabled = true
        etHargaBarang.isEnabled = true
        etJumlahBarang.isEnabled = true
    }

    private fun setInitialState() {
        // Nonaktifkan semua elemen kecuali tombol reset
        btnHitung.isEnabled = false
        etHargaBarang.isEnabled = false
        etJumlahBarang.isEnabled = false
        btnReset.text = "Start" // Ubah teks tombol menjadi "Start"
        isStart = true
    }
}