package com.hisu.hisumal;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;

import com.hisu.hisumal.dao.UserDAO;
import com.hisu.hisumal.database.AppDatabase;
import com.hisu.hisumal.entity.Product;
import com.hisu.hisumal.entity.Specification;
import com.hisu.hisumal.util.LocalDataManager;

import java.util.List;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "HM_Channel_ID";
    public static final String CHANNEL_NAME = "HisuMal_Notification_Channel";

    @Override
    public void onCreate() {
        super.onCreate();

//        createNotificationChannel();

        LocalDataManager.init(getApplicationContext());
        if (!LocalDataManager.getFirstTimeInstall()) {
            LocalDataManager.setFirstTimeInstall(true);
            initDatabase();
        }
    }

    private void createNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build();

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        channel.setSound(uri, attributes);

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }

    private void initDatabase() {
        UserDAO userDAO = AppDatabase.getInstance(getApplicationContext()).userDAO();

        Specification specification = new Specification("AMD Ryzen 5 5600H", "Windows 11 SL 64 Bit", "DDR4 8GB (1 x 8GB) 3200MHz; 2 slots, up to 32GB",
                "Geforce GTX 1650 4GB", "15.6 FullHD (1920x1080). 144Hz, IPS Panel",
                "512GB SSD NVMe M.2 PCIe Gen 3x4", "1x3.5mm Audio Jack, 1xHDMI, 1xRJ45 for LAN Gigabit Ethernet, 1xUSB 3.1 Type-C, 3xUSB 3.2 Gen 1 Type-A",
                "RGB Backlight", "4 Cell, 57Whr", 2.2f);

        Product product = new Product(
                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                "The MSI 15.6\" GE Series GE66 Raider Dragonshield Gaming Laptop combines both performance and aesthetics for gamers who want bit of flair. The bottom edge of the palm rest and its keys support customizable RGB lighting, which users can set to fit their style. This limited edition features the MSI Dragonshield logo and a sci-fi spaceship-themed design. Specs-wise, it's equipped with a 2.4 GHz Intel Core i9-10980HK eight-core processor, 32GB of DDR4 RAM, a 1TB NVMe PCIe M.2 SSD, and an NVIDIA GeForce RTX 2070 SUPER graphics card. These enable the system to quickly load applications, multitask efficiently, and play graphically demanding games. MSI has also paired the hardware with a 1080p Full HD 300 Hz IPS display. Connectivity options include USB Type-A and Type-C, HDMI, Mini DisplayPort, SD media cards, Gigabit LAN, Wi-Fi 6, and Bluetooth 5.1. Other integrated features includes a webcam, microphones, speakers, and a combo audio jack. The operating system installed is Windows 10 Home.",
                "ASUS", 20000000, 15, true, 4.5, 2,
                specification,
                List.of(
                        "R.drawable.laptop_1", "R.drawable.laptop_1_slide_1", "R.drawable.laptop_1_slide_2",
                        "R.drawable.laptop_1_slide_3", "R.drawable.laptop_1_slide_4")
        );

        userDAO.addProduct(product);
    }
}