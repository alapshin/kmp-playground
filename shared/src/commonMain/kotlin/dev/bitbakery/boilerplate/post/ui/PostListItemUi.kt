package dev.bitbakery.boilerplate.post.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.bitbakery.boilerplate.theme.spacing

@Composable
fun PostListItemUi(
    item: PostUiModel,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier.clickable {
                onItemClick(item.id)
            },
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                Icons.Default.AccountCircle,
                contentDescription = "User Avatar",
                modifier = Modifier.size(48.dp),
            )
            Text(
                text = item.username,
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = item.createdAt.date.toString(),
            )
        }
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = item.content,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier.align(Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Default.Forum,
                    contentDescription = "Comment",
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(item.commentCount.toString())
            }
            TextButton(
                onClick = { },
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Like")
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(item.likeCount.toString())
            }
        }
    }
}
