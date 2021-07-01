package com.google.mlkit.vision.demo.kotlin.facedetector

import android.content.Context
import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.demo.BitmapUtils
import com.google.mlkit.vision.demo.FrameMetadata
import com.google.mlkit.vision.demo.ml.EmotionsModel
import com.google.mlkit.vision.face.Face
import org.tensorflow.lite.Tensor
import org.tensorflow.lite.support.image.TensorImage
import java.text.NumberFormat

class FaceClassifierProcessor(private val context: Context) {
    fun getFaceClassifications(face: Face, image: InputImage): FaceWithClassifications {

        if (image.byteBuffer == null){
            return FaceWithClassifications(face, mutableListOf())
        }

        val bitmap: Bitmap =
            BitmapUtils.getBitmap(image.byteBuffer, FrameMetadata.Builder().setHeight(image.height).setWidth(image.width).setRotation(image.rotationDegrees).build())
                ?: return FaceWithClassifications(face, mutableListOf())

        val croppedBitmap = BitmapCropper.cropBitmap(bitmap, face.boundingBox)

        try {
            val model = EmotionsModel.newInstance(context)
            val tensorImage = TensorImage.fromBitmap(croppedBitmap)

            val outputs = model.process(tensorImage).probabilityAsCategoryList.apply { sortByDescending { it.score } }
                .take(3)

            val classifications: MutableList<String> = mutableListOf()
            val percentFormat: NumberFormat = NumberFormat.getPercentInstance()
            for (output in outputs){
                val score: String = percentFormat.format(output.score)
                val label: String = output.label
                classifications.add("$label ($score)")
            }
            model.close()
            return FaceWithClassifications(face, classifications)
        }
        finally{
            bitmap.recycle()
            croppedBitmap.recycle()
        }
    }
}