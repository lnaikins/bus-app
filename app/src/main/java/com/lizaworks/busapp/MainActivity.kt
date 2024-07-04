package com.lizaworks.busapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lizaworks.busapp.ui.theme.BrunswickGreen
import com.lizaworks.busapp.ui.theme.BrunswickGreenLight
import com.lizaworks.busapp.ui.theme.BusAppTheme
import com.lizaworks.busapp.ui.theme.MintCream
import com.lizaworks.busapp.ui.theme.Zomp
import kotlin.math.cos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OnboardingScreen()
                    Content()

                }
            }
        }
    }

    @Preview(showBackground = true, device = Devices.PIXEL_4)
    @Composable
    private fun OnboardingScreen(modifier: Modifier = Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = Color(0xFF003D2D)
                )
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "Ride with us.",
                color = Color(0xFF6E968C),
                fontWeight = FontWeight.W900,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.map_icon),
                contentDescription = "map",
                colorFilter = ColorFilter.tint(
                    color = Color(0xFF003D2D),
                    blendMode = BlendMode.Color
                )
            )

        }

    }

    @Preview(showBackground = true, device = Devices.PIXEL_4)
    @Composable
    private fun Content(modifier: Modifier = Modifier) {
        Column(
            modifier
                .background(color = Color.White)
                .fillMaxSize()
        ) {
            Greeting_TextFields(user = User())
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                AvailableDates()
                Divider(thickness = 1.dp, color = BrunswickGreenLight)
                Persons()
                Divider(thickness = 1.dp, color = BrunswickGreenLight)
                PleasurableTrip()
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF199675))
                ) {
                    Text("Find times and prices")
                }


            }
        }
    }
}


@Composable
fun Greeting_TextFields(user: User = User()) {
    var destination by remember {
        mutableStateOf("")
    }
    var busTerminal by remember {
        mutableStateOf("")
    }
//    var text: List<String> = List(2) { "$it" }
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp)
//            .background(color = MaterialTheme.colorScheme.primary )
    ) {
        TopSection(

            user = user,
            destination = destination,
            busTerminal = busTerminal,
            onDestinationChange = { destination = it },
            onBusTerminalChange = { busTerminal = it }
        )


    }
}


@Composable
private fun TopSection(
    user: User,
    destination: String,
    busTerminal: String,
    onDestinationChange: (String) -> Unit,
    onBusTerminalChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(BrunswickGreen)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(

            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "Hi ${user.name},",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
            //        Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = user.image),
                contentDescription = "profile picture",
                modifier = Modifier.size(40.dp),

                )
        }
        TextFields(destination, onDestinationChange, busTerminal, onBusTerminalChange)
    }
}


@Composable
private fun TextFields(
    destination: String,
    onDestinationChange: (String) -> Unit,
    busTerminal: String,
    onBusTerminalChange: (String) -> Unit
) {
    Box {
        Column {

            OutlinedTextField(value = destination,
                onValueChange = onDestinationChange,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text("Where are you going?", color = Color.White) }

            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = busTerminal,
                onValueChange = onBusTerminalChange,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text("Closest bus terminal", color = Color.White) })
        }
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .offset(x = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_two_arrows),
                    contentDescription = "two arrows"
                )
            }
        }
    }
}

@Composable
fun AvailableDates() {
    Column(
//        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Available dates")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf("Today", "Tomorrow", "22nd", "Other").forEach {
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp))
                        .padding(8.dp)
                ) {
                    Text(text = it)
                }
            }

        }

//        Spacer(modifier = Modifier.height(8.dp))
    }


}

@Composable
fun Persons() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.people), contentDescription = "people")
        Spacer(modifier = Modifier.width(16.dp))
        Text("1 person")
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .background(color = MintCream, shape = RoundedCornerShape(4.dp))
                .height(32.dp),
            verticalAlignment = Alignment.CenterVertically,
        )
        {

            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "minus"
                )
            }

            Divider(
                Modifier
                    .width(1.dp)
                    .height(16.dp), thickness = 1.dp
            )
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "plus",
                    modifier = Modifier.size(16.dp),
                    contentScale = ContentScale.Fit
                )
            }

        }


    }
}

@Composable
fun PleasurableTrip() {
    Column {
        Text("Make your trip much more pleasurable")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.horizontalScroll(
                rememberScrollState()
            )
        ) {


            TripModel.data.forEach { model ->


                Column {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = Color(0xFFD9E0BE), shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)

                    ) {
                        Image(
                            painter = painterResource(id = model.image),
                            contentDescription = "breakfast"
                        )
                    }
                    Text(text = model.title, fontSize = 10.sp)
                    Text(
                        text = model.cost,
                        color = Color(0xFF4D9F89),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


    }
}


@Composable
fun GreetingPreview() {
    BusAppTheme {
        Greeting_TextFields()
    }
}

class User {
    val image: Int = R.drawable.ellipse
    val name = "Kojo"
}

data class TripModel(val image: Int, val title: String, val cost: String) {
    companion object {
        val data = listOf(
            TripModel(
                image = R.drawable.breakfast,
                title = "Breakfast",
                cost = "GHS 50.00"
            ),
            TripModel(
                image = R.drawable.sleeping_pod,
                title = "Sleeping pod",
                cost = "GHS 100.00"
            ),
            TripModel(
                image = R.drawable.drinks,
                title = "Drinks",
                cost = "GHS 40.00"
            ),
            TripModel(
                image = R.drawable.blanket,
                title = "Blanket",
                cost = "GHS 92.00"
            )
        )
    }
}

object Icons {
    val two_arrows: Int = R.drawable.two_arrows
}