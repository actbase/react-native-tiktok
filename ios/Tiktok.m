#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Tiktok, NSObject)
  RCT_EXTERN_METHOD(
                    auth: (NSArray <NSString *>*)scopes
                    callback: (RCTResponseSenderBlock)callback
                    )
@end
