package ir.mahdi.filimoapplication.feature_search.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ir.mahdi.filimoapplication.R

@Composable
fun MovieItem(
    movieTitle: String,
    description: String,
    cover: String,
    rate: String,
    duration: String
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                Modifier.fillMaxWidth(0.7f),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = movieTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                )

                Text(text = "زمان: $duration",
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp)
                )

                Text(text = "امتیاز: $rate",
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp)
                )
            }
            AsyncImage(
                modifier = Modifier
                    .width(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = cover,
                contentDescription = movieTitle,
            )
        }

        Text(
            modifier = Modifier.padding(bottom = 8.dp , start = 8.dp , end = 8.dp),
            text = description,
//            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            maxLines = 2,
            )
    }

}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview(){
    MovieItem(
        "movieTitle",
        "description description description description description description descriptiondescriptiondescriptiondescriptiondescription",
        "cover",
        "rate",
        "duration",
    )
}