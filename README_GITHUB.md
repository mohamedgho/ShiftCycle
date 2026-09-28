# Shift Cycle Android App

تطبيق Android بسيط لحساب الشيفتات حسب دورة 4 أسابيع:

1. 4 عصرًا → 11 مساءً
2. 11 مساءً → 8 صباحًا
3. راحة
4. 8 صباحًا → 4 عصرًا

ثم تتكرر الدورة كل 4 أسابيع.

## البناء على GitHub

1. أنشئ Repository جديد على GitHub.
2. ارفع كل ملفات المشروع إلى الفرع `main`.
3. افتح تبويب **Actions**.
4. اختر **Build APK**.
5. اضغط **Run workflow**.
6. بعد انتهاء الـ workflow، افتح نتيجة التشغيل.
7. من قسم **Artifacts** حمّل:
   `ShiftCycle-debug-apk`
8. فك الضغط وستجد `app-debug.apk`.

## ملاحظة

الـ workflow يستخدم Gradle 8.10.2 مباشرة، لذلك لا تحتاج إلى Gradle Wrapper داخل المشروع.
