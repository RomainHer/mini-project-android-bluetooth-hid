package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier


import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.room.AppDatabase
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.BluetoothController
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.navigation.NavGraph
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.theme.SimpleNavComposeAppTheme

class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var bluetoothController: BluetoothController
    private lateinit var appDatabase : AppDatabase

    private fun ensureBluetoothPermission(activity: ComponentActivity) {
        val requestPermissionLauncher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()){
                isGranted: Boolean ->
            if (isGranted) {
                Log.d(MainActivity.TAG, "Bluetooth connection granted")
            } else { Log.e(MainActivity.TAG, "Bluetooth connection not granted, Bye!")
                activity.finish()
            }
        }
        requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ensureBluetoothPermission(this)
        bluetoothController = BluetoothController()
        appDatabase = AppDatabase.getDatabase(applicationContext)
        setContent {
            MainScreen(bluetoothController, appDatabase)
        }
    }

    override fun onPause() {
        super.onPause()
        bluetoothController.release()
    }
}

@Composable
private fun MainScreen(
    bluetoothController : BluetoothController,
    appDatabase : AppDatabase
) {
    SimpleNavComposeAppTheme {
        val navController = rememberNavController()
        NavGraph(navController, bluetoothController, appDatabase)
    }
}

typealias KeyModifier = Int
