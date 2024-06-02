package extension

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.ktor.KtorNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger

internal expect fun newDiskCache(): DiskCache?

fun getAsyncImageLoader(
    context: PlatformContext,
    debug: Boolean = false,
): ImageLoader {
    return ImageLoader.Builder(context)
//        .components { add(KtorNetworkFetcherFactory()) }
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, 0.25)
                .strongReferencesEnabled(true)
                .build()
        }
        .diskCache {
            newDiskCache()
        }
        // Show a short crossfade when loading images asynchronously.
        .crossfade(true)
        // Enable logging if this is a debug build.
        .apply {
            if (debug) {
                logger(DebugLogger())
            }
        }
        .build()
}