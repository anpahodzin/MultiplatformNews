package org.example.kmpnews.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class AppShapes internal constructor(
    val small: CornerBasedShape = RoundedCornerShape(8.dp),
    val medium: CornerBasedShape = RoundedCornerShape(12.dp),
    val large: CornerBasedShape = RoundedCornerShape(16.dp),
    val circle: Shape = CircleShape,
    val largeExtra: CornerBasedShape = RoundedCornerShape(24.dp),
) {
    //  Material
    val materialShapes
        get() = Shapes(
            small = small,
            medium = medium,
            large = large,
        )
}