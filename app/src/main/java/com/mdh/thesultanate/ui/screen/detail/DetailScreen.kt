package com.mdh.thesultanate.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mdh.thesultanate.data.repository.AppRepository
import com.mdh.thesultanate.ui.theme.TheSultanateTheme

@Composable
fun DetailScreen(
    detailId: Long,
    viewModel: DetailViewModel = remember {
        DetailViewModel(AppRepository())
    }
) {
    viewModel.getSultanateByUserId(detailId)
    val sultanates by viewModel.sultanates.collectAsState()

    Column(
        modifier = Modifier
            .padding(32.dp, 32.dp, 32.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        sultanates?.let {

            AsyncImage(
                model = it.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .heightIn(min = 250.dp,max = 300.dp)
            )

            Text(
                text = it.name,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            Text(
                text = it.description,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(){
    TheSultanateTheme {
        DetailScreen(detailId = 5)
    }
}


