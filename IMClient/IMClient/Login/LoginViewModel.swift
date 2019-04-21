//
//  LoginViewModel.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Foundation

struct LoginViewModel {
    func login(_ account: String, _ password: String) {
        APIService.post("api/account/login", ["account" : account ,"password": password],finishedCallback: {(result) in
            print(result)
            
        })
    }
}
