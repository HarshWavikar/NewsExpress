package com.codewithharsh.newsexpress.presentation.common

import android.content.res.Configuration
import android.speech.tts.TextToSpeech
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardVoice
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Speaker
import androidx.compose.material.icons.rounded.SpeakerNotes
import androidx.compose.material.icons.rounded.SpeakerPhone
import androidx.compose.material.icons.rounded.SurroundSound
import androidx.compose.material.icons.rounded.VoiceChat
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codewithharsh.newsexpress.R
import com.codewithharsh.newsexpress.domain.model.Article
import com.codewithharsh.newsexpress.domain.model.Source
import com.codewithharsh.newsexpress.presentation.Dimens.ArticleImageSize
import com.codewithharsh.newsexpress.presentation.Dimens.ExtraSmallPadding
import com.codewithharsh.newsexpress.presentation.Dimens.ExtraSmallPadding2
import com.codewithharsh.newsexpress.presentation.Dimens.MediumPadding2
import com.codewithharsh.newsexpress.presentation.Dimens.SmallIconSize
import com.codewithharsh.newsexpress.presentation.SpeakTextManager.TextToSpeechManager
import com.codewithharsh.newsexpress.ui.theme.NewsExpressTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {

    var newsExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val textToSpeechManager = remember { TextToSpeechManager(context) }


    Card(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { newsExpanded = !newsExpanded },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
//        modifier = modifier.clickable { onClick() }
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(
                        start = ExtraSmallPadding2,
                        top = ExtraSmallPadding2,
                        bottom = ExtraSmallPadding2
                    )
                    .size(ArticleImageSize)
                    .clip(MaterialTheme.shapes.medium),
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )


            Column(
                modifier = Modifier
                    .padding(horizontal = ExtraSmallPadding)
                    .padding(start = 10.dp, top = 6.dp)
                    .height(ArticleImageSize),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
//                color = colorResource(id = R.color.text_title),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.source.name,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
//                    color = colorResource(id = R.color.body),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),

                        )

                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        modifier = Modifier.size(SmallIconSize),
//                    colorResource(id = R.color.body)
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                    Text(
                        text = article.publishedAt.dropLast(10),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
//                    color = colorResource(id = R.color.body),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    )

                }
            }
        }
        AnimatedVisibility(
            visible = newsExpanded,
            enter = fadeIn() + expandVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {

            Column (
                Modifier.padding(top = 14.dp, start = 10.dp, end = 10.dp, bottom = 6.dp). fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = article.description
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        IconButton(onClick = { textToSpeechManager.speak(article.title) }){
                            Icon(
                                imageVector = Icons.Rounded.KeyboardVoice,
                                contentDescription = "Speaker icon"
                            )
                        }
                        Spacer(modifier = Modifier.width(MediumPadding2))
                        IconButton(onClick = {}){
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                contentDescription = "Share icon"
                            )
                        }
                    }
                    NewsDetailButton(onClick = { onClick() })
                }
            }
        }
    }
}


@Composable
fun NewsDetailButton(
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = "Show Details",
            style = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize)
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsExpressTheme {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC"),
                title = "On whether Kejriwal would attend Ram Temple inauguration, his minister says...",
                url = "https://facts.net/wp-content/uploads/2023/05/Naruto.jpeg",
                urlToImage = ""
            )
        ) {

        }
    }
}
