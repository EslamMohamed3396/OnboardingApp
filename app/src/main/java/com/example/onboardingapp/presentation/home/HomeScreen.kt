package com.example.onboardingapp.presentation.home

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import coil.compose.AsyncImage
import com.example.onboardingapp.R
import com.example.onboardingapp.presentation.onboarding.OnboardingViewModel
import kotlinx.coroutines.delay
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var showInput by remember { mutableStateOf(true) }

    val conversationMessages = remember { mutableStateListOf<ConversationMessage>() }

    val currentStepData = viewModel.aiOnboardingSteps.getOrElse(uiState.currentStep) {
        viewModel.aiOnboardingSteps.last()
    }

    val currentValue = if (uiState.currentStep == 0) name else country
    val isLastStep = uiState.currentStep >= viewModel.aiOnboardingSteps.size - 1

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(conversationMessages.size) {
        if (conversationMessages.isNotEmpty()) {
            listState.animateScrollToItem(conversationMessages.size - 1)
        }
    }

    LaunchedEffect(Unit) {
        if (conversationMessages.isEmpty()) {
            conversationMessages.add(
                ConversationMessage(
                    text = "Hi, I'm Emma, your personal AI Tutor. I'm here to help you learn English as fast as possible!",
                    isUser = false,
                )
            )
            conversationMessages.add(
                ConversationMessage(
                    text = "What is your name?",
                    isUser = false,
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1E3A8A),
                                Color(0xFF3B82F6)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 38.dp, end = 16.dp)
                        .align(Alignment.TopEnd),
                    text = "Skip",
                    color = Color.White.copy(alpha = 0.4f)
                )
                AsyncImage(
                    model = R.drawable.avatar,
                    contentDescription = "AI Tutor Avatar",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.avatar),
                    error = painterResource(R.drawable.avatar)
                )
            }

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = conversationMessages,
                    key = { it.id }
                ) { message ->
                    if (message.isUser) {
                        UserChatBubble(message = message.text)
                    } else {
                        AiChatBubble(
                            message = message.text,
                        )
                    }
                }

                if (showInput) {
                    item {
                        ConversationalInput(
                            prefix = currentStepData.prefix,
                            placeholder = currentStepData.placeholder,
                            value = currentValue,
                            onValueChange = {
                                if (uiState.currentStep == 0) {
                                    name = it
                                } else {
                                    country = it
                                }
                            },
                            onConfirm = {
                                if (currentValue.isNotBlank()) {
                                    val fullMessage = "${currentStepData.prefix} $currentValue"
                                    conversationMessages.add(
                                        ConversationMessage(
                                            text = fullMessage,
                                            isUser = true,
                                        )
                                    )
                                    showInput = false

                                    if (isLastStep) {
                                        viewModel.saveNameAndCountry(name, country)
                                    } else {
                                        if (uiState.currentStep == 0) {
                                            viewModel.saveName(name)
                                        }
                                        viewModel.setCurrentStep(uiState.currentStep + 1)

                                        scope.launch {
                                            delay(500)
                                            val nextQuestion =
                                                viewModel.aiOnboardingSteps.getOrElse(uiState.currentStep + 1) {
                                                    viewModel.aiOnboardingSteps.last()
                                                }.question
                                            conversationMessages.add(
                                                ConversationMessage(
                                                    text = nextQuestion,
                                                    isUser = false,
                                                )
                                            )
                                            showInput = true
                                        }
                                    }
                                }
                            },
                            isEnabled = currentValue.isNotBlank()
                        )
                    }
                }
            }
        }
    }
}

data class ConversationMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
)

@Composable
private fun AiChatBubble(
    message: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier.wrapContentWidth()
                .background(Color.White, RoundedCornerShape(20.dp))
        ) {

            Text(
                text = message,
                color = Color(0xFF1F2937),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                modifier = Modifier.padding(16.dp).wrapContentWidth()
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 16.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3B82F6).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = "Play sound",
                    tint = Color(0xFF3B82F6),
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AiChatBubblePreview() {
    AiChatBubble(message = "Eslam Mohamed")
}

@Composable
private fun UserChatBubble(
    message: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFF3B82F6),
            shadowElevation = 2.dp
        ) {
            Text(
                text = message,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 22.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConversationalInput(
    prefix: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    onConfirm: () -> Unit,
    isEnabled: Boolean
) {
    Column(
        modifier = Modifier.wrapContentWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF3B82F6),
                            Color(0xFF2563EB)
                        )
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = prefix,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text(
                            text = placeholder,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
                        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier
                        .wrapContentWidth()
                        .heightIn(min = 36.dp),
                    singleLine = true,
                    maxLines = 1
                )
            }
        }

        Button(
            onClick = onConfirm,
            modifier = Modifier
                .wrapContentWidth()
                .height(56.dp),
            enabled = isEnabled,
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDBEAFE),
                disabledContainerColor = Color(0xFFE5E7EB)
            )
        ) {
            Text(
                text = "Confirm",
                color = if (isEnabled) Color(0xFF1E40AF) else Color(0xFF9CA3AF),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
