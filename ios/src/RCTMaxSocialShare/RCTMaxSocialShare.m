//
//  RCTMaxSocialShare.h
//  RCTMaxSocialShare
//

#import "RCTMaxSocialShare.h"
#import <MaxSocialShare/MaxSocialShare.h>
#import "RCTConvert.h"
#import "MLShareItem+RCTMaxSocialShare.h"

@implementation RCTMaxSocialShare

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(share:(NSDictionary *)item
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    MaxSocialContainer *container = nil;
    if (item[@"rect"]) {
        CGRect rect = [RCTConvert CGRect:item[@"rect"]];
        container = [MaxSocialContainer containerWithRect:rect inView:[UIApplication sharedApplication].keyWindow];
    }
    MLShareItem *shareItem = [MLShareItem fromDictionary:item];
    [MaxSocialShare shareItem:shareItem withContainer:container completion:^(MLSActivityType activityType, BOOL completed, NSError * _Nullable activityError) {
        if (completed) {
            resolve(@(activityType));
        } else {
            reject([@(activityError.code) stringValue],
                   activityError.localizedDescription,
                   activityError);
        }
    }];
}

@end
