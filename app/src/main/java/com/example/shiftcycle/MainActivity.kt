package com.example.shiftcycle

import android.app.*
import android.os.Bundle
import android.widget.*
import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context

class MainActivity : Activity() {
    private val fmt = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private lateinit var startEdit: EditText
    private lateinit var dateEdit: EditText
    private lateinit var result: TextView
    private val prefs by lazy { getSharedPreferences("shift", Context.MODE_PRIVATE) }

    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        val box = LinearLayout(this).apply { orientation=LinearLayout.VERTICAL; setPadding(28,35,28,28) }
        fun label(s:String)=TextView(this).apply { text=s; textSize=18f; setPadding(0,12,0,8) }
        box.addView(label("تطبيق دورة الشيفتات"))
        box.addView(label("تاريخ بداية الدورة (الأسبوع الأول):"))
        startEdit=EditText(this).apply { hint="مثال: 26/09/2026"; setText(prefs.getString("start","26/09/2026")) }
        box.addView(startEdit)
        val save=Button(this).apply { text="حفظ تاريخ البداية" }
        box.addView(save)
        box.addView(label("اختر التاريخ:"))
        dateEdit=EditText(this).apply { hint="مثال: 03/10/2026"; setText(fmt.format(Date())) }
        box.addView(dateEdit)
        val check=Button(this).apply { text="اعرف الشيفت" }
        box.addView(check)
        val today=Button(this).apply { text="شيفت النهارده" }
        box.addView(today)
        result=TextView(this).apply { textSize=22f; setPadding(0,25,0,20) }
        box.addView(result)
        box.addView(label("الدورة: 4 أسابيع تتكرر تلقائيًا"))
        box.addView(TextView(this).apply {
            text="الأسبوع 1: 4 عصرًا → 11 مساءً\nالأسبوع 2: 11 مساءً → 8 صباحًا\nالأسبوع 3: راحة\nالأسبوع 4: 8 صباحًا → 4 عصرًا"
            textSize=18f
        })
        setContentView(box)

        save.setOnClickListener {
            prefs.edit().putString("start",startEdit.text.toString()).apply()
            toast("تم حفظ تاريخ البداية")
        }
        check.setOnClickListener { showShift(dateEdit.text.toString()) }
        today.setOnClickListener { dateEdit.setText(fmt.format(Date())); showShift(dateEdit.text.toString()) }
    }

    private fun showShift(s:String) {
        try {
            val start=fmt.parse(startEdit.text.toString())!!
            val date=fmt.parse(s)!!
            val days=((date.time-start.time)/86400000L).toInt()
            val week=((Math.floorDiv(days,7)%4)+4)%4
            val shift=when(week){0->"الأسبوع 1\n4 عصرًا → 11 مساءً";1->"الأسبوع 2\n11 مساءً → 8 صباحًا";2->"الأسبوع 3\nراحة";else->"الأسبوع 4\n8 صباحًا → 4 عصرًا"}
            result.text="📅 $s\n\n$shift"
        } catch(e:Exception){ result.text="اكتب التاريخ بالشكل: 26/09/2026" }
    }
    private fun toast(s:String)=Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
}
