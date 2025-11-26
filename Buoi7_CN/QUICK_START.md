# Quick Start Guide - App Quản lý Lịch hẹn cá nhân

## ✅ Checklist Files đã tạo

### Model Layer (Data)
- ✅ `data/model/Lich.kt` - Entity class cho Room Database
- ✅ `data/dao/LichDao.kt` - Data Access Object
- ✅ `data/database/LichDatabase.kt` - Room Database

### Repository Layer
- ✅ `repository/LichRepository.kt` - Repository pattern

### ViewModel Layer
- ✅ `ui/viewmodel/LichViewModel.kt` - ViewModel với LiveData

### View Layer
- ✅ `ui/view/MainActivity.kt` - Main Activity
- ✅ `ui/view/NotificationReceiver.kt` - Notification handler
- ✅ `ui/adapter/LichAdapter.kt` - RecyclerView Adapter

### Layout Files
- ✅ `res/layout/activity_main.xml` - Đã cập nhật với RecyclerView
- ✅ `res/layout/dialog_add_lich.xml` - Dialog thêm lịch hẹn
- ✅ `res/layout/lich_item.xml` - Item layout cho RecyclerView

### Configuration
- ✅ `AndroidManifest.xml` - Đã thêm permissions và receiver
- ✅ `app/build.gradle.kts` - Đã có dependencies cần thiết

## 🚀 Cách chạy app

### Bước 1: Sync Gradle
1. Mở Android Studio
2. File → Sync Project with Gradle Files
3. Đợi Gradle sync xong

### Bước 2: Build Project
1. Build → Make Project
2. Kiểm tra không có lỗi compile

### Bước 3: Chạy App
1. Chọn emulator hoặc kết nối thiết bị Android
2. Run → Run 'app' (hoặc nhấn Shift + F10)

## 📱 Yêu cầu hệ thống
- Android API 30+ (Android 11 trở lên)
- Android Studio Arctic Fox trở lên
- Gradle 8.0+
- Kotlin 1.9+

## 🧪 Test các tính năng

### 1. Thêm lịch hẹn mới
```
1. Nhấn button "Thêm lịch"
2. Nhập thông tin:
   - Họ tên: "Nguyễn Văn A"
   - Ngày giờ: Chọn ngày và giờ trong tương lai
   - Nội dung: "Họp team"
   - URL: https://i.pravatar.cc/150?img=1
3. Nhấn "Thêm"
4. Kiểm tra item xuất hiện trong RecyclerView
```

### 2. Xóa lịch hẹn
```
1. Click vào item trong danh sách
2. Dialog xác nhận sẽ hiện
3. Nhấn "Xóa"
4. Item sẽ biến mất khỏi danh sách
```

### 3. Lọc theo khoảng thời gian
```
1. Nhấn vào ô "Từ"
2. Chọn ngày bắt đầu
3. Nhấn vào ô "Đến"
4. Chọn ngày kết thúc
5. Danh sách tự động lọc theo khoảng thời gian
```

### 4. Test Notification
```
1. Thêm lịch hẹn với thời gian cách hiện tại khoảng 5 phút
2. Đợi 5 phút (notification sẽ hiện trước 30 phút)
   Hoặc thêm lịch hẹn cách 31 phút để test ngay
3. Notification sẽ xuất hiện trong notification bar
```

### 5. Test Load Image từ URL
```
Sử dụng các URL test trong file SAMPLE_IMAGE_URLS.md:
- https://i.pravatar.cc/150?img=1
- https://i.pravatar.cc/150?img=2
- https://randomuser.me/api/portraits/men/1.jpg
```

## 🔧 Troubleshooting

### Lỗi: "Cannot resolve symbol 'R'"
**Giải pháp:**
```
1. Build → Clean Project
2. Build → Rebuild Project
3. File → Invalidate Caches / Restart
```

### Lỗi: Notification không hiện
**Giải pháp:**
```
1. Kiểm tra permissions trong Settings → Apps → Buoi7_CN → Notifications
2. Bật quyền notification
3. Kiểm tra thời gian hẹn phải > hiện tại + 30 phút
```

### Lỗi: Ảnh không load
**Giải pháp:**
```
1. Kiểm tra internet connection
2. Kiểm tra URL hợp lệ
3. Thử URL khác từ SAMPLE_IMAGE_URLS.md
```

### Lỗi: Room database schema
**Giải pháp:**
```
1. Uninstall app khỏi thiết bị/emulator
2. Build → Clean Project
3. Chạy lại app
```

## 📊 Kiến trúc MVVM

```
┌──────────────────────────────────────────┐
│           View (Activity/UI)              │
│  - MainActivity.kt                        │
│  - LichAdapter.kt                         │
│  - NotificationReceiver.kt                │
└─────────────┬────────────────────────────┘
              │ observes LiveData
              ▼
┌──────────────────────────────────────────┐
│         ViewModel                         │
│  - LichViewModel.kt                       │
│  - Manages UI state                       │
│  - Exposes LiveData                       │
└─────────────┬────────────────────────────┘
              │ calls
              ▼
┌──────────────────────────────────────────┐
│         Repository                        │
│  - LichRepository.kt                      │
│  - Single source of truth                 │
└─────────────┬────────────────────────────┘
              │ uses
              ▼
┌──────────────────────────────────────────┐
│         Data Source (Room DB)             │
│  - LichDatabase.kt                        │
│  - LichDao.kt                             │
│  - Lich.kt (Entity)                       │
└──────────────────────────────────────────┘
```

## 📝 Ghi chú quan trọng

1. **Namespace**: App sử dụng namespace `com.example.buoi7` trong build.gradle
2. **Package**: Code sử dụng package `com.example.buoi7_cn`
3. **minSdk**: 30 (Android 11)
4. **targetSdk**: 36
5. **Room Version**: 2.8.3
6. **Lifecycle Version**: 2.9.4

## 🎯 Các tính năng đã implement

- ✅ Show danh sách lịch hẹn trong RecyclerView
- ✅ Click item để xóa với dialog xác nhận
- ✅ Date picker cho "Từ" và "Đến"
- ✅ Lọc lịch hẹn theo khoảng thời gian
- ✅ Dialog thêm lịch hẹn mới
- ✅ Load hình ảnh từ URL
- ✅ Notification trước 30 phút
- ✅ Áp dụng mô hình MVVM
- ✅ Room Database
- ✅ LiveData & ViewModel
- ✅ Coroutines

## 📞 Support

Nếu gặp vấn đề, kiểm tra:
1. Logcat trong Android Studio
2. Build output
3. README.md để xem chi tiết tính năng

---
**Chúc bạn code thành công! 🎉**

