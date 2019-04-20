//
//  AddFriendVC.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Foundation

class AddFriendVC: BaseTableViewController {
    var viewModel: AddFriendViewModel! {
        get {
            return super.baseViewModel as? AddFriendViewModel
        }
        set {
            super.baseViewModel = newValue
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        viewModel = AddFriendViewModel()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configUI()
        self.title = "添加朋友"
    }
}
