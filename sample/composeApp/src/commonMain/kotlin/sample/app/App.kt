package sample.app

import VideoPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import html_embeded_content.data.EmbedOptions
import html_embeded_content.domain.factory.HtmlContentViewerFactory
import html_embeded_content.domain.factory.HtmlEmbedFeature
import html_embeded_content.presentation.HtmlContentViewerView

@Composable
fun App() {
  var isSheetOpen by rememberSaveable { mutableStateOf(false) }
  Box(
    modifier = Modifier.fillMaxSize().background(Color.White),
    contentAlignment = Alignment.Center
  ) {
    Column {
      Spacer(modifier = Modifier.height(60.dp))
      Button(onClick = {
        isSheetOpen = true
      }) {
        Text("Open Bottom Sheet")
      }
//            VideoPlayer(
//                modifier = Modifier.fillMaxWidth().height(340.dp),
//                url = "https://www.youtube.com/watch?v=AD2nEllUMJw",
//                showControls = true,
//                autoPlay = false
//            )
//
//            VideoPlayer(modifier = Modifier.fillMaxWidth().height(340.dp),
//                url = "https://freetestdata.com/wp-content/uploads/2022/02/Free_Test_Data_1MB_MP4.mp4", // Automatically Detect the URL, Wether to Play YouTube Video or .mp4 e.g
//                showControls = true,
//                autoPlay = false
//            )

//            MediaPlayer(
//                modifier = Modifier.fillMaxWidth(),
//                url = "https://commondatastorage.googleapis.com/codeskulptor-demos/DDR_assets/Kangaroo_MusiQue_-_The_Neverwritten_Role_Playing_Game.mp3",
//                startTime = Color.Black,
//                endTime = Color.Black,
//                volumeIconColor = Color.Black,
//                playIconColor = Color.Blue,
//                sliderTrackColor = Color.LightGray,
//                sliderIndicatorColor = Color.Blue,
//                showControls = true,
//                headers = mapOf("String" to ""),
//                autoPlay = false
//            )


      val viewer = HtmlContentViewerFactory().createHtmlContentViewer()
      val htmlEmbedFeature = HtmlEmbedFeature(viewer)
      htmlEmbedFeature.embedHtml(
        url = "https://github.com/KhubaibKhan4/MediaPlayer-KMP/",
        options = EmbedOptions(
          customCss = "body { background-color: #f0f0f0; }",
          onPageLoaded = { println("Page loaded successfully!") },
          onError = { error -> println("Error loading page: $error") }
        )
      )
      HtmlContentViewerView(
        viewer = viewer,
        modifier = Modifier
          .fillMaxSize()
      )

    }
  }
  if (isSheetOpen) {
    Sheet {
      isSheetOpen = false
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sheet(
  onDismissRequestClick: () -> Unit,
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  ModalBottomSheet(
    onDismissRequest = { onDismissRequestClick.invoke() }, sheetState = sheetState
  ) {
    VideoPlayer(
      modifier = Modifier
        .fillMaxWidth()
        .height(380.dp)
        .background(Color.Red),
      url = "https://www.youtube.com/watch?v=RbxbfKurh6w",
      autoPlay = true,
      showControls = true,
    )
  }
}
