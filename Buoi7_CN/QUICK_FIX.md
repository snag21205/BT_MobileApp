# ⚡ QUICK FIX - ViewBinding Error

## ❌ LỖI
```
Cannot resolve symbol 'ActivityMainBinding'
Cannot resolve symbol 'DialogAddLichBinding'
Cannot resolve symbol 'LichItemBinding'
```

## ✅ GIẢI PHÁP (5 BƯỚC - 2 PHÚT)

### 1️⃣ Sync Gradle
```
File → Sync Project with Gradle Files
```

### 2️⃣ Clean Project
```
Build → Clean Project
```

### 3️⃣ Rebuild Project ⭐ (QUAN TRỌNG)
```
Build → Rebuild Project
```
**Chờ build xong ~1-2 phút**

### 4️⃣ Kiểm tra Import
Đảm bảo MainActivity có:
```kotlin
import com.example.buoi7_cn.databinding.ActivityMainBinding
import com.example.buoi7_cn.databinding.DialogAddLichBinding
```

Nếu import màu đỏ:
- Xóa dòng import
- Gõ lại "ActivityMainBinding"
- Alt + Enter để auto-import

### 5️⃣ Run App
```
Run → Run 'app' (Shift + F10)
```

---

## 🔥 NẾU VẪN LỖI

### Plan B: Invalidate Caches
```
File → Invalidate Caches / Restart → Invalidate and Restart
```

---

## ✅ ĐÃ HOÀN THÀNH

### Files đã áp dụng ViewBinding:
1. ✅ **MainActivity.kt** - ActivityMainBinding
2. ✅ **Dialog Add Lich** - DialogAddLichBinding  
3. ✅ **LichAdapter.kt** - LichItemBinding

### Lợi ích:
- ✅ Code ngắn gọn hơn 50%
- ✅ Type-safe & Null-safe
- ✅ Không còn findViewById()
- ✅ Dễ maintain

---

## 📋 SO SÁNH

### ❌ TRƯỚC (findViewById)
```kotlin
private lateinit var rvLich: RecyclerView
private lateinit var edtStartDate: EditText

override fun onCreate(savedInstanceState: Bundle?) {
    setContentView(R.layout.activity_main)
    rvLich = findViewById(R.id.rvLich)
    edtStartDate = findViewById(R.id.edtStartDate)
}
```

### ✅ SAU (ViewBinding)
```kotlin
private lateinit var binding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    
    binding.rvLich
    binding.edtStartDate
}
```

---

## 🎯 TẠI SAO CẦN REBUILD?

ViewBinding class được **generate tự động** khi build:
- `activity_main.xml` → `ActivityMainBinding.java`
- `dialog_add_lich.xml` → `DialogAddLichBinding.java`
- `lich_item.xml` → `LichItemBinding.java`

**Không build = không có class = lỗi compile!**

---

## 🚀 BÂY GIỜ HÃY:

```
1. Build → Rebuild Project
2. Chờ xong
3. Run app
```

**DONE! 🎉**

