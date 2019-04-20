//
//  RegisterVC.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit
import Alamofire
class RegisterVC: BaseViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        Alamofire.request(URL.init(string: "ff"))
        
    }
}


