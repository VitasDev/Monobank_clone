package com.example.mini_mono_clone

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini_mono_clone.ui.theme.Black20
import com.example.mini_mono_clone.ui.theme.Black95
import com.example.mini_mono_clone.ui.theme.BlackCardEnd
import com.example.mini_mono_clone.ui.theme.BlackCardStart
import com.example.mini_mono_clone.ui.theme.Gray
import com.example.mini_mono_clone.ui.theme.GrayNumber
import com.example.mini_mono_clone.ui.theme.OrangeEnd
import com.example.mini_mono_clone.ui.theme.OrangeStart
import com.example.mini_mono_clone.ui.theme.PurpleEnd
import com.example.mini_mono_clone.ui.theme.PurpleStart
import com.example.mini_mono_clone.ui.theme.White40
import com.example.mini_mono_clone.ui.theme.sfProText
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun HorizontalPagerWithIndicators() {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    fun PagerState.indicatorOffsetForPage(page: Int) =  1f - offsetForPage(page).coerceIn(-1f, 1f).absoluteValue

    val colorAnimation1 by animateColorAsState(
        targetValue = androidx.compose.ui.graphics.lerp(
            PurpleStart,
            OrangeStart,
            pagerState.indicatorOffsetForPage(1)
        ), label = "animate color"
    )

    val colorAnimation2 by animateColorAsState(
        targetValue = androidx.compose.ui.graphics.lerp(
            PurpleEnd,
            OrangeEnd,
            pagerState.indicatorOffsetForPage(1)
        ), label = "animate color"
    )

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = (-24).dp,
        modifier = Modifier
            .background(
                getHorizontalGradient(colorAnimation1, colorAnimation2)
            )
    ) { page ->
        Column {
            when (page) {
                0 -> {
                    DisplayBalance()
                }
                1 -> {
                    DisplayCard()
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                HorizontalPagerIndicator(
                    pageCount = 2,
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            val currentPage = pagerState.currentPage
                            val totalPages = 2
                            val nextPage = if (currentPage < totalPages - 1) currentPage + 1 else 0
                            coroutineScope.launch { pagerState.animateScrollToPage(nextPage) }
                        }
                )
            }
        }

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }
                .collect { currentPage ->
                    pagerState.animateScrollToPage(currentPage)
                }
        }
    }
}

@Composable
fun DisplayBalance() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(206.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .clickable {},
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Black20),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cards_update),
                    contentDescription = "My Cards"
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "My Cards",
                tint = White40
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .padding(start = 25.dp)
                .width(225.dp)
                .align(Alignment.Start)
        ) {
            Row {
                Text(
                    text = "1 180.",
                    color = Color.White,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 48.sp,
                    modifier = Modifier.alignByBaseline()
                )
                Text(
                    text = "87$",
                    color = Color.White,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 32.sp,
                    modifier = Modifier.alignByBaseline()
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Власні кошти",
                    color = Black95,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
                Text(
                    text = "1 180.87$",
                    color = Black95,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Кредитний ліміт",
                    color = Black95,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
                Text(
                    text = "1 000.00$",
                    color = Black95,
                    fontFamily = sfProText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun DisplayCard() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .fillMaxWidth()
            .height(206.dp)
            .background(getVerticalGradient(BlackCardStart, BlackCardEnd))
            .clickable {}
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, top = 15.dp)
        ) {
            Text(
                text = "monobank",
                color = Color.White,
                fontFamily = sfProText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
            Text(
                text = "Universal Bank",
                color = Gray,
                fontFamily = sfProText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 7.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .offset(y = (-3).dp)
            )
        }

        AutoResizeText(
            text = "1111  2222  3333  4444",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp, start = 20.dp, end = 18.5.dp),
            fontSizeRange = FontSizeRange(
                min = 12.sp,
                max = 25.5.sp,
            ),
            overflow = TextOverflow.Ellipsis,
            color = GrayNumber,
            maxLines = 1,
            fontFamily = sfProText,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            letterSpacing = TextUnit(0.8F, TextUnitType.Sp)
        )

        Text(
            text = "01\t/\t20",
            color = GrayNumber,
            fontFamily = sfProText,
            fontWeight = FontWeight.Normal,
            fontSize = 17.5.sp,
            letterSpacing = TextUnit(0.8F, TextUnitType.Sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 76.dp)
        )

        Image(
            modifier = Modifier
                .padding(end = 18.dp, top = 10.dp)
                .width(56.dp)
                .align(Alignment.End),
            painter = painterResource(id = R.drawable.ic_visa),
            contentDescription = "VISA"
        )
    }
}