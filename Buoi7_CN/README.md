# App Quản lý Lịch hẹn cá nhân

## Mô tả
Ứng dụng Android để quản lý lịch hẹn cá nhân với đầy đủ các tính năng theo yêu cầu.

## Kiến trúc
Ứng dụng được xây dựng theo mô hình **MVVM (Model-View-ViewModel)** với các thành phần sau:

### 1. **Model Layer** (`data` package)
- **Lich.kt**: Entity class đại diện cho một lịch hẹn trong database
  - `id`: Khóa chính tự động tăng
  - `hoVaTen`: Tên người hẹn
  - `ngayGio`: Thời gian hẹn (timestamp)
  - `noiDung`: Nội dung cuộc hẹn
  - `linkAnh`: URL hình ảnh đại diện

- **LichDao.kt**: Data Access Object để thao tác với database
  - `insert()`: Thêm lịch hẹn mới
  - `update()`: Cập nhật lịch hẹn
  - `delete()`: Xóa lịch hẹn
  - `getAllLich()`: Lấy tất cả lịch hẹn
  - `getLichByDateRange()`: Lấy lịch hẹn theo khoảng thời gian

- **LichDatabase.kt**: Room Database singleton

### 2. **Repository Layer** (`repository` package)
- **LichRepository.kt**: Trung gian giữa ViewModel và DAO

### 3. **ViewModel Layer** (`ui.viewmodel` package)
- **LichViewModel.kt**: Quản lý dữ liệu UI và business logic
  - Sử dụng LiveData để quan sát thay đổi dữ liệu
  - Sử dụng Coroutines cho các thao tác bất đồng bộ

### 4. **View Layer** (`ui.view` và `ui.adapter` package)
- **MainActivity.kt**: Activity chính
- **LichAdapter.kt**: Adapter cho RecyclerView
- **NotificationReceiver.kt**: BroadcastReceiver xử lý thông báo

## Các chức năng chính

### ✅ 1. Hiển thị danh sách lịch hẹn
- Sử dụng RecyclerView để hiển thị danh sách
- Mỗi item hiển thị:
  - Hình ảnh đại diện (load từ URL)
  - Họ và tên người hẹn
  - Ngày giờ hẹn (định dạng dd/MM/yyyy HH:mm)
  - Nội dung cuộc hẹn

### ✅ 2. Xóa lịch hẹn
- Click vào item trong RecyclerView
- Hiển thị Dialog xác nhận xóa
- Nếu đồng ý, xóa lịch hẹn và hủy notification đã đặt

### ✅ 3. Lọc theo khoảng thời gian
- Chọn ngày "Từ" bằng DatePicker
- Chọn ngày "Đến" bằng DatePicker
- Tự động lọc và hiển thị lịch hẹn trong khoảng thời gian đã chọn

### ✅ 4. Thêm lịch hẹn mới
- Nhấn button "Thêm Lịch Hẹn"
- Hiển thị Dialog với các trường:
  - Họ và tên
  - Ngày giờ (DatePicker + TimePicker)
  - Nội dung
  - Link ảnh (URL)
- Validation: Các trường bắt buộc phải nhập đầy đủ

### ✅ 5. Load hình ảnh từ URL
- Sử dụng URL lưu trong database
- Load hình ảnh trong background thread
- Hiển thị hình mặc định nếu lỗi

### ✅ 6. Notification trước 30 phút
- Tự động đặt notification khi thêm lịch hẹn mới
- Notification sẽ hiện trước thời điểm hẹn 30 phút
- Sử dụng AlarmManager để đặt lịch chính xác
- Hủy notification khi xóa lịch hẹn

## Permissions
Ứng dụng yêu cầu các quyền sau trong AndroidManifest.xml:
- `POST_NOTIFICATIONS`: Hiển thị thông báo (Android 13+)
- `SCHEDULE_EXACT_ALARM`: Đặt alarm chính xác
- `USE_EXACT_ALARM`: Sử dụng exact alarm
- `INTERNET`: Load hình ảnh từ URL

## Thư viện sử dụng
- **Room Database**: Lưu trữ dữ liệu local
- **LiveData & ViewModel**: Quản lý dữ liệu UI lifecycle-aware
- **Coroutines**: Xử lý bất đồng bộ
- **RecyclerView**: Hiển thị danh sách
- **Material Design**: UI components

## Cách sử dụng

### Thêm lịch hẹn:
1. Nhấn button "Thêm Lịch Hẹn"
2. Nhập họ tên người hẹn
3. Chọn ngày giờ hẹn
4. Nhập nội dung cuộc hẹn
5. Nhập URL hình ảnh (tùy chọn)
6. Nhấn "Thêm"

### Lọc theo thời gian:
1. Nhấn vào ô "Từ" để chọn ngày bắt đầu
2. Nhấn vào ô "Đến" để chọn ngày kết thúc
3. Danh sách sẽ tự động lọc

### Xóa lịch hẹn:
1. Nhấn vào lịch hẹn trong danh sách
2. Chọn "Xóa" trong dialog xác nhận

## Cấu trúc thư mục
```
app/src/main/java/com/example/buoi7_cn/
├── data/
│   ├── dao/
│   │   └── LichDao.kt
│   ├── database/
│   │   └── LichDatabase.kt
│   └── model/
│       └── Lich.kt
├── repository/
│   └── LichRepository.kt
└── ui/
    ├── adapter/
    │   └── LichAdapter.kt
    ├── view/
    │   ├── MainActivity.kt
    │   └── NotificationReceiver.kt
    └── viewmodel/
        └── LichViewModel.kt
```

## Layout Files
- **activity_main.xml**: Layout màn hình chính
- **dialog_add_lich.xml**: Layout dialog thêm lịch hẹn
- **lich_item.xml**: Layout item trong RecyclerView

## Build & Run
1. Mở project trong Android Studio
2. Sync Gradle
3. Run app trên thiết bị hoặc emulator (API 30+)

## Lưu ý
- Ứng dụng yêu cầu Android API 30 (Android 11) trở lên
- Cần cấp quyền notification trên Android 13+
- Đảm bảo thiết bị có kết nối internet để load hình ảnh từ URL

