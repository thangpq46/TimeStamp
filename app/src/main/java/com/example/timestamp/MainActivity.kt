package com.example.timestamp

import android.content.res.AssetManager
import android.graphics.RectF
import android.graphics.Typeface
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
fun TestTimeStamp( ){
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
    val text = "2001.04.06 Mon"
    val p = android.graphics.Paint().apply {
        textSize=60f
    }
    val bound = android.graphics.Rect()
    p.getTextBounds(text,0,text.length,bound)
    c.nativeCanvas.drawText(text,(witdh/2-bound.width()/2f),(height/2+bound.height()/2f),p)
    Canvas(modifier = Modifier.fillMaxSize(), onDraw ={
        drawImage(bit)
    } )
}