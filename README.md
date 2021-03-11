# Captcha Generator

[![](https://jitpack.io/v/zFlxw/CaptchaGenerator.svg)](https://jitpack.io/#zFlxw/CaptchaGenerator)

This project allows you to generate a captcha image for your project. I will probably not work actively on this repository. However, if you want to contribute to this repository, feel free to commit your code and open a pull request. 

# Add to your project
### Maven

 #### Add jitpack as repository

    <repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
    </repository>

#### Add the dependency

    <dependency>
	    <groupId>com.github.zflxw</groupId>
	    <artifactId>CaptchaGenerator</artifactId>
	    <version>1.0.1-SNAPSHOT</version>
    </dependency>

### Gradle
#### Add jitpack as repository
    repositories {
        maven { url 'https://jitpack.io'}
    }
#### Add the dependency
    dependencies {
       implementation 'com.github.zflxw:CaptchaGenerator:1.0.1-SNAPSHOT'
    }

# How does it work?
Here is a quick example snippet in Kotlin, how to work with this Library. In Java it's almost the same, but if someone wants to add it here, feel free to open a pull request.

    object ExampleUsage {
	    @JvmStatic  
	    fun main(args: Array<String>) {  
		// Declare references to the background and noise file and the output file.  
	        val bgFile: File = File("assets/images/", "background.png")  
	        val noiseFile: File = File("assets/images/", "noise.png")  
	        val outputFile: File = File("output/captcha.png")  
      
	        // Declare the instance of the captcha generator with the background and noise layer as parameter  
	        val captchaGenerator: CaptchaGenerator = CaptchaGenerator(bgFile, noiseFile)  
      
	        // write the generated image to a file  
	        ImageIO.write(captchaGenerator.getCaptchaImage(CaptchaGenerator.generateCode()), "png", outputFile)  
	     }
     }

# Example Result

![Image](https://cdn.discordapp.com/attachments/732701889283489862/819332726536011837/captcha.png)
