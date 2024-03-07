package ama.test.block1

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF


fun Bitmap.toCircular(): Bitmap {
    val squareBitmapWidth = this.width.coerceAtMost(this.height)
    val dstBitmap = Bitmap.createBitmap(
        squareBitmapWidth,
        squareBitmapWidth,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(dstBitmap)
    val paint = Paint()
    paint.isAntiAlias = true
    val rect = Rect(0, 0, squareBitmapWidth, squareBitmapWidth)
    val rectF = RectF(rect)
    canvas.drawOval(rectF, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    val left = ((squareBitmapWidth - this.width) / 2).toFloat()
    val top = ((squareBitmapWidth - this.height) / 2).toFloat()
    canvas.drawBitmap(this, left, top, paint)
    this.recycle()
    return dstBitmap
}

