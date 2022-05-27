package com.example.presentation.main.registration.aboutprofile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.navigation.HomeScreens
import com.example.presentation.ui.theme.BackgroundColor
import com.example.presentation.ui.theme.SelfIntroduction
import com.example.presentation.ui.theme.notosanskr
import com.example.presentation.main.registration.common.BackButton
import com.example.presentation.main.registration.common.RegistrationButton
import com.example.presentation.main.registration.common.Title
import com.example.presentation.util.gameHashMap
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun AboutProfileScreen(
    viewModel: AboutProfileViewModel = hiltViewModel(),
    navController: NavController,
    game: String,
    tier: String,
    gameCost: Int
) {
    val (imageUri, setImageUri) = remember {
        mutableStateOf<Uri?>(null)
    }
    val (selfIntroduction, setSelfIntroduction) = remember {
        mutableStateOf("입력 해주세요")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackButton { navController.popBackStack() }
        Spacer(Modifier.height(60.dp))
        Title(stringResource(id = R.string.about_profile_title))
        Spacer(Modifier.height(60.dp))
        ProfileSection(imageUri, setImageUri)
        Spacer(Modifier.height(55.dp))
        SelfIntroductionSection(selfIntroduction, setSelfIntroduction)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        RegistrationButton(
            text = "등록",
        ) {
            viewModel.addPlayingGame(game,tier,gameCost)
            //viewModel.updateProfile(imageUri,selfIntroduction)
            navController.navigate(HomeScreens.PersonalProfileScreen.route)
        }
    }
}

@Composable
internal fun ProfileSection(
    imageUri: Uri?,
    setImageUri: (Uri?) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        setImageUri(uri)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            CoilImage(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .clip(CircleShape)
                    .clickable { launcher.launch("image/*") },
                imageModel = imageUri,
                circularReveal = CircularReveal(duration = 250),
                error = ImageBitmap.imageResource(id = R.drawable.profile_image)
            )
        }

        Spacer(modifier = Modifier.width(20.dp))
        Text(text = "프로필 사진", color = Color.White)
    }
}

@Composable
internal fun SelfIntroductionSection(
    selfIntroduction: String,
    setSelfIntroduction: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 60.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.self_introduction),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(17.dp))

        SelfIntroductionInputField(
            selfIntroduction = selfIntroduction,
            setSelfIntroduction = setSelfIntroduction
        )
    }
}

@Composable
internal fun SelfIntroductionInputField(
    selfIntroduction: String,
    setSelfIntroduction: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .width(290.dp)
                .height(160.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = notosanskr,
                fontWeight = FontWeight.Bold
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = SelfIntroduction
            ),
            value = selfIntroduction,
            onValueChange = setSelfIntroduction,
        )
    }
}
