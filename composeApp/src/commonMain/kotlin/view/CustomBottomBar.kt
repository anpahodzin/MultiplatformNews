package view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

data class BottomBarTab(
    val title: String,
    val icon: Painter,
    val selectedColor: Color,
    val color: Color,
)

@Composable
fun CustomBottomNavigationBar(
    modifier: Modifier,
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab, Int) -> Unit,
) {
//    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RectangleShape) //
    ) {
        CustomBottomBarTabs(
            tabs,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected
        )

        val animatedSelectedTabIndex by animateFloatAsState(
            targetValue = selectedTab.toFloat(),
            label = "animatedSelectedTabIndex",
            animationSpec = spring(
                stiffness = Spring.StiffnessMediumLow,
                dampingRatio = Spring.DampingRatioLowBouncy,
            )
        )

        val animatedColor by animateColorAsState(
            targetValue = tabs[selectedTab].selectedColor,
            label = "animatedColor",
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
            )
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
//                .clip(shape)
                .blur(30.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            val tabWidth = size.width / tabs.size
            drawCircle(
                color = animatedColor.copy(alpha = .6f),
                radius = size.height / 2f,
                center = Offset(
                    x = (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                    y = size.height / 6f
                )
            )
        }

        val borderWidth = with(LocalDensity.current) { 4.dp.toPx() }
//        val cornerPx = shape.topStart.toPx(Size.Unspecified, LocalDensity.current)

        Canvas(
            modifier = Modifier
                .fillMaxSize()
//                .clip(shape)
        ) {
            val path = Path().apply {
                addRect(size.toRect())
//                addRoundRect(
//                    RoundRect(
//                        size.toRect(),
//                        topLeft = CornerRadius(cornerPx),
//                        topRight = CornerRadius(cornerPx)
//                    )
//                )
            }
            val length = PathMeasure().apply { setPath(path, false) }.length

            val tabWidth = size.width / tabs.size
            drawPath(
                path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        animatedColor.copy(alpha = 0f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 0f),
                    ),
                    startX = tabWidth * animatedSelectedTabIndex,
                    endX = tabWidth * (animatedSelectedTabIndex + 1),
                ),
                style = Stroke(
                    width = borderWidth,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length),
                        phase = length
                    )
                )
            )
        }
    }
}

@Composable
fun CustomBottomBarTabs(
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab, Int) -> Unit,
) {
    Row(modifier = Modifier.fillMaxSize()) {
        tabs.forEachIndexed { index, tab ->
            val color by animateColorAsState(
                targetValue = if (selectedTab == index) tab.selectedColor else tab.color,
                label = "color"
            )
            val scale by animateFloatAsState(
                targetValue = if (selectedTab == index) 1f else .94f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                ),
                label = "scale"
            )
            Column(
                modifier = Modifier
                    .scale(scale)
                    .fillMaxHeight()
                    .weight(1f)
                    .pointerInput(tab) {
                        detectTapGestures {
                            onTabSelected(tab, index)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = tab.icon,
                    contentDescription = "tab ${tab.title}",
                    tint = color,
                )
                Text(
                    text = tab.title,
                    color = color,
                )
            }
        }
    }
}