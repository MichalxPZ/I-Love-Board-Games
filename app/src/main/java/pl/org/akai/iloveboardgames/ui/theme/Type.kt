 package pl.org.akai.iloveboardgames.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.org.akai.iloveboardgames.R

 val interFontFamily = FontFamily(
     Font(R.font.interregular),
     Font(R.font.interbold, weight = FontWeight.Bold),
     Font(R.font.interextrabold, weight = FontWeight.ExtraBold),
     Font(R.font.interlight, weight = FontWeight.Light),
     Font(R.font.interthin, weight = FontWeight.Thin),
     Font(R.font.interextralight, weight = FontWeight.ExtraLight),
     Font(R.font.intermedium, weight = FontWeight.Medium),
     Font(R.font.interblack, weight = FontWeight.Black),
     Font(R.font.intersemibold, weight = FontWeight.SemiBold),
 )
// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Medium,
    ),
    labelMedium = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Light,
    ),
    labelSmall = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Thin,
        letterSpacing = 0.08.em
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        interFontFamilyize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        interFontFamilyize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)