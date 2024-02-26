package view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import multiplatformnews.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Image(
        modifier = modifier.circleButton(onClick),
        painter = painterResource(Res.drawable.ic_arrow_back_24),
        contentDescription = stringResource(Res.string.back)
    )
}