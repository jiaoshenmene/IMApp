//
//  RegisterViewModel.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Foundation



struct RegisterViewModel {
    
    func register(_ account: String, _ name: String, _ password: String) {
        APIService.post("api/account/register", ["account" : account,"name":name ,"password": password],finishedCallback: { (result) in
            print(result)
        })
    }
}
