package com.google.mlkit.vision.demo.kotlin.facedetector

import android.graphics.Rect
import kotlin.math.roundToInt

class FaceBoundingBoxExpander {
    companion object {
        fun expandedBoundingBox(boundingBox: Rect, imageWidth: Int, imageHeight: Int): Rect{
            var expanded = Rect()
            expanded.left = boundingBox.left - (0.5 * boundingBox.width()).roundToInt()
            expanded.top = boundingBox.top - (0.5 * boundingBox.height()).roundToInt()
            expanded.right = boundingBox.right + (0.5 * boundingBox.height()).roundToInt()
            expanded.bottom = boundingBox.bottom + (0.5 * boundingBox.height()).roundToInt()

            if (expanded.left < 0){
                expanded.left = 0
            }
            if (expanded.top < 0){
                expanded.top = 0
            }
            if (expanded.right > imageWidth){
                expanded.right = imageWidth
            }
            if (expanded.bottom > imageHeight){
                expanded.bottom = imageHeight
            }

            return expanded
        }
    }
}