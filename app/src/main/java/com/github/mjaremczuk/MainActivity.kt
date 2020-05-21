package com.github.mjaremczuk

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.DrawerState.Closed
import androidx.ui.material.DrawerState.Opened
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

@Composable
fun App(scaffoldState: ScaffoldState = remember { ScaffoldState() }) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                closeDrawer = { scaffoldState.drawerState = Closed }
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = "Compose Example") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = Opened }) {
                        Icon(vectorResource(R.drawable.ic_baseline_dehaze_24))
                    }
                }
            )
        },
        bodyContent = { modifier ->
            HomeScreenContent(modifier)
        }
    )
}

@Preview
@Composable
fun HomeScreenContent(modifier: Modifier) {
    ConstraintLayout(
        ConstraintSet { parent },
        modifier,
        children = {
            val context = ContextAmbient.current
            TextButton(
                padding = InnerPadding(start = 16.dp, end = 16.dp),
                text = { Text("Home button") },
                onClick = {
                    Toast.makeText(context, "Hello from compose", Toast.LENGTH_LONG).show()
                }
            )
        }
    )
}

@Composable
fun AppDrawer(
    closeDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        TextButton(
            text = { Text(text = "Drawer button") },
            onClick = { closeDrawer() }
        )
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        TextButton(
            text = { Text(text = "Drawer button 2") },
            onClick = { closeDrawer() }
        )
    }
}