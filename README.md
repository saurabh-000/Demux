# Demux Academy
Demux Academy App is an incubator for college students with the aim of discovering and training unrealized talents for today’s most in-demand careers.
## Installation
cd to your project and Clone this repository and import into __Android Studio__
``` bash
git clone https://github.com/saurabh-000/Demux.git
```
### Run your App
You can run the app on a real device or an emulator
#### Run on a real device
##### Set up your device as follows:
1. Connect your device to your development machine with a USB cable.
2. Perform the following steps to enable USB debugging in the Developer options window: 
   1. Open the Settings app.
   2. If your device uses Android v8.0 or higher, select System. Otherwise, proceed to the next step.
   3. Scroll to the bottom and select About phone.
   4. Scroll to the bottom and tap Build number seven times.
   5. Return to the previous screen, scroll to the bottom, and tap Developer options.
   6. In the Developer options window, scroll down to find and enable USB debugging.<br/>
   
Run the app on your device as follows:
   1. In Android Studio, select your app from the run/debug configurations drop-down menu in the toolbar.
   2. In the toolbar, select the device that you want to run your app on from the target device drop-down menu.
   3. Click __Run__<br/>
   Android Studio installs your app on your connected device and starts it. 
   
#### Run on a emulator
##### Run the app on an emulator as follows:
1. In Android Studio, create an Android Virtual Device (AVD) that the emulator can use to install and run your app.
2. In the toolbar, select your app from the run/debug configurations drop-down menu.
3. From the target device drop-down menu, select the AVD that you want to run your app on.
4. Click __Run__ <br/>
   Android Studio installs the app on the AVD and starts the emulator.
## Configuration
### Ketstores:
Create `app/keystore.gradle` with the following info:
```bash
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...' 
```
And place both keystores under `app/keystores/` directory:
* `playstore.keystore`
* `stage.keystore`
### Build varients
You can generate a __debug APK__ or __signed APK__
#### Generating Debug APK
To generate a Debug APK Follow These Steps-<br/>
In Android Studio <br/>
click on __Build__-> __Bundle(s)/APK's__ -> __Build APK(s)__<br/>
Now locate to __/app/build/outputs/apk/debug/__ . Here you will get your debug.apk file <br/>
#### Generating Signed APK
To generate a Signed APK Follow These Steps-<br/>
1. __Build__ menu 
2. __Generating Signed APK... 
3. Fill in the keystore information (You only need to do this once manually and then let android Studio remember it)
## Screenshots
Here are some screenshots of the app<br/>
#### Welcome Screen
<img src="https://github.com/saurabh-000/Demux/blob/main/screenshots/WhatsApp%20Image%202020-11-23%20at%2018.25.53.jpeg" width="350" height="700"/><br/>
#### Home Screen
<img src="https://github.com/saurabh-000/Demux/blob/main/screenshots/WhatsApp%20Image%202020-11-23%20at%2018.26.10.jpeg" width="350" height="700"/><br/>
#### Filter Categories on clicking filter icon
<img src="https://github.com/saurabh-000/Demux/blob/main/screenshots/WhatsApp%20Image%202020-11-23%20at%2018.27.15.jpeg" width="350" height="700"/><br/>
#### College Filter
<img src="https://github.com/saurabh-000/Demux/blob/main/screenshots/WhatsApp%20Image%202020-11-23%20at%2018.27.55.jpeg" width="350" height="700"/><br/>
#### College Filter Result
<img src="https://github.com/saurabh-000/Demux/blob/main/screenshots/WhatsApp%20Image%202020-11-23%20at%2018.28.07.jpeg" width="350" height="700"/><br/>
#### Frequency Filter
<img src="https://github.com/saurabh-000/Demux/blob/main/screenshots/WhatsApp%20Image%202020-11-23%20at%2018.28.29.jpeg" width="350" height="700"/><br/>
#### Frequency Filter Result
<img src="https://github.com/saurabh-000/Demux/blob/main/screenshots/WhatsApp%20Image%202020-11-23%20at%2018.29.11.jpeg" width="350" height="700"/><br/>
