package trpl.nim234311049.trofimonitor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import trpl.nim234311049.trofimonitor.ui.theme.TrofiMonitorTheme

@Composable
fun AddClubScreen(studentName: String, studentID: String, onAddClub: (Club) -> Unit) {
    // State untuk input data klub
    var name by remember { mutableStateOf("") }
    var premierLeague by remember { mutableStateOf("") }
    var faCup by remember { mutableStateOf("") }
    var eflCup by remember { mutableStateOf("") }
    var championsLeague by remember { mutableStateOf("") }
    var europaLeague by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Tampilkan Nama dan NIM di atas input form
        Text(text = "Nama: $studentName", style = MaterialTheme.typography.titleLarge)
        Text(text = "NIM: $studentID", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Input untuk nama klub
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama Klub") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input untuk jumlah trofi
        TextField(
            value = premierLeague,
            onValueChange = { premierLeague = it },
            label = { Text("Liga Primer Inggris") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = faCup,
            onValueChange = { faCup = it },
            label = { Text("FA Cup") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = eflCup,
            onValueChange = { eflCup = it },
            label = { Text("EFL Cup") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = championsLeague,
            onValueChange = { championsLeague = it },
            label = { Text("Liga Champions Eropa") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = europaLeague,
            onValueChange = { europaLeague = it },
            label = { Text("Liga Eropa UEFA") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk menambahkan klub
        Button(onClick = {
            val newClub = Club(
                name = name,
                premierLeague = premierLeague.toIntOrNull() ?: 0,
                faCup = faCup.toIntOrNull() ?: 0,
                eflCup = eflCup.toIntOrNull() ?: 0,
                championsLeague = championsLeague.toIntOrNull() ?: 0,
                europaLeague = europaLeague.toIntOrNull() ?: 0
            )
            onAddClub(newClub)
        }) {
            Text("Tambah Klub")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddClubScreenPreview() {
    TrofiMonitorTheme {
        AddClubScreen(
            studentName = "Ramizah Budi C.P",
            studentID = "234311049",
            onAddClub = {}
        )
    }
}
