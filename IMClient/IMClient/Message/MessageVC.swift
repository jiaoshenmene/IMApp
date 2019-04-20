//
//  MessageVC.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/17.
//  Copyright © 2019 杜甲. All rights reserved.
//

class MessageVC: BaseTableViewController {
    
    var viewModel: MessageViewModel! {
        get {
            return super.baseViewModel as? MessageViewModel
        }
        set {
            super.baseViewModel = newValue
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        viewModel = MessageViewModel()
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configUI()
        self.title = "消息"
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
    }
    
    
}
