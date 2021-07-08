package com.google.mlkit.vision.demo.kotlin.facedetector

import android.graphics.*
import java.lang.Exception

class BitmapCropper {
    companion object {
        fun cropBitmap(bitmap: Bitmap, rect: Rect): Bitmap {
            val expanded = FaceBoundingBoxExpander.expandedBoundingBox(rect, bitmap.width, bitmap.height)

            val x = expanded.left
            val y = expanded.top
            val width = expanded.right - expanded.left
            val height = expanded.bottom - expanded.top

//            if (x < 0) {
//                x = 0
//            }
//
//            if (y < 0) {
//                y = 0
//            }
//
//            if (x + width > bitmap.width) {
//                width = bitmap.width - x
//            }
//
//            if (y + height > bitmap.height){
//                height = bitmap.height - y
//            }
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