package com.infinitysoftware.navdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.infinitysoftware.navdrawer.ui.theme.*
import com.infinitysoftware.navdrawer.ui.theme.NavDrawerTheme
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavDrawerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    NavDrawer()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .background(MyBeautifulGreen)
                        .fillMaxWidth()
                        .height(61.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = stringResource(R.string.app_name), fontSize = 24.sp, color = Color.White)
                    }
                }
                Divider()

                NavigationDrawerItem(
                    label = { Text(text = "Home", color = MyBeautifulGreen) },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = MyBeautifulGreen) },
                    onClick = { coroutineScope.launch {
                        drawerState.close()
                    }
                        navController.navigate(Screens.Home.screen) {
                            popUpTo(id = 0)
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Profile", color = MyBeautifulGreen) },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Profile", tint = MyBeautifulGreen) },
                    onClick = { coroutineScope.launch {
                        drawerState.close()
                    }
                        navController.navigate(Screens.Profile.screen) {
                            popUpTo(id = 0)
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Settings", color = MyBeautifulGreen) },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings", tint = MyBeautifulGreen) },
                    onClick = { coroutineScope.launch {
                        drawerState.close()
                    }
                        navController.navigate(Screens.Settings.screen) {
                            popUpTo(id = 0)
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Logout", color = MyBeautifulGreen) },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Settings", tint = MyBeautifulGreen) },
                    onClick = { coroutineScope.launch {
                        drawerState.close()
                    }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                    })
            }
        },
        ){

        Scaffold(topBar = {
            val coroutineScope = rememberCoroutineScope()
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyBeautifulGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            Icons.Rounded.Menu, contentDescription = "Menu Button"
                        )
                    }
                },
            )
        }
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Home.screen)
            {
                composable(route = Screens.Home.screen)     { Home() }
                composable(route = Screens.Profile.screen)  { Profile() }
                composable(route = Screens.Settings.screen) { Settings() }
            }
        }
    }
}
