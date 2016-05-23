//
//  MLShareItem.m
//  RCTMaxSocialShare
//
//  Created by Sun Jin on 5/11/16.
//  Copyright © 2016 maxleap. All rights reserved.
//

#import "MLShareItem+RCTMaxSocialShare.h"
#import "RCTConvert.h"

@implementation RCTConvert (MaxSocialShare)

RCT_ENUM_CONVERTER(MLSContentMediaType,
                   (@{@"text":@(MLSContentMediaTypeText),
                      @"image":@(MLSContentMediaTypeImage),
                      @"webpage":@(MLSContentMediaTypeWebpage),
                      @"music":@(MLSContentMediaTypeMusic),
                      @"video":@(MLSContentMediaTypeVideo)}),
                   MLSContentMediaTypeText,
                   intValue)

@end

@implementation MLShareItem (RCTMaxSocialShare)

+ (instancetype)fromDictionary:(NSDictionary *)dict {
    MLShareItem *item = [MLShareItem new];
    item.title = dict[@"title"];
    item.detail = dict[@"detail"];
    item.webpageURL = [NSURL URLWithString:dict[@"webpageURL"]];
    item.previewImageData = [NSData dataWithContentsOfFile:dict[@"previewImagePath"]];
    item.attachmentURL = [NSURL URLWithString:dict[@"attachmentURL"]];
    item.mediaType = [RCTConvert MLSContentMediaType:dict[@"type"]];
    if (dict[@"latitude"] || dict[@"longitude"]) {
        double latitude = [dict[@"latitude"] doubleValue];
        double longitude = [dict[@"longitude"] doubleValue];
        [item setLocationWithLatitude:latitude longitude:longitude];
    }
    return item;
}

@end
