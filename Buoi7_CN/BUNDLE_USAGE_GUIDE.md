# Hướng dẫn sử dụng Bundle trong App Quản lý Lịch hẹn

## Tổng quan về Bundle

Bundle là một đối tượng dùng để truyền dữ liệu giữa các Activity, Fragment, hoặc lưu trữ trạng thái của ứng dụng. App này đã tích hợp Bundle ở nhiều nơi.

---

## 1. Bundle trong MainActivity

### 1.1 Lưu và khôi phục trạng thái khi xoay màn hình

**File:** `MainActivity.kt`

```kotlin
// Companion object chứa các key constants
companion object {
    private const val KEY_START_DATE = "start_date_millis"
    private const val KEY_END_DATE = "end_date_millis"
    private const val KEY_START_DATE_TEXT = "start_date_text"
    private const val KEY_END_DATE_TEXT = "end_date_text"
}

// Lưu state khi configuration thay đổi (xoay màn hình, thay đổi ngôn ngữ, etc.)
override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putLong(KEY_START_DATE, startDateMillis)
    outState.putLong(KEY_END_DATE, endDateMillis)
    outState.putString(KEY_START_DATE_TEXT, binding.edtStartDate.text.toString())
    outState.putString(KEY_END_DATE_TEXT, binding.edtEndDate.text.toString())
}

// Khôi phục state trong onCreate
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // ...
    savedInstanceState?.let {
        startDateMillis = it.getLong(KEY_START_DATE, 0)
        endDateMillis = it.getLong(KEY_END_DATE, Long.MAX_VALUE)
        binding.edtStartDate.setText(it.getString(KEY_START_DATE_TEXT, ""))
        binding.edtEndDate.setText(it.getString(KEY_END_DATE_TEXT, ""))
    }
}
```

**Lợi ích:** 
- Khi người dùng xoay màn hình, các bộ lọc ngày tháng không bị mất
- Trải nghiệm người dùng tốt hơn, không phải nhập lại

---

## 2. Bundle trong Intent (Chuyển dữ liệu giữa Activity)

### 2.1 Gửi dữ liệu từ MainActivity sang LichDetailActivity

**File:** `MainActivity.kt`

```kotlin
private fun openLichDetail(lich: Lich) {
    val intent = Intent(this, LichDetailActivity::class.java)
    // Sử dụng BundleHelper để tạo bundle từ đối tượng Lich
    val bundle = BundleHelper.lichToBundle(lich)
    intent.putExtras(bundle)
    startActivity(intent)
}
```

### 2.2 Nhận dữ liệu trong LichDetailActivity

**File:** `LichDetailActivity.kt`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // ...
    
    val bundle = intent.extras
    if (bundle != null) {
        displayLichDetail(bundle)
    }
}

