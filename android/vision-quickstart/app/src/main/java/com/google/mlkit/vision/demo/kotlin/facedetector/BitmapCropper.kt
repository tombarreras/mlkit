package com.google.mlkit.vision.demo.kotlin.facedetector

import android.graphics.*
import java.lang.Exception

class BitmapCropper {
    companion object {
        fun cropBitmap(bitmap: Bitmap, rect: Rect): Bitmap {

            var x = rect.left
            var y = rect.top
            var width = rect.right - rect.left
            var height = rect.bottom - rect.top

            if (x < 0) {
                x = 0
            }

            if (y < 0) {
                y = 0
            }

            if (x + width > bitmap.width) {
                width = bitmap.width - x
            }

            if (y + height > bitmap.height){
                height = bitmap.height - y
            }
            try{
                val cropped: Bitmap = Bitmap.createBitmap(bitmap, x, y, width, height)
                return cropped
            }
            catch (e: Exception){
                print(e.message)
                return Bitmap.createBitmap(bitmap)
            }
        }
    }
}