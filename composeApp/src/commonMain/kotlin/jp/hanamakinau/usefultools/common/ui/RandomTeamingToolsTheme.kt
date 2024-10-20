package jp.hanamakinau.usefultools.common.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import useful_tools.composeapp.generated.resources.NotoSansJP_Regular
import useful_tools.composeapp.generated.resources.Res

@Composable
fun RandomTeamingToolsTheme(
    content: @Composable () -> Unit,
) {
    val fontFamily = FontFamily(
        Font(
            Res.font.NotoSansJP_Regular,
            FontWeight.Normal,
            FontStyle.Normal
        ),
    )

    MaterialTheme(
        typography = MaterialTheme.typography.copy(fontFamily = fontFamily),
    ) {
        content()
    }
}

@Composable
private fun androidx.compose.material.Typography.copy(fontFamily: FontFamily): androidx.compose.material.Typography {
    return this.copy(
        h1 = this.h1.copy(fontFamily = fontFamily),
        h2 = this.h2.copy(fontFamily = fontFamily),
        h3 = this.h3.copy(fontFamily = fontFamily),
        h4 = this.h4.copy(fontFamily = fontFamily),
        h5 = this.h5.copy(fontFamily = fontFamily),
        h6 = this.h6.copy(fontFamily = fontFamily),
        subtitle1 = this.subtitle1.copy(fontFamily = fontFamily),
        subtitle2 = this.subtitle2.copy(fontFamily = fontFamily),
        body1 = this.body1.copy(fontFamily = fontFamily),
        body2 = this.body2.copy(fontFamily = fontFamily),
        button = this.button.copy(fontFamily = fontFamily),
        caption = this.caption.copy(fontFamily = fontFamily),
        overline = this.overline.copy(fontFamily = fontFamily),
    )
}
