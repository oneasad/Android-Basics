package com.oneasad.emailloginjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oneasad.emailloginjetpackcompose.myviewmodels.SignInViewModel
import com.oneasad.emailloginjetpackcompose.screens.LoginScreen
import com.oneasad.emailloginjetpackcompose.screens.ProfileEnum
import com.oneasad.emailloginjetpackcompose.screens.ProfileScreen
import com.oneasad.emailloginjetpackcompose.ui.theme.EmailLoginJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmailLoginJetpackComposeTheme {
                SignInApp()
            }
        }
    }
}

@Composable
fun SignInApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val context = LocalContext.current
    val signInViewModel = SignInViewModel()

    NavHost(navController = navController, startDestination = ProfileEnum.home.name) {

        composable(route = ProfileEnum.home.name) {
            LoginScreen(
                onGoogleSignInClick = { signInViewModel.handleGoogleSignIn(context,navController) },
                onEmailSignInClick = { userEmail, userPassword, isSignUpBool -> signInViewModel.handleEmailAuth(
                    email = userEmail,
                    password = userPassword,
                    context = context,
                    navController = navController,
                    isSignUp = isSignUpBool,
                ) },
                modifier = modifier
            )
        }

        composable(route = ProfileEnum.profile.name) {
            ProfileScreen(
                googleSignInViewModel = signInViewModel,
                onGoogleSignOutClick = { signInViewModel.signOut(navController) },
                modifier = modifier
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmailLoginJetpackComposeTheme {
        SignInApp()
    }
}