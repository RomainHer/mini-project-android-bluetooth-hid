package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.BluetoothController
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.BluetoothDesk
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.BluetoothUiConnection
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.theme.SimpleNavComposeAppTheme



@Composable
fun BluetoothScreen(
    navigateToBluetooth: () -> Unit,
    navigateToHome: () -> Unit,

) {
    Surface(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DefaultButton(
                    text = "Bluetooth",
                    onClick = { navigateToBluetooth() }
                )

                DefaultButton(
                    text = "Home",
                    onClick = { navigateToHome() }
                )
            }

            Spacer(modifier = Modifier.height(1.dp).background(Color.White).padding(top = 5.dp))

            Text("Bluetooth", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val bluetoothController = BluetoothController()
                BluetoothUiConnection(bluetoothController)
                BluetoothDesk(bluetoothController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SimpleNavComposeAppTheme(useSystemUiController = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            BluetoothScreen(
                navigateToBluetooth = {},
                navigateToHome = {}
            )
        }
    }
}



