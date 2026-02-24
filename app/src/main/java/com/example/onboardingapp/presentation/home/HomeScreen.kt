package com.example.onboardingapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

    val conversationMessages = remember { mutableStateListOf<ConversationMessage>() }

    val currentStepData = viewModel.aiOnboardingSteps.getOrElse(uiState.currentStep) {
        viewModel.aiOnboardingSteps.last()
    }

    val currentValue = if (uiState.currentStep == 0) name else country
    val isLastStep = uiState.currentStep >= viewModel.aiOnboardingSteps.size - 1

    val listState = rememberLazyListState()

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
                    showAvatar = false
                )
            )
            conversationMessages.add(
                ConversationMessage(
                    text = "What is your name?",
                    isUser = false,
                    showAvatar = true
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
                    .weight(0.35f)
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
                TextButton(
                    onClick = {  },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Skip",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                AsyncImage(
                    model = R.drawable.ai_avatar,
                    contentDescription = "AI Tutor Avatar",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ai_avatar),
                    error = painterResource(R.drawable.ai_avatar)
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
                            showAvatar = message.showAvatar
                        )
                    }
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f)
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = currentStepData.prefix,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            value = currentValue,
                            onValueChange = {
                                if (uiState.currentStep == 0) {
                                    name = it
                                } else {
                                    country = it
                                }
                            },
                            placeholder = {
                                Text(
                                    text = currentStepData.placeholder,
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
                                .weight(1f)
                                .heightIn(min = 36.dp),
                            singleLine = true,
                            maxLines = 1
                        )
                    }
                }

                val scope = rememberCoroutineScope()
                Button(
                    onClick = {
                        if (currentValue.isNotBlank()) {
                            // Add user's answer to conversation
                            conversationMessages.add(
                                ConversationMessage(
                                    text = currentValue,
                                    isUser = true,
                                    showAvatar = false
                                )
                            )

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
                                            showAvatar = true
                                        )
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = currentValue.isNotBlank(),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDBEAFE),
                        disabledContainerColor = Color(0xFFE5E7EB)
                    )
                ) {
                    Text(
                        text = if (isLastStep) "Confirm" else "Next",
                        color = if (currentValue.isNotBlank()) Color(0xFF1E40AF) else Color(
                            0xFF9CA3AF
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

data class ConversationMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val showAvatar: Boolean
)

@Composable
private fun AiChatBubble(
    message: String,
    showAvatar: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        if (showAvatar) {
            // Small AI avatar icon
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3B82F6)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🤖",
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        } else {
            Spacer(modifier = Modifier.width(40.dp))
        }

        Surface(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Text(
                text = message,
                color = Color(0xFF1F2937),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
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

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
