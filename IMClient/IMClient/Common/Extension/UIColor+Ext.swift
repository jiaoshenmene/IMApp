//
//  UIColor+Ext.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit

extension UIColor {
    static func hex(hex: Int, alpha: CGFloat) -> UIColor {
        return UIColor(red: CGFloat((hex & 0xFF0000) >> 16 ) / 255.0, green: CGFloat((hex & 0xFF00) >> 8 ) / 255.0, blue: CGFloat((hex & 0xFF) ) / 255.0, alpha: alpha)
    }
}
