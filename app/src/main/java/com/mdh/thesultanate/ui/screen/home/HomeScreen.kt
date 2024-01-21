package com.mdh.thesultanate.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mdh.thesultanate.data.helper.ViewModelFactory
import com.mdh.thesultanate.data.repository.AppRepository
import com.mdh.thesultanate.ui.component.Search
import com.mdh.thesultanate.ui.component.SultanateListItem
import com.mdh.thesultanate.ui.theme.TheSultanateTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (Long) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(AppRepository())
    ),
) {
    val groupedSultanates by viewModel.groupedSultanates.collectAsState()
    val query by viewModel.query

    Column {
        LazyColumn {
            item {
                Search(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
            items(groupedSultanates, key = { it.id }) { data ->
                SultanateListItem(
                    name = data.name,
                    photoUrl = data.photoUrl,
                    detailId = data.id,
                    navigateToDetail = {onClick(data.id)}
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    TheSultanateTheme {
        HomeScreen({})
    }
}