//
//  APIService.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Alamofire
import ReactiveSwift

class APIService {
    static let apihost = "http://localhost:8080/Gradle___iTalker___iTalker_1_0_SNAPSHOT_war/"
    
    static let sharedInstance = APIService()
    
    class func get(_ api: String, _ params: [String:Any]) {
        let url = APIService.apihost + api
        Alamofire.request(url, method: .get).validate().responseJSON { (response) in
            print(response)
        }
    }
    
    class func post(_ api: String, _ params: [String:Any],finishedCallback:@escaping(_ result:Any)->() ) {
        let url = APIService.apihost + api
 
        Alamofire.request(url, method: .post, parameters: params,encoding: JSONEncoding.default).responseJSON { (response) in
            
            finishedCallback(response)
        }
    }
}

