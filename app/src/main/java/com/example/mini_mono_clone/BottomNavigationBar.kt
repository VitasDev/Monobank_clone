package com.example.mini_mono_clone

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini_mono_clone.data.BottomNavigation
import com.example.mini_mono_clone.ui.theme.GreyBottomBar
import com.example.mini_mono_clone.ui.theme.LightGray
import com.example.mini_mono_clone.ui.theme.RedBottomBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

val itemsBottomNavigation = listOf(
    BottomNavigation(
        title = "Картка",
        icon = R.drawable.ic_bottom_card
    ),

    BottomNavigation(
        title = "Кредити",
        icon = R.drawable.ic_bottom_credit
    ),

    BottomNavigation(
        title = "Накопичення",
        icon = R.drawable.ic_bottom_savings
    ),

    BottomNavigation(
        title = "Кешбек",
        icon = R.drawable.ic_bottom_cashback
    ),

    BottomNavigation(
        title = "Ще",
        icon = R.drawable.ic_bottom_more
    )
)


@Preview
@Composable
fun BottomNavigationBar() {
    var indexSelected by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = Modifier
            .height(74.dp)
    ) {
        Row(
            modifier = Modifier.background(LightGray)
        ) {
            itemsBottomNavigation.forEachIndexed { index, item ->
                NavigationBarItem(
                    interactionSource = NoRippleInteractionSource(),
                    colors = NavigationBarItemDefaults
                        .colors(
                            indicatorColor = LightGray
                        ),
                    selected = indexSelected == index,
                    onClick = {
                        indexSelected = index
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = if (indexSelected == index) {
                                RedBottomBar
                            } else {
                                GreyBottomBar
                            }
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            maxLines = 1,
                            fontSize = 10.5.sp,
                            color = if (indexSelected == index) {
                                RedBottomBar
                            } else {
                                GreyBottomBar
                            }
                        )
                    }
                )
            }
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}