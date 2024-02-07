package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.BluetoothController
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.KeyboardSender
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.Shortcut
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.common.DefaultButton

@Composable
fun BoardScreen(
    boardName : String,
    navigateToBluetooth : () -> Unit,
    navigateToHome : () -> Unit,
    bluetoothController: BluetoothController
) {
    val connected = bluetoothController.status as? BluetoothController.Status.Connected
    var keyboardSender : KeyboardSender? = null;
    var isConnected = false

    if(connected != null) {
        val keyboardSender = KeyboardSender(connected.btHidDevice, connected.hostDevice)
    }

    fun press(shortcut: Shortcut, releaseModifiers: Boolean = true) : Boolean {
        @SuppressLint("MissingPermission")
        if(keyboardSender == null) return false
        else {
            val result = keyboardSender.sendKeyboard(shortcut.shortcutKey, shortcut.modifiers, releaseModifiers)
            if (!result)  {
                Log.e("PressShortcut", "Error sending shortcut")
                return false
            } else {
                return true
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
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
            Spacer(modifier = Modifier
                .height(1.dp)
                .background(Color.White)
                .padding(top = 5.dp))

            Text(boardName, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(modifier = Modifier.height(10.dp))

            if(!isConnected) {
                Card {
                    Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment=Alignment.CenterVertically) {
                        Text(text = "Le bluetooth est deconnecté", color = Color.Red, fontStyle = FontStyle.Italic, modifier = Modifier.padding(end = 6.dp))
                        TextButton(
                            onClick = { navigateToBluetooth() }
                        ) {
                            Text("l'activer")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ListToucheCards(onPressCard = { shortcut, releaseModifiers -> press(shortcut, releaseModifiers) })
        }
    }
}