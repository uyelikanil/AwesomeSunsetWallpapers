package com.anilyilmaz.awesomesunsetwallpapers.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

class HomeScreenPreviewParameterProvider: PreviewParameterProvider<List<Photo>> {
    override val values: Sequence<List<Photo>>
        get() =  sequenceOf(
            listOf(
                Photo(id = 848573, src = PexelsPhotoSrc(portrait =
                "https://images.pexels.com/photos/848573/pexels-photo-848573.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800")),

                Photo(id = 1454769, src = PexelsPhotoSrc(portrait =
                "https://images.pexels.com/photos/1454769/pexels-photo-1454769.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800")),

                Photo(id = 1142941, src = PexelsPhotoSrc(portrait =
                "https://images.pexels.com/photos/1142941/pexels-photo-1142941.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800")),

                Photo(id = 2441461, src = PexelsPhotoSrc(portrait =
                "https://images.pexels.com/photos/2441461/pexels-photo-2441461.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800")),

                Photo(id = 3618162, src = PexelsPhotoSrc(portrait =
                "https://images.pexels.com/photos/3618162/pexels-photo-3618162.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800")),

                Photo(id = 165505, src = PexelsPhotoSrc(portrait =
                "https://images.pexels.com/photos/165505/pexels-photo-165505.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800"))
            )
        )
}