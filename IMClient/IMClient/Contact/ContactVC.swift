//
//  ContactVC.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/19.
//  Copyright © 2019 杜甲. All rights reserved.
//

class ContactVC: BaseTableViewController {
    
    var viewModel: ContactViewModel! {
        get {
            return super.baseViewModel as? ContactViewModel
        }
        set {
            super.baseViewModel = newValue
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        viewModel = ContactViewModel()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configUI()
        self.title = "联系人"
    }
}
