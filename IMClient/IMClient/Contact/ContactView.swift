//
//  ContactView.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/19.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit

class ContactViewCell: BaseTableViewCell {
    @IBOutlet weak var portrait: UIImageView!
    
    @IBOutlet weak var name: UILabel!
    
    var contactModel: ContactCellModel!
    
    
    override func configUI() {
        super.configUI()
        contactModel = cellModel as? ContactCellModel
    }
    
    override func updateUI() {
        super.updateUI()
        name.text = contactModel.name
//        mes.text = messageModel.msg
    }
    
    override class func height(_ data: CellModel?) -> CGFloat {
        return 60
    }
    
}
