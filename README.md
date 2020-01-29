# react-native-open-security-settings

## Getting started

`$ npm install react-native-open-security-settings --save`

### Mostly automatic installation

`$ react-native link react-native-open-security-settings`

### Manual installation



#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.OpenSecuritySettingsPackage;` to the imports at the top of the file
  - Add `new OpenSecuritySettingsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-open-security-settings'
  	project(':react-native-open-security-settings').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-open-security-settings/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-open-security-settings')
  	```


## Usage
```javascript
import {Platform} from 'react-native'
import OpenSecuritySettings from 'react-native-open-security-settings';

if(Platform.OS == 'android'){
	OpenSecuritySettings.openSecuritySettings()
}else{
	Linking.openURL('app-settings:')
}

//Additional Method: isDeviceSecure
let isSecure = await OpenSecuritySettings.isDeviceSecure();


```
