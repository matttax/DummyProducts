package com.matttax.dummyproducts.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.presentation.model.CategoryUiModel

@Composable
fun CategoryCard(
    category: CategoryUiModel,
    onClick: (Boolean) -> Unit
) {
    var selected by rememberSaveable {
        mutableStateOf(category.isSelected)
    }
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                selected = !selected
                onClick(selected)
            }.padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            if (selected)
                MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = category.name,
            style = MaterialTheme.typography.displaySmall,
            color = if (selected)
                MaterialTheme.colorScheme.onTertiary
            else MaterialTheme.colorScheme.onSecondary
        )
    }
}