private fun displayLichDetail(bundle: Bundle) {
    // Sử dụng BundleHelper để lấy dữ liệu
    val hoVaTen = BundleHelper.getHoVaTen(bundle)
    val ngayGio = BundleHelper.getNgayGio(bundle)
    val noiDung = BundleHelper.getNoiDung(bundle)
    val linkAnh = BundleHelper.getLinkAnh(bundle)
    // ... hiển thị dữ liệu
}
```

---

## 3. Bundle trong BroadcastReceiver (Notification)

### 3.1 Gửi dữ liệu cho Notification

**File:** `MainActivity.kt`

```kotlin
private fun scheduleNotification(lich: Lich) {
    val notificationTime = lich.ngayGio - (30 * 60 * 1000)
    
    val intent = Intent(this, NotificationReceiver::class.java)
    val bundle = Bundle().apply {
        putString(NotificationReceiver.EXTRA_HO_VA_TEN, lich.hoVaTen)
        putString(NotificationReceiver.EXTRA_NOI_DUNG, lich.noiDung)
        putInt(NotificationReceiver.EXTRA_LICH_ID, lich.id)
    }
    intent.putExtras(bundle)
    
    val pendingIntent = PendingIntent.getBroadcast(
        this, lich.id, intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    // ... schedule alarm
}
```

### 3.2 Nhận dữ liệu trong NotificationReceiver

**File:** `NotificationReceiver.kt`

```kotlin
companion object {
    const val EXTRA_HO_VA_TEN = "extra_ho_va_ten"
    const val EXTRA_NOI_DUNG = "extra_noi_dung"
    const val EXTRA_LICH_ID = "extra_lich_id"
}

override fun onReceive(context: Context?, intent: Intent?) {
    if (context != null && intent != null) {
        val bundle = intent.extras
        val hoVaTen = bundle?.getString(EXTRA_HO_VA_TEN) ?: ""
        val noiDung = bundle?.getString(EXTRA_NOI_DUNG) ?: ""
        
        showNotification(context, hoVaTen, noiDung)
    }
}
```

---

## 4. BundleHelper - Utility Class

### 4.1 Mục đích

BundleHelper giúp code sạch hơn, dễ bảo trì hơn bằng cách:
- Tập trung hóa logic chuyển đổi Lich ↔ Bundle
- Tránh lặp code
- Giảm thiểu lỗi do nhầm lẫn key
- Dễ dàng thay đổi cấu trúc dữ liệu sau này

### 4.2 Các phương thức chính

**File:** `utils/BundleHelper.kt`

```kotlin
object BundleHelper {
    // 1. Chuyển đổi Lich object → Bundle
    fun lichToBundle(lich: Lich): Bundle {
        return Bundle().apply {
            putInt(KEY_ID, lich.id)
            putString(KEY_HO_VA_TEN, lich.hoVaTen)
            putLong(KEY_NGAY_GIO, lich.ngayGio)
            putString(KEY_NOI_DUNG, lich.noiDung)
            putString(KEY_LINK_ANH, lich.linkAnh)
        }
    }
    
    // 2. Chuyển đổi Bundle → Lich object
    fun bundleToLich(bundle: Bundle): Lich? {
        return try {
            Lich(
                id = bundle.getInt(KEY_ID, 0),
                hoVaTen = bundle.getString(KEY_HO_VA_TEN) ?: return null,
                ngayGio = bundle.getLong(KEY_NGAY_GIO, 0),
                noiDung = bundle.getString(KEY_NOI_DUNG) ?: return null,
                linkAnh = bundle.getString(KEY_LINK_ANH) ?: ""
            )
        } catch (e: Exception) {
            null
        }
    }
    
    // 3. Thêm dữ liệu Lich vào Bundle có sẵn
    fun putLichData(bundle: Bundle, lich: Lich) { ... }
    
    // 4. Lấy từng trường dữ liệu riêng lẻ
    fun getLichId(bundle: Bundle): Int = ...
    fun getHoVaTen(bundle: Bundle): String = ...
    fun getNgayGio(bundle: Bundle): Long = ...
    fun getNoiDung(bundle: Bundle): String = ...
    fun getLinkAnh(bundle: Bundle): String = ...
}
```

---

## 5. Các tính năng mới đã thêm

### 5.1 Xem chi tiết lịch hẹn

**Cách sử dụng:**
1. Trong RecyclerView, **nhấn giữ** (long press) vào một lịch hẹn
2. App sẽ mở màn hình chi tiết với đầy đủ thông tin
3. Hiển thị thêm: thời gian còn lại, format ngày giờ đẹp hơn

**Kỹ thuật:**
- Sử dụng `setOnLongClickListener` trong LichAdapter
- Truyền dữ liệu qua Bundle với BundleHelper
- Activity mới với layout đẹp hơn

### 5.2 Xóa lịch hẹn

**Cách sử dụng:**
1. **Click thường** vào lịch hẹn
2. Dialog xác nhận xóa xuất hiện
3. Chọn "Xóa" để xóa hoặc "Hủy" để giữ lại

---

## 6. So sánh các cách truyền dữ liệu

### 6.1 Cách cũ (không dùng Bundle)

```kotlin
// ❌ Không tốt - dữ liệu rải rác
intent.putExtra("id", lich.id)
intent.putExtra("name", lich.hoVaTen)
intent.putExtra("date", lich.ngayGio)
// ... nhiều dòng
```

### 6.2 Cách mới (dùng Bundle)

```kotlin
// ✅ Tốt hơn - nhóm dữ liệu lại
val bundle = Bundle().apply {
    putInt("id", lich.id)
    putString("name", lich.hoVaTen)
    // ...
}
intent.putExtras(bundle)
```

### 6.3 Cách tốt nhất (dùng BundleHelper)

```kotlin
// ✅✅ Tốt nhất - clean code
val bundle = BundleHelper.lichToBundle(lich)
intent.putExtras(bundle)
```

---

## 7. Best Practices đã áp dụng

### 7.1 Sử dụng Constants cho keys

```kotlin
companion object {
    private const val KEY_START_DATE = "start_date_millis"
    // Tránh typo, dễ refactor
}
```

### 7.2 Null safety

```kotlin
// Luôn kiểm tra null và cung cấp giá trị mặc định
val hoVaTen = bundle.getString(KEY_HO_VA_TEN) ?: ""
val ngayGio = bundle.getLong(KEY_NGAY_GIO, 0)
```

### 7.3 Try-catch khi parse dữ liệu

```kotlin
fun bundleToLich(bundle: Bundle): Lich? {
    return try {
        // ... parse data
    } catch (e: Exception) {
        null  // Trả về null nếu có lỗi
    }
}
```

---

## 8. Các loại dữ liệu Bundle hỗ trợ

Bundle hỗ trợ các kiểu dữ liệu sau:
- **Primitive types:** Int, Long, Float, Double, Boolean
- **String**
- **CharSequence**
- **Parcelable** (custom objects)
- **Serializable** (ít hiệu quả hơn Parcelable)
- **Arrays và ArrayLists** của các type trên
- **Nested Bundle** (Bundle trong Bundle)

---

## 9. Kịch bản sử dụng trong app

### Kịch bản 1: Người dùng xoay màn hình
1. User đang lọc lịch hẹn từ 01/01/2025 đến 31/01/2025
2. User xoay màn hình (portrait ↔ landscape)
3. **Kết quả:** Bộ lọc vẫn giữ nguyên nhờ `onSaveInstanceState`

### Kịch bản 2: Xem chi tiết lịch hẹn
1. User nhấn giữ lịch hẹn "Họp với Nguyễn Văn A"
2. Dữ liệu được đóng gói vào Bundle
3. LichDetailActivity mở và hiển thị chi tiết
4. User thấy: ảnh lớn hơn, thời gian còn lại, nội dung đầy đủ

### Kịch bản 3: Nhận notification
1. 30 phút trước lịch hẹn
2. AlarmManager trigger PendingIntent
3. NotificationReceiver nhận Bundle với thông tin
4. Hiển thị notification cho user

---

## 10. Debug Tips

### Kiểm tra Bundle có dữ liệu gì

```kotlin
fun printBundleContents(bundle: Bundle) {
    for (key in bundle.keySet()) {
        val value = bundle.get(key)
        Log.d("Bundle", "$key = $value (${value?.javaClass?.simpleName})")
    }
}
```

### Kiểm tra size của Bundle

```kotlin
// Bundle có giới hạn kích thước (~500KB-1MB)
// Không nên truyền ảnh bitmap qua Bundle
val size = bundle.size()
Log.d("Bundle", "Bundle has $size items")
```

---

## 11. Tổng kết

App đã sử dụng Bundle ở **4 nơi chính:**

1. ✅ **MainActivity** - Lưu/khôi phục filter state
2. ✅ **Intent Communication** - Truyền dữ liệu giữa Activity
3. ✅ **Notification** - Truyền dữ liệu cho BroadcastReceiver
4. ✅ **BundleHelper** - Utility class để code sạch hơn

Tất cả đều tuân theo **best practices** và **clean code principles**.

---

## 12. Cách test các tính năng

### Test 1: Xoay màn hình
1. Chọn "Từ" và "Đến" để lọc lịch
2. Xoay màn hình (Ctrl+F11 trong emulator)
3. Kiểm tra: Filter vẫn còn

### Test 2: Xem chi tiết
1. Nhấn **giữ** một lịch hẹn trong danh sách
2. Kiểm tra: Màn hình chi tiết hiện lên
3. Kiểm tra: Thông tin hiển thị đúng

### Test 3: Xóa lịch
1. **Click** thường vào lịch hẹn
2. Dialog xác nhận xuất hiện
3. Chọn "Xóa" → Lịch biến mất

---

**Chúc bạn code vui vẻ! 🚀**

