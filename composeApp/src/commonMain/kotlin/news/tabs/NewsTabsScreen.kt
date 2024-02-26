package news.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.pages.Pages
import com.arkivanov.decompose.extensions.compose.pages.PagesScrollAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import extension.pxToDp
import multiplatformnews.composeapp.generated.resources.Res
import news.favorite.NewsFavoriteScreen
import news.list.NewsListScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.AppColors
import view.BottomBarTab
import view.CustomBottomNavigation

@OptIn(ExperimentalDecomposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun NewsTabsScreen(
    component: NewsTabsComponent,
    modifier: Modifier = Modifier
) {

    val childPages by component.childPages.subscribeAsState()

    val tabs = childPages.items.map {
        when (it.configuration as? NewsTabsDefaultComponent.TabConfig) {

            NewsTabsDefaultComponent.TabConfig.NewsList ->
                BottomBarTab(
                    title = stringResource(Res.string.news),
                    icon = painterResource(Res.drawable.ic_news_24),
                    selectedColor = AppColors.amber600,
                    color = AppColors.blueGray400,
                )

            NewsTabsDefaultComponent.TabConfig.NewsFavorite ->
                BottomBarTab(
                    title = stringResource(Res.string.favorite),
                    icon = painterResource(Res.drawable.ic_favorite_24),
                    selectedColor = AppColors.flamingo600,
                    color = AppColors.blueGray400,
                )

            null -> throw NullPointerException("Instance can't be null")
        }
    }

    Box {
        var bottomBarSize by remember { mutableStateOf(IntSize.Zero) }

        Pages(
            modifier = modifier,
            pages = childPages,
            onPageSelected = component::selectPage,
            scrollAnimation = PagesScrollAnimation.Default,
//            pager = defaultHorizontalPager(userScrollEnabled = true),
        ) { _, page ->
            when (page) {
                is NewsTabsComponent.TabChild.NewsList -> NewsListScreen(
                    component = page.component,
                    bottomPadding = bottomBarSize.height.pxToDp()
                )

                is NewsTabsComponent.TabChild.NewsFavorite -> NewsFavoriteScreen(page.component)
            }
        }

        CustomBottomNavigation(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .onSizeChanged { bottomBarSize = it },
            tabs,
            childPages.selectedIndex
        ) { _, index ->
            if (childPages.selectedIndex != index) {
                component.selectPage(index)
            }
        }
    }
}