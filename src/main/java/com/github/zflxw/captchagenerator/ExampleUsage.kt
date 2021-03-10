package com.github.zflxw.captchagenerator

import com.github.zflxw.captchagenerator.api.CaptchaGenerator
import java.io.File
import javax.imageio.ImageIO

object ExampleUsage {
    @JvmStatic
    fun main(args: Array<String>) {
        // Input files -
        val bgFile: File = File("assets/images/", "background.png")
        val noiseFile: File = File("assets/images/", "noise.png")
        val outputFile: File = File("output/captcha.png")

        if (!bgFile.exists()) {
            bgFile.mkdirs()
            bgFile.createNewFile()
        }

        if (!noiseFile.exists()) {
            noiseFile.mkdirs()
            noiseFile.createNewFile()
        }

        if (!outputFile.exists()) {
            outputFile.mkdirs()
            outputFile.createNewFile()
        }

        val captchaGenerator: CaptchaGenerator = CaptchaGenerator(bgFile, noiseFile)

        ImageIO.write(captchaGenerator.getCaptchaImage(CaptchaGenerator.generateCode()), "png", outputFile)
    }
}

