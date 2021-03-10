package com.github.zflxw.captchagenerator.api

import java.io.File
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.util.concurrent.ThreadLocalRandom
import java.io.IOException
import java.awt.*
import java.awt.geom.Line2D
import java.lang.StringBuilder
import java.util.*
import kotlin.math.pow

class CaptchaGenerator {
    private val defaultFontNames = ArrayList<String>()
    private val TEXT_COLOR: String
    private val IMAGE_WIDTH: Int
    private val IMAGE_HEIGHT: Int
    private val TEXT_SIZE: Int

    private var backgroundFile: File
    private var noiseFile: File

    constructor(backgroundFile: File, noiseFile: File) {
        defaultFontNames.add("Verdana")
        defaultFontNames.add("Arial")
        defaultFontNames.add("Calibri")
        defaultFontNames.add("Bahnschrift")
        defaultFontNames.add("Century Gothic")

        this.backgroundFile = backgroundFile
        this.noiseFile = noiseFile

        TEXT_COLOR = "0xf598ed"

        IMAGE_WIDTH = 500
        IMAGE_HEIGHT = 250
        TEXT_SIZE = 100
    }

    constructor(backgroundFile: File, noiseFile: File, imageWidth: Int, imageHeight: Int, textSize: Int, colorCode: String) {
        defaultFontNames.add("Verdana")
        defaultFontNames.add("Arial")
        defaultFontNames.add("Calibri")
        defaultFontNames.add("Bahnschrift")
        defaultFontNames.add("Century Gothic")

        this.backgroundFile = backgroundFile
        this.noiseFile = noiseFile

        IMAGE_WIDTH = imageWidth
        IMAGE_HEIGHT = imageHeight
        TEXT_SIZE = textSize
        TEXT_COLOR = colorCode
    }

    val captchaImage: BufferedImage?
        get() = this.getCaptchaImage(generateCode())

    fun getCaptchaImage(code: String): BufferedImage? {
        return this.getCaptchaImage(code, defaultFontNames)
    }

    fun getCaptchaImage(code: String, vararg fontNames: String): BufferedImage? {
        return this.getCaptchaImage(code, listOf(*fontNames))
    }

    fun getCaptchaImage(code: String, fonts: List<String>): BufferedImage? {
        val backgroundImage: BufferedImage
        val noiseImage: BufferedImage
        try {
            backgroundImage = ImageIO.read(backgroundFile)
            noiseImage = ImageIO.read(noiseFile)

            val graphics = backgroundImage.createGraphics()
            val font = Font(fonts[ThreadLocalRandom.current().nextInt(0, fonts.size)], Font.BOLD, TEXT_SIZE)

            graphics.font = font
            graphics.color = Color.decode("0xF598F2")
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

            val chars: Array<Char> = code.toCharArray().toTypedArray()
            val x: Int = 10
            val y: Int = IMAGE_HEIGHT / 2 + TEXT_SIZE / 2

            for (i: Int in chars.indices) {
                val char: Char = chars[i]
                graphics.drawString(char.toString(), (x + font.size * i).toFloat(), (y + (-1 * Math.random() * 1.5).pow(i) * (TEXT_SIZE / 6)).toFloat())
            }

            val stroke: Stroke = BasicStroke(2.5F)

            for (i in 1..10) {
                graphics.color = Color.decode("0xffffff")
                graphics.stroke = stroke
                graphics.draw(Line2D.Double(0.0, 40.0 + (20.0 * i), IMAGE_WIDTH.toDouble(), 40.0 + (20.0 * i)))
            }

            graphics.drawImage(noiseImage, 0, 0, null)
            graphics.dispose()

            return backgroundImage
        } catch (exception: IOException) {
            exception.printStackTrace()
        }

        return null
    }

    companion object {
        private const val DEFAULT_CODE_LENGTH = 5

        /**
         * Generate a random ode with the given length
         * @param length the length of the code
         * @return the code
         */
        @JvmStatic
        fun generateCode(length: Int = DEFAULT_CODE_LENGTH): String {
            val builder = StringBuilder()
            for (i in 0..4) {
                builder.append(ThreadLocalRandom.current().nextInt(0, 10))
            }
            return builder.toString()
        }
    }
}