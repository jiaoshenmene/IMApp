//
//  AddFriendView.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit

class AddFriendViewCell: BaseTableViewCell {
    var addFriendModel: AddFriendViewModel!
    
    @IBOutlet weak var searchTextField: UITextField!
    

    @IBOutlet weak var searchIcon: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        print("awakeFromNib")
    }
    override func configUI() {
        super.configUI()
        addFriendModel = cellModel as? AddFriendViewModel
        
    }
    
    override func updateUI() {
        super.updateUI()
    }
    
    override class func height(_ data: CellModel?) -> CGFloat {
        return 60
    }
}

