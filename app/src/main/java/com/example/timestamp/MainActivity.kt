package com.example.timestamp

import android.graphics.RectF
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timestamp.ui.theme.TimeStampTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeStampTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimeStampTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun TestTimeStamp(){
    val witdh = with(LocalDensity.current){
        300.dp.toPx()
    }.toInt()
    val height = with(LocalDensity.current){
        200.dp.toPx()
    }.toInt()
    val bit = ImageBitmap(witdh, height, config = ImageBitmapConfig.Argb8888)
    val c = androidx.compose.ui.graphics.Canvas(bit)
    c.drawRect(Rect(0f,0f,witdh.toFloat(),height.toFloat()), Paint().apply {
        this.color= Color.Gray
        style= PaintingStyle.Fill
    })
    val path = android.graphics.Path()
    path.addArc(RectF(witdh/2f, 100f, witdh.toFloat(), height.toFloat()), 230f, 260f)
    c.nativeCanvas.drawTextOnPath("Phan Quang Thang",path,0f,0f,android.graphics.Paint().apply {
        textSize=40f
    })

    Canvas(modifier = Modifier.fillMaxSize(), onDraw ={
        drawImage(bit)
//        drawIntoCanvas {
//            val path = android.graphics.Path()
//            path.addArc(Rect(0f, 100f, 200f, 300f), 270f, 180f)
//            it.nativeCanvas.drawTextOnPath("Hello World Example", path, 0f, 0f, paint)
//        }
    } )
}