package com.egbuna.autocheck.screens

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.egbuna.autocheck.R
import com.egbuna.autocheck.data.model.CarMediaModel
import com.egbuna.autocheck.state.CarUiState
import com.egbuna.autocheck.ui.CarViewModel
import com.egbuna.autocheck.ui.components.Toolbar
import com.egbuna.autocheck.ui.components.VideoPlayer
import com.egbuna.autocheck.util.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarDetailRoute(
    id: String,
    carName: String,
    navController: NavController,
    viewModel: CarViewModel = hiltViewModel()
) {

    viewModel.getCarDetails(id)
    viewModel.getCarMedia(id)
    val state by viewModel.carDetailModel.collectAsState()
    val carMediaState by viewModel.carMedia.collectAsState()

    CarDetailScreen(carName = carName,
        carMediaState = carMediaState,
        carUiState = state,
        onBackClicked = {
            navController.navigateUp()
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarDetailScreen(
    carName: String,
    carMediaState: CarUiState,
    carUiState: CarUiState,
    onBackClicked: () -> Unit
) {

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Toolbar(
            onCartClicked = { /*TODO*/ },
            icon = R.drawable.ic_baseline_arrow_back_ios_24,
            count = 0,
            title = carName,
            onBackClicked = {
                onBackClicked()
            }
        )

        if (carMediaState is CarUiState.Success) {
            val carMediaImages = carMediaState.carMedia
            Box(modifier = Modifier.height(250.dp)) {
                HorizontalPager(count = carMediaImages?.size ?: 0,
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()) { page ->
                    val type = carMediaImages?.get(page)?.type
                    if (MediaImageTags.contains(type)) {
                        AsyncImage(
                            model = carMediaImages?.get(page)?.url,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    } else {
                        VideoPlayer(uri = Uri.parse(carMediaImages?.get(page)?.url))
                    }
                }
                MediaCounter(currentCount = pagerState.currentPage + 1,
                    mediaSize = carMediaImages?.size ?: 0,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (carUiState is CarUiState.Success) {
            val car = carUiState.carDetail
            CarDetailItem(
                carYear = car?.year ?: 0,
                carAmount = car?.marketplacePrice ?: 0,
                gradeScore = car?.gradeScore ?: 0.0,
                mileageUnit = car?.mileageUnit.orEmpty(),
                mileage = car?.mileage ?: 0,
                city = car?.city.orEmpty(),
                state = car?.state.orEmpty(),
                sellingCondition = car?.sellingCondition.orEmpty(),
                transmission = car?.transmission.orEmpty(),
                exteriorColor = car?.exteriorColor.orEmpty(),
                interiorColor = car?.interiorColor.orEmpty(),
                carTitle = carName,
                engineType = car?.engineType.orEmpty(),
                websiteUrl = car?.websiteUrl.orEmpty(),
                fuelType = car?.fuelType.orEmpty(),
                vin = car?.vin.orEmpty()
            )
        }
    }
}

@Composable
fun CarDetailItem(
    carTitle: String,
    carYear: Int,
    carAmount: Long,
    gradeScore: Double,
    mileageUnit: String,
    mileage: Long,
    city: String,
    state: String,
    transmission: String,
    interiorColor: String,
    exteriorColor: String,
    engineType: String,
    websiteUrl: String,
    fuelType: String,
    sellingCondition: String,
    vin: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                IconText(icon = painterResource(id = R.drawable.ic_baseline_miliage_24),
                    text = "$mileage $mileageUnit")
                IconText(icon = painterResource(id = R.drawable.ic_baseline_location_on_24),
                    text = "$city, $state")
            }
            IconText(icon = painterResource(id = R.drawable.ic_baseline_directions_car_24),
                text = "$sellingCondition used")
            CarInfoSection(
                carYear,
                carTitle,
                carAmount,
                transmission,
                interiorColor,
                exteriorColor,
                engineType,
                gradeScore,
                websiteUrl,
                fuelType,
                vin
            )
        }
    }
}

@Composable
private fun CarInfoSection(
    carYear: Int,
    carTitle: String,
    carAmount: Long,
    transmission: String,
    interiorColor: String,
    exteriorColor: String,
    engineType: String,
    gradeScore: Double,
    websiteUrl: String,
    fuelType: String,
    vin: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$carYear $carTitle",
                fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_star_rate_24),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(text = gradeScore.roundDown(),
                    textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = carAmount.getDecoratedStringFromNumber(stringResource(id = R.string.naira)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Description", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        SingleRowItem(title = "Engine Type", value = engineType)
        Spacer(modifier = Modifier.height(20.dp))

        SingleRowItem(title = "Interior Color", value = interiorColor,
            color = colorResource(id = R.color.blue_grey_light))
        Spacer(modifier = Modifier.height(20.dp))

        SingleRowItem(title = "Exterior Color", value = exteriorColor)
        Spacer(modifier = Modifier.height(20.dp))

        SingleRowItem(title = "VIN", value = vin,
            color = colorResource(id = R.color.blue_grey_light))
        Spacer(modifier = Modifier.height(20.dp))

        SingleRowItem(title = "Transmission", value = transmission)
        Spacer(modifier = Modifier.height(20.dp))

        SingleRowItem(title = "Fuel Type", value = fuelType,
            color = colorResource(id = R.color.blue_grey_light))
    }
}

@Composable
fun SingleRowItem(
    title: String,
    value: String,
    color: Color = colorResource(id = R.color.white)
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}

@Preview(
    showBackground = true,
    name = "single row item",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun SingleRowItem() {
    SingleRowItem(title = "Engine Type", value = "6-cylinder(V6)")
}

@Preview(
    showBackground = true,
    name = "car item",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun CarDetailItemPreview() {
    CarDetailItem(
        carTitle = "Toyota Corrola",
        carYear = 2008,
        carAmount = 3200000,
        fuelType = "Fuel",
        exteriorColor = "Blue",
        interiorColor = "Cream",
        websiteUrl = "",
        transmission = "auto",
        gradeScore = 4.0,
        mileageUnit = "miles",
        city = "Apo",
        state = "Abuja",
        sellingCondition = "Local",
        engineType = "",
        mileage = 0,
        vin = ""
    )
}

@Preview(
    showBackground = true,
    name = "car detail",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun CarInfoPreview() {
    CarInfoSection(
        carTitle = "Toyota Corrola",
        carYear = 2008,
        carAmount = 3200000,
        fuelType = "Fuel",
        exteriorColor = "Blue",
        interiorColor = "Cream",
        websiteUrl = "",
        transmission = "auto",
        gradeScore = 4.0,
        engineType = "",
        vin = ""
    )
}

@Composable
fun IconText(icon: Painter,
             text: String
) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 10.sp,
            color = Color.Gray,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}

@Preview(
    showBackground = true,
    name = "icon text",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun IconTextPreview() {
    IconText(icon = painterResource(id = R.drawable.ic_baseline_miliage_24), text = "1000km")
}

@Composable
fun MediaCounter(
    currentCount: Int,
    mediaSize: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = Color.Gray)
    ) {
        Text(text = "$currentCount of $mediaSize",
        modifier = Modifier.padding(8.dp))
    }
}

@Preview(
    showBackground = true,
    name = "media count",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun MediaCountPreview() {
    MediaCounter(currentCount = 2, mediaSize = 16)
}