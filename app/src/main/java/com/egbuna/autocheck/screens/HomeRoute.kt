package com.egbuna.autocheck.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.egbuna.autocheck.R
import com.egbuna.autocheck.navigation.Screen
import com.egbuna.autocheck.state.CarUiState
import com.egbuna.autocheck.state.FetchCarUIState
import com.egbuna.autocheck.ui.CarViewModel
import com.egbuna.autocheck.ui.components.SearchTextField
import com.egbuna.autocheck.ui.components.Toolbar
import com.egbuna.autocheck.util.getDecoratedStringFromNumber
import com.egbuna.autocheck.util.roundDown
import kotlin.math.roundToInt

@Composable
fun HomeRoute(
    navController: NavController,
    viewModel: CarViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = 1) {
        viewModel.loadNextItems()
    }

    val state by viewModel.topCarModel.collectAsState()
    val carMainState = viewModel.state
    HomeScreen(carUiState = state, carMainState = carMainState, loadNextItems = {
        viewModel.loadNextItems()
    }, navigateToDetailScreen = {id, carName ->
        navController.navigate(Screen.Detail.route + "/$id/$carName")
    })
}

@Composable
fun HomeScreen(
    carUiState: CarUiState,
    carMainState: FetchCarUIState,
    loadNextItems: () -> Unit,
    navigateToDetailScreen:(id: String, name: String) -> Unit
) {
    Column(
    ) {
        Toolbar(
            onCartClicked = { /*TODO*/ },
            icon = R.drawable.ic_baseline_widgets_24,
            count = 0,
            title = "Explore"
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchTextField(
            text = "", onTextChanged = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyRow(
            modifier = Modifier.padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            content = {
                TopCarModels(
                    carUiState
                )
            })
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), content = {
            GetCars(carMainState, loadNextItems = {
                loadNextItems.invoke()
            }) { id, carName ->
                navigateToDetailScreen(id, carName)
            }
        })
    }
}

@Composable
fun CarItem(
    carTitle: String,
    carImage: String,
    carYear: Int,
    carAmount: Long,
    gradeScore: Double,
    mileageUnit: String,
    mileage: Long,
    city: String,
    state: String,
    id: String,
    sellingCondition: String,
    onItemClick: (id: String, carName: String) -> Unit

) {
    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onItemClick.invoke(id, carTitle)
            }
    ) {
        val (image, card, details) = createRefs()

        Card(
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(image.top, margin = 90.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(400.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
        }
        AsyncImage(
            model = carImage, contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(width = 295.dp, height = 250.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .border(color = Color.White, width = 2.dp, shape = RoundedCornerShape(20.dp))
                .background(color = Color.Transparent, shape = RoundedCornerShape(20.dp))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Column(
            modifier = Modifier
                .constrainAs(details) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CarDetailSection(
                carYear,
                carTitle,
                gradeScore,
                carAmount,
                mileage,
                mileageUnit,
                city,
                state,
                sellingCondition
            )
        }
    }
}

@Composable
private fun CarDetailSection(
    carYear: Int,
    carTitle: String,
    gradeScore: Double,
    carAmount: Long,
    mileage: Long,
    mileageUnit: String,
    city: String,
    state: String,
    sellingCondition: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 6.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$carYear $carTitle", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = gradeScore.roundDown(),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = carAmount.getDecoratedStringFromNumber(stringResource(id = R.string.naira)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color.Black,

        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_miliage_24),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$mileage $mileageUnit",
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "$city, $state",
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier.align(alignment = Alignment.CenterVertically))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "$sellingCondition used",
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier.align(alignment = Alignment.CenterVertically))
        }
    }
}

fun LazyListScope.GetCars(
    carMainState: FetchCarUIState,
    loadNextItems: () -> Unit,
    onItemClick: (String, String) -> Unit
) {
    items(carMainState.items.size) { index ->
        val car = carMainState.items[index]
        if (index >= carMainState.items.size - 1 &&
            !carMainState.endReached && carMainState.isLoading.not()) {
            loadNextItems()
        }
        CarItem(
            carTitle = car?.title.orEmpty(),
            carImage = car?.imageUrl ?: "",
            carYear = car?.year ?: 0,
            carAmount = car?.marketPlacePrice ?: 0,
            gradeScore = car?.gradeScore ?: 0.0,
            mileage = car?.mileage ?: 0,
            mileageUnit = car?.mileageUnit.orEmpty(),
            city = car?.city.orEmpty(),
            state = car?.state.orEmpty(),
            id = car?.id.orEmpty(),
            sellingCondition = car.sellingCondition.orEmpty(),
            onItemClick = onItemClick
        )
    }

    item {
        if (carMainState.isLoading) {
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator()
            }
        }
    }
}

fun LazyListScope.TopCarModels(
    carUiState: CarUiState
) {
    if (carUiState is CarUiState.Success) {
        items(carUiState.topCars ?: emptyList()) {
            TopItems(modelImage = it.imageUrl, carModel = it.name.orEmpty())
        }
    } else if (carUiState is CarUiState.Loading) {
        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun TopItems(
    modelImage: String,
    carModel: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(70.dp)
            .padding(4.dp)
            .background(color = colorResource(id = R.color.blue_grey_light), shape = CircleShape)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(modelImage)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = carModel, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(
    showBackground = true,
    name = "top car item",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun TopCarsPreview() {
    TopItems(carModel = "Toyota", modelImage = "")
}

@Preview(
    showBackground = true,
    name = "car item",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun CarItemPreview() {
    CarItem(
        carTitle = "Toyota Corrola", carImage = "",
        carYear = 2008, carAmount = 3200000, mileage = 50000,
        gradeScore = 4.0,
        mileageUnit = "miles",
        city = "Apo",
        state = "Abuja",
        id = "qw",
        sellingCondition = "local",
        onItemClick = {_, _ ->  }
    )
}

@Preview(
    showBackground = true,
    name = "car detail",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Composable
fun CarDetailPreview() {
    CarDetailSection(
        carTitle = "Toyota Corrola",
        carYear = 2008, carAmount = 3200000, mileage = 50000,
        gradeScore = 4.0,
        mileageUnit = "miles",
        city = "Apo",
        state = "Abuja",
        sellingCondition = "local"
    )
}