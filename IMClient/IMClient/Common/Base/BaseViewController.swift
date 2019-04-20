//
//  BaseViewController.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/17.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit


/**
 ViewController，为View和ViewModel建立连接
 
 - 创建和持有View，ViewModel
 - 绑定View，ViewModel
 - 相应事件，更新View，ViewModel
 */

class BaseViewController: UIViewController {
    var baseViewModel: ViewModel?
    
    
    func configUI() {
        
    }
    
    func updateUI() {
    }
    
    func updateUIEmpty() {

    }

    
}


