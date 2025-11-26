# 🔴 ĐANG BUILD - VUI LÒNG ĐỢI!

## ✅ Ctrl + F9 đang chạy
Khi bạn nhấn **Ctrl + F9**, sẽ thấy:
```
Gradle Build Running...
```

👉 **ĐÂY LÀ BÌNH THƯỜNG!** Chờ nó chạy xong (~1-2 phút)

---

## 🔧 VẤN ĐỀ: Auto-import sai namespace

### Hiện tượng:
Khi gõ `ActivityMainBinding` và nhấn Alt+Enter, Android Studio suggest:
```kotlin
import com.example.buoi7.databinding.ActivityMainBinding  ❌ SAI
```

Thay vì:
```kotlin
import com.example.buoi7_cn.databinding.ActivityMainBinding  ✅ ĐÚNG
```

### Nguyên nhân:
Android Studio đang cache namespace cũ (`buoi7` thay vì `buoi7_cn`)

---

## ✅ GIẢI PHÁP - LÀM NGAY

### Bước 1: Chờ Gradle Build xong
Nhìn vào thanh progress bar ở góc dưới phải Android Studio.
Chờ đến khi thấy:
```
BUILD SUCCESSFUL
```

### Bước 2: Invalidate Caches (QUAN TRỌNG)
```
File → Invalidate Caches / Restart...
→ Chọn: "Invalidate and Restart"
```

**Tại sao?** Xóa cache cũ của Android Studio, buộc nó đọc lại namespace mới.

Android Studio sẽ restart (~1-2 phút)

### Bước 3: Sau khi restart, Rebuild
```
Build → Clean Project
Build → Make Project (Ctrl + F9)
```

### Bước 4: Sửa import thủ công (nếu cần)
Nếu vẫn còn import sai, xóa và gõ lại:

1. Xóa dòng import sai:
```kotlin
// XÓA DÒNG NÀY
import com.example.buoi7.databinding.ActivityMainBinding  ❌
```

2. Gõ lại đúng:
```kotlin
import com.example.buoi7_cn.databinding.ActivityMainBinding  ✅
```

3. Hoặc gõ đầy đủ trong code:
```kotlin
private lateinit var binding: com.example.buoi7_cn.databinding.ActivityMainBinding
```

Sau đó Alt+Enter để auto-import đúng.

---

## 🎯 SAU KHI INVALIDATE CACHES

Auto-import sẽ suggest đúng namespace:
```kotlin
import com.example.buoi7_cn.databinding.ActivityMainBinding  ✅
import com.example.buoi7_cn.databinding.DialogAddLichBinding  ✅
import com.example.buoi7_cn.databinding.LichItemBinding      ✅
```

---

## 📋 THỨ TỰ THỰC HIỆN ĐẦY ĐỦ

### 1. Chờ Gradle Build xong
```
[============================] 100%
BUILD SUCCESSFUL
```

### 2. Invalidate Caches & Restart
```
File → Invalidate Caches / Restart → Invalidate and Restart
```
Chờ Android Studio restart

### 3. Sau khi restart, Make Project
```
Build → Make Project (Ctrl + F9)
```

### 4. Run App
```
Run → Run 'app' (Shift + F10)
```

---

## 🔍 KIỂM TRA NAMESPACE ĐÚNG

Mở file: `app/build.gradle.kts`

Kiểm tra dòng:
```kotlin
android {
    namespace = "com.example.buoi7_cn"  // ✅ PHẢI LÀ buoi7_cn
```

Nếu sai, sửa lại:
```kotlin
android {
    namespace = "com.example.buoi7_cn"  // Sửa thành buoi7_cn
```

Sau đó:
```
File → Sync Project with Gradle Files
File → Invalidate Caches / Restart
```

---

## 💡 TẠI SAO XẢY RA?

Android Studio cache namespace cũ khi:
- Đổi namespace từ `buoi7` → `buoi7_cn`
- Build nhiều lần với namespace khác nhau
- Cache không được clear

**Giải pháp:** Invalidate Caches để Android Studio đọc lại config mới.

---

## 🎉 SAU KHI INVALIDATE CACHES

- ✅ Auto-import suggest đúng namespace
- ✅ ViewBinding classes có namespace đúng
- ✅ Không còn lỗi import
- ✅ App build và chạy thành công

---

## 🆘 NẾU VẪN SAI

### Option 1: Xóa build folders
1. Close Android Studio
2. Xóa thư mục:
   - `app/build/`
   - `.gradle/`
   - `.idea/`
3. Open Android Studio
4. Sync Gradle
5. Make Project

### Option 2: Re-import project
```
File → Close Project
File → Open... (chọn lại thư mục project)
```

### Option 3: Kiểm tra lại build.gradle.kts
Đảm bảo:
```kotlin
android {
    namespace = "com.example.buoi7_cn"  // ✅ ĐÚNG
    
    // KHÔNG PHẢI:
    // namespace = "com.example.buoi7"  ❌ SAI
}
```

---

## 📝 TÓM TẮT

1. ✅ **Đang build** - Chờ Gradle Build xong
2. 🔄 **Invalidate Caches** - Xóa cache cũ
3. 🔨 **Rebuild** - Build lại với namespace mới
4. ✅ **Auto-import đúng** - Không còn suggest sai

---

**BÂY GIỜ:**
1. Chờ Gradle Build xong
2. File → Invalidate Caches / Restart
3. Sau khi restart, Make Project
4. Run app

**SAU ĐÓ AUTO-IMPORT SẼ ĐÚNG! 🎉**

