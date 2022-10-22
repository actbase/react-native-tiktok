import Foundation
import TikTokOpenSDK
import Photos

@objc(Tiktok)
class Tiktok: UIViewController {

  @objc
  func auth(_ scopes: [String], callback: @escaping RCTResponseSenderBlock) {
    let scopesSet = NSOrderedSet(array:scopes)
    let request = TikTokOpenSDKAuthRequest()
    request.permissions = scopesSet

    DispatchQueue.main.async {
      request.send(self, completion: { resp -> Void in
        callback([
          ["status": resp.errCode.rawValue, "code": resp.code]
        ])
      })
    }
  }
}
