# ⚡ FIX NGAY - AUTO-IMPORT SAI NAMESPACE

## 🔴 VẤN ĐỀ
Auto-import suggest sai:
```kotlin
import com.example.buoi7.databinding.ActivityMainBinding  ❌ SAI
```

Thay vì:
```kotlin
import com.example.buoi7_cn.databinding.ActivityMainBinding  ✅ ĐÚNG
```

---

## ✅ GIẢI PHÁP - 2 BƯỚC

### 1️⃣ Chờ Gradle Build xong
```
Gradle Build Running...
[============================] 100%
BUILD SUCCESSFUL ✅
```

### 2️⃣ Invalidate Caches & Restart ⭐
```
File → Invalidate Caches / Restart...
→ Chọn "Invalidate and Restart"
```

Chờ Android Studio restart (~1-2 phút)

---

## 🎯 SAU ĐÓ

Auto-import sẽ đúng namespace:
```kotlin
import com.example.buoi7_cn.databinding.ActivityMainBinding  ✅
```

---

## 💡 TẠI SAO?

Android Studio đang cache namespace cũ (`buoi7`).

**Invalidate Caches** = Xóa cache → Đọc lại config mới.

---

## 📋 CÁCH SỬA IMPORT THỦ CÔNG (nếu cần)

Nếu import đã có sẵn nhưng sai:

1. **Xóa dòng import sai:**
```kotlin
// XÓA DÒNG NÀY
import com.example.buoi7.databinding.ActivityMainBinding
```

2. **Gõ lại đúng:**
```kotlin
import com.example.buoi7_cn.databinding.ActivityMainBinding
```

3. **Hoặc gõ đầy đủ trong code:**
```kotlin
private lateinit var binding: com.example.buoi7_cn.databinding.ActivityMainBinding
```
Rồi Alt+Enter để auto-import.

---

## 🔍 KIỂM TRA NAMESPACE

File `app/build.gradle.kts` phải có:
```kotlin
android {
    namespace = "com.example.buoi7_cn"  ✅ ĐÚNG
}
```

Nếu thấy:
```kotlin
namespace = "com.example.buoi7"  ❌ SAI
```

Sửa thành `buoi7_cn` rồi:
- Sync Gradle
- Invalidate Caches

---

## 🚀 THỨ TỰ THỰC HIỆN

```
1. Chờ Gradle Build xong (từ Ctrl+F9)
2. File → Invalidate Caches / Restart
3. Chờ restart xong
4. Build → Make Project (Ctrl+F9)
5. Run app (Shift+F10)
```

---

**INVALIDATE CACHES LÀ KEY! 🔑**

Sau đó auto-import sẽ suggest đúng namespace!

