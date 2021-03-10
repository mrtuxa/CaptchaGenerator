/*
 * MIT License
 *
 * Copyright (c) 2021 zFlxw
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.github.zflxw.captchagenerator

import com.github.zflxw.captchagenerator.api.CaptchaGenerator
import java.io.File
import javax.imageio.ImageIO

object ExampleUsage {
    @JvmStatic
    fun main(args: Array<String>) {
        // Input files
        val bgFile: File = File("assets/images/", "background.png")
        val noiseFile: File = File("assets/images/", "noise.png")
        val outputFile: File = File("output/captcha.png")

        // Declare the instance of the captcha generator with the background and noise layer as parameter
        val captchaGenerator: CaptchaGenerator = CaptchaGenerator(bgFile, noiseFile)

        // write the generated image to a file
        ImageIO.write(captchaGenerator.getCaptchaImage(CaptchaGenerator.generateCode()), "png", outputFile)
    }
}

