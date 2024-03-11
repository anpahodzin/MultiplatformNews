package view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import theme.AppTheme

data class BottomBarTab(
    val title: String,
    val icon: Painter,
    val selectedColor: Color,
    val color: Color,
)

@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab, Int) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        tabs.forEachIndexed { index, tab ->
            CustomBottomBarTab(
                modifier = Modifier
                    .weight(1f),
                tab = tab,
                isSelected = selectedTab == index,
                onClick = { onTabSelected(tab, index) }
            )
        }
    }
}

@Composable
private fun CustomBottomBarTab(
    modifier: Modifier = Modifier,
    tab: BottomBarTab,
    isSelected: Boolean,
    onClick: () -> Unit,
) {

    val color by animateColorAsState(
        targetValue = if (isSelected) tab.selectedColor else tab.color,
        label = "tabColor",
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
        )
    )

    Box(
        modifier = modifier.wrapContentSize()
            .size(70.dp)
            .border(AppTheme.sizes.borderMedium, color, AppTheme.shapes.circle)
            .background(AppTheme.colors.onSecondary, AppTheme.shapes.circle)
            .clip(AppTheme.shapes.circle)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)

    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
                .blur(20.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .background(color.copy(alpha = 0.4f), AppTheme.shapes.circle)
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Image(
                modifier = Modifier
                    .padding(2.dp)
                    .size(24.dp),
                painter = tab.icon,
                contentDescription = "tab ${tab.title}",
                colorFilter = ColorFilter.tint(color),
            )
            Text(
                text = tab.title,
                color = color,
                style = AppTheme.typography.button,
            )
        }
    }
}