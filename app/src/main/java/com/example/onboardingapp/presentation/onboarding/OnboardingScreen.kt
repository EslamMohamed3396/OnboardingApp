package com.example.onboardingapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onboardingapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onOnboardingCompleted: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { viewModel.onboardingPages.size }

    val scope = rememberCoroutineScope()

    if (pagerState.currentPage != uiState.currentPage) {
        viewModel.setCurrentPage(pagerState.currentPage)
    }

    Box {
        TextButton(
            onClick = {
                viewModel.skipOnboarding()
                onOnboardingCompleted()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 32.dp)
        ) {
            Text(text = stringResource(id = R.string.button_skip))
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) { page ->
            OnboardingPage(
                pageData = viewModel.onboardingPages[page],
                modifier = Modifier.fillMaxWidth(),
                onSkipClick = {
                    viewModel.skipOnboarding()
                    onOnboardingCompleted()
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(viewModel.onboardingPages.size) { index ->
                    val isSelected = index == uiState.currentPage
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .width(if (isSelected) 24.dp else 8.dp)
                            .height(8.dp),
                        shape = CircleShape,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        }
                    ) {}
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (uiState.currentPage > 0 && uiState.currentPage < viewModel.onboardingPages.lastIndex) {
                    OutlinedButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(uiState.currentPage - 1)
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.button_back))
                    }
                } else {
                    Spacer(modifier = Modifier.width(100.dp))
                }

                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(48.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Button(
                        onClick = {
                            if (uiState.currentPage < viewModel.onboardingPages.lastIndex) {
                                scope.launch {
                                    pagerState.animateScrollToPage(uiState.currentPage + 1)
                                }
                            } else {
                                viewModel.completeOnboarding()
                                onOnboardingCompleted()
                            }
                        }
                    ) {
                        Text(
                            text = if (uiState.currentPage < viewModel.onboardingPages.lastIndex) {
                                stringResource(id = R.string.button_next)
                            } else {
                                stringResource(id = R.string.button_get_started)
                            }
                        )
                    }
                }
            }
        }

        uiState.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    MaterialTheme {
        Surface {
            OnboardingScreen({})
        }
    }
}
