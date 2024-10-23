package trpl.nim234311049.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import trpl.nim234311049.trofimonitor.ui.theme.TrofiMonitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrofiMonitorTheme {
                var showAddClubScreen by remember { mutableStateOf(false) }

                val studentName = "Ramizah Budi C.P"
                val studentID = "234311049"

                // State untuk daftar klub sepak bola
                val clubs = remember {
                    mutableStateListOf(
                        Club("Liverpool", 19, 8, 10, 6, 3),
                        Club("Manchester United", 20, 12, 6, 3, 1),
                        Club("Chelsea", 6, 8, 5, 2, 2),
                        Club("Manchester City", 9, 7, 8, 1, 0),
                        Club("Arsenal", 13, 14, 2, 0, 0),
                        Club("Tottenham Hotspur", 2, 8, 4, 0, 0)
                    )
                }

                // Menampilkan AddClubScreen atau Layar Utama
                if (showAddClubScreen) {
                    AddClubScreen(
                        studentName = studentName,
                        studentID = studentID,
                        onAddClub = { newClub ->
                            // Tambahkan klub baru ke daftar
                            clubs.add(newClub)
                            // Kembali ke layar utama setelah menambahkan klub
                            showAddClubScreen = false
                        }
                    )
                } else {
                    // Layar utama yang menampilkan daftar klub
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            DisplayClubs(
                                clubs = clubs,
                                studentName = studentName,
                                studentID = studentID,
                                modifier = Modifier.padding(16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            // Tombol untuk menavigasi ke AddClubScreen
                            Button(
                                onClick = { showAddClubScreen = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text("Tambah Data Klub")
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Klub yang memiliki lebih dari 30 trofi:",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(start = 16.dp)
                            )

                            for (club in clubs.filter { it.totalTrophies > 30 }) {
                                Text(
                                    text = "${club.name}: ${club.totalTrophies} trofi",
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayClubs(clubs: List<Club>, studentName: String, studentID: String, modifier: Modifier = Modifier) {
    // Mengurutkan klub berdasarkan total trofi
    val sortedClubs = clubs.sortedByDescending { it.totalTrophies }

    Column(modifier = modifier.padding(16.dp)) {
        // Tampilkan nama mahasiswa dan NIM
        Text(text = "Nama: $studentName", style = MaterialTheme.typography.titleLarge)
        Text(text = "NIM: $studentID", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Tampilkan daftar klub dan total trofi
        LazyColumn {
            items(sortedClubs) { club ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    // Menampilkan gambar logo klub
                    val logoResId = when (club.name) {
                        "Liverpool" -> R.drawable.liverpool_logo
                        "Manchester United" -> R.drawable.mu_logo
                        "Chelsea" -> R.drawable.chelsea_logo
                        "Manchester City" -> R.drawable.mancity_logo
                        "Arsenal" -> R.drawable.arsenal_logo
                        "Tottenham Hotspur" -> R.drawable.tottenham_logo
                        else -> R.drawable.default_logo
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = logoResId),
                            contentDescription = "${club.name} Logo",
                            modifier = Modifier.size(64.dp).padding(end = 8.dp)
                        )

                        Column {
                            Text(
                                text = "${club.name} - Total Trofi: ${club.totalTrophies}",
                                style = MaterialTheme.typography.bodyLarge
                            )

                            // Cek apakah klub memiliki trofi internasional
                            if (club.championsLeague == 0 && club.europaLeague == 0) {
                                Text(
                                    text = "${club.name} belum pernah memenangkan trofi internasional.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayClubsPreview() {
    TrofiMonitorTheme {
        val clubs = listOf(
            Club("Liverpool", 19, 8, 10, 6, 3),
            Club("Manchester United", 20, 12, 6, 3, 1),
            Club("Chelsea", 6, 8, 5, 2, 2),
            Club("Manchester City", 9, 7, 8, 1, 0),
            Club("Arsenal", 13, 14, 2, 0, 0),
            Club("Tottenham Hotspur", 2, 8, 4, 0, 0)
        )
        DisplayClubs(clubs, "Ramizah Budi C.P", "234311049")
    }
}
