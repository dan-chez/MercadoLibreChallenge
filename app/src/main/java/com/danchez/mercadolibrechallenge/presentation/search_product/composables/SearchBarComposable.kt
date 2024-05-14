package com.danchez.mercadolibrechallenge.presentation.search_product.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danchez.mercadolibrechallenge.R
import com.danchez.mercadolibrechallenge.presentation.search_product.SearchProductViewModel
import com.danchez.mercadolibrechallenge.presentation.theme.MercadoLibreTheme
import com.danchez.mercadolibrechallenge.presentation.theme.medium
import com.danchez.mercadolibrechallenge.presentation.theme.mercadoLibreColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComposable(
    viewModel: SearchProductViewModel = hiltViewModel(),
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = mercadoLibreColor),
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var query by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier
                        .padding(vertical = medium)
                        .fillMaxWidth()
                        .background(color = Color.White, RoundedCornerShape(22.dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    value = query,
                    onValueChange = { query = it },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search_placeholder),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            tint = Color.Gray,
                            contentDescription = stringResource(id = R.string.search_icon_content_description)
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    query = ""
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = stringResource(id = R.string.delete_icon_content_description)
                                )
                            }
                        }
                    },
                    shape = RoundedCornerShape(22.dp),
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (query.isNotEmpty()) {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                viewModel.searchProduct(query)
                            }
                        }
                    ),
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = stringResource(id = R.string.menu_icon_content_description),
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = stringResource(id = R.string.shopping_cart_icon_content_description),
                )
            }
        }
    )
}

@Preview
@Composable
private fun PreviewSearchBarComposable() {
    MercadoLibreTheme {
        SearchBarComposable()
    }
}
