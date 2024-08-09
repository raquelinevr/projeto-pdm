package com.example.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacao.ui.theme.NavegacaoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Login")
                            },
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            TelaLogin(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize(),
                                onSignInClick = { navController.navigate("principal") },
                                onSignUpClick = { navController.navigate("cadastro") }
                            )
                        }
                        composable("cadastro") {
                            TelaCadastro(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize(),
                                onSignUpComplete = { navController.navigate("login") }
                            )
                        }
                        composable("principal") {
                            TelaPrincipal(
                                modifier = Modifier.padding(innerPadding),
                                onLogoffClick = { navController.navigate("login") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TelaLogin(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Login", fontSize = 32.sp, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nome de usuário") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (username.text == password.text) {
                        onSignInClick()
                    } else {
                        errorMessage = "Credenciais inválidas"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Entrar", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onSignUpClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Cadastrar", color = Color.White)
            }
            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = errorMessage, color = Color.Red)
            }
        }
    }
}

@Composable
fun TelaCadastro(
    modifier: Modifier = Modifier,
    onSignUpComplete: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Cadastro", fontSize = 32.sp, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nome do Usuário") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onSignUpComplete,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Registrar", color = Color.White)
            }
        }
    }
}

@Composable
fun TelaPrincipal(
    modifier: Modifier = Modifier,
    onLogoffClick: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Bem-vindo à Tela Principal", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))

        // Card 1
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier
                .padding(8.dp)
                .size(width = 240.dp, height = 100.dp)
        ) {
            Text(
                text = "Tênis",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card 2
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier
                .padding(8.dp)
                .size(width = 240.dp, height = 100.dp)
        ) {
            Text(
                text = "Futebol",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onLogoffClick) {
            Text(text = "Sair")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    NavegacaoTheme {
        TelaLogin(onSignInClick = {}, onSignUpClick = {})
    }
}
