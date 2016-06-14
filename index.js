'use strict';

import ReactNative, { NativeModules } from 'react-native';

/**
@param item
{
	type: 'text', // valid values are: text,image,webpage,music,video
	title: '',
	detail: '',
	webpageURL: '',
	previewImagePath: '',
	attachmentURL: '',
	latitude: 13.1,
	longitude: 23.3,
	// for iPad
	rect: {x: 0, y: 0, width: 320, height: 30}
}
*/
async function share(item) {
  return await NativeModules.MaxSocialShare.share(item);
}

export default share;
